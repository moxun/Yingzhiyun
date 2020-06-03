package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.TikuMvp;
import com.yingzhiyun.yingquxue.Mvp.VideoinfoMvp;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.MyCollectBean;
import com.yingzhiyun.yingquxue.OkBean.VideoinfoBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.VideoinfoModle;

public class VideoinPresenter <V extends VideoinfoMvp.Videoinfo_View> extends ImlBasePresenter<VideoinfoMvp.Videoinfo_View> implements VideoinfoMvp.Videoinfo_CallBack {
    VideoinfoModle videoinfoModle=new VideoinfoModle();

    public  void getVideoinfo(String json){
        videoinfoModle.getVideoinfo(this,json);
    }

    public  void getCollectVideo(String json){
        videoinfoModle.getCollectVideo(this,json);
    }

    public  void getMyCollect(String json){
        videoinfoModle.getMyCollect(this,json);
    }
    @Override
    public void showVideoinfo(VideoinfoBean videoinfoBean) {
        mView.setVideoinfo(videoinfoBean);
    }

    @Override
    public void showCollectVideo(CollectBean collectVideo) {
        mView.setCollectVideo(collectVideo);
    }

    @Override
    public void showMyCollect(ZiyuanBean collectBean) {
        mView.setMyCollect(collectBean);
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
