package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.InteractionsMvp;
import com.yingzhiyun.yingquxue.Mvp.LoginMvp;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.HudongIfoBean;
import com.yingzhiyun.yingquxue.OkBean.InteractionsListBean;
import com.yingzhiyun.yingquxue.OkBean.MyInteractionListBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.InteractionsModle;

public class InteractionsPresenter <V extends InteractionsMvp.Interaction_View> extends ImlBasePresenter<InteractionsMvp.Interaction_View> implements InteractionsMvp.Interaction_CallBack {

    InteractionsModle interactionsModle=new InteractionsModle();
    public  void getInteractionList(String json){
        interactionsModle.getInteractionList(this,json);
    }
    public  void getFanlkui(String json){
        interactionsModle.getFanlkui(this,json);
    }
    public void getInteractionBean(String json){
        interactionsModle.getInteractionBean(this,json);
    }
    public void getMyInteractionList(String json){
        interactionsModle.getMyInteractionList(this,json);
    }
    @Override
    public void showInteractionList(InteractionsListBean interactionsListBean) {
        mView.setInteractionList(interactionsListBean);
    }

    @Override
    public void showInteractionBean(HudongIfoBean hudongIfoBean) {
        mView.setInteractionBean(hudongIfoBean);
    }

    @Override
    public void showMyInteractionList(MyInteractionListBean myInteractionList) {
        mView.setMyInteractionList(myInteractionList);
    }

    @Override
    public void showFanlkui(CollectBean collectBean) {
        mView.setFanlkui(collectBean);
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
