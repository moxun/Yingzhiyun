package com.yingzhiyun.yingquxue.activity.examination;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.YuejuanTeaBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.QueryscoreActivity;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.units.MD5Util;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.StringUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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

public class ExaminationActivity extends SimpleActivity {
    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.ed_pass)
    EditText edPass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private String md5;
    private String body;

    @Override
    protected void initData() throws ParseException {

    }
    private void sendMultipart(String name,String pwd) {

        OkHttpClient okhttpClient = HttpManager.getInstance().getOkhttpClient();


        MediaType mediaType = MediaType.parse("application/json");
        Map<String,Object> json = new HashMap<>();
        json.put("command",1);
        json.put("name",name);
        json.put("pwd",pwd);
        json.put("userId",0);

        String str = new Gson().toJson(json)+"yzy.ruiyunqu.com";

        System.out.println(str);

        try {
            md5 = MD5Util.getMD5(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Log.d("lllll", "initData: "+ md5);
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
                int code = response.code();
                if(code==200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                body = Objects.requireNonNull(response.body()).string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            YuejuanTeaBean yuejuanTeaBean = new Gson().fromJson(body, YuejuanTeaBean.class);
                            if(yuejuanTeaBean.getResult()==1){
                                Bundle bundle = new Bundle();
                                bundle.putInt("id",yuejuanTeaBean.getData().getId());
                                SharedPreferenceUtils.setteacherid(yuejuanTeaBean.getData().getId()+"");
                                startActivity(ExaminationListActivity.class,bundle);
                            }else {
                                ToastUtil.makeShortText(ExaminationActivity.this,"用户名密码错误");
                            }
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.makeShortText(ExaminationActivity.this,"网络错误请稍后重试");
                        }
                    });
                }

            }
        });
    }
    @Override
    public int createLayoutID() {
        return R.layout.activity_examination;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.finish, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.btn_login:
                String telphone = edUsername.getText().toString().trim();

                String password = edPass.getText().toString().trim();
                boolean isEmpty = StringUtils.isEmpty(telphone) && StringUtils.isEmpty(password);
                if (isEmpty) {
                    ToastUtil.makeLongText(this, "填写信息不能为空");
                }else {
                    sendMultipart(telphone,password);
                }
                break;
        }
    }
}
