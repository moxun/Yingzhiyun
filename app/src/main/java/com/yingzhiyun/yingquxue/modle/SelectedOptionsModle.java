package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.SelectedOptionsMvp;
import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.FolderListOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.OkBean.skillCourseListBeam;
import com.yingzhiyun.yingquxue.OkBean.skillTypeListBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SelectedOptionsModle implements SelectedOptionsMvp.SelectedOptions_Modle {
    public  static FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    public  static FristServer textServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void getSelectedOptions(SelectedOptionsMvp.SelectedOptions_CallBack selectedOptions_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getSelectedOptions(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<SelectedOptionsBean>(selectedOptions_callBack) {
                    @Override
                    public void onNext(SelectedOptionsBean value) {
                        selectedOptions_callBack.showSelectedOptions(value);
                    }
                });
    }

    @Override
    public void getZiyuan(SelectedOptionsMvp.SelectedOptions_CallBack selectedOptions_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        textServer.getZiyuan(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ZiyuanBean>(selectedOptions_callBack) {
                    @Override
                    public void onNext(ZiyuanBean value) {
                        selectedOptions_callBack.showZiyuan(value);
                    }
                });
    }

    @Override
    public void getCoursewareList(SelectedOptionsMvp.SelectedOptions_CallBack classify_callBack, String json) {
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

    @Override
    public void getskillTypeList(SelectedOptionsMvp.SelectedOptions_CallBack classify_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.skillTypeList(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<skillTypeListBean>(classify_callBack) {
                    @Override
                    public void onNext(skillTypeListBean coursewareListBean) {
                        classify_callBack.showskillTypeList(coursewareListBean);
                    }
                });
    }

    @Override
    public void getskillCourseList(SelectedOptionsMvp.SelectedOptions_CallBack classify_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.skillCourseList(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<skillCourseListBeam>(classify_callBack) {
                    @Override
                    public void onNext(skillCourseListBeam coursewareListBean) {
                        classify_callBack.showskillCourseList(coursewareListBean);
                    }
                });
    }

    @Override
    public void getfolderListOptions(SelectedOptionsMvp.SelectedOptions_CallBack classify_callBack) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),"");
        fristServer.getfolderListOptions(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<FolderListOptionsBean>(classify_callBack) {
                    @Override
                    public void onNext(FolderListOptionsBean folderListOptionsBean) {
                        classify_callBack.showfolderListOptions(folderListOptionsBean);
                    }
                });
    }

    @Override
    public void getfolderDetail(SelectedOptionsMvp.SelectedOptions_CallBack classify_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.folderDetail(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BashiinfoBean>(classify_callBack) {
                    @Override
                    public void onNext(BashiinfoBean coursewareListBean) {
                        classify_callBack.showfolderDetail(coursewareListBean);
                    }
                });
    }
}
