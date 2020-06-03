package com.yingzhiyun.yingquxue.activity.examination;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.JobListBean;
import com.yingzhiyun.yingquxue.OkBean.YuejuanTeaBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.ExaminationAdapter;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.units.MD5Util;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

public class ExaminationListActivity extends SimpleActivity {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.recy_live)
    RecyclerView recyLive;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    private int page=1;
    private ArrayList<JobListBean.DataBean> examinationList;
    private ExaminationAdapter examinationAdapter;
    private String md5;
    private int id;
    private String body;

    @Override
    protected void initData() throws ParseException {
        toolTitle.setText("阅卷任务");
        id = getIntent().getExtras().getInt("id");

        examinationList = new ArrayList<>();
        recyLive.setLayoutManager(new LinearLayoutManager(this));
        examinationAdapter = new ExaminationAdapter(examinationList, this);
        recyLive.setAdapter(examinationAdapter);

        getList();

    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_mylive;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }

    private void getList() {

        OkHttpClient okhttpClient = HttpManager.getInstance().getOkhttpClient();


        MediaType mediaType = MediaType.parse("application/json");
        Map<String,Object> json = new HashMap<>();
        json.put("command",2);
        json.put("teacherId",id);
        json.put("pageSize",20);
        json.put("pageIndex",page);
        json.put("userId",id);
        json.put("key","");
        String str = new Gson().toJson(json);
        str = str += "yzy.ruiyunqu.com";
        Log.d("------------", "getList: "+str);

        try {
            md5 = MD5Util.getMD5(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Log.d("------------", "getList: "+md5);
        RequestBody requestBody = RequestBody.create(mediaType, new Gson().toJson(json));
        Request request = new Request
                .Builder()
                .post(requestBody)//Post请求的参数传递
                .url(MyApp.yueurl+md5)
                .build();

        okhttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, @NotNull Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            body = Objects.requireNonNull(response.body()).string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        JobListBean jobListBean = new Gson().fromJson(body, JobListBean.class);
                        if(jobListBean.getResult()==1){
                            if(jobListBean.getData().size()>0){
                                linearModle.setVisibility(View.GONE);
                                recyLive.setVisibility(View.VISIBLE);
                                examinationList.addAll(jobListBean.getData());
                                examinationAdapter.notifyDataSetChanged();
                            }else {
                                linearModle.setVisibility(View.VISIBLE);
                                recyLive.setVisibility(View.GONE);
                            }

                        }else {
                            linearModle.setVisibility(View.VISIBLE);
                            recyLive.setVisibility(View.GONE);
                            ToastUtil.makeShortText(ExaminationListActivity.this,"暂无数据");
                        }
                    }
                });
            }
        });
    }

}
