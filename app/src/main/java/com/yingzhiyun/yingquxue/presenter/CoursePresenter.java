package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.ClassifyMvp;
import com.yingzhiyun.yingquxue.Mvp.CourseMvp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ClassBeaginBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.OkBean.CourseinfoBean;
import com.yingzhiyun.yingquxue.OkBean.MineCourseBean;
import com.yingzhiyun.yingquxue.OkBean.MineTeacherBean;
import com.yingzhiyun.yingquxue.OkBean.PlayVideoBean;
import com.yingzhiyun.yingquxue.OkBean.TeacherinfoBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.CourseModle;

public class CoursePresenter<V extends CourseMvp.Course_View> extends ImlBasePresenter<CourseMvp.Course_View> implements CourseMvp.Course_CallBack {
    CourseModle courseModle = new CourseModle();

    public void getAllSubject(String json) {
        courseModle.getAllSubject(this, json);

    }

    public void getCourseList(String json) {
        courseModle.getCourseList(this, json);
    }

    public void getcourseInfo(String json) {
        courseModle.getcourseInfo(this, json);

    }

    public void getTeacherinfo(String json) {
        courseModle.getTeacherinfo(this, json);
    }

    public void getfollowTeacher(String json) {
        courseModle.getfollowTeacher(this, json);

    }
    public void getPlayVideo(String json) {
        courseModle.getPlayVideo(this, json);

    }
    public  void  getclassBegins(String json){
        courseModle.getclassBegins(this,json);
    }
    public void getcourseSignUp(String json) {
        courseModle.getcourseSignUp(this, json);
    }
    public  void getmyCourse(String json){
        courseModle.getmyCourse(this,json);
    }
    @Override
    public void showCourseList(CourseBean courseList) {
        mView.setCourseList(courseList);
    }

    @Override
    public void showAllSubject(AllsubjectBean allsubjectBean) {
        mView.setAllSubject(allsubjectBean);
    }

    @Override
    public void showcourseInfo(CourseinfoBean courseinfoBean) {
        mView.setcourseInfo(courseinfoBean);
    }

    @Override
    public void showcourseSignUp(CollectionTiBean collectionTiBean) {
        mView.setcourseSignUp(collectionTiBean);
    }
    public  void getmyFollowTeacher(String json){
        courseModle.getmyFollowTeacher(this,json);
    }
    @Override
    public void showTeacherinfo(TeacherinfoBean teacherinfo) {
        mView.setTeacherinfo(teacherinfo);
    }

    @Override
    public void showfollowTeacher(CollectionTiBean collectionTiBean) {
        mView.setfollowTeacher(collectionTiBean);
    }

    @Override
    public void showclassBegins(ClassBeaginBean classBeaginBean) {
        mView.setclassBegins(classBeaginBean);
    }

    @Override
    public void showmyCourse(MineCourseBean courseBean) {
        mView.setmyCourse(courseBean);
    }

    @Override
    public void showmyFollowTeacher(MineTeacherBean mineTeacherBean) {
        mView.setmyFollowTeacher(mineTeacherBean);
    }

    @Override
    public void showPlayVideo(PlayVideoBean playVideo) {
        mView.setPlayVideo(playVideo);    }

    @Override
    public void setShowProgressbar() {
        mView.showProgressbar();
    }

    @Override
    public void setHideProgressbar() {
        mView.hideProgressbar();
    }

    @Override
    public void setError(String error) {
        mView.showError(error);
    }
}
