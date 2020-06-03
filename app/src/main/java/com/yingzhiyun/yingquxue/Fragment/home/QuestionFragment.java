package com.yingzhiyun.yingquxue.Fragment.home;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.TestPaperListMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BetDetailBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TestPaperListjson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.ZiyuanJsonBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.OkBean.ZutijiluBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.QuestionFoildAdapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.TestPaperListPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class QuestionFragment extends BaseFragment<TestPaperListMvp.TestPaperList_View, TestPaperListPresenter<TestPaperListMvp.TestPaperList_View>>
        implements TestPaperListMvp.TestPaperList_View {
    private final int mid;
    private final int grid;
    @BindView(R.id.recy_tsetpagper)
    PullLoadMoreRecyclerView recyTsetpagper;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private ArrayList<ZiyuanBean.ResultBean> resultBeans;
    private QuestionFoildAdapter questionFoildAdapter;
        private int page=1;
    public QuestionFragment(int modleid, int gradeid) {
        super();
        mid =modleid;
        grid =gradeid;
    }

    @Override
    public void setTestPaperList(TestPaperListBean testPaperList) {

    }

    @Override
    public void setfilterItem(TeachingShaixuanBean teachingShaixuanBean) {

    }

    @Override
    public void setZiyuan(ZiyuanBean ziyuan) {


        if (ziyuan.getStatus() == 200) {
            recyTsetpagper.setPullLoadMoreCompleted();

            if (page == 1) {
                if (ziyuan.getResult().size() > 0) {
                    resultBeans.clear();
                    linearModle.setVisibility(View.GONE);
                    recyTsetpagper.setVisibility(View.VISIBLE);

                    resultBeans.addAll(ziyuan.getResult());
                    questionFoildAdapter.notifyDataSetChanged();
                } else {
                    linearModle.setVisibility(View.VISIBLE);
                    recyTsetpagper.setVisibility(View.GONE);
                }
            } else {
                if (ziyuan.getResult().size() > 0) {

                    resultBeans.addAll(ziyuan.getResult());
                    questionFoildAdapter.notifyDataSetChanged();
                } else {
                    recyTsetpagper.setPullRefreshEnable(false);
                }
            }


        } else {

            ToastUtil.makeShortText(context, "身份过期");
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    public void setbetList(BetListBean betListBean) {

    }

    @Override
    public void setbetDetail(BetDetailBean betDetailBean) {

    }

    @Override
    protected TestPaperListPresenter<TestPaperListMvp.TestPaperList_View> createPresenter() {
        return new TestPaperListPresenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_testlist;
    }

    @Override
    protected void initData() throws JSONException {
        resultBeans = new ArrayList<>();
        questionFoildAdapter = new QuestionFoildAdapter(resultBeans,context);
        recyTsetpagper.setGridLayout(3);
        recyTsetpagper.setAdapter(questionFoildAdapter);

        recyTsetpagper.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page=1;
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
                    jsonObject.put("token", SharedPreferenceUtils.getToken());
                    jsonObject.put("indexListTypeId",mid);
                    jsonObject.put("level", grid);
                    jsonObject.put("pageNum", page);
                    jsonObject.put("version", MyApp.version);
                    jsonObject.put("device", "Android");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                presenter.getZiyuan(jsonObject.toString());
            }

            @Override
            public void onLoadMore() {


                page++;
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
                    jsonObject.put("token", SharedPreferenceUtils.getToken());
                    jsonObject.put("indexListTypeId",mid);
                    jsonObject.put("level", grid);
                    jsonObject.put("pageNum", page);
                    jsonObject.put("version", MyApp.version);
                    jsonObject.put("device", "Android");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                presenter.getZiyuan(jsonObject.toString());
            }
        });
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());
            jsonObject.put("indexListTypeId",mid);
            jsonObject.put("level", grid);
            jsonObject.put("pageNum", page);
            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("moxun", "initData: "+jsonObject.toString());
        presenter.getZiyuan(jsonObject.toString());
    }
}
