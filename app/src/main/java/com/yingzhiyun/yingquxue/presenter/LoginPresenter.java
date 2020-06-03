package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.HomePagerMvp;
import com.yingzhiyun.yingquxue.Mvp.LoginMvp;
import com.yingzhiyun.yingquxue.OkBean.CodeBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.LoginSuccesBean;
import com.yingzhiyun.yingquxue.OkBean.RegisterBean;
import com.yingzhiyun.yingquxue.OkBean.UpdatePassBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.LoginModle;

public class LoginPresenter <V extends LoginMvp.Login_View> extends ImlBasePresenter<LoginMvp.Login_View> implements LoginMvp.Login_CallBack {

    LoginModle loginModle=new LoginModle();
    public  void  getCode(String json){
        loginModle.getSubject(this,json);
    }
    public  void  getRegister(String json){
        loginModle.getRegister(this,json);
    }
    public  void  getCodeLogin(String json){
        loginModle.getCodeLogin(this,json);
    }
    public  void  getLoginSucces(String json){
        loginModle.getLoginSucces(this,json);
    }
    public  void  getCodeForget(String json){
        loginModle.getCodeForgrt(this,json);
    }
    public  void  getUpdatePass(String json){
        loginModle.getUpdatePass(this,json);
    }
    public  void  getpwdLogin(String json){
        loginModle.getpwdLogin(this,json);
    }
    public  void  getupdapwd(String json){
        loginModle.getupdapwd(this,json);
    }
    public  void  getTPLogin(String json,int type){
        loginModle.getTPLogin(this,json,type);
    }
    @Override
    public void showCode(CodeBean codeBean) {
        mView.setCode(codeBean);
    }

    @Override
    public void showRegister(RegisterBean registerBean) {
        mView.setRegister(registerBean);
    }



    @Override
    public void showCodeLogin(CodeBean codeLogin) {
        mView.setCodeLogin(codeLogin);
    }

    @Override
    public void showLoginSucces(LoginSuccesBean loginSucces) {
        mView.setLoginSucces(loginSucces);
    }

    @Override
    public void showCodeForgrt(CodeBean codeLogin) {
        mView.setCodeForgrt(codeLogin);
    }

    @Override
    public void showUpdatePass(UpdatePassBean updatePass) {
        mView.setUpdatePass(updatePass);
    }

    @Override
    public void showpwdLogin(LoginSuccesBean loginSucces) {
        mView.setpwdLogin(loginSucces);
    }

    @Override
    public void showupdapas(CollectBean collectBean) {
        mView.setupdapas(collectBean);
    }

    @Override
    public void showTPLogin(LoginSuccesBean loginSucces) {
        mView.setTPLogin(loginSucces);
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
