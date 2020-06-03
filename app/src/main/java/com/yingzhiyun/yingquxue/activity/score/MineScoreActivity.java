package com.yingzhiyun.yingquxue.activity.score;

import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Fragment.home.ChartModleFragment;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.StudentinfoBean;
import com.yingzhiyun.yingquxue.OkBean.TestBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.adapter.FrgmentAdapter;
import com.yingzhiyun.yingquxue.adapter.KaoshiAdapter;
import com.yingzhiyun.yingquxue.adapter.SelectAdapter;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartConfiger.AAChartModel;
import com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartConfiger.AAChartView;
import com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAChartConfiger.AAMoveOverEventMessageModel;
import com.yingzhiyun.yingquxue.units.NoScrollViewPager;
import com.yingzhiyun.yingquxue.units.ScreenUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineScoreActivity extends SimpleActivity implements AAChartView.AAChartViewCallBack {


    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.score_tab)
    TabLayout scoreTab;
    @BindView(R.id.navigation_RecyclerView)
    NoScrollViewPager viewpager;
    @BindView(R.id.kaoshi)
    TextView kaoshi;
    @BindView(R.id.recy_ziyuan)
    RecyclerView recyZiyuan;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.refresh_layout)
    NestedScrollView refreshLayout;
    private RecyclerView recyclerView;
    private SelectAdapter subjectAdapter;
    //x轴坐标对应的数据
    private List<String> xValue = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValue = new ArrayList<>();
    //折线对应的数据
    private Map<String, Integer> value = new HashMap<>();
    private PopupWindow popupWindow;
    private List<String> strings;
    private List<Fragment> fragments;
    //弹出PopupWindow时，背景变暗的动画
    private Animation animIn, animOut;
    private SelectAdapter selectAdapter;
    private KaoshiAdapter kaoshiAdapter;
    private AAChartModel aaChartModel;
    private StudentinfoBean bean;
    private StringBuilder stringBuilder;
    private TestBean testBean;

    @Override
    protected void initData() throws ParseException {
        toolTitle.setText("我的成绩");
//
        bean = (StudentinfoBean) getIntent().getExtras().getSerializable("bean");
        strings = new ArrayList<>();
        fragments = new ArrayList<>();
        strings.add("总成绩");
        strings.add("联考排名");
        strings.add("年级排名");
        strings.add("班级排名");


        for (int i = 0; i < 4; i++) {
            fragments.add(new ChartModleFragment(strings.get(i),bean));
        }
        scoreTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        scoreTab.setInlineLabel(true);


        scoreTab.setupWithViewPager(viewpager);


        FrgmentAdapter bookFrgmentAdapter = new FrgmentAdapter(getSupportFragmentManager(), fragments, strings);
        viewpager.setAdapter(bookFrgmentAdapter);

        viewpager.setOffscreenPageLimit(10);


        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(scoreTab));
