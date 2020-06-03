package com.yingzhiyun.yingquxue.activity.homepagr;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.SelectedOptionsMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.FolderListOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.ZiyuanJsonBean;
import com.yingzhiyun.yingquxue.OkBean.ModuleBean;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.OkBean.skillCourseListBeam;
import com.yingzhiyun.yingquxue.OkBean.skillTypeListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.MoreSelectionAdapter;
import com.yingzhiyun.yingquxue.adapter.MoreVideoAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.SelectedOptionsPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class MoreVideoActivity  extends BaseActicity<
        SelectedOptionsMvp.SelectedOptions_View, SelectedOptionsPresenter<SelectedOptionsMvp.SelectedOptions_View>> implements SelectedOptionsMvp.SelectedOptions_View{
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.recy_live)
    RecyclerView recyLive;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private ArrayList<ZiyuanBean.ResultBean> resultBeans;
    private MoreVideoAdapter moreSelectionAdapter;
    private int moduleBean;
    private String name;

    @Override
    public void setSelectedOptions(SelectedOptionsBean selectedOptions) {

    }

    @Override
    public void setZiyuan(ZiyuanBean ziyuan) {
        if (ziyuan.getStatus() == 200) {
            linearModle.setVisibility(View.GONE);
            recyLive.setVisibility(View.VISIBLE);
            resultBeans.addAll(ziyuan.getResult());
            moreSelectionAdapter.notifyDataSetChanged();
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
        moduleBean = getIntent().getIntExtra("bean",0);
        name = getIntent().getStringExtra("name");

        resultBeans = new ArrayList<>();
        toolTitle.setText(name);
        moreSelectionAdapter = new MoreVideoAdapter(resultBeans, this);
        recyLive.setLayoutManager(new LinearLayoutManager(this));
        recyLive.setAdapter(moreSelectionAdapter);
        mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(1, moduleBean, SharedPreferenceUtils.getUserid(), 0,0,SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_mylive;
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
