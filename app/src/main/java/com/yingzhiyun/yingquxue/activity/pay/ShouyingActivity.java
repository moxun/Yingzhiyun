package com.yingzhiyun.yingquxue.activity.pay;

import android.os.Bundle;
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
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CoursePayBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.YueChongzhiJson;
import com.yingzhiyun.yingquxue.OkBean.QueryPayJson;
import com.yingzhiyun.yingquxue.OkBean.RecordBean;
import com.yingzhiyun.yingquxue.OkBean.WxPAyBean;
import com.yingzhiyun.yingquxue.OkBean.YatijuanPayBean;
import com.yingzhiyun.yingquxue.OkBean.YitiPayinfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.PayWayAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.PayPresenter;
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

public class ShouyingActivity extends BaseActicity<PayMvp.Pay_View, PayPresenter<PayMvp.Pay_View>> implements PayMvp.Pay_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.jine)
    RelativeLayout jine;
    @BindView(R.id.pay_way)
    TextView payWay;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.recy_payway)
    RecyclerView recyPayway;
    @BindView(R.id.numbermoney)
    TextView numbermoney;
    private ArrayList<CoursePayBean.ResultBean.PayTypeListBean> payWayBeans;
    private PayReq req;
    private IWXAPI iwxapi;
    private int payid = 0;
    private int number;
    private String partnerid;
    public static ShouyingActivity instance = null;

    //获得时间戳
    private static long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    @Override
    protected void initData() throws ParseException {
        instance = this;
        payWayBeans = new ArrayList<>();
        payWayBeans.add(new CoursePayBean.ResultBean.PayTypeListBean(3, "微信", "https://s2.ax1x.com/2019/10/24/KUihRO.png"));
//        payWayBeans.add(new CoursePayBean.ResultBean.PayTypeListBean(2, "支付宝", "https://s2.ax1x.com/2019/10/24/KUiNaq.png"));
        PayWayAdapter payWayAdapter = new PayWayAdapter(payWayBeans);
        Bundle extras = getIntent().getExtras();
        number = extras.getInt("number");
        numbermoney.setText("￥"+number);
        iwxapi = WXAPIFactory.createWXAPI(MyApp.getMyApp(), null);

        //将应用的appid注册到微信
        iwxapi.registerApp("wx07c1fa2b28ba0dfa");
        recyPayway.setLayoutManager(new LinearLayoutManager(this));
        recyPayway.setAdapter(payWayAdapter);

        payWayAdapter.setOnItemListener(new PayWayAdapter.OnItemListener() {
            @Override
            public void onClick(int pos, CoursePayBean.ResultBean.PayTypeListBean payWayBean) {
                payWayAdapter.setDefSelect(pos);
                payid = payWayBean.getId();
            }
        });
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_shouying;
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
                if (payid == 3) {
                    mPresentser.getRecharge4Android(new Gson().toJson(new YueChongzhiJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), number*100)));

                } else if (payid == 0) {
                    ToastUtil.makeShortText(this, "请选择支付方式");
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
        if (wxPay.getStatus() == 200) {
            finish();

        }
    }

    @Override
    public void setBalance(BalanceBean balance) {

    }

    @Override
    public void setcourseSignUp(CollectionTiBean collectionTiBean) {

    }

    public void Query() {
        mPresentser.getquerypay(new Gson().toJson(new QueryPayJson(partnerid)));
    }

    @Override
    public void setRecharge4Android(WxPAyBean wxPay) {
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


            List<OkHttpUtils.Param> signParams = new LinkedList<OkHttpUtils.Param>();
            signParams.add(new OkHttpUtils.Param("appid", req.appId));
            signParams.add(new OkHttpUtils.Param("noncestr", req.nonceStr));
            signParams.add(new OkHttpUtils.Param("package", req.packageValue));
            signParams.add(new OkHttpUtils.Param("partnerid", req.partnerId));
            signParams.add(new OkHttpUtils.Param("prepayid", req.prepayId));
            signParams.add(new OkHttpUtils.Param("timestamp", req.timeStamp));
            SharedPreferenceUtils.setprepayid("qianbao");
            req.sign = result.getSign();
            iwxapi.sendReq(req);
        } else if (wxPay.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, "身份过期，请重新登录");
            startActivity(PwdLoginActivity.class);
        } else {
            ToastUtil.makeShortText(this, wxPay.getHint());
        }
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

    @Override
    protected PayPresenter<PayMvp.Pay_View> createPresenter() {
        return new PayPresenter<>();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
