package com.yingzhiyun.yingquxue.activity.pay;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.yingzhiyun.yingquxue.OkBean.CoursePayBean;
import com.yingzhiyun.yingquxue.OkBean.QueryPayJson;
import com.yingzhiyun.yingquxue.OkBean.RecordBean;
import com.yingzhiyun.yingquxue.OkBean.WxPAyBean;
import com.yingzhiyun.yingquxue.OkBean.WxPayJson;
import com.yingzhiyun.yingquxue.OkBean.YatijuanPayBean;
import com.yingzhiyun.yingquxue.OkBean.YitiPayinfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.ForecastTestActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.PayWayAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.presenter.PayPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FprecastPayActivity  extends BaseActicity<PayMvp.Pay_View, PayPresenter<PayMvp.Pay_View>> implements PayMvp.Pay_View  {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.pay_title)
    TextView payTitle;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.pay_way)
    TextView payWay;
    @BindView(R.id.recy_payway)
    RecyclerView recyPayway;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    private JSONObject jsonObject;
    private ArrayList<CoursePayBean.ResultBean.PayTypeListBean> payWayBeans;
    private PayWayAdapter payWayAdapter;
    private IWXAPI iwxapi;
    private int payid;
    private boolean isEnough;
    private int id;
    private PayReq req;
    private String partnerid;
    public static FprecastPayActivity instance = null;

    @Override
    protected void initData() throws ParseException {
        id = getIntent().getExtras().getInt("id");
        instance = this;
        jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());
            jsonObject.put("betId", id);


            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresentser.getbetPaymentPage(jsonObject.toString());
        payWayBeans = new ArrayList<>();
//        payWayBeans.add(new PayWayBean(R.mipmap.icon_payweixin, "微信"));
//        payWayBeans.add(new PayWayBean(R.mipmap.icon_payappily, "支付宝"));
        payWayAdapter = new PayWayAdapter(payWayBeans);
        iwxapi = WXAPIFactory.createWXAPI(MyApp.getMyApp(), null);

        //将应用的appid注册到微信
        iwxapi.registerApp("wx07c1fa2b28ba0dfa");
        recyPayway.setLayoutManager(new LinearLayoutManager(this));
        recyPayway.setAdapter(payWayAdapter);
        payWayAdapter.setOnItemListener(new PayWayAdapter.OnItemListener() {
            @Override
            public void onClick(int pos,CoursePayBean.ResultBean.PayTypeListBean payWayBean) {
                payWayAdapter.setDefSelect(pos);
                payid = payWayBean.getId();
            }
        });
    }

    @Override
    public int createLayoutID() {
        return R.layout.fprecastpay;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @Override
    protected PayPresenter<PayMvp.Pay_View> createPresenter() {
        return new PayPresenter<>();
    }

    @OnClick({R.id.finish, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.btn_login:
                if(payid==3){
                    Log.d("========", "onViewClicked: "+jsonObject.toString());
                    mPresentser.getyatipay(jsonObject.toString());
                }else if(payid==1){
                    if(isEnough){
                        mPresentser.getyatiyue(jsonObject.toString());
                    }else{
                        startActivity(RechargeActivity.class);
                    }

                }else if(payid==0){
                    ToastUtil.makeShortText(this,"请选择支付方式");
                }
                break;
        }
    }

    @Override
    public void setCoursePay(CoursePayBean coursePayBean) {

    }

    @Override
    public void setWxPay(WxPAyBean wxPay) {

    }

    @Override
    public void setquerypay(WxPAyBean wxPay) {
        if(wxPay.getStatus()==200){

            Intent intent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if(wxPay.getStatus()==524){
            ToastUtil.makeShortText(this,"支付失败");
        }
    }

    @Override
    public void setBalance(BalanceBean balance) {

    }

    @Override
    public void setcourseSignUp(CollectionTiBean collectionTiBean) {

    }

    @Override
    public void setRecharge4Android(WxPAyBean wxPAyBean) {

    }

    @Override
    public void setuserWalletRecord(RecordBean recordBean) {

    }

    @Override
    public void setyatipay(WxPAyBean yatijuanPayBean) {
        req = new PayReq();



        if (yatijuanPayBean.getStatus() == 200) {
            Log.d("===========", "setyatipay: "+yatijuanPayBean.getResult().getAppid());
            partnerid = yatijuanPayBean.getResult().getPrepayid();
            req.appId = yatijuanPayBean.getResult().getAppid();
            req.partnerId = yatijuanPayBean.getResult().getPartnerid();
            req.prepayId = yatijuanPayBean.getResult().getPrepayid();
            req.packageValue = yatijuanPayBean.getResult().getPackageX();
            req.nonceStr = yatijuanPayBean.getResult().getNoncestr();
            req.timeStamp = String.valueOf(yatijuanPayBean.getResult().getTimestamp());
            req.sign = yatijuanPayBean.getResult().getSign();
            iwxapi.sendReq(req);
            SharedPreferenceUtils.setprepayid("bet");
//            List<OkHttpUtils.Param> signParams = new LinkedList<OkHttpUtils.Param>();
//            signParams.add(new OkHttpUtils.Param("appid", req.appId));
//            signParams.add(new OkHttpUtils.Param("noncestr", req.nonceStr));
//            signParams.add(new OkHttpUtils.Param("package", req.packageValue));
//            signParams.add(new OkHttpUtils.Param("partnerid", req.partnerId));
//            signParams.add(new OkHttpUtils.Param("prepayid", req.prepayId));
//            signParams.add(new OkHttpUtils.Param("timestamp", req.timeStamp));
//            SharedPreferenceUtils.setprepayid("course");
//            Log.d("=======", "setWxPay: "+result.getSign());
//            Log.d("------", "setWxPay: "+genAppSign(signParams));

        } else if (yatijuanPayBean.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, "身份过期，请重新登录");
            startActivity(PwdLoginActivity.class);
        } else {
            ToastUtil.makeShortText(this, yatijuanPayBean.getHint());
        }
    }

    @Override
    public void setyatiyue(WxPAyBean yatijuanPayBean) {
        if (yatijuanPayBean.getStatus() == 200) {

                ToastUtil.makeShortText(this,"购买成功");
            Intent intent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (yatijuanPayBean.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, "身份过期，请重新登录");
            startActivity(PwdLoginActivity.class);
        } else {
            ToastUtil.makeShortText(this, yatijuanPayBean.getHint());
        }
    }

    @Override
    public void setbetPaymentPage(YitiPayinfo yitiPayinfo) {
        if (yitiPayinfo.getStatus() == 200) {
            payWayBeans.clear();
            isEnough = yitiPayinfo.getResult().isIsEnough();
            payWayBeans.addAll(yitiPayinfo.getResult().getPayTypeList());
            payWayAdapter.notifyDataSetChanged();


            payTitle.setText(yitiPayinfo.getResult().getTitle());
            price.setText(yitiPayinfo.getResult().getPrice()+"元");
        } else if (yitiPayinfo.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, "身份过期，请重新登录");
            startActivity(PwdLoginActivity.class);
        } else {
            ToastUtil.makeShortText(this, yitiPayinfo.getHint());
        }
    }
    public void Query(){
        mPresentser.getquerypay(new Gson().toJson(new QueryPayJson(partnerid)));
    }
}
