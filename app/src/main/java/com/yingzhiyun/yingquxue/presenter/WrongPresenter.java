package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.WrongMvp;
import com.yingzhiyun.yingquxue.Mvp.ZjietestMvp;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.MyTiBean;
import com.yingzhiyun.yingquxue.OkBean.WrongtitleBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.WrongModle;

public class WrongPresenter <V extends WrongMvp.Wrong_View> extends ImlBasePresenter<WrongMvp.Wrong_View> implements WrongMvp.Wrong_CallBack {

   WrongModle wrongModle=new WrongModle();
    public  void getWronglist(String json){
        wrongModle.getWronglist(this,json);
    }
    @Override
    public void showWronglist(WrongtitleBean wronglist) {
        mView.setWronglist(wronglist);
    }
    public void getdeleteWrong(String json){
        wrongModle.getdeleteWrong(this,json);
    }
    public void getCollection(String json){
        wrongModle.getCollection(this,json);
    }
    public  void getWrong(String json){
        wrongModle.getWrong(this,json);
    }
    public  void getCollectionti(String json){
        wrongModle.getCollectionti(this,json);
    }
    public  void getTiRecordlist(String json){
        wrongModle.getTiRecordlist(this,json);
    }
    @Override
    public void showWrong(MyTiBean myTiBean) {
        mView.setWrong(myTiBean);
    }

    @Override
    public void showdeleteWrong(CollectionTiBean collectionTiBean) {
        mView.setdeleteWrong(collectionTiBean);
    }

    @Override
    public void showCollection(MyTiBean myTiBean) {
        mView.setCollection(myTiBean);
    }

    @Override
    public void showCollectionti(CollectionTiBean collectionti) {
        mView.setCollectionti(collectionti);
    }

    @Override
    public void showTiRecordlist(WrongtitleBean wronglist) {
        mView.setTiRecordlist(wronglist);
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
