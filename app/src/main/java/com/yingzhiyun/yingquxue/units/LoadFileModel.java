package com.yingzhiyun.yingquxue.units;

import android.text.TextUtils;

import com.yingzhiyun.yingquxue.httpUnits.HttpManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 12457 on 2017/8/21.
 */

public class LoadFileModel {


    public static void loadPdfFile(String url, Callback<ResponseBody> callback) {

        LoadFileApi noServer = HttpManager.getInstance().getNoServer("http://192.168.0.120/yzy/", LoadFileApi.class);
//        LoadFileApi mLoadFileApi = retrofit.create(LoadFileApi.class);
        if (!TextUtils.isEmpty(url)) {
            Call<ResponseBody> call = noServer.loadPdfFile(url);
            call.enqueue(callback);
        }

    }
}
