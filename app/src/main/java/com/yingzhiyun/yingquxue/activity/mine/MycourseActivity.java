package com.yingzhiyun.yingquxue.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.CourseMvp;
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
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.CourseListAdapter;
import com.yingzhiyun.yingquxue.adapter.MoreVideoAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.CoursePresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MycourseActivity extends BaseActicity<CourseMvp.Course_View, CoursePresenter<CourseMvp.Course_View>> implements CourseMvp.Course_View {

    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.recy_intera)
    RecyclerView recyIntera;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.begun)
    TextView begun;
    @BindView(R.id.notbegun)
    TextView notbegun;
    @BindView(R.id.recy_notbegan)
    RecyclerView recyNotbegan;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.recy_rea)
    RelativeLayout recyRea;

    private ArrayList<CourseBean.ResultBean> beganList = new ArrayList<>();
    private ArrayList<CourseBean.ResultBean> notbeganList = new ArrayList<>();
    private MoreVideoAdapter moreSelectionAdapter;
    private CourseListAdapter beganAdapter;
    private CourseListAdapter notbeganAdapter;

    @Override
    protected void initData() {
        mPresentser.getmyCourse(new Gson().toJson(new HomePagerJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
        beganAdapter = new CourseListAdapter(beganList, this);
        notbeganAdapter = new CourseListAdapter(notbeganList, this);
        recyIntera.setLayoutManager(new LinearLayoutManager(this));
        recyNotbegan.setLayoutManager(new LinearLayoutManager(this));
        recyIntera.setAdapter(beganAdapter);
        recyNotbegan.setAdapter(notbeganAdapter);
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_mycourse;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
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

    }

    @Override
    public void setfollowTeacher(CollectionTiBean collectionTiBean) {

    }

    @Override
    public void setclassBegins(ClassBeaginBean classBeaginBean) {

    }

    @Override
    public void setmyCourse(MineCourseBean courseBean) {
        if (courseBean.getStatus() == 200) {
            recyRea.setVisibility(View.VISIBLE);
            linearModle.setVisibility(View.GONE);
            beganList.addAll(courseBean.getResult().getBegun());
            notbeganList.addAll(courseBean.getResult().getNotBegun());
            beganAdapter.notifyDataSetChanged();
            notbeganAdapter.notifyDataSetChanged();

        }else {
            finish();
            ToastUtil.makeShortText(this,"身份过期");startActivity(PwdLoginActivity.class);
            recyRea.setVisibility(View.GONE);
            linearModle.setVisibility(View.VISIBLE);
        }
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
