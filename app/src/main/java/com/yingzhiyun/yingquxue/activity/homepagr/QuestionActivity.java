package com.yingzhiyun.yingquxue.activity.homepagr;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Fragment.home.QuestionFragment;
import com.yingzhiyun.yingquxue.Fragment.tiku.TestPaperListFragment;
import com.yingzhiyun.yingquxue.Fragment.tiku.TiRecordFragment;
import com.yingzhiyun.yingquxue.Mvp.TestPaperListMvp;
import com.yingzhiyun.yingquxue.OkBean.BetDetailBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TeachingShaiJson;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.base.adapter.NewsListadapter;
import com.yingzhiyun.yingquxue.presenter.TestPaperListPresenter;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionActivity extends BaseActicity<TestPaperListMvp.TestPaperList_View, TestPaperListPresenter<TestPaperListMvp.TestPaperList_View>>
        implements TestPaperListMvp.TestPaperList_View {
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.tv_senior)
    TextView tvSenior;
    @BindView(R.id.tv_junior)
    TextView tvJunior;
    @BindView(R.id.score_tab)
    TabLayout scoreTab;
    @BindView(R.id.view)
    ViewPager view;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    private ArrayList<String> strings;
    private ArrayList<Fragment> fragments;
    private NewsListadapter newsListadapter;
    private TeachingShaixuanBean.ResultBean.SubjectBean.HeightSchoolBean heightSchool;
    private TeachingShaixuanBean.ResultBean.SubjectBean.MiddleSchoolBean middleSchool;
    private HomePagerBean.ResultBean.MenuBean moduleBean;
    private int p=0;
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("================", "onRestart: ");
    }
    @Override
    protected void initData() throws ParseException {
        strings = new ArrayList<>();
        fragments = new ArrayList<>();
        moduleBean = (HomePagerBean.ResultBean.MenuBean) getIntent().getSerializableExtra("bean");

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
        return R.layout.activity_question;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.back, R.id.tv_senior, R.id.tv_junior})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_senior:
                if(p==0){
                    return;
                }
                tvSenior.setBackgroundResource(R.drawable.chooseleft);
                tvJunior.setBackgroundResource(R.drawable.righttuoyuan);
                tvSenior.setTextColor(Color.parseColor("#ffffff"));
                tvJunior.setTextColor(Color.parseColor("#1091E9"));
                strings.clear();
                fragments.clear();
                for (int i = 0; i < heightSchool.getDetail().size(); i++) {
                    fragments.add(new QuestionFragment(moduleBean.getId(),heightSchool.getDetail().get(i).getId()));
                    strings.add(heightSchool.getDetail().get(i).getTitle());
                }
                newsListadapter.notifyDataSetChanged();
                p=0;
                break;
            case R.id.tv_junior:
                if(p==1){
                    return;
                }
                tvSenior.setBackgroundResource(R.drawable.lefttuoyuan);
                tvJunior.setBackgroundResource(R.drawable.chosseright);
                tvSenior.setTextColor(Color.parseColor("#1091E9"));
                tvJunior.setTextColor(Color.parseColor("#ffffff"));
                strings.clear();
                fragments.clear();
                for (int i = 0; i < middleSchool.getDetail().size(); i++) {
                    fragments.add(new QuestionFragment(moduleBean.getId(),middleSchool.getDetail().get(i).getId()));
                    strings.add(middleSchool.getDetail().get(i).getTitle());
                }
                newsListadapter.notifyDataSetChanged();
                p=1;
                break;
        }
    }

    @Override
    public void setTestPaperList(TestPaperListBean testPaperList) {

    }

    @Override
    public void setfilterItem(TeachingShaixuanBean teachingShaixuanBean) {
        if(teachingShaixuanBean.getStatus()==200){
            heightSchool = teachingShaixuanBean.getResult().getGrade().getHeightSchool();
            middleSchool = teachingShaixuanBean.getResult().getGrade().getMiddleSchool();
            for (int i = 0; i < heightSchool.getDetail().size(); i++) {
                fragments.add(new QuestionFragment(moduleBean.getId(),heightSchool.getDetail().get(i).getId()));
                strings.add(heightSchool.getDetail().get(i).getTitle());
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
}
