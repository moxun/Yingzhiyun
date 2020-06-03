package com.yingzhiyun.yingquxue.activity.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.yingzhiyun.yingquxue.activity.homepagr.OnceSearchActivity;
import com.yingzhiyun.yingquxue.adapter.MoreTeacherAadpter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.SelectedOptionsPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreTeacherActivity extends BaseActicity<
        SelectedOptionsMvp.SelectedOptions_View, SelectedOptionsPresenter<SelectedOptionsMvp.SelectedOptions_View>> implements SelectedOptionsMvp.SelectedOptions_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.recy_live)
    PullLoadMoreRecyclerView recyLive;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    private ArrayList<ZiyuanBean.ResultBean> resultBeans;
    private MoreTeacherAadpter moreSelectionAdapter;
    private int moduleBean;
    private String name;
    private  int page=1;
    @Override
    public void setSelectedOptions(SelectedOptionsBean selectedOptions) {
    }
    @Override
    public void setZiyuan(ZiyuanBean ziyuan) {
        if (ziyuan.getStatus() == 200) {
            if(ziyuan.getResult().size()>0){
                linearModle.setVisibility(View.GONE);
                recyLive.setVisibility(View.VISIBLE);
                resultBeans.addAll(ziyuan.getResult());
                moreSelectionAdapter.notifyDataSetChanged();
            }else {
                recyLive.setPushRefreshEnable(false);
                ToastUtil.makeShortText(this,"没有更多数据了");
            }
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
    protected void initData() throws ParseException {
        moduleBean = getIntent().getIntExtra("bean", 0);
        name = getIntent().getStringExtra("name");
        resultBeans = new ArrayList<>();
        toolTitle.setText(name);
        moreSelectionAdapter = new MoreTeacherAadpter(resultBeans, this);
        recyLive.setLinearLayout();
        recyLive.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page=1;
                mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, moduleBean, SharedPreferenceUtils.getUserid(), 0, 0, SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
                recyLive.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {


                page++;
                mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, moduleBean, SharedPreferenceUtils.getUserid(), 0, 0, SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
                recyLive.setPullLoadMoreCompleted();
            }
        });
        recyLive.setAdapter(moreSelectionAdapter);
        mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, moduleBean, SharedPreferenceUtils.getUserid(), 0, 0, SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_searchlist;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }



    @OnClick({R.id.finish, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.iv_search:
                Bundle bundle = new Bundle();
                bundle.putInt("id",moduleBean);
                bundle.putString("type","course");
                startActivity(OnceSearchActivity.class,bundle);
                break;
        }
    }
}