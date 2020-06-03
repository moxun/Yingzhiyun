package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.TikuMvp;
import com.yingzhiyun.yingquxue.Mvp.VideoinfoMvp;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.MyCollectBean;
import com.yingzhiyun.yingquxue.OkBean.VideoinfoBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class VideoinfoModle implements VideoinfoMvp.Videoinfo_Modle {
    FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void getVideoinfo(VideoinfoMvp.Videoinfo_CallBack videoinfo_callBack, String json) {


        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getVideoinfo(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<VideoinfoBean>(videoinfo_callBack) {
                    @Override
                    public void onNext(VideoinfoBean value) {
                        videoinfo_callBack.showVideoinfo(value);
                    }
                });
    }

    @Override
    public void getCollectVideo(VideoinfoMvp.Videoinfo_CallBack videoinfo_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getCollect(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectBean>(videoinfo_callBack) {
                    @Override
                    public void onNext(CollectBean value) {
                        videoinfo_callBack.showCollectVideo(value);

                    }
                });
    }

    @Override
    public void getMyCollect(VideoinfoMvp.Videoinfo_CallBack videoinfo_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getMyCollect(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ZiyuanBean>(videoinfo_callBack) {
                    @Override
                    public void onNext(ZiyuanBean value) {
                        videoinfo_callBack.showMyCollect(value);

                    }
                });
    }
}
