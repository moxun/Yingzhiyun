package com.yingzhiyun.yingquxue.Fragment.score;

import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AverageBean;
import com.yingzhiyun.yingquxue.OkBean.FullscoreBean;
import com.yingzhiyun.yingquxue.OkBean.RankTypeBean;
import com.yingzhiyun.yingquxue.OkBean.StudentinfoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.score.DatikaActivity;
import com.yingzhiyun.yingquxue.adapter.AverageAdapter;
import com.yingzhiyun.yingquxue.adapter.RankTypeAdapter;
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.units.CircleProgressBar;
import com.yingzhiyun.yingquxue.units.MD5Util;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

public class PointsFragment extends SimpleFragment {
    private final FullscoreBean.GradeListBean bean;
    private final StudentinfoBean.StudentBean studentbean;
    @BindView(R.id.fenshu)
    TextView fenshu;
    @BindView(R.id.cp)
    CircleProgressBar cp;
    @BindView(R.id.all_fen)
    TextView allFen;
    @BindView(R.id.manfen)
    TextView manfen;
    @BindView(R.id.all_score)
    TextView allScore;
    @BindView(R.id.text_score)
    TextView textScore;
    @BindView(R.id.recy_ranktype)
    RecyclerView recyRanktype;
    @BindView(R.id.pingjun)
    TextView pingjun;
    @BindView(R.id.recy_average)
    RecyclerView recyAverage;
    @BindView(R.id.defen)
    TextView defen;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    private ArrayList<RankTypeBean> rankTypeBeans;
    private ArrayList<AverageBean> averageBeans;
    private String md5;
    private FullscoreBean fullscoreBean;

    public PointsFragment(FullscoreBean.GradeListBean gradeListBean, StudentinfoBean.StudentBean student) {
        super();
        this.bean = gradeListBean;
        this.studentbean = student;
    }

    public void doLogin() {

        OkHttpClient okhttpClient = HttpManager.getInstance().getOkhttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        Map<String, Object> json = new HashMap<>();
        json.put("command", 10);
        json.put("idNumber", studentbean.getIdNumber());
        json.put("paperId", bean.getPaperId());
        json.put("classId", studentbean.getClassId());
        json.put("gradeId", studentbean.getGradeId());
        json.put("schoolId", studentbean.getSchoolId());
        String str = new Gson().toJson(json);
        str = str += "yzy.ruiyunqu.com";

        try {
            md5 = MD5Util.getMD5(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Log.d("moxun", "doLogin: " + str);
        Log.d("moxun", "doLogin: " + md5);
        RequestBody requestBody = RequestBody.create(mediaType, new Gson().toJson(json));
        Request request = new Request
                .Builder()
                .post(requestBody)//Post请求的参数传递
                .url(MyApp.yueurl + md5)
                .build();
        okhttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String scord = response.body().string();
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fullscoreBean = new Gson().fromJson(scord, FullscoreBean.class);
                        cp.setMaxProgress(bean.getFullScore());
                        cp.setProgress(bean.getDoScore());
                        allScore.setText(bean.getFullScore() + "");
                        defen.setText(bean.getDoScore() + "");
                        allFen.setText(bean.getFullScore() + "");
                        rankTypeBeans = new ArrayList<>();
                        rankTypeBeans.add(new RankTypeBean(fullscoreBean.getClassRank() + "", "/" + fullscoreBean.getClassStudent(), "班级排名"));
                        rankTypeBeans.add(new RankTypeBean(fullscoreBean.getGradeRank() + "", "/" + fullscoreBean.getGradeStudent(), "年级排名"));
                        rankTypeBeans.add(new RankTypeBean(fullscoreBean.getBatchRank() + "", "/" + fullscoreBean.getBatchStudent(), "联考排名"));
                        RankTypeAdapter rankTypeAdapter = new RankTypeAdapter(rankTypeBeans);
                        recyRanktype.setLayoutManager(new GridLayoutManager(context, 3));
                        recyRanktype.setAdapter(rankTypeAdapter);

                        averageBeans = new ArrayList<>();
                        averageBeans.add(new AverageBean(bean.getDoScore(), fullscoreBean.getClassAvg(), "班级平均分"));
                        averageBeans.add(new AverageBean(bean.getDoScore(), fullscoreBean.getGradeAvg(), "年级平均分"));
                        averageBeans.add(new AverageBean(bean.getDoScore(), fullscoreBean.getBatchRank(), "联考平均分"));
                        AverageAdapter averageAdapter = new AverageAdapter(averageBeans);
                        recyAverage.setLayoutManager(new LinearLayoutManager(context));
                        recyAverage.setAdapter(averageAdapter);
                    }
                });
            }
        });
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_points;
    }

    @Override
    protected void initData() throws JSONException {
        doLogin();

    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        startActivity(DatikaActivity.class);
    }
}
