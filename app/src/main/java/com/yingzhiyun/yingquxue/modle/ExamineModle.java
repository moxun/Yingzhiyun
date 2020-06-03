package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.ExamineMvp;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import java.text.ParseException;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ExamineModle implements ExamineMvp.Examine_Modle {
    FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void getExamine(ExamineMvp.Examine_CallBack examine_callBack, String json,String url) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.gettestPaperCheck(requestBody,url)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ExamineBean>(examine_callBack) {
                    @Override
                    public void onNext(ExamineBean value) {
                        try {
                            examine_callBack.showExamine(value);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void getCollectionti(ExamineMvp.Examine_CallBack examine_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.userItemBankCollection(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectionTiBean>(examine_callBack) {
                    @Override
                    public void onNext(CollectionTiBean collectionTiBean) {
                        examine_callBack.showCollectionti(collectionTiBean);
                    }
                });
    }
}
