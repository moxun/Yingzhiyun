package com.yingzhiyun.yingquxue.activity.examination;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.DoScoreJson;
import com.yingzhiyun.yingquxue.OkBean.NumberListBean;
import com.yingzhiyun.yingquxue.OkBean.ParperinfoBean;
import com.yingzhiyun.yingquxue.OkBean.ScorejieguoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.KeyWordAdapter;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.units.MD5Util;
import com.yingzhiyun.yingquxue.units.MulitPointTouchListener;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
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
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChangeScoreActivity extends SimpleActivity {
    @BindView(R.id.yiyue)
    TextView yiyue;
    @BindView(R.id.shijuan)
    ImageView shijuan;
    @BindView(R.id.yuetika)
    ImageView yuetika;
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.keyword)
    RecyclerView keyword;
    @BindView(R.id.keyword_heng)
    RecyclerView keywordHeng;
    @BindView(R.id.chengji)
    EditText editText;
    @BindView(R.id.delete)
    TextView delete;
    private ArrayList<String> wordsList;
    private ArrayList<NumberListBean.DataBean>  nmumberList=new ArrayList<>();
    private String md5;

    private String testNo;
    private int testId;
    private String img;
    private int paperId;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initData() throws ParseException {

        wordsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            wordsList.add(i + "");
        }
        yuetika.setVisibility(View.GONE);
        paperId = Objects.requireNonNull(getIntent().getExtras()).getInt("paperId");
        testNo = getIntent().getExtras().getString("testNo");
        testId = getIntent().getExtras().getInt("testId");
        img = getIntent().getExtras().getString("Img");
        shijuan.setOnTouchListener(new MulitPointTouchListener());

        Glide.with(this).load(img).into(shijuan);
        editText.setKeyListener(null);//不可粘贴，长按不会弹出粘贴框
        editText.setClickable(false);//不可点击，但是这个效果我这边没体现出来，不知道怎没用
        wordsList.add(".");
        keyword.setLayoutManager(new GridLayoutManager(this, 6));
        keywordHeng.setLayoutManager(new GridLayoutManager(this, 2));
        KeyWordAdapter keyWordAdapter = new KeyWordAdapter(this, wordsList);
        keyword.setAdapter(keyWordAdapter);
        keywordHeng.setAdapter(keyWordAdapter);

        keyWordAdapter.OnsetOnClickListener(new KeyWordAdapter.setOnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void setOnClickListener(String s) {
                int curSelection = editText.getSelectionStart();
                int length = editText.getText().toString().length();


                if (curSelection < length) {
                    String content = editText.getText().toString();
                    editText.setText(content.substring(0, curSelection) + s + content.subSequence(curSelection, length));
                    editText.setSelection(curSelection + 1);
                } else {
                    editText.setText(editText.getText().toString() + s);
                    editText.setSelection(editText.getText().toString().length());
                }
            }

            @Override
            public void setfootClickListener() {
//判断当前屏幕方向
                if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {

                    //切换竖屏
                    ChangeScoreActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    keyword.setVisibility(View.VISIBLE);
                    keywordHeng.setVisibility(View.GONE);
                } else {

                    ChangeScoreActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    keyword.setVisibility(View.GONE);
                    keywordHeng.setVisibility(View.VISIBLE);
                }

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = editText.getText().toString().length();
                int curSelection = editText.getSelectionStart();
                if (length > 0 && curSelection > 0 && curSelection <= length) {
                    String content = editText.getText().toString();
                    editText.setText(content.substring(0, curSelection - 1) + content.subSequence(curSelection, length));
                    editText.setSelection(curSelection - 1);
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {

            //切换竖屏
            keyword.setVisibility(View.GONE);
            keywordHeng.setVisibility(View.VISIBLE);

        } else {

            keyword.setVisibility(View.VISIBLE);
            keywordHeng.setVisibility(View.GONE);

        }
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_yuejuan;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.yuetika, R.id.finish,R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yuetika:

                break;
            case R.id.finish:
                finish();
                break;
            case R.id.ok:
                if(editText.getText().toString()!=null){
                    doscore();
                }else {
                    ToastUtil.makeShortText(this,"请输入分数");
                }

                break;
        }
    }



    public void doscore(){
        ArrayList<DoScoreJson> doScoreJsons = new ArrayList<>();
        doScoreJsons.add(new DoScoreJson(testId,editText.getText().toString(),testNo));
        OkHttpClient okhttpClient = HttpManager.getInstance().getOkhttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        Map<String,Object> json = new HashMap<>();
        json.put("command",5);
        json.put("groupId", SharedPreferenceUtils.getgroup_id());
        json.put("userId",SharedPreferenceUtils.getteachetid());
        json.put("teacherId",SharedPreferenceUtils.getteachetid());
        json.put("list",new Gson().toJson(doScoreJsons));
        String str = new Gson().toJson(json);
        str = str += "yzy.ruiyunqu.com";
        try {
            md5 = MD5Util.getMD5(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Log.d("----------", "doscore: "+str);
        Log.d("----------", "doscore: "+md5);
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
                String scord = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ScorejieguoBean scorejieguoBean = new Gson().fromJson(scord, ScorejieguoBean.class);
                        if(scorejieguoBean.getResult()==1){
                            editText.setText("");
                            finish();
                            ToastUtil.makeShortText(ChangeScoreActivity.this,"修改成功");
                        }else {
                            ToastUtil.makeShortText(ChangeScoreActivity.this,"网络错误");
                        }
                    }
                });
            }
        });
    }
}
