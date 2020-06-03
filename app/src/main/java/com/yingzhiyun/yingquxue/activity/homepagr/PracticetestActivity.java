package com.yingzhiyun.yingquxue.activity.homepagr;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Fragment.home.PracticeteseFragment;
import com.yingzhiyun.yingquxue.Fragment.tiku.TestPaperListFragment;
import com.yingzhiyun.yingquxue.Mvp.TestPaperListMvp;
import com.yingzhiyun.yingquxue.Mvp.TikuMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.BetDetailBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.FolderListOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.HomePagerJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.SelectJsonBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TeachingShaiJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.ZiyuanJsonBean;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.ChooseStateAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.base.adapter.NewsListadapter;
import com.yingzhiyun.yingquxue.base.adapter.ResherFragmentADapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.TestPaperListPresenter;
import com.yingzhiyun.yingquxue.presenter.TikuPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PracticetestActivity extends BaseFragment<TestPaperListMvp.TestPaperList_View, TestPaperListPresenter<TestPaperListMvp.TestPaperList_View>>
        implements TestPaperListMvp.TestPaperList_View {
    @BindView(R.id.tv_senior)
    TextView tvSenior;
    @BindView(R.id.tv_junior)
    TextView tvJunior;
    @BindView(R.id.text_stage)
    TextView textStage;
    @BindView(R.id.choose_stage)
    ImageView chooseStage;
    @BindView(R.id.score_tab)
    TabLayout scoreTab;
    @BindView(R.id.view)
    ViewPager view;
    private  int gradeid,type=1;
    private LayoutInflater mInflater;
    private ArrayList<SelectedOptionsBean.ResultBean> highsubject=new ArrayList<>();
    private ArrayList<SelectedOptionsBean.ResultBean> middlesubject=new ArrayList<>();
    private ArrayList<SelectedOptionsBean.ResultBean> highgrade=new ArrayList<>();
    private ArrayList<SelectedOptionsBean.ResultBean> middlegrade=new ArrayList<>();
    private ArrayList<String> strings=new ArrayList<>();
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private ResherFragmentADapter bookFrgmentAdapter;
    private NewsListadapter newsListadapter;
    private PopupWindow popupWindow1;
    private ChooseStateAdapter chooseStateAdapter;
    private ArrayList<FolderListOptionsBean.ResultBean> titles;


    @Override
    public int createLayoutId() {
        return R.layout.activity_practicetest;
    }

    @Override
    protected void initData()  {
        mInflater = getLayoutInflater();
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
        view.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(scoreTab));
        newsListadapter = new NewsListadapter(getChildFragmentManager(), strings, fragments);
        view.setAdapter(newsListadapter);
        ArrayList<String> jsonstrings = new ArrayList<>();
        jsonstrings.add("grade");
        jsonstrings.add("subject");
        presenter.getfilterItem(new Gson().toJson(new TeachingShaiJson(jsonstrings)));
        showSohwnpop();

//<font color='red' size='24'>你好</font>"

    }










    @OnClick({R.id.tv_senior, R.id.tv_junior, R.id.text_stage, R.id.choose_stage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_senior:
                if(type==1){
                    return;
                }
                tvSenior.setBackgroundResource(R.drawable.chooseleft);
                tvJunior.setBackgroundResource(R.drawable.righttuoyuan);
                tvSenior.setTextColor(Color.parseColor("#ffffff"));
                tvJunior.setTextColor(Color.parseColor("#1091E9"));
                scoreTab.removeAllTabs();
                fragments.clear();
                strings.clear();
                gradeid=highgrade.get(0).getId();

                for (int i = 0; i < highsubject.size(); i++) {
                    fragments.add(new PracticeteseFragment(highsubject.get(i).getId(),gradeid));
                    strings.add(highsubject.get(i).getName());
                }
                newsListadapter.notifyDataSetChanged();
                titles.clear();
                for (int i = 0; i <highgrade.size() ; i++) {
                    titles.add(new FolderListOptionsBean.ResultBean(highgrade.get(i).getId(),highgrade.get(i).getTitle()));
                }
                textStage.setText(highgrade.get(0).getTitle());
                type=1;
                chooseStateAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_junior:
                if(type==2){
                    return;
                }
                tvSenior.setBackgroundResource(R.drawable.lefttuoyuan);
                tvJunior.setBackgroundResource(R.drawable.chosseright);
                tvSenior.setTextColor(Color.parseColor("#1091E9"));
                tvJunior.setTextColor(Color.parseColor("#ffffff"));
                scoreTab.removeAllTabs();
                fragments.clear();
                strings.clear();
                textStage.setText(middlegrade.get(0).getTitle());
                gradeid=middlegrade.get(0).getId();
                for (int i = 0; i < middlesubject.size(); i++) {
                    fragments.add(new PracticeteseFragment(middlesubject.get(i).getId(),gradeid));
                    strings.add(middlesubject.get(i).getName());
                }
                newsListadapter.notifyDataSetChanged();
                titles.clear();
                for (int i = 0; i <middlegrade.size() ; i++) {
                    titles.add(new FolderListOptionsBean.ResultBean(middlegrade.get(i).getId(),middlegrade.get(i).getTitle()));
                }
                type = 2;

                break;
            case R.id.text_stage:
                chooseStage.setImageResource(R.mipmap.topdeatile);
                popupWindow1.showAsDropDown(textStage);
                break;
            case R.id.choose_stage:
                chooseStage.setImageResource(R.mipmap.topdeatile);
                popupWindow1.showAsDropDown(textStage);
                break;
        }
    }

    @Override
    public void setTestPaperList(TestPaperListBean testPaperList) {

    }

    @Override
    public void setfilterItem(TeachingShaixuanBean teachingShaixuanBean) {
        if(teachingShaixuanBean.getStatus()==200){
           highsubject.addAll(teachingShaixuanBean.getResult().getSubject().getHeightSchool().getDetail());
           middlesubject.addAll(teachingShaixuanBean.getResult().getSubject().getMiddleSchool().getDetail());
           highgrade.addAll(teachingShaixuanBean.getResult().getGrade().getHeightSchool().getDetail());
           middlegrade.addAll(teachingShaixuanBean.getResult().getGrade().getMiddleSchool().getDetail());
            gradeid=highgrade.get(0).getId();
            for (int i = 0; i <highsubject.size() ; i++) {
                fragments.add(new PracticeteseFragment(highsubject.get(i).getId(),gradeid));
                strings.add(highsubject.get(i).getName());
            }
            newsListadapter.notifyDataSetChanged();

            for (int i = 0; i <highgrade.size() ; i++) {
                titles.add(new FolderListOptionsBean.ResultBean(highgrade.get(i).getId(),highgrade.get(i).getTitle()));
            }
            textStage.setText(highgrade.get(0).getTitle());

            chooseStateAdapter.notifyDataSetChanged();
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

    public void showSohwnpop() {

        titles = new ArrayList<>();

        View view = mInflater.inflate(R.layout.pop_choosestate, null);
        RecyclerView recy_choose = view.findViewById(R.id.recy_choose);
        recy_choose.setLayoutManager(new LinearLayoutManager(context));
        chooseStateAdapter = new ChooseStateAdapter(titles);
        recy_choose.setAdapter(chooseStateAdapter);
        
        popupWindow1 = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = view.getMeasuredWidth();
        int popupHeight = view.getMeasuredHeight();
        int[] location = new int[2];
        // 允许点击外部消失
        popupWindow1.setBackgroundDrawable(new BitmapDrawable());
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.setFocusable(true);
        // 获得位置
        textStage.getLocationOnScreen(location);
        popupWindow1.setAnimationStyle(R.style.mypopwindow_anim_style);
//				popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);



        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                chooseStage.setImageResource(R.mipmap.blackdetail);
            }
        });
        chooseStateAdapter.OnsetOnClickListener(new ChooseStateAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(FolderListOptionsBean.ResultBean resultBean, int position) {
                popupWindow1.dismiss();
                textStage.setText(resultBean.getTitle());
                gradeid=resultBean.getId();
                scoreTab.removeAllTabs();
                fragments.clear();
                strings.clear();
                if(type==1){
                    for (int i = 0; i <highsubject.size() ; i++) {

                        fragments.add(new PracticeteseFragment(highsubject.get(i).getId(),gradeid));
                        strings.add(highsubject.get(i).getName());
                    }

                }else {
                    for (int i = 0; i <middlesubject.size() ; i++) {
                        fragments.add(new PracticeteseFragment(middlesubject.get(i).getId(),gradeid));
                        strings.add(middlesubject.get(i).getName());
                    }
                }
                newsListadapter.notifyDataSetChanged();
                chooseStage.setImageResource(R.mipmap.blackdetail);
            }
        });

    }
}
