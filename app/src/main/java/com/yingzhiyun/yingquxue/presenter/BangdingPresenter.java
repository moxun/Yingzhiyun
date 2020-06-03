package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.BangdingMvp;
import com.yingzhiyun.yingquxue.Mvp.ClassifyMvp;
import com.yingzhiyun.yingquxue.OkBean.CodeBean;
import com.yingzhiyun.yingquxue.OkBean.LoginSuccesBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.BangdingModle;

public class BangdingPresenter<V extends BangdingMvp.Bangding_View> extends ImlBasePresenter<BangdingMvp.Bangding_View> implements BangdingMvp.Bangding_CallBack {

    BangdingModle bangdingModle = new BangdingModle();
    public void getTPLogin(int type,String jsobn){
        bangdingModle.getTPLogin(this,jsobn,type);
    }
    public void getTPLoginMatchesPhoneNum(int type,String jsobn){
        bangdingModle.getTPLoginMatchesPhoneNum(this,jsobn,type);
    }
    public void getPwdByOpenid(int type,String jsobn){
        bangdingModle.getPwdByOpenid(this,jsobn,type);
    }
    public void getTPLoginBinPhone(int type,String jsobn){
        bangdingModle.getTPLoginBinPhone(this,jsobn,type);
    }
    @Override
    public void showTPLogin(LoginSuccesBean codeBean) {
        mView.setTPLogin(codeBean);
    }

    @Override
    public void showTPLoginMatchesPhoneNum(CodeBean codeBean) {
        mView.setTPLoginMatchesPhoneNum(codeBean);
    }

    @Override
    public void showTPLoginBinPhone(LoginSuccesBean codeLogin) {
        mView.setTPLoginBinPhone(codeLogin);
    }

    @Override
    public void showPwdByOpenid(LoginSuccesBean loginSucces) {
        mView.setPwdByOpenid(loginSucces);
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
