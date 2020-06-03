package com.yingzhiyun.yingquxue.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.yingzhiyun.yingquxue.adapter.MyexamAdapter;
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

public class MyExamActivity extends BaseActicity<ExaminationMvp.Examination_View, ExaminationPresenter<ExaminationMvp.Examination_View>>
        implements ExaminationMvp.Examination_View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.recy_book)
    RecyclerView recyBook;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.sendmessage)
    ImageView sendmessage;
    @BindView(R.id.refresh_layout)
    VerticalSwipeRefreshLayout refreshLayout;
    private ArrayList<MyExamBean.ResultBean> resultBeans;
    private MyexamAdapter myexamAdapter;
    private JSONObject jsonObject;

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
        if (myExamBean.getStatus() == 200) {
            if (myExamBean.getResult().size() > 0) {

                linearModle.setVisibility(View.GONE);
                resultBeans.addAll(myExamBean.getResult());
                myexamAdapter.notifyDataSetChanged();
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
    public void setscorequery(AcoreQueryBean acoreQueryBean) {

    }

    @Override
    public void setexamAnalysis(ExamAnalysisBean examAnalysisBean) {

    }

    @Override
    protected ExaminationPresenter<ExaminationMvp.Examination_View> createPresenter() {
        return new ExaminationPresenter<>();
    }

    @Override
    protected void initData() throws ParseException {
        refreshLayout.setOnRefreshListener(this);
        toolTitle.setText("成绩报告");
        sendmessage.setVisibility(View.GONE);
        resultBeans = new ArrayList<>();
        myexamAdapter = new MyexamAdapter(resultBeans, this);
        recyBook.setLayoutManager(new LinearLayoutManager(this));
        recyBook.setAdapter(myexamAdapter);
        jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());


            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mPresentser.gwtexam(jsonObject.toString());
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
        resultBeans.clear();
        mPresentser.gwtexam(jsonObject.toString());
        refreshLayout.setRefreshing(false);
    }
}
