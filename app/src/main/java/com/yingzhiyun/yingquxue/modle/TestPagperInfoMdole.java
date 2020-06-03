package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.TestPaperInfoMvp;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TestPagperInfoMdole implements TestPaperInfoMvp.TestPagperInfo_Modle {
    FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void getTestPagperInfo(TestPaperInfoMvp.TestPagperInfo_CallBack testPagperInfo_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getTestPaperinfo(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<TestPagperInfo>(testPagperInfo_callBack) {
                    @Override
                    public void onNext(TestPagperInfo value) {
                        testPagperInfo_callBack.showTestPaperList(value);
                    }
                });
    }

    @Override
    public void getCollectionti(TestPaperInfoMvp.TestPagperInfo_CallBack testPagperInfo_callBack, String json) {
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
}
