package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.ClassifyMvp;
import com.yingzhiyun.yingquxue.Mvp.HomePagerMvp;
import com.yingzhiyun.yingquxue.OkBean.CoursewareListBean;
import com.yingzhiyun.yingquxue.OkBean.SubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.ClassifyModle;

public class ClassifyPresenter <V extends ClassifyMvp.Classify_View> extends ImlBasePresenter<ClassifyMvp.Classify_View> implements ClassifyMvp.Classify_CallBack {
    public ClassifyModle classifyModle=new ClassifyModle();

    public void getSubject(String type){
        classifyModle.getSubject(this,type);
    }
    public void getCoursewareList(String json){
        classifyModle.getCoursewareList(this,json);
    }
    @Override
    public void showSubject(SubjectBean subjectBean) {
        mView.setSubject(subjectBean);
    }

    @Override
    public void showCoursewareList(ZiyuanBean coursewareList) {
        mView.setCoursewareList(coursewareList);
    }

    @Override
    public void setShowProgressbar() {

    }

    @Override
    public void setHideProgressbar() {

    }

    @Override
    public void setError(String error) {
        mView.showError(error);
    }
}
