package com.yingzhiyun.yingquxue.Fragment.home;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.SelectedOptionsMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.FolderListOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.ZiyuanJsonBean;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.OkBean.skillCourseListBeam;
import com.yingzhiyun.yingquxue.OkBean.skillTypeListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.ZiyuanAdapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.SelectedOptionsPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;

public class MustTestFragment extends BaseFragment<SelectedOptionsMvp.SelectedOptions_View, SelectedOptionsPresenter<SelectedOptionsMvp.SelectedOptions_View>> implements SelectedOptionsMvp.SelectedOptions_View {

    private final String gradeid;
    private final int subjectid;
    private final int id;
    @BindView(R.id.fragment_recy)
    PullLoadMoreRecyclerView fragmentRecy;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.teacher_info)
    TextView teacherInfo;
    private ZiyuanAdapter ziyuanAdapter;
    private ArrayList<ZiyuanBean.ResultBean> resultBeans;
private  int page=1;
    public MustTestFragment(int subjectId, String string, int id) {
        super();
        this.subjectid = subjectId;
        this.gradeid = string;
        this.id = id;
    }

    @Override
    public void setSelectedOptions(SelectedOptionsBean selectedOptions) {

    }

    @Override
    public void setZiyuan(ZiyuanBean ziyuan) {
        if (ziyuan.getStatus() == 200) {
            fragmentRecy.setPullLoadMoreCompleted();

            if (page == 1) {
                if (ziyuan.getResult().size() > 0) {
                    resultBeans.clear();
                    linearModle.setVisibility(View.GONE);
                    fragmentRecy.setVisibility(View.VISIBLE);

                    resultBeans.addAll(ziyuan.getResult());
                    ziyuanAdapter.notifyDataSetChanged();
                } else {
                    linearModle.setVisibility(View.VISIBLE);
                    fragmentRecy.setVisibility(View.GONE);
                }
            } else {
                if (ziyuan.getResult().size() > 0) {

                    resultBeans.addAll(ziyuan.getResult());
                    ziyuanAdapter.notifyDataSetChanged();
                } else {
                    fragmentRecy.setPullRefreshEnable(false);
                }
            }


        } else  if(ziyuan.getStatus()==511) {
            mActivity.finish();
            ToastUtil.makeShortText(context, "身份过期");
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    public void setCoursewareList(ZiyuanBean coursewareList) {

    }

    @Override
    public void setskillTypeList(skillTypeListBean skillTypeListBean) {

    }

    @Override
    public void setskillCourseList(skillCourseListBeam skillCourseListBeam) {

    }

    @Override
    public void setfolderListOptions(FolderListOptionsBean folderListOptionsBean) {

    }

    @Override
    public void setfolderDetail(BashiinfoBean bashiinfoBean) {

    }

    @Override
    protected SelectedOptionsPresenter<SelectedOptionsMvp.SelectedOptions_View> createPresenter() {
        return new SelectedOptionsPresenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initData() throws JSONException {
        resultBeans = new ArrayList<>();
        ziyuanAdapter=new ZiyuanAdapter(resultBeans,context);
        fragmentRecy.setLinearLayout();
        fragmentRecy.setPullRefreshEnable(false);
        fragmentRecy.setAdapter(ziyuanAdapter);
        fragmentRecy.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page=1;
                presenter.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, id, SharedPreferenceUtils.getUserid(), Integer.parseInt(gradeid), subjectid, SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
                fragmentRecy.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {


                page++;
                presenter.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, id, SharedPreferenceUtils.getUserid(), Integer.parseInt(gradeid), subjectid, SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
                fragmentRecy.setPullLoadMoreCompleted();
            }
        });
        Log.d("-----------------",new Gson().toJson(new ZiyuanJsonBean(page, id, SharedPreferenceUtils.getUserid(), Integer.parseInt(gradeid), subjectid, SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
        presenter.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, id, SharedPreferenceUtils.getUserid(), Integer.parseInt(gradeid), subjectid, SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
    }
}
