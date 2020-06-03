package com.yingzhiyun.yingquxue.activity.homepagr;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.yingzhiyun.yingquxue.Mvp.ExaminationMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AcoreQueryBean;
import com.yingzhiyun.yingquxue.OkBean.BaominginfoBean;
import com.yingzhiyun.yingquxue.OkBean.ExamAnalysisBean;
import com.yingzhiyun.yingquxue.OkBean.ExaminationListBean;
import com.yingzhiyun.yingquxue.OkBean.MyExamBean;
import com.yingzhiyun.yingquxue.OkBean.PracticeZuoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.ExamAnalysisAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.ExaminationPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.yingzhiyun.yingquxue.units.VerticalSwipeRefreshLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExamAnalysisActivity extends BaseActicity<ExaminationMvp.Examination_View, ExaminationPresenter<ExaminationMvp.Examination_View>>
        implements ExaminationMvp.Examination_View, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.recy_book)
    RecyclerView recyBook;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.sendmessage)
    ImageView sendmessage;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.refresh_layout)
    VerticalSwipeRefreshLayout refreshLayout;
    private ArrayList<ExamAnalysisBean.ResultBean.ExamDetailBean> examDetailBeans;
    private ExamAnalysisAdapter examAnalysisAdapter;
    private int id;
    private String title;
    private String type;

    @Override
    public void setExaminationList(ExaminationListBean examineBean) {

    }

    @Override
    public void setexamDetail(BaominginfoBean baominginfoBean) {

    }

    @Override
    public void setexamsing(BaominginfoBean baominginfoBean) {

    }

    @Override
    public void setgoExam(PracticeZuoBean practiceZuoBean) {

    }

    @Override
    public void setexamSubmit(BaominginfoBean baominginfoBean) {

    }

    @Override
    public void setexamTimes(BaominginfoBean baominginfoBean, String type) {

    }

    @Override
    public void setexam(MyExamBean myExamBean) {

    }

    @Override
    public void setscorequery(AcoreQueryBean acoreQueryBean) {

    }

    @Override
    public void setexamAnalysis(ExamAnalysisBean myExamBean) {
        if (myExamBean.getStatus() == 200) {
            if (myExamBean.getResult().getExamDetail().size() > 0) {

                linearModle.setVisibility(View.GONE);
                for (int i = 0; i < myExamBean.getResult().getExamDetail().size(); i++) {
                    if (myExamBean.getResult().getExamDetail().get(i).getAnalysis() != null) {
                        examDetailBeans.add(myExamBean.getResult().getExamDetail().get(i));
                    }

                }

                examAnalysisAdapter.notifyDataSetChanged();
            } else {

                linearModle.setVisibility(View.VISIBLE);
            }
        } else if (myExamBean.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, myExamBean.getHint());
            startActivity(PwdLoginActivity.class);
        } else {

            linearModle.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected ExaminationPresenter<ExaminationMvp.Examination_View> createPresenter() {
        return new ExaminationPresenter<>();
    }

    @Override
    protected void initData() throws ParseException {
        refreshLayout.setOnRefreshListener(this);
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id", 0);
        title = extras.getString("title");
        type = extras.getString("type");
        toolTitle.setText(title);
        sendmessage.setVisibility(View.GONE);
        examDetailBeans = new ArrayList<>();
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int x = point.x;
        examAnalysisAdapter = new ExamAnalysisAdapter(examDetailBeans,x);
        recyBook.setLayoutManager(new LinearLayoutManager(this));
        recyBook.setAdapter(examAnalysisAdapter);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());
            jsonObject.put("oenId", id);
            jsonObject.put("type", type);


            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresentser.getexamAnalysis(jsonObject.toString());
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_hudong;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void onRefresh() {
        examDetailBeans.clear();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());
            jsonObject.put("oenId", id);
            jsonObject.put("type", type);


            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresentser.getexamAnalysis(jsonObject.toString());
        refreshLayout.setRefreshing(false);
    }
}
