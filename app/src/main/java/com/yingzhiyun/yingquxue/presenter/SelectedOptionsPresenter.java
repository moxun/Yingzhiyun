package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.HomePagerMvp;
import com.yingzhiyun.yingquxue.Mvp.SelectedOptionsMvp;
import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.FolderListOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.OkBean.skillCourseListBeam;
import com.yingzhiyun.yingquxue.OkBean.skillTypeListBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.SelectedOptionsModle;

public class SelectedOptionsPresenter
        <V extends SelectedOptionsMvp.SelectedOptions_View> extends ImlBasePresenter<SelectedOptionsMvp.SelectedOptions_View>
        implements SelectedOptionsMvp.SelectedOptions_CallBack {
    SelectedOptionsModle selectedOptionsModle=new SelectedOptionsModle();
    public  void  getSelect(String json){
        selectedOptionsModle.getSelectedOptions(this,json);
    }
    public  void  getZiyuan(String json){
        selectedOptionsModle.getZiyuan(this,json);
    }
    public void getCoursewareList(String json){
        selectedOptionsModle.getCoursewareList(this,json);
    }
    public void getskillTypeList(String json){
        selectedOptionsModle.getskillTypeList(this,json);
    }
    public void getskillCourseList(String json){
        selectedOptionsModle.getskillCourseList(this,json);
    } public void getfolderListOptions(){
        selectedOptionsModle.getfolderListOptions(this);
    }


    public void getfolderDetail(String json){
        selectedOptionsModle.getfolderDetail(this,json);
    }
    @Override
    public void showSelectedOptions(SelectedOptionsBean selectedOptionsBean) {
        mView.setSelectedOptions(selectedOptionsBean);
    }

    @Override
    public void showZiyuan(ZiyuanBean ziyuan) {
        mView.setZiyuan(ziyuan);
    }

    @Override
    public void showCoursewareList(ZiyuanBean coursewareList) {
        mView.setCoursewareList(coursewareList);
    }

    @Override
    public void showskillTypeList(skillTypeListBean skillTypeListBean) {
        mView.setskillTypeList(skillTypeListBean);
    }

    @Override
    public void showskillCourseList(skillCourseListBeam skillCourseListBeam) {
        mView.setskillCourseList(skillCourseListBeam);
    }

    @Override
    public void showfolderListOptions(FolderListOptionsBean folderListOptionsBean) {
        mView.setfolderListOptions(folderListOptionsBean);
    }

    @Override
    public void showfolderDetail(BashiinfoBean bashiinfoBean) {
        mView.setfolderDetail(bashiinfoBean);
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

    }
}
