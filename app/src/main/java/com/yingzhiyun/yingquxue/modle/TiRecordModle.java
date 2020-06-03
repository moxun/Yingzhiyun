package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.TiRecordMvp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.OkBean.WrongtitleBean;
import com.yingzhiyun.yingquxue.OkBean.ZutijiluBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class TiRecordModle implements TiRecordMvp.TiRecord_Modle {
    FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void getTiRecordlist(TiRecordMvp.TiRecord_CallBack wrong_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getTestPaperCollectionSubjectList(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<AllsubjectBean>(wrong_callBack) {
                    @Override
                    public void onNext(AllsubjectBean wrongtitleBean) {
                        wrong_callBack.showTiRecordlist(wrongtitleBean);
                    }
                });
    }

    @Override
    public void getjiLu(TiRecordMvp.TiRecord_CallBack wrong_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.userTestPaperCollectionList(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ZutijiluBean>(wrong_callBack) {
                    @Override
                    public void onNext(ZutijiluBean zutijiluBean) {
                        wrong_callBack.showjiLu(zutijiluBean);
                    }
                });
    }

    @Override
    public void getuserTestPaper(TiRecordMvp.TiRecord_CallBack wrong_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.userTestPaper(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ExamineBean>(wrong_callBack) {
                    @Override
                    public void onNext(ExamineBean examineBean) {
                        wrong_callBack.showuserTestPaper(examineBean);
                    }
                });
    }
}
