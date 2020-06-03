package com.yingzhiyun.yingquxue.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;


import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AdvertisingBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.tiku.SendQuestionActivity;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.units.AppConstants;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.SpUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @desc 启动屏
 * Created by devilwwj on 16/1/23.
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断是否是第一次开启应用
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //注意要清除 FLAG_TRANSLUCENT_STATUS flag
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        boolean isFirstOpen = SpUtils.getBoolean(this, AppConstants.FIRST_OPEN);
        // 如果是第一次启动，则先进入功能引导页
        if (!isFirstOpen) {
            Intent intent = new Intent(this, WelcomeGuideActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // 如果不是第一次启动app，则正常显示启动屏
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Tiaozhuanguanggao();
            }
        }, 2000);
    }








    private void Tiaozhuanguanggao() {



        MediaType mediaType = MediaType.parse("application/json");



        RequestBody requestBody = RequestBody.create(mediaType,"");
        Request request = new Request
                .Builder()
                .post(requestBody)//Post请求的参数传递
                .url("http://192.168.0.120/yzy/app/advertisement")
                .build();

        HttpManager.getInstance().getOkhttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, IOException e) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);

                startActivity(intent);
                finish();
            }

            @Override
            public void onResponse(Call call, @NotNull Response response) throws IOException {
                String string = response.body().string();
                AdvertisingBean advertisingBean = new Gson().fromJson(string, AdvertisingBean.class);
                AdvertisingBean.ResultBean result = advertisingBean.getResult();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(advertisingBean.getStatus()==200){
                            if(result.getUrl()!=null){
                                Intent intent = new Intent(SplashActivity.this, AdvertisingActivity.class);
                                intent.putExtra("bean",result);
                                startActivity(intent);
                                finish();
                            }else {
                                Intent intent = new Intent(SplashActivity.this, MainActivity.class);

                                startActivity(intent);
                                finish();
                            }
                        }else {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);

                            startActivity(intent);
                            finish();
                        }
                    }
                });

            }
        });
    }
}
