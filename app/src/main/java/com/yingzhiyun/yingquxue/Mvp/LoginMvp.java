package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.CodeBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.LoginSuccesBean;
import com.yingzhiyun.yingquxue.OkBean.RegisterBean;
import com.yingzhiyun.yingquxue.OkBean.SubjectBean;
import com.yingzhiyun.yingquxue.OkBean.UpdatePassBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface LoginMvp {
    interface Login_View extends BaseView {

        void setCode(CodeBean code);
        void setRegister(RegisterBean register);
        void setCodeLogin(CodeBean codeLogin);
        void setLoginSucces(LoginSuccesBean loginSucces);
        void setCodeForgrt(CodeBean codeLogin);
        void setUpdatePass(UpdatePassBean updatePass);
        void setpwdLogin(LoginSuccesBean loginSucces);
        void setupdapas(CollectBean collectBean);
        void setTPLogin(LoginSuccesBean loginSucces);
    }

    interface Login_CallBack extends HttpFinishCallback {
        void showCode(CodeBean codeBean);
        void showRegister(RegisterBean registerBean);
        void showCodeLogin(CodeBean codeLogin);
        void showLoginSucces(LoginSuccesBean loginSucces);
        void showCodeForgrt(CodeBean codeLogin);
        void showUpdatePass(UpdatePassBean updatePass);
        void showpwdLogin(LoginSuccesBean loginSucces);
        void showupdapas(CollectBean collectBean);
        void showTPLogin(LoginSuccesBean loginSucces);
    }

    interface Login_Modle {
        void getSubject(Login_CallBack login_callBack, String json);
        void getRegister(Login_CallBack login_callBack,String json);
        void getCodeLogin(Login_CallBack login_callBack,String json);
        void getLoginSucces(Login_CallBack login_callBack,String json);
        void getCodeForgrt(Login_CallBack login_callBack,String json);
        void getUpdatePass(Login_CallBack login_callBack,String json);
        void getpwdLogin(Login_CallBack login_callBack,String json);
        void getupdapwd(Login_CallBack login_callBack,String json);
        void getTPLogin(Login_CallBack login_callBack,String json,int type);

    }
}
