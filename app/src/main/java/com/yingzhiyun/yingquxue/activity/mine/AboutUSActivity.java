package com.yingzhiyun.yingquxue.activity.mine;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.yingzhiyun.yingquxue.OkBean.RecordBean;
import com.yingzhiyun.yingquxue.OkBean.WxPAyBean;
import com.yingzhiyun.yingquxue.OkBean.YatijuanPayBean;
import com.yingzhiyun.yingquxue.OkBean.YitiPayinfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.presenter.PayPresenter;
import com.yingzhiyun.yingquxue.units.OkHttpUtils;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUSActivity  extends BaseActicity<PayMvp.Pay_View, PayPresenter<PayMvp.Pay_View>> implements PayMvp.Pay_View{
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    private String url="";
    private String name;
    private IWXAPI iwxapi;
    private PayReq req;
    private String partnerid;
    private String name1;

    @Override
    protected void initData() throws ParseException {
        Bundle extras = getIntent().getExtras();

        assert extras != null;
        iwxapi = WXAPIFactory.createWXAPI(MyApp.getMyApp(), null);
        url = extras.getString("url");
        iwxapi = WXAPIFactory.createWXAPI(MyApp.getMyApp(), null);
        name1 = extras.getString("name");
        //将应用的appid注册到微信
        iwxapi.registerApp("wx07c1fa2b28ba0dfa");

        toolTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mPresentser.getRecharge4Android(new Gson().toJson(new YueChongzhiJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), 1)));
                return true;
            }
        });


        initView();
    }

    @Override
    public boolean
    onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();//返回上个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);//退出H5界面
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {


        //解决webview加载的网页上的按钮点击失效  以及有些图标显示不出来的问题
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadUrl(url);


        // 将webView的横向竖向的scrollBar都禁用掉，将不再与ScrollView冲突，解决了大面积空白的问题。
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.setVerticalScrollBarEnabled(false);
        webView.setVerticalScrollbarOverlay(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setHorizontalScrollbarOverlay(false);

        //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        if(name1.equals("")){
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    String title = view.getTitle();
                    if (!TextUtils.isEmpty(title)) {
                        toolTitle.setText(title);
                    }
                }
            });
        }else {
            toolTitle.setText(name1);
        }

    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_aboutus;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    private String getVersionName() throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        return packInfo.versionName;
    }


    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void setCoursePay(CoursePayBean coursePayBean) {

    }

    @Override
    public void setWxPay(WxPAyBean wxPay) {

    }

    @Override
    public void setquerypay(WxPAyBean wxPay) {

    }

    @Override
    public void setBalance(BalanceBean balance) {

    }

    @Override
    public void setcourseSignUp(CollectionTiBean collectionTiBean) {

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
            req.sign = genAppSign(signParams);
            iwxapi.sendReq(req);
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
}
