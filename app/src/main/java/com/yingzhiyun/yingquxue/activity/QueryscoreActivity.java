package com.yingzhiyun.yingquxue.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.DoScoreJson;
import com.yingzhiyun.yingquxue.OkBean.ScorejieguoBean;
import com.yingzhiyun.yingquxue.OkBean.StudentinfoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.examination.YueJuanActivity;
import com.yingzhiyun.yingquxue.activity.score.MineScoreActivity;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.units.MD5Util;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.StringUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

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

public class QueryscoreActivity extends SimpleActivity {
    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.btn_login)
    TextView btnLogin;
   private String md5;
    @Override
    protected void initData() throws ParseException {
        toolTitle.setText("成绩查询");
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_queryscore;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    public void doLogin(){

        OkHttpClient okhttpClient = HttpManager.getInstance().getOkhttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        Map<String,Object> json = new HashMap<>();
        json.put("command",8);
        json.put("idNumber",edUsername.getText().toString());

        String str = new Gson().toJson(json);
        str = str += "yzy.ruiyunqu.com";

        try {
            md5 = MD5Util.getMD5(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Log.d("moxun", "doLogin: "+str);
        Log.d("moxun", "doLogin: "+md5);
        RequestBody requestBody = RequestBody.create(mediaType, new Gson().toJson(json));
        Request request = new Request
                .Builder()
                .post(requestBody)//Post请求的参数传递
                .url(MyApp.yueurl+ md5)
                .build();
        okhttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                int code = response.code();
                if(code==200){
                    String scord = response.body().string();
                    Log.d("-------------", "onResponse: "+code);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            StudentinfoBean scorejieguoBean = new Gson().fromJson(scord, StudentinfoBean.class);
                            if(scorejieguoBean.getError()==null){
                                if(scorejieguoBean.getResult()==1){

                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("bean",scorejieguoBean);

                                    startActivity(MineScoreActivity.class,bundle);
                                }else {
                                    ToastUtil.makeShortText(QueryscoreActivity.this,"学号输入错误");
                                }
                            }else {
                                ToastUtil.makeShortText(QueryscoreActivity.this,"学号输入错误");
                            }

                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.makeShortText(QueryscoreActivity.this,"网络错误请稍后重试");
                        }
                    });

                }

            }
        });
    }
    @OnClick({R.id.finish, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.btn_login:
                if(StringUtils.isEmpty(edUsername.getText().toString())){
                    ToastUtil.makeShortText(this,"请输入学号");
                }else {
//                    startActivity(MineScoreActivity.class);
                    doLogin();
                }

                break;
        }
    }
}
