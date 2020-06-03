package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.HomePagerMvp;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.LeftBean;
import com.yingzhiyun.yingquxue.OkBean.RightLeft;
import com.yingzhiyun.yingquxue.OkBean.VersionBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.HomePagerModle;

public class HomePagerPresenter <V extends HomePagerMvp.HomePager_View> extends ImlBasePresenter<HomePagerMvp.HomePager_View> implements HomePagerMvp.HomePager_CallBack {
    HomePagerModle homePagerModle=new HomePagerModle();

    public  void  getHome(String json){
        homePagerModle.showHomePager(this,json);
    }
    public  void getVersion(String json){
        homePagerModle.getVersion(this,json);
    }
    public  void  getLeft(String json){
        homePagerModle.getLeft(this,json);
    }
    public  void getRight(String json){
        homePagerModle.getRight(this,json);
    }

    @Override
    public void showHomePager(HomePagerBean homePager) {
        mView.setHomePager(homePager);
    }

    @Override
    public void showVersion(VersionBean version) {
        mView.setVersion(version);
    }

    @Override
    public void showLeft(LeftBean leftBean) {
        mView.setLeft(leftBean);
    }

    @Override
    public void showRight(RightLeft rightLeft) {
        mView.setRight(rightLeft);
    }

    @Override
    public void setShowProgressbar() {

    }

    @Override
    public void setHideProgressbar() {

    }

    @Override
    public void setError(String error) {

    }
}
