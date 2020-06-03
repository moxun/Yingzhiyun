package com.yingzhiyun.yingquxue.activity.pay;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yingzhiyun.yingquxue.Mvp.PayMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BalanceBean;
import com.yingzhiyun.yingquxue.OkBean.BaomingJson;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CoursePAyJson;
import com.yingzhiyun.yingquxue.OkBean.CoursePayBean;
import com.yingzhiyun.yingquxue.OkBean.QueryPayJson;
import com.yingzhiyun.yingquxue.OkBean.RecordBean;
import com.yingzhiyun.yingquxue.OkBean.WxPAyBean;
import com.yingzhiyun.yingquxue.OkBean.WxPayJson;
import com.yingzhiyun.yingquxue.OkBean.YatijuanPayBean;
import com.yingzhiyun.yingquxue.OkBean.YitiPayinfo;
import com.yingzhiyun.yingquxue.OkBean.localbean.BalanceJson;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.tiku.ZuTiActivity;
import com.yingzhiyun.yingquxue.activity.zhibo.AliveintroduceActivity;
import com.yingzhiyun.yingquxue.adapter.PayWayAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.PayPresenter;
import com.yingzhiyun.yingquxue.units.MD5;
import com.yingzhiyun.yingquxue.units.OkHttpUtils;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FuKuanActivity extends BaseActicity<PayMvp.Pay_View, PayPresenter<PayMvp.Pay_View>> implements PayMvp.Pay_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subject_type)
    TextView subjectType;
    @BindView(R.id.course_title)
    TextView courseTitle;
    @BindView(R.id.course_time)
    TextView courseTime;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.yu_e)
    TextView yuE;
    @BindView(R.id.fen1)
    View fen1;
    private ArrayList<CoursePayBean.ResultBean.PayTypeListBean> payWayBeans;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.recy_payway)
    RecyclerView recyPayway;
    private PayWayAdapter payWayAdapter;
    private IWXAPI iwxapi;
    private PayReq req;
    private int payid=0;
    private int id=0;
    public static FuKuanActivity instance = null;
    private String partnerid;
    private boolean isEnough;
    private String type;


    //获得时间戳
    private static long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }
    @Override
    protected void initData() throws ParseException {
        instance=this;
        id = getIntent().getExtras().getInt("id");
        type = getIntent().getExtras().getString("type");
        payWayBeans = new ArrayList<>();

        payWayAdapter = new PayWayAdapter(payWayBeans);
        iwxapi = WXAPIFactory.createWXAPI(MyApp.getMyApp(), null);

        //将应用的appid注册到微信
        iwxapi.registerApp("wx07c1fa2b28ba0dfa");
        recyPayway.setLayoutManager(new LinearLayoutManager(this));
        recyPayway.setAdapter(payWayAdapter);




        recyPayway.setLayoutManager(new LinearLayoutManager(this));
        recyPayway.setAdapter(payWayAdapter);
        payWayAdapter.setOnItemListener(new PayWayAdapter.OnItemListener() {
            @Override
            public void onClick(int pos,CoursePayBean.ResultBean.PayTypeListBean payWayBean) {
                payWayAdapter.setDefSelect(pos);
                payid = payWayBean.getId();
            }
        });

        mPresentser.getCoursePay(new Gson().toJson(new CoursePAyJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), id, "Android")));
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_fukuan;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.finish, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.btn_login:
//                startActivity(RechargeActivity.class);
                dialogPrompt("确定支付","确认购买该资源吗","确定","取消").show();




                break;
        }
    }

    @Override
    protected void dialogRightBtn() {
        super.dialogRightBtn();
        if(payid==3){
            mPresentser.getWxPay(new Gson().toJson(new WxPayJson(SharedPreferenceUtils.getUserid()+"",SharedPreferenceUtils.getToken(),id)));
        }else if(payid==1){
            if(isEnough){
                mPresentser.getcourseSignUp(new Gson().toJson(new BaomingJson(SharedPreferenceUtils.getUserid(), id,SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
            }else{
                startActivity(RechargeActivity.class);
            }

        }else if(payid==0){
            ToastUtil.makeShortText(this,"请选择支付方式");
        }
    }

    @Override
    public void setCoursePay(CoursePayBean coursePayBean) {
        if (coursePayBean.getStatus() == 200) {
            payWayBeans.clear();
            isEnough = coursePayBean.getResult().isIsEnough();
            subjectType.setText(coursePayBean.getResult().getSubject());
            courseTitle.setText(coursePayBean.getResult().getTitle());
            courseTime.setText(coursePayBean.getResult().getEffective());
            price.setText("￥" + coursePayBean.getResult().getPrice());
            yuE.setText(coursePayBean.getResult().getMsg());
            payWayBeans.addAll(coursePayBean.getResult().getPayTypeList());
            payWayAdapter.notifyDataSetChanged();
        } else if (coursePayBean.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, "身份过期，请重新登录");
            startActivity(PwdLoginActivity.class);
        } else {
            ToastUtil.makeShortText(this, coursePayBean.getHint());
        }
    }

    @Override
    public void setWxPay(WxPAyBean wxPay) {
        req = new PayReq();


        WxPAyBean.ResultBean result = wxPay.getResult();
        if (wxPay.getStatus() == 200) {
            partnerid = result.getPrepayid();
            req.appId = result.getAppid();
            req.partnerId = result.getPartnerid();
            req.prepayId = result.getPrepayid();
            req.packageValue = result.getPackageX();
            req.nonceStr = result.getNoncestr();
            req.timeStamp = String.valueOf(result.getTimestamp());
            req.sign = result.getSign();
            iwxapi.sendReq(req);

            SharedPreferenceUtils.setprepayid("course");

        } else if (wxPay.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, "身份过期，请重新登录");
            startActivity(PwdLoginActivity.class);
        } else {
            ToastUtil.makeShortText(FuKuanActivity.this, wxPay.getHint());
        }
    }

    @Override
    public void setquerypay(WxPAyBean wxPay) {
        if(wxPay.getStatus()==200){
            finish();
            if(type.equals("alive")){
                startActivity(new Intent(this, AliveintroduceActivity.class).putExtra("id",id));
            }else {
                startActivity(new Intent(this, CourseInfoActivity.class).putExtra("id",id));
            }
        }else if(wxPay.getStatus()==524){
            ToastUtil.makeShortText(this,"支付失败");
        }
    }

    @Override
    public void setBalance(BalanceBean balance) {

    }
    @Override
    protected void onResume() {
        super.onResume();
        if(id!=0){
            mPresentser.getCoursePay(new Gson().toJson(new CoursePAyJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), id, "Android")));
        }

    }
    @Override
    public void setcourseSignUp(CollectionTiBean collectionTiBean) {
        if (collectionTiBean.getStatus() == 200) {
            if(type.equals("alive")){
                startActivity(new Intent(this, AliveintroduceActivity.class).putExtra("id",id));
            }else {
                startActivity(new Intent(this, CourseInfoActivity.class).putExtra("id",id));
            }
            finish();

        } else if (collectionTiBean.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, "身份过期，请重新登录");
            startActivity(PwdLoginActivity.class);
        } else {
            ToastUtil.makeShortText(FuKuanActivity.this, collectionTiBean.getHint());
        }
    }

    @Override
    public void setRecharge4Android(WxPAyBean wxPAyBean) {

    }

    @Override
    public void setuserWalletRecord(RecordBean recordBean) {

    }

    @Override
    public void setyatipay(WxPAyBean yatijuanPayBean) {

    }

    @Override
    public void setyatiyue(WxPAyBean yatijuanPayBean) {

    }

    @Override
    public void setbetPaymentPage(YitiPayinfo yitiPayinfo) {

    }

    public void Query(){

        mPresentser.getquerypay(new Gson().toJson(new QueryPayJson(partnerid)));
    }
    @Override
    protected PayPresenter<PayMvp.Pay_View> createPresenter() {
        return new PayPresenter<>();
    }


}
