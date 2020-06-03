package com.yingzhiyun.yingquxue.modle;

import android.util.Log;

import com.yingzhiyun.yingquxue.Mvp.HomePagerMvp;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.LeftBean;
import com.yingzhiyun.yingquxue.OkBean.RightLeft;
import com.yingzhiyun.yingquxue.OkBean.VersionBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static me.nereo.multi_image_selector.MultiImageSelectorFragment.TAG;

public class HomePagerModle implements HomePagerMvp.HomePager_Modle {
    FristServer fristServer=HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void showHomePager(HomePagerMvp.HomePager_CallBack homePager_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getHomepager(requestBody).compose(RxUtils.rxObserableSchedulerHelper()).subscribe(new BaseObserver<HomePagerBean>(homePager_callBack) {
            @Override
            public void onNext(HomePagerBean value) {
                homePager_callBack.showHomePager(value);
            }
        });
    }
    @Override
    public void getVersion(HomePagerMvp.HomePager_CallBack homePager_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.version(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<VersionBean>(homePager_callBack) {
                    @Override
                    public void onNext(VersionBean versionBean) {
                        homePager_callBack.showVersion(versionBean);
                    }
                });
    }

    @Override
    public void getLeft(HomePagerMvp.HomePager_CallBack homePager_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.indexListReloadLeft(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LeftBean>(homePager_callBack) {
                    @Override
                    public void onNext(LeftBean versionBean) {
                        homePager_callBack.showLeft(versionBean);
                    }
                });
    }

    @Override
    public void getRight(HomePagerMvp.HomePager_CallBack homePager_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.indexListReloadRoght(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<RightLeft>(homePager_callBack) {
                    @Override
                    public void onNext(RightLeft versionBean) {
                        homePager_callBack.showRight(versionBean);
                    }
                });
    }
}
