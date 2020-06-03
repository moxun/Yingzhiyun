package com.yingzhiyun.yingquxue.Fragment.home;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.ExaminationMvp;
import com.yingzhiyun.yingquxue.Mvp.TestPaperListMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AcoreQueryBean;
import com.yingzhiyun.yingquxue.OkBean.BaominginfoBean;
import com.yingzhiyun.yingquxue.OkBean.ExamAnalysisBean;
import com.yingzhiyun.yingquxue.OkBean.ExaminationListBean;
import com.yingzhiyun.yingquxue.OkBean.MyExamBean;
import com.yingzhiyun.yingquxue.OkBean.PracticeZuoBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.ExaminationAdapter;
import com.yingzhiyun.yingquxue.adapter.ExaminationListAdapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.ExaminationPresenter;
import com.yingzhiyun.yingquxue.presenter.TestPaperListPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;

public class PracticeteseFragment extends BaseFragment<ExaminationMvp.Examination_View, ExaminationPresenter<ExaminationMvp.Examination_View>>
        implements ExaminationMvp.Examination_View {
    private final int subject;
    private final int gradeid;
    @BindView(R.id.recy_tsetpagper)
    PullLoadMoreRecyclerView recyTsetpagper;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private ArrayList<ExaminationListBean.ResultBean> resultBeans;
    private ExaminationListAdapter examinationListAdapter;

    public PracticeteseFragment(int subject, int gradeid) {
        this.subject=subject;
        this.gradeid=gradeid;
    }


    @Override
    public int createLayoutId() {
        return R.layout.fragment_testlist;
    }

    @Override
    protected void initData() throws JSONException {
        text.setVisibility(View.VISIBLE);
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("在线考试结束后，系统将自动评分生成成绩报告可以前往").append("<font color='#7D7DFF' size='24'>“我的”</font>").append("成绩报告中查看");
        text.setText(Html.fromHtml(stringBuilder.toString()));
        resultBeans = new ArrayList<>();
        examinationListAdapter = new ExaminationListAdapter(resultBeans,context);

        recyTsetpagper.setPushRefreshEnable(false);
        recyTsetpagper.setLinearLayout();
        recyTsetpagper.setAdapter(examinationListAdapter);
        recyTsetpagper.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                recyTsetpagper.setPullLoadMoreCompleted();
                resultBeans.clear();

                getList();
            }

            @Override
            public void onLoadMore() {

            }
        });
        resultBeans.clear();
        getList();
    }
    private void getList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());
            jsonObject.put("subject",subject);
            jsonObject.put("level", gradeid);

            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("moxun", "initData: "+jsonObject.toString());
        presenter.getExaminationList(jsonObject.toString());
    }
    @Override
    public void setExaminationList(ExaminationListBean examineBean) {
        resultBeans.clear();
      if(examineBean.getStatus()==200){
          if(linearModle==null){
              return;
          }
          if(examineBean.getResult().size()>0){
              linearModle.setVisibility(View.GONE);
              recyTsetpagper.setVisibility(View.VISIBLE);
              resultBeans.addAll(examineBean.getResult());
              examinationListAdapter.notifyDataSetChanged();
          }else {
              linearModle.setVisibility(View.VISIBLE);
              recyTsetpagper.setVisibility(View.GONE);
          }
      }
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
    public void setexamAnalysis(ExamAnalysisBean examAnalysisBean) {

    }


    @Override
    protected ExaminationPresenter<ExaminationMvp.Examination_View> createPresenter() {
        return new ExaminationPresenter<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        resultBeans.clear();

        getList();
    }
}
