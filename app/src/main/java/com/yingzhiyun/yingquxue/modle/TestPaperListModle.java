package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.TestPaperListMvp;
import com.yingzhiyun.yingquxue.Mvp.VideoinfoMvp;
import com.yingzhiyun.yingquxue.OkBean.BetDetailBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.RightLeft;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TestPaperListModle implements TestPaperListMvp.TestPaperList_Modle {

    FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);




    @Override
    public void getTestPaperList(TestPaperListMvp.TestPaperList_CallBack testPaperList_callBack, String json) {
            RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
            fristServer.getTestPaperList(requestBody)
                    .compose(RxUtils.rxObserableSchedulerHelper())
                    .subscribe(new BaseObserver<TestPaperListBean>(testPaperList_callBack) {
                        @Override
                        public void onNext(TestPaperListBean value) {
                            testPaperList_callBack.showTestPaperList(value);
                        }
                    });
    }

    @Override
    public void getfilterItem(TestPaperListMvp.TestPaperList_CallBack zjietest_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.filterItem(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<TeachingShaixuanBean>(zjietest_callBack) {
                    @Override
                    public void onNext(TeachingShaixuanBean knowPointerBean) {
                        zjietest_callBack.showfilterItem(knowPointerBean);
                    }
                });
    }

    @Override
    public void getZiyuan(TestPaperListMvp.TestPaperList_CallBack zjietest_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getZiyuan(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ZiyuanBean>(zjietest_callBack) {
                    @Override
                    public void onNext(ZiyuanBean value) {
                        zjietest_callBack.showZiyuan(value);
                    }
                });
    }

    @Override
    public void getbetList(TestPaperListMvp.TestPaperList_CallBack zjietest_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.betList(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BetListBean>(zjietest_callBack) {
                    @Override
                    public void onNext(BetListBean rightLeft) {
                        zjietest_callBack.showbetList(rightLeft);
                    }
                });
    }

    @Override
    public void getbetDetail(TestPaperListMvp.TestPaperList_CallBack zjietest_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.betDetail(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BetDetailBean>(zjietest_callBack) {
                    @Override
                    public void onNext(BetDetailBean rightLeft) {
                        zjietest_callBack.showbetDetail(rightLeft);
                    }
                });
    }
}
