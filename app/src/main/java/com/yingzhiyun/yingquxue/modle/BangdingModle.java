package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.BangdingMvp;
import com.yingzhiyun.yingquxue.OkBean.CodeBean;
import com.yingzhiyun.yingquxue.OkBean.LoginSuccesBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BangdingModle implements BangdingMvp.Bangding_Modle {

    FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);

    @Override
    public void getTPLogin(BangdingMvp.Bangding_CallBack login_callBack, String json, int type) {
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

    @Override
    public void getTPLoginMatchesPhoneNum(BangdingMvp.Bangding_CallBack login_callBack, String json, int type) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);

        fristServer.TPLoginMatchesPhoneNum(type,requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CodeBean>(login_callBack) {
                    @Override
                    public void onNext(CodeBean loginSuccesBean) {
                        login_callBack.showTPLoginMatchesPhoneNum(loginSuccesBean);
                    }
                });
    }

    @Override
    public void getTPLoginBinPhone(BangdingMvp.Bangding_CallBack login_callBack, String json, int type) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.TPLoginBinPhone(type,requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginSuccesBean>(login_callBack) {
                    @Override
                    public void onNext(LoginSuccesBean loginSuccesBean) {
                        login_callBack.showTPLoginBinPhone(loginSuccesBean);
                    }
                });
    }

    @Override
    public void getPwdByOpenid(BangdingMvp.Bangding_CallBack login_callBack, String json, int type) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.setPwdByOpenid(type,requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginSuccesBean>(login_callBack) {
                    @Override
                    public void onNext(LoginSuccesBean loginSuccesBean) {
                        login_callBack.showPwdByOpenid(loginSuccesBean);
                    }
                });
    }
}
