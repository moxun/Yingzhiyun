package com.yingzhiyun.yingquxue.activity.login;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.OnClick;

public class XieyiActivity  extends SimpleActivity {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    private String url="http://www.yingzhiyunwenhua.cn/userAgreement";
    private String name;

    @Override
    protected void initData() throws ParseException {
        Bundle extras = getIntent().getExtras();

        assert extras != null;

        toolTitle.setText("用户协议");
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



}
