package com.yingzhiyun.yingquxue.activity.homepagr.course;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Fragment.home.CourseListFragment;
import com.yingzhiyun.yingquxue.Fragment.home.TeacherInfoFragment;
import com.yingzhiyun.yingquxue.Fragment.zhibo.TacherevaluateFragment;
import com.yingzhiyun.yingquxue.Mvp.CourseMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ClassBeaginBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.OkBean.CourseinfoBean;
import com.yingzhiyun.yingquxue.OkBean.MineCourseBean;
import com.yingzhiyun.yingquxue.OkBean.MineTeacherBean;
import com.yingzhiyun.yingquxue.OkBean.PlayVideoBean;
import com.yingzhiyun.yingquxue.OkBean.TeacherinfoBean;
import com.yingzhiyun.yingquxue.OkBean.TeacherinfoJson;
import com.yingzhiyun.yingquxue.OkBean.localbean.FollowTeacherJson;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.FrgmentAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.CoursePresenter;
import com.yingzhiyun.yingquxue.units.NeViewpager;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeacherinfoActivity  extends BaseActicity<CourseMvp.Course_View, CoursePresenter<CourseMvp.Course_View>> implements CourseMvp.Course_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.teacher_name)
    TextView teacherName;
    @BindView(R.id.follow)
    TextView follow;
    @BindView(R.id.score_tab)
    TabLayout tabWeChat;
    @BindView(R.id.view)
    ViewPager viewpager;
    private ArrayList<String> strings;
    private ArrayList<Fragment> fragments;

    private LayoutInflater baseInflater;
    private boolean isFollow=false;
    private int id;
    private String type;

    @Override
    protected void initData() throws ParseException {
        baseInflater = LayoutInflater.from(this);
        strings = new ArrayList<>();



        id = getIntent().getExtras().getInt("id");
        fragments = new ArrayList<>();
        type = getIntent().getExtras().getString("type");
        strings.add("介绍");
        if(type.equals("alive")){
            strings.add("评论");
        }
        strings.add("课程");
        Log.d("--------", "initData: "+new Gson().toJson(new TeacherinfoJson(SharedPreferenceUtils.getUserid(), id, MyApp.version,"Android",SharedPreferenceUtils.getToken())));
        mPresentser.getTeacherinfo(new Gson().toJson(new TeacherinfoJson(SharedPreferenceUtils.getUserid(), id, MyApp.version,"Android",SharedPreferenceUtils.getToken())));
        for (int i = 0; i < strings.size(); i++) {

            if(i==1){
                if(type.equals("alive")){
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",id);
                    TacherevaluateFragment tacherevaluateFragment = new TacherevaluateFragment();
                    tacherevaluateFragment.setArguments(bundle);
                    fragments.add(tacherevaluateFragment);
                }else {
                    fragments.add(new TeacherInfoFragment(i, id));
                }

            }else {
                fragments.add(new TeacherInfoFragment(i, id));
            }
            tabWeChat.addTab(tabWeChat.newTab().setText(strings.get(i)));


        }
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


        FrgmentAdapter bookFrgmentAdapter = new FrgmentAdapter(getSupportFragmentManager(), fragments, strings);
        viewpager.setAdapter(bookFrgmentAdapter);


        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabWeChat));
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_teacherinfo;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }



    @OnClick({R.id.finish, R.id.follow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.follow:

                mPresentser.getfollowTeacher(new Gson().toJson(new FollowTeacherJson(SharedPreferenceUtils.getUserid(),id,SharedPreferenceUtils.getToken())));
                break;
        }
    }

    @Override
    public void setCourseList(CourseBean courseList) {

    }

    @Override
    public void setAllSubject(AllsubjectBean allSubject) {

    }

    @Override
    public void setcourseInfo(CourseinfoBean courseinfoBean) {

    }

    @Override
    public void setcourseSignUp(CollectionTiBean collectionTiBean) {

    }

    @Override
    public void setTeacherinfo(TeacherinfoBean teacherinfo) {
        if(teacherinfo.getStatus()==200){
            teacherName.setText(teacherinfo.getResult().getName());
            Glide.with(this).load(teacherinfo.getResult().getHeadImg()).into(back);
            if(teacherinfo.getResult().isIsFollow()){
                isFollow = teacherinfo.getResult().isIsFollow();
                follow.setTextColor(getResources().getColor(R.color.white));
                follow.setText("已关注");
                follow.setBackgroundResource(R.drawable.yiguanzhu);
            }
        }
    }

    @Override
    public void setfollowTeacher(CollectionTiBean collectionTiBean) {
        ToastUtil.makeShortText(this,collectionTiBean.getHint());
        if(collectionTiBean.getStatus()==200){
            if(isFollow){
                isFollow=false;

                follow.setText("关注");
                follow.setBackgroundResource(R.mipmap.bluetuo);
            }else{
                isFollow=true;
                follow.setTextColor(getResources().getColor(R.color.white));
                follow.setText("已关注");
                follow.setBackgroundResource(R.drawable.yiguanzhu);
            }
        }

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
