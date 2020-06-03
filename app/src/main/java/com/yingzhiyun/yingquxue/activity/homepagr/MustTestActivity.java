package com.yingzhiyun.yingquxue.activity.homepagr;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Fragment.home.CourseListFragment;
import com.yingzhiyun.yingquxue.Fragment.home.MustTestFragment;
import com.yingzhiyun.yingquxue.Fragment.home.PracticeteseFragment;
import com.yingzhiyun.yingquxue.Mvp.TestPaperListMvp;
import com.yingzhiyun.yingquxue.Mvp.TikuMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.BetDetailBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.FolderListOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.HomePagerJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.ShaixuanJson;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.base.adapter.NewsListadapter;
import com.yingzhiyun.yingquxue.base.adapter.ResherFragmentADapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.TestPaperListPresenter;
import com.yingzhiyun.yingquxue.presenter.TikuPresenter;
import com.yingzhiyun.yingquxue.units.DialogUtil;
import com.yingzhiyun.yingquxue.units.PickerView;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MustTestActivity extends BaseActicity<TestPaperListMvp.TestPaperList_View, TestPaperListPresenter<TestPaperListMvp.TestPaperList_View>>
        implements TestPaperListMvp.TestPaperList_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.text_stage)
    TextView textStage;
    @BindView(R.id.choose_stage)
    ImageView chooseStage;
    @BindView(R.id.score_tab)
    TabLayout scoreTab;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.view)
    ViewPager view;
    private ArrayList<SelectedOptionsBean.ResultBean> highsubject = new ArrayList<>();
    private ArrayList<SelectedOptionsBean.ResultBean> middlesubject = new ArrayList<>();
    private ArrayList<SelectedOptionsBean.ResultBean> highgrade = new ArrayList<>();
    private ArrayList<SelectedOptionsBean.ResultBean> middlegrade = new ArrayList<>();
    private int subject, gradeid;
    private ArrayList<String> grade;
    private ArrayList<Fragment> fragments;
    private NewsListadapter bookFrgmentAdapter;
    private String grade1 = "高一";
    private LayoutInflater baseInflater;
    private String gradetitle = "10";
    private int id;
    private ArrayList<String> strings = new ArrayList<>();
    private PickerView pickerView;
    private Dialog dialog;
    private String gradetype;

    @Override
    protected void initData() throws ParseException {
        baseInflater = LayoutInflater.from(this);
        id = getIntent().getIntExtra("id", 0);
        gradetype = getIntent().getStringExtra("gradetype");
        if(gradetype.equals("1")){
            textStage.setVisibility(View.GONE);
            chooseStage.setVisibility(View.GONE);
        }
        dialognianji();
        grade = new ArrayList<String>();


        fragments = new ArrayList<>();

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


        bookFrgmentAdapter = new NewsListadapter(getSupportFragmentManager(), strings, fragments);
        view.setAdapter(bookFrgmentAdapter);


        view.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(scoreTab));
        ArrayList<String> jsonstrings = new ArrayList<>();
        jsonstrings.add("grade");
        jsonstrings.add("subject");

      mPresentser.getfilterItem(new Gson().toJson(new ShaixuanJson(jsonstrings,1)));
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_musttest;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    protected void dialognianji() {

        View dialogView = baseInflater.inflate(R.layout.dialog_nianji, null);
        dialog = DialogUtil.showDialogCenter(this, dialogView, 300);
        TextView tvTitle = (TextView) dialogView.findViewById(R.id.title);

        TextView dialog_left_btn = (TextView) dialogView.findViewById(R.id.dialog_left_btn);
        TextView dialog_right_btn = (TextView) dialogView.findViewById(R.id.dialog_right_btn);
        pickerView = dialogView.findViewById(R.id.picker_view);


        pickerView.setOnSelectListener(new PickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {

                grade1 = text;
            }
        });

        dialog_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog_right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (grade1 != null) {
                    if (grade1.equals("七年级")) {
                        gradetitle = "7";
                    } else if (grade1.equals("八年级")) {
                        gradetitle = "8";
                    } else if (grade1.equals("九年级")) {
                        gradetitle = "9";
                    } else if (grade1.equals("高一")) {
                        gradetitle = "10";
                    } else if (grade1.equals("高二")) {
                        gradetitle = "11";
                    } else {
                        gradetitle = "12";
                    }
                }
                fragments.clear();
                strings.clear();

                for (int i = 0; i < highsubject.size(); i++) {
                    fragments.add(new MustTestFragment(highsubject.get(i).getId(), gradetitle, id));
                    strings.add(highsubject.get(i).getName());
                }
                bookFrgmentAdapter.notifyDataSetChanged();
                textStage.setText(grade1);

            }
        });

    }

    @OnClick({R.id.finish, R.id.text_stage, R.id.choose_stage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.text_stage:

                dialog.show();
                break;
            case R.id.choose_stage:
                dialog.show();
                break;
        }
    }


    @Override
    public void setTestPaperList(TestPaperListBean testPaperList) {

    }

    @Override
    public void setfilterItem(TeachingShaixuanBean teachingShaixuanBean) {
        if (teachingShaixuanBean.getStatus() == 200) {
            highsubject.addAll(teachingShaixuanBean.getResult().getSubject().getHeightSchool().getDetail());
            middlesubject.addAll(teachingShaixuanBean.getResult().getSubject().getMiddleSchool().getDetail());
            highgrade.addAll(teachingShaixuanBean.getResult().getGrade().getHeightSchool().getDetail());
            middlegrade.addAll(teachingShaixuanBean.getResult().getGrade().getMiddleSchool().getDetail());
            gradetitle = highgrade.get(0).getId()+"";

            if(gradetype.equals("1")){

                for (int i = 0; i < middlesubject.size(); i++) {
                    fragments.add(new MustTestFragment(middlesubject.get(i).getId(), "9", id));
                    strings.add(middlesubject.get(i).getName());
                }
            }else {
                for (int i = 0; i < highsubject.size(); i++) {
                    fragments.add(new MustTestFragment(highsubject.get(i).getId(), gradetitle, id));
                    strings.add(highsubject.get(i).getName());
                }
            }

            bookFrgmentAdapter.notifyDataSetChanged();

            for (int i = 0; i < highgrade.size(); i++) {
                grade.add(highgrade.get(i).getTitle());
            }
            textStage.setText(highgrade.get(0).getTitle());
            pickerView.setData(grade);

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
