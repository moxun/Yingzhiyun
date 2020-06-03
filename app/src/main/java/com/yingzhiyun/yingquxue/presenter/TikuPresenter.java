package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.TikuMvp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.TikuModle;

public class TikuPresenter <V extends TikuMvp.Tiku_View> extends ImlBasePresenter<TikuMvp.Tiku_View> implements TikuMvp.Tiku_CallBack {
   public TikuModle tikuModle=new TikuModle();
   public  void getAllSubject(String json){
       tikuModle.getAllSubject(this,json);

   }
    @Override
    public void showAllSubject(AllsubjectBean allsubjectBean) {
        mView.setAllSubject(allsubjectBean);

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
