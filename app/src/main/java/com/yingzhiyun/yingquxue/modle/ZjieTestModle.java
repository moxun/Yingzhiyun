package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.ZjietestMvp;
import com.yingzhiyun.yingquxue.OkBean.BookinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.ChapterListBean;
import com.yingzhiyun.yingquxue.OkBean.KnowPointerBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ZjieTestModle implements ZjietestMvp.Zjietest_Modle {
    FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    FristServer TestServer= HttpManager.getInstance().getServer(FristServer.TestUrl, FristServer.class);
    @Override
    public void getChapterList(ZjietestMvp.Zjietest_CallBack zjietest_callBack, String json,String title) {
        FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getChapterList(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ChapterListBean>(zjietest_callBack) {
                    @Override
                    public void onNext(ChapterListBean value) {
                        zjietest_callBack.showChapterList(value,title);
                    }
                });
    }



    @Override
    public void getBookList(ZjietestMvp.Zjietest_CallBack zjietest_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getBookList(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BooklistBean>(zjietest_callBack) {
                    @Override
                    public void onNext(BooklistBean value) {
                        zjietest_callBack.showBookList(value);
                    }
                });
    }

    @Override
    public void getKnowPointer(ZjietestMvp.Zjietest_CallBack zjietest_callBack, String json) {
        FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getKnowPointer(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<KnowPointerBean>(zjietest_callBack) {
                    @Override
                    public void onNext(KnowPointerBean knowPointerBean) {
                        zjietest_callBack.showKnowPointer(knowPointerBean);
                    }
                });


    }

    @Override
    public void getfilterItem(ZjietestMvp.Zjietest_CallBack zjietest_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        TestServer.filterItem(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<TeachingShaixuanBean>(zjietest_callBack) {
                    @Override
                    public void onNext(TeachingShaixuanBean knowPointerBean) {
                        zjietest_callBack.showfilterItem(knowPointerBean);
                    }
                });
    }

    @Override
    public void getBookinfo(ZjietestMvp.Zjietest_CallBack zjietest_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        TestServer.getBookinfo(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BookinfoBean>(zjietest_callBack) {
                    @Override
                    public void onNext(BookinfoBean knowPointerBean) {
                        zjietest_callBack.showbookinfo(knowPointerBean);
                    }
                });
    }
}
