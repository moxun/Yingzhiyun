package com.yingzhiyun.yingquxue.presenter;

import android.util.Log;

import com.yingzhiyun.yingquxue.Mvp.SContentMvp;
import com.yingzhiyun.yingquxue.Mvp.SelectedOptionsMvp;
import com.yingzhiyun.yingquxue.OkBean.SearchcontentBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.SContentModle;

public class ScontentPresenter <V extends SContentMvp.SContent_View> extends ImlBasePresenter<SContentMvp.SContent_View>
        implements SContentMvp.SContent_CallBack  {
    SContentModle sContentModle=new SContentModle();
    public  void getSContent(String json){
        Log.d("moxun", "serarch: "+json);
        sContentModle.getSContent(this,json);
    }
    @Override
    public void showSContent(SearchcontentBean searchcontentBean) {
        mView.setSContent(searchcontentBean);
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
