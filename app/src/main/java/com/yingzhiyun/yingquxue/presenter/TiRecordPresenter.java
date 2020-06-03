package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.TiRecordMvp;
import com.yingzhiyun.yingquxue.Mvp.TikuMvp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.OkBean.WrongtitleBean;
import com.yingzhiyun.yingquxue.OkBean.ZutijiluBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.TiRecordModle;

public class TiRecordPresenter<V extends TiRecordMvp.TiRecord_View> extends ImlBasePresenter<TiRecordMvp.TiRecord_View> implements TiRecordMvp.TiRecord_CallBack {


    TiRecordModle tiRecordModle=new TiRecordModle();
    public  void getTiRecordlist(String json){
        tiRecordModle.getTiRecordlist(this,json);
    }
    public  void getjiLu(String json){
        tiRecordModle.getjiLu(this,json);
    }
    public void getuserTestPaper(String json){
        tiRecordModle.getuserTestPaper(this,json);
    }
    @Override
    public void showTiRecordlist(AllsubjectBean wronglist) {
        mView.setTiRecordlist(wronglist);
    }

    @Override
    public void showjiLu(ZutijiluBean myTiBean) {
        mView.setjiLu(myTiBean);
    }

    @Override
    public void showuserTestPaper(ExamineBean examineBean) {
        mView.setuserTestPaper(examineBean);
    }

    @Override
    public void setShowProgressbar() {

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
