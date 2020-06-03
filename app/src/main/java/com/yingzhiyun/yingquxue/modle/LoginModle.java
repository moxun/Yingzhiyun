package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.LoginMvp;
import com.yingzhiyun.yingquxue.OkBean.CodeBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.LoginSuccesBean;
import com.yingzhiyun.yingquxue.OkBean.RegisterBean;
import com.yingzhiyun.yingquxue.OkBean.UpdatePassBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginModle implements LoginMvp.Login_Modle {
    FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void getSubject(LoginMvp.Login_CallBack login_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getCode(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CodeBean>(login_callBack) {
                    @Override
                    public void onNext(CodeBean value) {
                        login_callBack.showCode(value);
                    }
                });
    }

    @Override
    public void getRegister(LoginMvp.Login_CallBack login_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getRegister(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<RegisterBean>(login_callBack) {
                    @Override
                    public void onNext(RegisterBean value) {
                        login_callBack.showRegister(value);
                    }
                });
    }

    @Override
    public void getCodeLogin(LoginMvp.Login_CallBack login_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getCodeLogin(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CodeBean>(login_callBack) {
                    @Override
                    public void onNext(CodeBean value) {
                        login_callBack.showCodeLogin(value);
                    }
                });
    }

    @Override
    public void getLoginSucces(LoginMvp.Login_CallBack login_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getLoginSucces(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginSuccesBean>(login_callBack) {
                    @Override
                    public void onNext(LoginSuccesBean value) {
                        login_callBack.showLoginSucces(value);
                    }
                });
    }

    @Override
    public void getCodeForgrt(LoginMvp.Login_CallBack login_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getCodeForget(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CodeBean>(login_callBack) {
                    @Override
                    public void onNext(CodeBean value) {
                        login_callBack.showCodeForgrt(value);
                    }
                });
    }

    @Override
    public void getUpdatePass(LoginMvp.Login_CallBack login_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getUpdatePass(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<UpdatePassBean>(login_callBack) {
                    @Override
                    public void onNext(UpdatePassBean value) {
                        login_callBack.showUpdatePass(value);
                    }
                });
    }

    @Override
    public void getpwdLogin(LoginMvp.Login_CallBack login_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getpwdlogin(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginSuccesBean>(login_callBack) {
                    @Override
                    public void onNext(LoginSuccesBean value) {
                        login_callBack.showpwdLogin(value);
                    }
                });
    }

    @Override
    public void getupdapwd(LoginMvp.Login_CallBack login_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.modifyPwd(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectBean>(login_callBack) {
                    @Override
                    public void onNext(CollectBean value) {
                        login_callBack.showupdapas(value);
                    }
                });
    }

    @Override
    public void getTPLogin(LoginMvp.Login_CallBack login_callBack, String json, int type) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);

        fristServer.TPLogin(type,requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginSuccesBean>(login_callBack) {
                    @Override
                    public void onNext(LoginSuccesBean loginSuccesBean) {
                        login_callBack.showTPLogin(loginSuccesBean);
                    }
                });
    }
}
