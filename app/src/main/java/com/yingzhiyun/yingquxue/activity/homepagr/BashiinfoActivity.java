package com.yingzhiyun.yingquxue.activity.homepagr;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.SelectedOptionsMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BashiinfoJson;
import com.yingzhiyun.yingquxue.OkBean.FolderListOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.OkBean.skillCourseListBeam;
import com.yingzhiyun.yingquxue.OkBean.skillTypeListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.BashiinfoAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.SelectedOptionsPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.yingzhiyun.yingquxue.units.VerticalSwipeRefreshLayout;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BashiinfoActivity extends BaseActicity<SelectedOptionsMvp.SelectedOptions_View, SelectedOptionsPresenter<SelectedOptionsMvp.SelectedOptions_View>> implements SelectedOptionsMvp.SelectedOptions_View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.title)
    TextView mtitle;
    @BindView(R.id.recy_kao)
    RecyclerView recyKao;
    @BindView(R.id.refresh_layout)
    VerticalSwipeRefreshLayout refreshLayout;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    private ArrayList<BashiinfoBean.ResultBean.DetailBean> resultBeans=new ArrayList<>();
    private BashiinfoAdapter bashiinfoAdapter;
    private int id;
    private boolean is;

    @Override
    public void onRefresh() {
        resultBeans.clear();
        mPresentser.getfolderDetail(new Gson().toJson(new BashiinfoJson(id, SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), "Android", MyApp.version)));
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void setSelectedOptions(SelectedOptionsBean selectedOptions) {

    }

    @Override
    public void setZiyuan(ZiyuanBean ziyuan) {

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

        if (bashiinfoBean.getStatus() == 200) {

            if (bashiinfoBean.getResult() != null) {
                for (int i = 0; i < bashiinfoBean.getResult().getDetail().size(); i++) {
                    if (bashiinfoBean.getResult().getDetail().get(i).getSubjectDetail().size() > 0) {
                        resultBeans.add(bashiinfoBean.getResult().getDetail().get(i));
                    }
                }

                if (resultBeans.size() > 0) {

                    recyKao.setVisibility(View.VISIBLE);
                    linearModle.setVisibility(View.GONE);
                    bashiinfoAdapter.notifyDataSetChanged();
                } else {
                    recyKao.setVisibility(View.GONE);
                    linearModle.setVisibility(View.VISIBLE);
                }
            }

        } else if (bashiinfoBean.getStatus() == 511) {
            finish();
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    protected SelectedOptionsPresenter<SelectedOptionsMvp.SelectedOptions_View> createPresenter() {
        return new SelectedOptionsPresenter<>();
    }

    @Override
    protected void initData() throws ParseException {
        refreshLayout.setOnRefreshListener(this);
        id = getIntent().getExtras().getInt("id");
        String title = getIntent().getExtras().getString("title");
        is = getIntent().getExtras().getBoolean("is");
        mtitle.setText(title);

        Log.d("", "initData: ");
        bashiinfoAdapter = new BashiinfoAdapter(resultBeans, this);
        recyKao.setLayoutManager(new LinearLayoutManager(this));
        recyKao.setAdapter(bashiinfoAdapter);
        recyKao.setNestedScrollingEnabled(false);
        mPresentser.getfolderDetail(new Gson().toJson(new BashiinfoJson(id, SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), "Android", MyApp.version)));
        Log.d("-------", "initData: "+new Gson().toJson(new BashiinfoJson(id, SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), "Android", MyApp.version)));
        recyKao.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                refreshLayout.setEnabled(topRowVerticalPosition >= 0 && recyclerView != null && !recyclerView.canScrollVertically(-1));

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        if (is) {
            ivSearch.setImageResource(R.mipmap.icon_shoucang);
        } else {
            ivSearch.setImageResource(R.mipmap.favorite);
        }
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_bashiinfo;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }




    @OnClick({R.id.back, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.iv_search:
                CollictionList(id);
                break;
        }
    }
    @Override
    protected void Errorcool(String msg) {
        super.showLoading(msg);
      finish();
        startActivity(PwdLoginActivity.class);
        ToastUtil.makeShortText(this,msg);
    }
    @Override
    protected void showLoading(String msg) {
        super.showLoading(msg);
        if(msg.equals("1")){
            ivSearch.setImageResource(R.mipmap.icon_shoucang);
            ToastUtil.makeShortText(this,"收藏成功");
        }else {
            ivSearch.setImageResource(R.mipmap.favorite);
            ToastUtil.makeShortText(this,"取消收藏成功");
        }

    }
}
