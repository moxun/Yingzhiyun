package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.BrowsingMvp;
import com.yingzhiyun.yingquxue.Mvp.ClassifyMvp;
import com.yingzhiyun.yingquxue.OkBean.BrowsingBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.BrowsingModle;

public class BrowsingPrseenter <V extends BrowsingMvp.Browsing_View> extends ImlBasePresenter<BrowsingMvp.Browsing_View> implements BrowsingMvp.Browsing_CallBack {

    BrowsingModle browsingModle=new BrowsingModle();

    public  void get(String json){
        browsingModle.getBrowsing(this,json);
    }
    @Override
    public void showBrowsing(BrowsingBean Browsing) {
        mView.setBrowsing(Browsing);
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
