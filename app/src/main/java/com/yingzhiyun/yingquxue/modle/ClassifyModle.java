package com.yingzhiyun.yingquxue.modle;

import android.util.Log;

import com.yingzhiyun.yingquxue.Mvp.ClassifyMvp;
import com.yingzhiyun.yingquxue.OkBean.CoursewareListBean;
import com.yingzhiyun.yingquxue.OkBean.SubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ClassifyModle implements ClassifyMvp.Classify_Modle {
    FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void getSubject(ClassifyMvp.Classify_CallBack classify_callBack, String schoolType) {
        fristServer.getItem(schoolType).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<SubjectBean>(classify_callBack) {
                    @Override
                    public void onNext(SubjectBean value) {
                        classify_callBack.showSubject(value);
                    }
                });
    }

    @Override
    public void getCoursewareList(ClassifyMvp.Classify_CallBack classify_callBack, String json) {

        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getCoursewareList(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ZiyuanBean>(classify_callBack) {
                    @Override
                    public void onNext(ZiyuanBean coursewareListBean) {
                        classify_callBack.showCoursewareList(coursewareListBean);
                    }
                });
    }
}
