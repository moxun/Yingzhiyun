package com.yingzhiyun.yingquxue.wxapi;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yingzhiyun.yingquxue.OkBean.WxPAyBean;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.activity.pay.FprecastPayActivity;
import com.yingzhiyun.yingquxue.activity.pay.FuKuanActivity;
import com.yingzhiyun.yingquxue.activity.pay.ShouyingActivity;
import com.yingzhiyun.yingquxue.activity.vip.VipTopupActivity;
import com.yingzhiyun.yingquxue.units.OkHttpUtils;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.LinkedList;
import java.util.List;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, "wx07c1fa2b28ba0dfa");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            if (resp.errCode == 0) {
                if(SharedPreferenceUtils.getprepayid().equals("course")){
                    FuKuanActivity.instance.Query();
                }else if(SharedPreferenceUtils.getprepayid().equals("bet")){
                    FprecastPayActivity.instance.Query();
                }else if(SharedPreferenceUtils.getprepayid().equals("qianbao")){
                    ShouyingActivity.instance.Query();
                }else if(SharedPreferenceUtils.getprepayid().equals("vip")){
                    VipTopupActivity.instance.Query();
                }

                finish();

            } else if (resp.errCode == -2) {
                Toast.makeText(this, "您已取消付款!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "参数错误", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            finish();
        }
    }
}