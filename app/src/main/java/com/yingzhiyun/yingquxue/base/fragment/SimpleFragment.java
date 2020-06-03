package com.yingzhiyun.yingquxue.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
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

public abstract class SimpleFragment extends Fragment {
    public Context context;
    public Activity mActivity;
    public Unbinder unbinder;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (Activity) context;
        this.context=context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(createLayoutId(), null);
        Log.d("执行的逻辑", "onCreateView: "+"执行了");

        return view;
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
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(collectBean.getStatus()==200){
                            showLoading(collectBean.getHint());
                        }else if(collectBean.getStatus()==511){
                                Errorcool(collectBean.getHint());
                        }

                    }
                });

            }
        });
    }

    protected void showLoading(String msg) {

    }
    protected void Errorcool(String msg) {

    }
    public abstract int createLayoutId();

    //onCreateView是创建的时候调用，onViewCreated是在onCreateView后被触发的事件，前后关系
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder= ButterKnife.bind(this,view);
        try {
            initData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected abstract void initData() throws JSONException;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){

        }
    }

    /**
     * TODO 懒加载方法 需要就重写
     */
    public void loading() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder!=null){
            unbinder.unbind();
        }
    }
    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(mActivity, clz));
    }

    /**
     * 携带数据页面跳转
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }

        startActivity(intent);
    }
    /**
     * 携带数据页面跳转并且有请求码
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


}
