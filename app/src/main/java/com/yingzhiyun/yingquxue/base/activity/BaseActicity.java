package com.yingzhiyun.yingquxue.base.activity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.Nullable;


import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.presenter.BasePresenter;
import com.yingzhiyun.yingquxue.base.view.BaseView;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.units.CommonUtils;
import com.yingzhiyun.yingquxue.units.DialogUtil;
import com.yingzhiyun.yingquxue.units.MD5;
import com.yingzhiyun.yingquxue.units.MMLoading;
import com.yingzhiyun.yingquxue.units.OkHttpUtils;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.StringUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Administrator on 2018/12/21.
 */

public abstract class BaseActicity<V,P extends BasePresenter<V>> extends SimpleActivity implements BaseView {

    public P mPresentser;
    public Observable observable;
    private AlertDialog alertDialog;
    private MMLoading mmLoading;
    //生成支付随机签名
    public static String genAppSign(List<OkHttpUtils.Param> params){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            OkHttpUtils.Param param = params.get(i);
            sb.append(param.getKey());
            sb.append('=');
            sb.append(param.getValue());
            sb.append('&');
        }

        sb.append("key=");
        sb.append("ce31bd8329364869b485b1276194f5de");
        Log.d("-------", "setWxPay: "+sb.toString());
        String appSign = MD5.getMessageDigest(sb.toString().getBytes());
        return appSign.toUpperCase();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void viewCreated() {
        super.viewCreated();
        mPresentser = createPresenter();
        if(mPresentser!=null){
            mPresentser.attachView((V) this);
        }
    }
    //创建子类的P层对象
    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresentser != null) {
            mPresentser.detachView();
            mPresentser=null;
        }
    }

    @Override
    public void showProgressbar() {
        Log.d("moxun", "showProgressbar: ");
        showLoading();
    }

    @Override
    public void showError(String error) {
        ToastUtil.makeLongText(this,error);
    }

    @Override
    public void hideProgressbar() {
        hideLoading();
    }

    protected void showLoading() {
        if (mmLoading == null) {
            MMLoading.Builder builder = new MMLoading.Builder(this)
                    .setMessage("加载中...")
                    .setCancelable(false)
                    .setCancelOutside(true);
            mmLoading = builder.create();
        }else {
            mmLoading.dismiss();
            MMLoading.Builder builder = new MMLoading.Builder(this)
                    .setMessage("加载中...")
                    .setCancelable(false)
                    .setCancelOutside(true);
            mmLoading = builder.create();
        }
        mmLoading.show();
    }

    protected void  CollictionList(int  id){



        MediaType mediaType = MediaType.parse("application/json");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());
            jsonObject.put("id",id);
            jsonObject.put("version", MyApp.version);
            jsonObject.put("device","Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(mediaType, jsonObject.toString());
        Request request = new Request
                .Builder()
                .post(requestBody)//Post请求的参数传递
                .url("http://192.168.0.120/yzy/app/indexListCollection")
                .build();

        HttpManager.getInstance().getOkhttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, @NotNull Response response) throws IOException {
                String string = response.body().string();
                CollectBean collectBean = new Gson().fromJson(string, CollectBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(collectBean.getStatus()==200){
                            showLoading(collectBean.getResult().getCollection_status());
                        }else if(collectBean.getStatus()==511){
                            Errorcool(collectBean.getHint());
                        }

                    }
                });
            }
        });
    }
    protected void Errorcool(String msg) {

    }
    protected void showLoading(String msg) {

    }

    protected void hideLoading() {
        if (mmLoading != null && mmLoading.isShowing()) {
            mmLoading.dismiss();
        }
    }

}
