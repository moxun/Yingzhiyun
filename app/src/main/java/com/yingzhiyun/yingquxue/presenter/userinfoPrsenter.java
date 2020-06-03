package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.VideoinfoMvp;
import com.yingzhiyun.yingquxue.Mvp.userinfoMvp;
import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.SchoolBean;
import com.yingzhiyun.yingquxue.OkBean.UserinfoBean;
import com.yingzhiyun.yingquxue.OkBean.YatiBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.userinfoModle;

public class userinfoPrsenter  <V extends userinfoMvp.userinfo_View> extends ImlBasePresenter<userinfoMvp.userinfo_View> implements userinfoMvp.userinfo_CallBack  {

    userinfoModle userinfoModle=new userinfoModle();
    public  void getuserinfo(String json){
        userinfoModle.getuserinfo(this,json);
    }
    public  void getupdateinfo(String json){
        userinfoModle.getupdateinfo(this,json);
    }
    public  void getmyBetList(String json){
        userinfoModle.getmyBetList(this,json);
    }

    public  void getmyBetFiles(String json){
        userinfoModle.getmyBetFiles(this,json);
    }
    @Override
    public void showuserinfo(UserinfoBean selectedOptionsBean) {
        mView.setuserinfo(selectedOptionsBean);
    }
    public  void  getSchool(String json){
        userinfoModle.getSchool(this,json);
    }
    @Override
    public void showSchool(SchoolBean schoolBean) {
        mView.setSchool(schoolBean);
    }

    @Override
    public void showupdateinfo(CollectBean collectBean) {
        mView.setupdateinfo(collectBean);
    }

    @Override
    public void showmyBetList(BetListBean betListBean) {
        mView.setmyBetList(betListBean);
    }

    @Override
    public void showmyBetFiles(YatiBean bashiinfoBean) {
        mView.setmyBetFiles(bashiinfoBean);
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
