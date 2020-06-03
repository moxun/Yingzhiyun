package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.SubjectinfoMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.SubjectInfoBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

public class SubjectinfoModle implements SubjectinfoMvp.Subjectinfo_Modle {
    public  static FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void getSubjectinfo(SubjectinfoMvp.Subjectinfo_CallBack subjectinfo_callBack, String subjectid) {
     fristServer.getSubjectInfo(subjectid).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<SubjectInfoBean>(subjectinfo_callBack) {
                    @Override
                    public void onNext(SubjectInfoBean value) {
                        subjectinfo_callBack.showSubjectinfo(value);
                    }
                });
    }
}
