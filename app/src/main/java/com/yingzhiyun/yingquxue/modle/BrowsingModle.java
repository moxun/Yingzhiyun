package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.BrowsingMvp;
import com.yingzhiyun.yingquxue.OkBean.BrowsingBean;
import com.yingzhiyun.yingquxue.OkBean.LoginSuccesBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BrowsingModle implements BrowsingMvp.Browsing_Modle {
    FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void getBrowsing(BrowsingMvp.Browsing_CallBack Browsing_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.userHistoricRecord(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BrowsingBean>(Browsing_callBack) {
                    @Override
                    public void onNext(BrowsingBean loginSuccesBean) {
                        Browsing_callBack.showBrowsing(loginSuccesBean);
                    }
                });
    }
}
