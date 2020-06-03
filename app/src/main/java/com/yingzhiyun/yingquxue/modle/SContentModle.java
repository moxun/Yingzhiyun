package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.SContentMvp;
import com.yingzhiyun.yingquxue.OkBean.SearchcontentBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SContentModle implements SContentMvp.SContent_Modle {
    public  static FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void getSContent(SContentMvp.SContent_CallBack sContent_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getcontent(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<SearchcontentBean>(sContent_callBack) {
                    @Override
                    public void onNext(SearchcontentBean value) {
                        sContent_callBack.showSContent(value);
                    }
                });
    }
}
