package com.yingzhiyun.yingquxue.activity.tiku;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Fragment.tiku.TiRecordFragment;
import com.yingzhiyun.yingquxue.Mvp.TiRecordMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.KnowledgeJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TestPaperListjson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TestpagperinfoJson;
import com.yingzhiyun.yingquxue.OkBean.ZutijiluBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.PatrolGroupAdapter;
import com.yingzhiyun.yingquxue.adapter.TiRecordAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.base.adapter.ResherFragmentADapter;
import com.yingzhiyun.yingquxue.presenter.TiRecordPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TiRecordActivirty extends BaseActicity<TiRecordMvp.TiRecord_View, TiRecordPresenter<TiRecordMvp.TiRecord_View>>
        implements TiRecordMvp.TiRecord_View {

    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.tv_senior)
    TextView tvSenior;
    @BindView(R.id.tv_junior)
    TextView tvJunior;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.score_tab)
    TabLayout scoreTab;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.view)
    ViewPager view;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    private LayoutInflater mInflater;
    private int schooltype;
    private int subjectId;
    private PopupWindow popupWindow;
    private AllsubjectBean bean;
    private List<ZutijiluBean.ResultBean.DetailBean> detailBeans;
    private TiRecordAdapter tiRecordAdapter;
    private PatrolGroupAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<AllsubjectBean.ResultBean.DetailBean> highsubject;
    private ArrayList<AllsubjectBean.ResultBean.DetailBean> middlesubject;
    private ArrayList<AllsubjectBean.ResultBean.DetailBean> strings;
    private int page = 1;
    private ArrayList<Fragment> fragments;
    private ResherFragmentADapter bookFrgmentAdapter;

    @Override
    protected void initData() {
        mInflater = getLayoutInflater();
        detailBeans = new ArrayList<>();
        highsubject = new ArrayList<>();
        middlesubject = new ArrayList<>();
        strings=new ArrayList<>();
        fragments = new ArrayList<>();
        tiRecordAdapter = new TiRecordAdapter(detailBeans, this);
//        recyTirecord.setLinearLayout();
//        recyTirecord.setAdapter(tiRecordAdapter);
//        tiRecordAdapter.OnsetOnClickListener(new TiRecordAdapter.setOnClickListener() {
//            @Override
//            public void setOnClickListener(ZutijiluBean.ResultBean.DetailBean musicBean) {
//                mPresentser.getuserTestPaper(new Gson().toJson(new TestpagperinfoJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), musicBean.getId() + "", MyApp.version, "Android")));
//            }
//        });
//        recyTirecord.setPullRefreshEnable(false);
//        recyTirecord.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//
//            @Override
//            public void onLoadMore() {
//                page++;
//                getList();
//            }
//        });

        mPresentser.getTiRecordlist(new Gson().toJson(new KnowledgeJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), SharedPreferenceUtils.getsubject_id() + "", MyApp.version, "Android")));
        view.setOffscreenPageLimit(10);
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


        bookFrgmentAdapter = new ResherFragmentADapter(getSupportFragmentManager(), fragments, strings);
        view.setAdapter(bookFrgmentAdapter);


        view.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(scoreTab));


    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_record;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @Override
    protected TiRecordPresenter<TiRecordMvp.TiRecord_View> createPresenter() {
        return new TiRecordPresenter<>();
    }


    @Override
    public void setTiRecordlist(AllsubjectBean wronglist) {
        if(wronglist.getStatus()==200){
            bean = wronglist;
            for (int i = 0; i < wronglist.getResult().size(); i++) {
                if (wronglist.getResult().get(i).getName().equals("初中")) {
                    middlesubject.addAll(wronglist.getResult().get(i).getDetail());
                } else {
                    highsubject.addAll(wronglist.getResult().get(i).getDetail());
                }
            }


            for (int i = 0; i < highsubject.size(); i++) {
                fragments.add(new TiRecordFragment(highsubject.get(i).getSubjectId()));
                strings.add(highsubject.get(i));
            }
            bookFrgmentAdapter.notifyDataSetChanged();
        }else if(wronglist.getStatus()==511){
            ToastUtil.makeShortText(this,"身份信息过期请重新登录");
            finish();
            startActivity(PwdLoginActivity.class);
        }


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setjiLu(ZutijiluBean myTiBean) {
    }

    /**
     * 毫秒转分
     *
     * @param ms
     * @return
     */
    public static String msToM(int ms) {
        int seconds = ms / 1000;
        int minutes = seconds / 60;
        seconds = seconds % 60;

        String m = null;
        String s = null;

        if (minutes == 0 && seconds == 0)
            seconds = 1;

        if (minutes < 10)
            m = "0" + minutes;
        else
            m = "" + minutes;

        if (seconds < 10)
            s = "0" + seconds;
        else
            s = "" + seconds;

        return m + ":" + s;
    }

    @Override
    public void setuserTestPaper(ExamineBean examineBean) {
        if (examineBean.getStatus() == 200) {
            startActivity(new Intent(this, AnalysisActivity.class).putExtra("bean", examineBean).putExtra("type", "all"));
        }
    }


    @OnClick({R.id.finish})
    public void onViewClicked() {

        finish();


    }


    public void getList() {
        mPresentser.getjiLu(new Gson().toJson(new TestPaperListjson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), subjectId, page, MyApp.version, "Android")));
    }

    @OnClick({R.id.tv_senior, R.id.tv_junior})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_senior:
                tvSenior.setBackgroundResource(R.drawable.chooseleft);
                tvJunior.setBackgroundResource(R.drawable.righttuoyuan);
                tvSenior.setTextColor(Color.parseColor("#ffffff"));
                tvJunior.setTextColor(Color.parseColor("#1091E9"));
               strings.clear();
               fragments.clear();
                for (int i = 0; i < highsubject.size(); i++) {
                    fragments.add(new TiRecordFragment(highsubject.get(i).getSubjectId()));
                    strings.add(highsubject.get(i));
                }
                bookFrgmentAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_junior:
                tvSenior.setBackgroundResource(R.drawable.lefttuoyuan);
                tvJunior.setBackgroundResource(R.drawable.chosseright);
                tvSenior.setTextColor(Color.parseColor("#1091E9"));
                tvJunior.setTextColor(Color.parseColor("#ffffff"));
                strings.clear();
                fragments.clear();
                for (int i = 0; i < middlesubject.size(); i++) {
                    fragments.add(new TiRecordFragment(middlesubject.get(i).getSubjectId()));
                    strings.add(middlesubject.get(i));
                }
                bookFrgmentAdapter.notifyDataSetChanged();
                break;
        }
    }



}
