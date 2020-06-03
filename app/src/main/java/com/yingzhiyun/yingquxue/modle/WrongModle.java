package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.WrongMvp;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.MyTiBean;
import com.yingzhiyun.yingquxue.OkBean.WrongtitleBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class WrongModle implements WrongMvp.Wrong_Modle {
    public  static FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void getWronglist(WrongMvp.Wrong_CallBack wrong_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getWrongSubjectList(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WrongtitleBean>(wrong_callBack) {
                    @Override
                    public void onNext(WrongtitleBean value) {
                        wrong_callBack.showWronglist(value);
                    }
                });
    }

    @Override
    public void getWrong(WrongMvp.Wrong_CallBack wrong_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.wrongTitleList(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MyTiBean>(wrong_callBack) {
                    @Override
                    public void onNext(MyTiBean myTiBean) {
                        wrong_callBack.showWrong(myTiBean);
                    }
                });
    }

    @Override
    public void getdeleteWrong(WrongMvp.Wrong_CallBack wrong_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.wrongDelete(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectionTiBean>(wrong_callBack) {
                    @Override
                    public void onNext(CollectionTiBean collectionTiBean) {
                        wrong_callBack.showdeleteWrong(collectionTiBean);

                    }
                });
    }

    @Override
    public void getCollection(WrongMvp.Wrong_CallBack wrong_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.userItemCollectionList(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MyTiBean>(wrong_callBack) {
                    @Override
                    public void onNext(MyTiBean myTiBean) {
                        wrong_callBack.showCollection(myTiBean);
                    }
                });
    }

    @Override
    public void getCollectionti(WrongMvp.Wrong_CallBack testPagperInfo_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.userItemBankCollection(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectionTiBean>(testPagperInfo_callBack) {
                    @Override
                    public void onNext(CollectionTiBean collectionTiBean) {
                        testPagperInfo_callBack.showCollectionti(collectionTiBean);
                    }
                });
    }

    @Override
    public void getTiRecordlist(WrongMvp.Wrong_CallBack testPagperInfo_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getCollectionSubjectList(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WrongtitleBean>(testPagperInfo_callBack) {
                    @Override
                    public void onNext(WrongtitleBean value) {
                        testPagperInfo_callBack.showTiRecordlist(value);
                    }
                });
    }
}
