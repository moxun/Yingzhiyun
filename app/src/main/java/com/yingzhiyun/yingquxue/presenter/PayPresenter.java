package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.PayMvp;
import com.yingzhiyun.yingquxue.Mvp.SContentMvp;
import com.yingzhiyun.yingquxue.OkBean.BalanceBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CoursePayBean;
import com.yingzhiyun.yingquxue.OkBean.RecordBean;
import com.yingzhiyun.yingquxue.OkBean.WxPAyBean;
import com.yingzhiyun.yingquxue.OkBean.YatijuanPayBean;
import com.yingzhiyun.yingquxue.OkBean.YitiPayinfo;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.PayModle;

public class PayPresenter <V extends PayMvp.Pay_View> extends ImlBasePresenter<PayMvp.Pay_View>
        implements PayMvp.Pay_CallBack {
    PayModle payModle =new PayModle();
    public  void getCoursePay(String json){
        payModle.getCoursePay(this,json);
    }
    public  void getWxPay(String json){
        payModle.getWxPay(this,json);
    }
    public void getBalance(String json){
        payModle.getBalance(this,json);
    }
    public void getcourseSignUp(String json){
        payModle.getcourseSignUp(this,json);
    }
    public void getyatiyue(String json){
        payModle.getyatiyue(this,json);
    }
    public  void getquerypay(String json){
        payModle.getquerypay(this,json);
    }
    public  void getRecharge4Android(String json){
        payModle.getRecharge4Android(this,json);
    }
    public void getuserWalletRecord(String json){
        payModle.getuserWalletRecord(this,json);
    }
    @Override
    public void showCoursePay(CoursePayBean coursePayBean) {
        mView.setCoursePay(coursePayBean);
    }

    @Override
    public void showWxPay(WxPAyBean wxPAyBean) {

        mView.setWxPay(wxPAyBean);
    }
    public void getyatipay(String json){
        payModle.getyatipay(this,json);
    }
    public void getbetPaymentPage(String json){
        payModle.getbetPaymentPage(this,json);
    }

    @Override
    public void showquerypay(WxPAyBean wxPay) {
        mView.setquerypay(wxPay);
    }

    @Override
    public void showBalance(BalanceBean balance) {
        mView.setBalance(balance);
    }

    @Override
    public void showcourseSignUp(CollectionTiBean collectionTiBean) {
        mView.setcourseSignUp(collectionTiBean);
    }

    @Override
    public void showRecharge4Android(WxPAyBean wxPAyBean) {
        mView.setRecharge4Android(wxPAyBean);
    }

    @Override
    public void setuserWalletRecord(RecordBean recordBean) {
        mView.setuserWalletRecord(recordBean);
    }

    @Override
    public void showyatipay(WxPAyBean yatijuanPayBean) {
        mView.setyatipay(yatijuanPayBean);
    }

    @Override
    public void showbetPaymentPage(YitiPayinfo yitiPayinfo) {
        mView.setbetPaymentPage(yitiPayinfo);
    }

    @Override
    public void showyatiyue(WxPAyBean yatijuanPayBean) {
        mView.setyatiyue(yatijuanPayBean);
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
