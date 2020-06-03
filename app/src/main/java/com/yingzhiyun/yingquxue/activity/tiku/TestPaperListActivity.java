package com.yingzhiyun.yingquxue.activity.tiku;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Fragment.tiku.TestPaperListFragment;
import com.yingzhiyun.yingquxue.Mvp.TestPaperListMvp;
import com.yingzhiyun.yingquxue.OkBean.BetDetailBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TeachingShaiJson;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.TestPagperAapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.base.adapter.NewsListadapter;
import com.yingzhiyun.yingquxue.base.adapter.ResherFragmentADapter;
import com.yingzhiyun.yingquxue.presenter.TestPaperListPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestPaperListActivity extends BaseActicity<TestPaperListMvp.TestPaperList_View, TestPaperListPresenter<TestPaperListMvp.TestPaperList_View>>
        implements TestPaperListMvp.TestPaperList_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.score_tab)
    TabLayout scoreTab;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.view)
    ViewPager view;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;


    private ArrayList<TestPaperListBean.ResultBean> resultBeans;
    private TestPagperAapter testPagperAapter;
    private int id;
    private int page = 1;
    private int mNextRequestPage;
    private TeachingShaixuanBean.ResultBean.SubjectBean.HeightSchoolBean heightSchool;
    private TeachingShaixuanBean.ResultBean.SubjectBean.MiddleSchoolBean middleSchool;
    private int type;
    private ArrayList<String> strings;
    private ArrayList<Fragment> fragments;
    private NewsListadapter newsListadapter;

    @Override
    public void setTestPaperList(TestPaperListBean testPaperList) {
//        if(testPaperList.getStatus()==200){
//            if (testPaperList.getResult().size() > 0) {
//                recyTsetpagper.setVisibility(View.VISIBLE);
//                linearModle.setVisibility(View.GONE);
//                resultBeans.addAll(testPaperList.getResult());
//                testPagperAapter.notifyDataSetChanged();
//            }
//            testPagperAapter.loadMoreComplete();
//        }else if(testPaperList.getStatus()==511){
//            finish();
//            ToastUtil.makeShortText(this,"账号已在别处登录");
//            startActivity(PwdLoginActivity.class);
//        }

    }

    @Override
    public void setfilterItem(TeachingShaixuanBean teachingShaixuanBean) {
        if(teachingShaixuanBean.getStatus()==200){
            heightSchool = teachingShaixuanBean.getResult().getGrade().getHeightSchool();
            middleSchool = teachingShaixuanBean.getResult().getGrade().getMiddleSchool();
            if(type==1){
                for (int i = 0; i < heightSchool.getDetail().size(); i++) {
                    fragments.add(new TestPaperListFragment(id,heightSchool.getDetail().get(i).getId()));
                    strings.add(heightSchool.getDetail().get(i).getTitle());
                }
            }else {
                for (int i = 0; i < middleSchool.getDetail().size(); i++) {
                    fragments.add(new TestPaperListFragment(id,middleSchool.getDetail().get(i).getId()));
                    strings.add(middleSchool.getDetail().get(i).getTitle());
                }
            }
            newsListadapter.notifyDataSetChanged();
        }

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
    protected void initData() {
        id = getIntent().getIntExtra("id", 0);
        type = getIntent().getIntExtra("type", 0);
        SharedPreferenceUtils.setsubject_id(id);
        strings = new ArrayList<>();
        fragments = new ArrayList<>();


        view.setOffscreenPageLimit(3);
        scoreTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        scoreTab.setInlineLabel(true);
        scoreTab.setupWithViewPager(view);
        view.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(scoreTab));
        newsListadapter = new NewsListadapter(getSupportFragmentManager(), strings, fragments);
        view.setAdapter(newsListadapter);
        ArrayList<String> jsonstrings = new ArrayList<>();
        jsonstrings.add("grade");
        mPresentser.getfilterItem(new Gson().toJson(new TeachingShaiJson(jsonstrings)));


    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_testpaperlist;
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
