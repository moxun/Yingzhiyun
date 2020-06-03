package com.yingzhiyun.yingquxue.activity.score;

import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Fragment.score.PointsFragment;
import com.yingzhiyun.yingquxue.Fragment.score.TotalpointsFragment;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.FullscoreBean;
import com.yingzhiyun.yingquxue.OkBean.StudentinfoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.QueryscoreActivity;
import com.yingzhiyun.yingquxue.adapter.FrgmentAdapter;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.units.AlphaPageTransformer;
import com.yingzhiyun.yingquxue.units.DeviceUtils;
import com.yingzhiyun.yingquxue.units.MD5Util;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.yingzhiyun.yingquxue.units.ZoomOutPageTransformer;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
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

public class ScoreInfoActivity extends SimpleActivity {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.score_tab)
    TabLayout tabWeChat;
    @BindView(R.id.view)
    ViewPager viewpager;

    private ArrayList<String> strings;
    private ArrayList<Fragment> fragments;
    private static int TOTAL_COUNT = 10;
    private String md5;
    private StudentinfoBean bean;
    int bachid;
    private FullscoreBean fullscoreBean;

    public void doLogin(){
        StudentinfoBean.StudentBean student = bean.getStudent();
        OkHttpClient okhttpClient = HttpManager.getInstance().getOkhttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        Map<String,Object> json = new HashMap<>();
        json.put("command",9);
        json.put("idNumber",student.getIdNumber());
        json.put("batchId",bachid);
        json.put("classId",student.getClassId());
        json.put("gradeId",student.getGradeId());
        json.put("schoolId",student.getSchoolId());
        String str = new Gson().toJson(json);
        str = str += "yzy.ruiyunqu.com";

        try {
            md5 = MD5Util.getMD5(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Log.d("moxun", "doLogin: "+str);
        Log.d("moxun", "doLogin: "+ md5);
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
                        fullscoreBean = new Gson().fromJson(scord, FullscoreBean.class);
                        fragments.add(new TotalpointsFragment(fullscoreBean));
                        strings.add("总分");
                        for (int i = 0; i < fullscoreBean.getGradeList().size(); i++) {
                            strings.add(fullscoreBean.getGradeList().get(i).getSubjectName());
                            fragments.add(new PointsFragment(fullscoreBean.getGradeList().get(i),student));
                        }
                        tabWeChat.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(TabLayout.Tab tab) {
                                viewpager.setCurrentItem(tab.getPosition());
                            }

                            @Override
                            public void onTabUnselected(TabLayout.Tab tab) {

                            }

                            @Override
                            public void onTabReselected(TabLayout.Tab tab) {

                            }
                        });
                        tabWeChat.setInlineLabel(true);


                        tabWeChat.setupWithViewPager(viewpager);


                        FrgmentAdapter bookFrgmentAdapter = new FrgmentAdapter(getSupportFragmentManager(), fragments, strings);
                        viewpager.setAdapter(bookFrgmentAdapter);

                        viewpager.setOffscreenPageLimit(TOTAL_COUNT);
                        viewpager.setPageMargin(40);

                        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabWeChat));
                    }
                });
            }
        });
    }


    @Override
    protected void initData() throws ParseException {
        toolTitle.setText(getIntent().getStringExtra("title"));
        bean = (StudentinfoBean) getIntent().getSerializableExtra("bean");
         bachid = getIntent().getIntExtra("id",0);
        strings = new ArrayList<>();


        fragments = new ArrayList<>();
        doLogin();

    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_score_info;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }


}
