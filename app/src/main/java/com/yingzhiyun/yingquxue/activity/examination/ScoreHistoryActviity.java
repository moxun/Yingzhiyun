package com.yingzhiyun.yingquxue.activity.examination;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.ParperinfoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.ScoreListAdapter;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.units.MD5Util;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ScoreHistoryActviity extends SimpleActivity {

    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.recy_live)
    RecyclerView recyLive;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private String md5;
    private String string;
    private ParperinfoBean parperinfoBean;
    private ArrayList<ParperinfoBean.DataBean> parperinfoBeans;
    private ScoreListAdapter scoreListAdapter;
    private int page=1;
    private int id;

    @Override
    protected void initData() throws ParseException {
        id = getIntent().getExtras().getInt("id");
        parperinfoBeans = new ArrayList<>();
        scoreListAdapter = new ScoreListAdapter(parperinfoBeans,this);
        recyLive.setLayoutManager(new LinearLayoutManager(this));
        recyLive.setAdapter(scoreListAdapter);
        getList();
    }

    public void getList() {
        OkHttpClient okhttpClient = HttpManager.getInstance().getOkhttpClient();


        MediaType mediaType = MediaType.parse("application/json");
        Map<String, Object> json = new HashMap<>();
        json.put("paperId", id);
        json.put("command", 7);
        json.put("teacherId", SharedPreferenceUtils.getteachetid());
        json.put("pageSize", 20);
        json.put("pageIndex", page);
        json.put("userId", SharedPreferenceUtils.getteachetid());
        json.put("testNo", "");
        String str = new Gson().toJson(json);
        str = str += "yzy.ruiyunqu.com";
        Log.d("------------", "getList: " + str);

        try {
            md5 = MD5Util.getMD5(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Log.d("------------", "getList: " + md5);
        RequestBody requestBody = RequestBody.create(mediaType, new Gson().toJson(json));
        Request request = new Request
                .Builder()
                .post(requestBody)//Post请求的参数传递
                .url(MyApp.yueurl + md5)
                .build();

        okhttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                string = response.body().string();
                runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        parperinfoBeans.clear();
                        parperinfoBean = new Gson().fromJson(string, ParperinfoBean.class);
                        if (parperinfoBean.getResult() == 1) {
                            if (parperinfoBean.getData().size() > 0) {
                                linearModle.setVisibility(View.GONE);
                                recyLive.setVisibility(View.VISIBLE);
                                parperinfoBeans.addAll(parperinfoBean.getData());
                                scoreListAdapter.notifyDataSetChanged();
                            } else {
                                linearModle.setVisibility(View.VISIBLE);
                                recyLive.setVisibility(View.GONE);
                            }
                        }

                    }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }
        });
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_history;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }

    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }
}
