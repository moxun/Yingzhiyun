package com.yingzhiyun.yingquxue.activity.homepagr;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.SelectedOptionsMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.FolderListOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.CoursewareJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.ZiyuanJsonBean;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.OkBean.skillCourseListBeam;
import com.yingzhiyun.yingquxue.OkBean.skillTypeListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.MoreSelectionAdapter;
import com.yingzhiyun.yingquxue.adapter.MoreVideoAdapter;
import com.yingzhiyun.yingquxue.adapter.ZiyuanAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.SelectedOptionsPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.StringUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnceSearchActivity extends BaseActicity<SelectedOptionsMvp.SelectedOptions_View, SelectedOptionsPresenter<SelectedOptionsMvp.SelectedOptions_View>> implements SelectedOptionsMvp.SelectedOptions_View {


    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_search)
    EditText tvSearch;
    @BindView(R.id.serach_Lin)
    LinearLayout serachLin;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.deleteall)
    ImageView deleteall;
    @BindView(R.id.recy_search)
    RecyclerView recyZiyuan;
    @BindView(R.id.rela_history)
    RelativeLayout relaHistory;
    @BindView(R.id.recy_content)
    PullLoadMoreRecyclerView recyContent;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.jilu)
    TextView jilu;
    private int page;
    private int id;
    private ArrayList<ZiyuanBean.ResultBean> resultBeans1;
    private ZiyuanAdapter ziyuanAdapter;
    private ArrayList<ZiyuanBean.ResultBean> resultBeans;
    private MoreSelectionAdapter moreSelectionAdapter;
    private String type;
    private MoreVideoAdapter moreVideoAdapter;
    private ArrayList<ZiyuanBean.ResultBean> courseList;

    @Override
    protected void initData() throws ParseException {
        id = getIntent().getExtras().getInt("id");
        type = getIntent().getExtras().getString("type");


        resultBeans1 = new ArrayList<>();
        resultBeans = new ArrayList<>();
        courseList = new ArrayList<>();
        switch (type) {
            case "zhuanji"://更多专辑的搜索
                moreSelectionAdapter = new MoreSelectionAdapter(this.resultBeans, this);
                recyZiyuan.setLayoutManager(new GridLayoutManager(this, 2));
                recyZiyuan.setAdapter(moreSelectionAdapter);
                break;
            case "ziyuan":
                ziyuanAdapter = new ZiyuanAdapter(resultBeans1, this);
                recyZiyuan.setLayoutManager(new LinearLayoutManager(this));
                recyZiyuan.setAdapter(ziyuanAdapter);
                break;
            case "classify":
                ziyuanAdapter = new ZiyuanAdapter(resultBeans1, this);
                recyZiyuan.setLayoutManager(new LinearLayoutManager(this));
                recyZiyuan.setAdapter(ziyuanAdapter);
                break;
            case "course":
                moreVideoAdapter = new MoreVideoAdapter(courseList, this);
                recyZiyuan.setLayoutManager(new LinearLayoutManager(this));
                recyZiyuan.setAdapter(moreVideoAdapter);
                break;
        }

        jilu.setVisibility(View.GONE);
        deleteall.setVisibility(View.GONE);




        tvSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
//如果actionId是搜索的id，则进行下一步的操作
                    serarch();
                }
                return false; }
        });
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    @Override
    public void setSelectedOptions(SelectedOptionsBean selectedOptions) {

    }

    @Override
    public void setZiyuan(ZiyuanBean ziyuan) {
        resultBeans1.clear();
        resultBeans.clear();

        if (ziyuan.getResult().size() > 0) {
            recyZiyuan.setVisibility(View.VISIBLE);

            switch (type) {
                case "zhuanji"://更多专辑的搜索
                    resultBeans.addAll(ziyuan.getResult());
                    moreSelectionAdapter.notifyDataSetChanged();
                    break;
                case "ziyuan":
                    resultBeans1.addAll(ziyuan.getResult());
                    ziyuanAdapter.notifyDataSetChanged();
                    break;
                case "classify":
                    resultBeans1.addAll(ziyuan.getResult());
                    ziyuanAdapter.notifyDataSetChanged();
                    break;
                case "course":
                    courseList.addAll(ziyuan.getResult());
                    moreVideoAdapter.notifyDataSetChanged();
                    break;
            }
        } else {
            ToastUtil.makeShortText(this,"暂无搜索内容");
            recyZiyuan.setVisibility(View.GONE);
        }
    }

    @Override
    public void setCoursewareList(ZiyuanBean coursewareList) {

        if (coursewareList.getStatus() == 200) {
            if (coursewareList.getResult().size() != 0) {

                resultBeans1.addAll(coursewareList.getResult());
                ziyuanAdapter.notifyDataSetChanged();
            } else {
                ToastUtil.makeShortText(this,"暂无搜索内容");
            }

        }
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

    private void serarch() {
        page = 1;
        String search = tvSearch.getText().toString().trim();
        if (StringUtils.isEmpty(search)) {
            ToastUtil.makeLongText(this, "请输入搜索内容");
            return;
        }
        if(type.equals("classify")){
            mPresentser.getCoursewareList(new Gson().toJson(new CoursewareJson(MyApp.version,"Android",1, 0, 1, SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(),search)));
        }else {
            mPresentser.getZiyuan(new Gson().toJson(new ZiyuanJsonBean(page, id, SharedPreferenceUtils.getUserid(), 0, 0, SharedPreferenceUtils.getToken(),search, MyApp.version,"Android")));
        }



    }

    @OnClick({R.id.finish, R.id.iv_search, R.id.deleteall, R.id.search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.iv_search:
                serarch();
                break;

            case R.id.search:
                serarch();
                break;
        }
    }


}