//        selectAdapter = new SelectAdapter(this.resultBeans);
//        subjectAdapter = new SelectAdapter(resultsujbects);
//        initPopup();
//        for (int i = 0; i < 12; i++) {
//            xValue.add((i + 1) + "月");
//            value.put((i + 1) + "月", (int) (Math.random() * 181 + 60));//60--240
//        }
////
//        for (int i = 0; i < 6; i++) {
//            yValue.add(i * 60);
//        }
//
//        xValue.add("八市联考");
//
//        value.put("八市联考", 300);
//        AAChartModel aaChartModel = new AAChartModel()
//                .chartType(AAChartType.Line)
//                .xAxisVisible(true)
//                .yAxisVisible(true)
//                .title("")
//                .yAxisTitle("")
//                .backgroundColor("#ffffff")
//                .yAxisLineWidth((float) 1)
//                .dataLabelsEnabled(true)
//                .colorsTheme(new Object[]{"#7AC5FF"})
//                .series(new AASeriesElement[]{
//                        new AASeriesElement()
//                                .name("分数")
//                                .data(new Object[]{655, 555, 750, 66, 2.0,20}),
//
//                })
//                .categories(new String[]{"两市联考","三市联考","死市联考","市联考","两市联考","laji"});
//        AAChartView.callBack=this;
//        aaChartModel.markerSymbolStyle(AAChartSymbolStyleType.BorderBlank);
//        AAChartView.aa_drawChartWithChartModel(aaChartModel);



        recyZiyuan.setLayoutManager(new LinearLayoutManager(this));
        kaoshiAdapter = new KaoshiAdapter(bean.getBatchList(), this, bean);
        recyZiyuan.setAdapter(kaoshiAdapter);
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_myscore;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
//            case R.id.linear_grade:
//                recyclerView.setLayoutManager(new LinearLayoutManager(this));
//                recyclerView.setAdapter(selectAdapter);
//                resultBeans.clear();
////                resultBeans.addAll(result.get(0));
//                selectAdapter.notifyDataSetChanged();
//                textGrade.setTextColor(getResources().getColor(R.color.mainColor));
//                chooseGrade.setImageResource(R.mipmap.topdeatile);
//                if (popupWindow.isShowing()) {
//                    popupWindow.dismiss();
//                } else {
//                    popupWindow.showAsDropDown(findViewById(R.id.main_div_line));
//                    popupWindow.setAnimationStyle(-1);
//                    //背景变暗
////                    mainDivLine.startAnimation(animIn);
////                    mainDivLine.setVisibility(View.VISIBLE);
//                }
//                break;
//            case R.id.linear_subject:
//                recyclerView.setLayoutManager(new LinearLayoutManager(this));
//                recyclerView.setAdapter(subjectAdapter);
//                resultsujbects.clear();
//                resultsujbects.add(new SelectedOptionsBean.ResultBean(0, "总分对比"));
//                resultsujbects.add(new SelectedOptionsBean.ResultBean(0, "名次对比"));
//                Log.d("moxun", "onViewClicked: " + resultsujbects);
//                subjectAdapter.notifyDataSetChanged();
//                textSubject.setTextColor(getResources().getColor(R.color.mainColor));
//                chooseSubject.setImageResource(R.mipmap.topdeatile);
//                if (popupWindow.isShowing()) {
//                    popupWindow.dismiss();
//                } else {
//                    popupWindow.showAsDropDown(findViewById(R.id.main_div_line));
//                    popupWindow.setAnimationStyle(-1);
//                    //背景变暗
////                    mainDivLine.startAnimation(animIn);
////                    mainDivLine.setVisibility(View.VISIBLE);
//                }
//                break;
        }
    }

//    private void initPopup() {
//        popupWindow = new PopupWindow(this);
//        View view = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);
//        recyclerView = view.findViewById(R.id.pop_listview_left);
//        popupWindow.setContentView(view);
//        popupWindow.setBackgroundDrawable(new PaintDrawable());
//        popupWindow.setFocusable(true);
//
////        popupWindow.setHeight(ScreenUtils.getScreenH(this) * 2 / 3);
//        popupWindow.setWidth(ScreenUtils.getScreenW(this));
//
//        selectAdapter.OnsetOnClickListener(new SelectAdapter.setOnClickListener() {
//            @Override
//            public void setOnClickListener(SelectedOptionsBean.ResultBean resultBean) {
//                popupWindow.dismiss();
//                textGrade.setText(resultBean.getTitle());
//
//            }
//        });
//        subjectAdapter.OnsetOnClickListener(new SelectAdapter.setOnClickListener() {
//            @Override
//            public void setOnClickListener(SelectedOptionsBean.ResultBean resultBean) {
//                popupWindow.dismiss();
//                textSubject.setText(resultBean.getTitle());
//
//            }
//        });
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
////                mainDivLine.startAnimation(animOut);
////                mainDivLine.setVisibility(View.GONE);
//                textGrade.setTextColor(getResources().getColor(R.color.black));
//                textSubject.setTextColor(getResources().getColor(R.color.black));
//                chooseGrade.setImageResource(R.mipmap.blackdetail);
//                chooseSubject.setImageResource(R.mipmap.blackdetail);
//            }
//        });
//
//
//    }


    @Override
    public void chartViewDidFinishedLoad(AAChartView aaChartView) {

    }

    @Override
    public void chartViewMoveOverEventMessage(AAChartView aaChartView, AAMoveOverEventMessageModel messageModel) {

    }


}
