package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.HomePagerMvp;
import com.yingzhiyun.yingquxue.Mvp.SubjectinfoMvp;
import com.yingzhiyun.yingquxue.OkBean.SubjectInfoBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.SubjectinfoModle;

public class SubjectinfoPresenter<V extends SubjectinfoMvp.Subjectinfo_View> extends ImlBasePresenter<SubjectinfoMvp.Subjectinfo_View> implements SubjectinfoMvp.Subjectinfo_CallBack {


    SubjectinfoModle subjectinfoModle=new SubjectinfoModle();

    public  void getSubjectinfo(String id){
        subjectinfoModle.getSubjectinfo(this,id);
    }


    @Override
    public void showSubjectinfo(SubjectInfoBean subjectInfoBean) {
        mView.setSubjectinfo(subjectInfoBean);
    }

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
