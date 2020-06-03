package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.VideoinfoMvp;
import com.yingzhiyun.yingquxue.Mvp.userinfoMvp;
import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.SchoolBean;
import com.yingzhiyun.yingquxue.OkBean.UserinfoBean;
import com.yingzhiyun.yingquxue.OkBean.YatiBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class userinfoModle implements userinfoMvp.userinfo_Modle {
    FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);

    @Override
    public void getuserinfo(userinfoMvp.userinfo_CallBack userinfo_callBack, String json) {
            RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
            fristServer.getUserInfo(requestBody)
                    .compose(RxUtils.rxObserableSchedulerHelper())
                    .subscribe(new BaseObserver<UserinfoBean>(userinfo_callBack) {
                        @Override
                        public void onNext(UserinfoBean userinfoBean) {
                            userinfo_callBack.showuserinfo(userinfoBean);
                        }
                    });
    }

    @Override
    public void getSchool(userinfoMvp.userinfo_CallBack userinfo_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.searchSchool(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<SchoolBean>(userinfo_callBack) {
                    @Override
                    public void onNext(SchoolBean userinfoBean) {
                        userinfo_callBack.showSchool(userinfoBean);
                    }
                });
    }

    @Override
    public void getupdateinfo(userinfoMvp.userinfo_CallBack userinfo_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.updateUserInfo(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectBean>(userinfo_callBack) {
                    @Override
                    public void onNext(CollectBean userinfoBean) {
                        userinfo_callBack.showupdateinfo(userinfoBean);
                    }
                });
    }

    @Override
    public void getmyBetList(userinfoMvp.userinfo_CallBack userinfo_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.myBetList(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BetListBean>(userinfo_callBack) {
                    @Override
                    public void onNext(BetListBean userinfoBean) {
                        userinfo_callBack.showmyBetList(userinfoBean);
                    }
                });
    }

    @Override
    public void getmyBetFiles(userinfoMvp.userinfo_CallBack userinfo_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.myBetFiles(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<YatiBean>(userinfo_callBack) {
                    @Override
                    public void onNext(YatiBean userinfoBean) {
                        userinfo_callBack.showmyBetFiles(userinfoBean);
                    }
                });
    }

}
