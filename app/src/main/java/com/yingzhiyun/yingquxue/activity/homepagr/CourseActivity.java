package com.yingzhiyun.yingquxue.activity.homepagr;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Fragment.home.CourseListFragment;
import com.yingzhiyun.yingquxue.Fragment.score.PointsFragment;
import com.yingzhiyun.yingquxue.Fragment.score.TotalpointsFragment;
import com.yingzhiyun.yingquxue.Mvp.CourseMvp;
import com.yingzhiyun.yingquxue.Mvp.SelectedOptionsMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ClassBeaginBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.OkBean.CourseinfoBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.HomePagerJson;
import com.yingzhiyun.yingquxue.OkBean.MineCourseBean;
import com.yingzhiyun.yingquxue.OkBean.MineTeacherBean;
import com.yingzhiyun.yingquxue.OkBean.PlayVideoBean;
import com.yingzhiyun.yingquxue.OkBean.TeacherinfoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseSearchActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.FrgmentAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.base.adapter.ResherFragmentADapter;

import com.yingzhiyun.yingquxue.presenter.CoursePresenter;
import com.yingzhiyun.yingquxue.units.DialogUtil;
import com.yingzhiyun.yingquxue.units.PickerView;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.yingzhiyun.yingquxue.units.VerticalSwipeRefreshLayout;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseActivity extends BaseActicity<CourseMvp.Course_View, CoursePresenter<CourseMvp.Course_View>> implements CourseMvp.Course_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.text_stage)
    TextView textStage;
    @BindView(R.id.choose_stage)
    ImageView chooseStage;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.score_tab)
    TabLayout tabWeChat;
    @BindView(R.id.view)
    ViewPager viewpager;

    private ArrayList<String> strings;
    private ArrayList<Fragment> fragments;
    private List<String> grade;
    private String grade1;
    private LayoutInflater baseInflater;
    private String gradetitle="7";
    private ArrayList<AllsubjectBean.ResultBean.DetailBean> chuZhong;
    private ArrayList<AllsubjectBean.ResultBean.DetailBean> gaoZhong;
    private ResherFragmentADapter bookFrgmentAdapter;
    private List<AllsubjectBean.ResultBean> result;
    private ArrayList<AllsubjectBean.ResultBean.DetailBean> highsubject=new ArrayList<>();
    private ArrayList<AllsubjectBean.ResultBean.DetailBean> middlesubject=new ArrayList<>();
    private boolean isVip;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initData() throws ParseException {
        baseInflater = LayoutInflater.from(this);
        chuZhong = new ArrayList<>();
        gaoZhong = new ArrayList<>();
        mPresentser.getAllSubject(new Gson().toJson(new HomePagerJson(SharedPreferenceUtils.getUserid()+"", SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
        grade = new ArrayList<String>();
        grade.add("高一");
        grade.add("高二");
        grade.add("高三");
        grade.add("七年级");
        grade.add("八年级");
        grade.add("九年级");
        fragments = new ArrayList<>();
        textStage.setText("高三");
        isVip = getIntent().getBooleanExtra("isVip", false);
        if(isVip){
            toolTitle.setText("VIP视频课程");
            ivSearch.setVisibility(View.GONE);
        }
        viewpager.setOffscreenPageLimit(10);
        tabWeChat.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        tabWeChat.setInlineLabel(true);


        tabWeChat.setupWithViewPager(viewpager);


        bookFrgmentAdapter = new ResherFragmentADapter(getSupportFragmentManager(), fragments, chuZhong);
        viewpager.setAdapter(bookFrgmentAdapter);


        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabWeChat));

    }

    protected Dialog dialognianji() {

        View dialogView = baseInflater.inflate(R.layout.dialog_nianji, null);
        final Dialog dialog = DialogUtil.showDialogCenter(this, dialogView, 300);
        TextView tvTitle = (TextView) dialogView.findViewById(R.id.title);

        TextView dialog_left_btn = (TextView) dialogView.findViewById(R.id.dialog_left_btn);
        TextView dialog_right_btn = (TextView) dialogView.findViewById(R.id.dialog_right_btn);
        PickerView pickerView = dialogView.findViewById(R.id.picker_view);

        pickerView.setData(grade);

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
                chuZhong.clear();
                fragments.clear();
                if (gradetitle.equals("7") || gradetitle.equals("8") || gradetitle.equals("9")) {
                    chuZhong.addAll(middlesubject);
                    for (int i = 0; i < middlesubject.size(); i++) {
                        fragments.add(new CourseListFragment(middlesubject.get(i).getSubjectId(),gradetitle,isVip));
                    }
                    bookFrgmentAdapter.notifyDataSetChanged();
                } else {
                    chuZhong.addAll(highsubject);
                    for (int i = 0; i <highsubject.size(); i++) {
                        fragments.add(new CourseListFragment(highsubject.get(i).getSubjectId(),gradetitle,isVip));
                    }
                    bookFrgmentAdapter.notifyDataSetChanged();
                }
                textStage.setText(grade1);
            }
        });
        return dialog;
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_course;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.finish, R.id.text_stage, R.id.choose_stage, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.text_stage:
                dialognianji().show();
                break;
            case R.id.choose_stage:
                dialognianji().show();
                break;
            case R.id.iv_search:
                startActivity(CourseSearchActivity.class);
                break;
        }
    }

    @Override
    public void setCourseList(CourseBean courseList) {

    }

    @Override
    public void setAllSubject(AllsubjectBean allSubject) {
        if (allSubject.getStatus() == 200) {
            result = allSubject.getResult();
            for (int i = 0; i < allSubject.getResult().size(); i++) {
                if (allSubject.getResult().get(i).getName().equals("初中")) {
                    middlesubject.addAll(allSubject.getResult().get(i).getDetail());
                } else {
                    highsubject.addAll(allSubject.getResult().get(i).getDetail());
                }
            }
            chuZhong.addAll(highsubject);

            for (int i = 0; i <highsubject.size(); i++) {
                fragments.add(new CourseListFragment(highsubject.get(i).getSubjectId(),"12",isVip));
            }
            bookFrgmentAdapter.notifyDataSetChanged();
        }else if(allSubject.getStatus()==511){
            finish();
            ToastUtil.makeShortText(this,allSubject.getHint());
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    public void setcourseInfo(CourseinfoBean courseinfoBean) {

    }

    @Override
    public void setcourseSignUp(CollectionTiBean collectionTiBean) {

    }

    @Override
    public void setTeacherinfo(TeacherinfoBean teacherinfo) {

    }

    @Override
    public void setfollowTeacher(CollectionTiBean collectionTiBean) {

    }

    @Override
    public void setclassBegins(ClassBeaginBean classBeaginBean) {

    }

    @Override
    public void setmyCourse(MineCourseBean courseBean) {

    }

    @Override
    public void setmyFollowTeacher(MineTeacherBean mineTeacherBean) {

    }

    @Override
    public void setPlayVideo(PlayVideoBean playVideo) {

    }

    @Override
    protected CoursePresenter<CourseMvp.Course_View> createPresenter() {
        return new CoursePresenter<>();
    }
}
