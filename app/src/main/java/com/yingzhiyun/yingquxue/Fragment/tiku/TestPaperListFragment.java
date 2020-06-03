package com.yingzhiyun.yingquxue.Fragment.tiku;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.TestPaperListMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BetDetailBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TestPaperListjson;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.tiku.TestPagperinfoActivity;
import com.yingzhiyun.yingquxue.activity.tiku.TestPaperListActivity;
import com.yingzhiyun.yingquxue.adapter.TestPagperAapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.TestPaperListPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;

public class TestPaperListFragment extends BaseFragment<TestPaperListMvp.TestPaperList_View, TestPaperListPresenter<TestPaperListMvp.TestPaperList_View>>
        implements TestPaperListMvp.TestPaperList_View {
    private final int subjectid;
    private final int gradeid;
    @BindView(R.id.recy_tsetpagper)
    PullLoadMoreRecyclerView recyTsetpagper;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private int page = 1;
    private ArrayList<TestPaperListBean.ResultBean> resultBeans;
    private TestPagperAapter testPagperAapter;
    public TestPaperListFragment(int subject, int grade) {
        super();
        this.subjectid=subject;
        this.gradeid=grade;
    }

    @Override
    public void setTestPaperList(TestPaperListBean testPaperList) {
        if(testPaperList.getStatus()==200){
            recyTsetpagper.setPullLoadMoreCompleted();
            if(page==1){
                if (testPaperList.getResult().size() > 0) {
                    recyTsetpagper.setVisibility(View.VISIBLE);
                    linearModle.setVisibility(View.GONE);
                    resultBeans.addAll(testPaperList.getResult());
                    testPagperAapter.notifyDataSetChanged();
                }else {
                    recyTsetpagper.setVisibility(View.GONE);
                    linearModle.setVisibility(View.VISIBLE);
                }
            }else {
                if (testPaperList.getResult().size() > 0) {
                    resultBeans.addAll(testPaperList.getResult());
                    testPagperAapter.notifyDataSetChanged();
                }else {
                  recyTsetpagper.setPullRefreshEnable(false);
                }
            }


        }else if(testPaperList.getStatus()==511){
            mActivity.finish();
            ToastUtil.makeShortText(context,"账号已在别处登录");
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    public void setfilterItem(TeachingShaixuanBean teachingShaixuanBean) {

    }

    @Override
    public void setZiyuan(ZiyuanBean ziyuan) {

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
        testPagperAapter = new TestPagperAapter(R.layout.item_testpaper, resultBeans);
        recyTsetpagper.setLinearLayout();
        recyTsetpagper.setAdapter(testPagperAapter);


        testPagperAapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                startActivity(new Intent(context, TestPagperinfoActivity.class).putExtra("id", testPagperAapter.resultBeans.get(position).getId()).putExtra("juantype","testPaperCheck"));
            }
        });
   recyTsetpagper.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
       @Override
       public void onRefresh() {
                   page = 1;
            resultBeans.clear();
                getlist();

       }

       @Override
       public void onLoadMore() {
            page++;
           getlist();
       }
   });

        getlist();
    }
    public void getlist(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());
            jsonObject.put("subject_id", subjectid);
            jsonObject.put("pageNum", page);
            jsonObject.put("grade", gradeid);
            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        presenter.getTestPaperList(jsonObject.toString());
    }
}
