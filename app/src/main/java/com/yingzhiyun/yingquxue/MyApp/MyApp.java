package com.yingzhiyun.yingquxue.MyApp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.util.LongSparseArray;

import androidx.multidex.MultiDex;

import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMGroupEventListener;
import com.tencent.imsdk.TIMGroupTipsElem;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMLogListener;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMRefreshListener;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.imsdk.session.SessionWrapper;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import com.tencent.rtmp.TXLiveBase;
import com.tencent.smtt.sdk.QbSdk;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;

import com.yingzhiyun.yingquxue.service.KeepLifeService;

import org.scilab.forge.jlatexmath.core.AjLatexMath;

import java.util.List;

import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatformConfig;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;


import static me.nereo.multi_image_selector.MultiImageSelectorFragment.TAG;


/**
 * Created by Administrator on 2018/12/21.
 */

public class MyApp extends Application {
    public static  MyApp sMyApp;
    public static int tenentappid=1301807923;
    public static String version="";
    private String registrationId;
    String tag="---------------------";
    public static String yueurl="http://yzy.ruiyunqu.com:8088/getdata.ashx?md5=";
    private IWXAPI iwxapi;
    public LongSparseArray<String> mApkPaths=new LongSparseArray<>();
    // 如何获取License? 请参考官网指引 https://cloud.tencent.com/document/product/454/34750
    String licenceUrl = "http://license.vod2.myqcloud.com/license/v1/95769c07431331cd68b2701f5a47d6d8/TXLiveSDK.licence";
    String licenseKey = "8f688fe4e507356bebcb8a166a87bb34";
    @Override
    public void onCreate() {
        super.onCreate();

        sMyApp = this;
        try {
            version=getVersionName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //init flexiblerichtextview
        AjLatexMath.init(this);


        /**
         * 极光分享
         */
        JShareInterface.setDebugMode(true);
        boolean init = JMessageClient.init(this);
        Log.d("Im初始化",init+"");
        PlatformConfig platformConfig = new PlatformConfig()
                .setWechat("wx07c1fa2b28ba0dfa", "049e3c91d05e4ba7b7304829b8ce1433")
                .setQQ("1109835168", "7hd8kqYS27bUvvSE");

        JShareInterface.init(this, platformConfig);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        registrationId = JPushInterface.getRegistrationID(this);
        Log.e("测试", "run:--------->registrationId： "+ registrationId);


        QbSdk.setDownloadWithoutWifi(true);
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.e("print","加载内核是否成功:"+arg0);
            }

            @Override
            public void onCoreInitFinished() {
                Log.e("print","加载内核是否成功:");
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);


//        CrashReport.UserStrategy userStrategy = new CrashReport.UserStrategy(this);
//        userStrategy.setAppChannel("myChannel");//渠道
//        userStrategy.setAppVersion(MyApp.version);//版本号
//        userStrategy.setAppPackageName("com.yingzhiyun.yingquxue");//包名
//        CrashReport.initCrashReport(getApplicationContext(),"b23d5d20c2",false);//
//        TXLiveBase.getInstance().setLicence(this, licenceUrl, licenseKey);




    }
    public static MyApp getMyApp(){
        return sMyApp;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this) ;
    }
    private String getVersionName() throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = sMyApp.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(sMyApp.getPackageName(), 0);
        return packInfo.versionName;
    }

    //  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃   神兽保佑
//    ┃　　　┃   代码无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛
}
