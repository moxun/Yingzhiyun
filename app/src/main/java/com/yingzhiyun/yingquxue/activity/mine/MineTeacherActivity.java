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
import com.yingzhiyun.yingquxue.adapter.MineTeacherAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.presenter.CoursePresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineTeacherActivity extends BaseActicity<CourseMvp.Course_View, CoursePresenter<CourseMvp.Course_View>> implements CourseMvp.Course_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.recy_intera)
    RecyclerView recyIntera;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    private ArrayList<MineTeacherBean.ResultBean> teacherList;
    private MineTeacherAdapter mineTeacherAdapter;


    @Override
    protected void initData() {
        teacherList = new ArrayList<>();
        mineTeacherAdapter = new MineTeacherAdapter(teacherList, this);
        recyIntera.setLayoutManager(new LinearLayoutManager(this));
        recyIntera.setAdapter(mineTeacherAdapter);
        mPresentser.getmyFollowTeacher(new Gson().toJson(new HomePagerJson(SharedPreferenceUtils.getUserid()+"",SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresentser.getmyFollowTeacher(new Gson().toJson(new HomePagerJson(SharedPreferenceUtils.getUserid()+"",SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_myteacher;
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

    }

    @Override
    public void setmyFollowTeacher(MineTeacherBean mineTeacherBean) {
        teacherList.clear();
        if(mineTeacherBean.getStatus()==200){
            if(mineTeacherBean.getResult().size()>0){

                recyIntera.setVisibility(View.VISIBLE);
                linearModle.setVisibility(View.GONE);
                teacherList.addAll(mineTeacherBean.getResult());
                mineTeacherAdapter.notifyDataSetChanged();
            }else{
                recyIntera.setVisibility(View.GONE);
                linearModle.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void setPlayVideo(PlayVideoBean playVideo) {

    }

    @Override
    protected CoursePresenter<CourseMvp.Course_View> createPresenter() {
        return new CoursePresenter<>();
    }
}
