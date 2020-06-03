package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.TestPaperListMvp;
import com.yingzhiyun.yingquxue.Mvp.TikuMvp;
import com.yingzhiyun.yingquxue.OkBean.BetDetailBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;

import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.TestPaperListModle;

public class TestPaperListPresenter  <V extends TestPaperListMvp.TestPaperList_View> extends ImlBasePresenter<TestPaperListMvp.TestPaperList_View>
        implements TestPaperListMvp.TestPaperList_CallBack {

    TestPaperListModle testPaperListModle=new TestPaperListModle();
    public void getTestPaperList(String json){
        testPaperListModle.getTestPaperList(this,json);
    }
    public void getfilterItem(String json){
        testPaperListModle.getfilterItem(this,json);
    }

    public void getZiyuan(String json){
        testPaperListModle.getZiyuan(this,json);
    }

    public  void getbetList(String json){
        testPaperListModle.getbetList(this,json);
    }
    public  void getbetDetail(String json){
        testPaperListModle.getbetDetail(this,json);
    }
    @Override
    public void showTestPaperList(TestPaperListBean testPaperListBean) {
        mView.setTestPaperList(testPaperListBean);
    }

    @Override
    public void showfilterItem(TeachingShaixuanBean teachingShaixuanBean) {
        mView.setfilterItem(teachingShaixuanBean);
    }

    @Override
    public void showZiyuan(ZiyuanBean ziyuan) {
        mView.setZiyuan(ziyuan);
    }

    @Override
    public void showbetList(BetListBean betListBean) {
        mView.setbetList(betListBean);
    }

    @Override
    public void showbetDetail(BetDetailBean betDetailBean) {
        mView.setbetDetail(betDetailBean);
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
