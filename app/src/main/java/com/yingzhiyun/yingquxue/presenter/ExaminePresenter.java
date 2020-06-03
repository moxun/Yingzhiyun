package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.ExamineMvp;
import com.yingzhiyun.yingquxue.Mvp.HomePagerMvp;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.ExamineModle;

import java.text.ParseException;

public class ExaminePresenter  <V extends ExamineMvp.Examine_View> extends ImlBasePresenter<ExamineMvp.Examine_View>
        implements ExamineMvp.Examine_CallBack {
    ExamineModle examineModle=new ExamineModle();
    public  void getExamine(String json,String url){
        examineModle.getExamine(this,json,url);
    }
    @Override
    public void showExamine(ExamineBean examineBean) throws ParseException {
        mView.setExamineBean(examineBean);
    }
    public void getCollectionti(String json){
        examineModle.getCollectionti(this,json);
    }
    @Override
    public void showCollectionti(CollectionTiBean collectionti) {
        mView.setCollectionti(collectionti);
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
