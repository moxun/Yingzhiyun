package com.yingzhiyun.yingquxue.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.yingzhiyun.yingquxue.OkBean.WxPAyBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.units.OkHttpUtils;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.LinkedList;
import java.util.List;

import cn.jiguang.share.wechat.WeChatHandleActivity;

/**
 * 微信客户端回调activity示例
 */
public class WXEntryActivity extends WeChatHandleActivity implements IWXAPIEventHandler {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Toast.makeText(this, "openid = " + baseReq.openId, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onResp(BaseResp baseResp) {

        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            if (baseResp.errCode == 0) {


            } else if (baseResp.errCode == -2) {
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