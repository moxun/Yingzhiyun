package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.ExaminationMvp;
import com.yingzhiyun.yingquxue.OkBean.AcoreQueryBean;
import com.yingzhiyun.yingquxue.OkBean.BaominginfoBean;
import com.yingzhiyun.yingquxue.OkBean.ExamAnalysisBean;
import com.yingzhiyun.yingquxue.OkBean.ExaminationListBean;
import com.yingzhiyun.yingquxue.OkBean.MyExamBean;
import com.yingzhiyun.yingquxue.OkBean.PracticeZuoBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ExaminationModle implements ExaminationMvp.Examination_Modle {
    FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void getExaminationList(ExaminationMvp.Examination_CallBack examine_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getexaminationList(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ExaminationListBean>(examine_callBack) {
                    @Override
                    public void onNext(ExaminationListBean examinationListBean) {
                        examine_callBack.showExaminationList(examinationListBean);
                    }
                });
    }

    @Override
    public void getexamDetail(ExaminationMvp.Examination_CallBack examine_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getexamDetail(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BaominginfoBean>(examine_callBack) {
                    @Override
                    public void onNext(BaominginfoBean examinationListBean) {
                        examine_callBack.showexamDetail(examinationListBean);
                    }
                });
    }

    @Override
    public void getexamsing(ExaminationMvp.Examination_CallBack examine_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getexamSign(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BaominginfoBean>(examine_callBack) {
                    @Override
                    public void onNext(BaominginfoBean examinationListBean) {
                        examine_callBack.showexamsing(examinationListBean);
                    }
                });
    }

    @Override
    public void setgoExam(ExaminationMvp.Examination_CallBack examine_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getgoExam(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<PracticeZuoBean>(examine_callBack) {
                    @Override
                    public void onNext(PracticeZuoBean examinationListBean) {
                        examine_callBack.showgoExam(examinationListBean);
                    }
                });
    }

    @Override
    public void getexamSubmit(ExaminationMvp.Examination_CallBack examine_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getexamSubmit(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BaominginfoBean>(examine_callBack) {
                    @Override
                    public void onNext(BaominginfoBean examinationListBean) {
                        examine_callBack.showexamSubmit(examinationListBean);
                    }
                });
    }

    @Override
    public void gwtexam(ExaminationMvp.Examination_CallBack examine_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getmyExam(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MyExamBean>(examine_callBack) {
                    @Override
                    public void onNext(MyExamBean examinationListBean) {
                        examine_callBack.showexam(examinationListBean);
                    }
                });
    }

    @Override
    public void getscorequery(ExaminationMvp.Examination_CallBack examine_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getscoreQuery(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<AcoreQueryBean>(examine_callBack) {
                    @Override
                    public void onNext(AcoreQueryBean examinationListBean) {
                        examine_callBack.showscorequery(examinationListBean);
                    }
                });
    }

    @Override
    public void getexamTimes(ExaminationMvp.Examination_CallBack examine_callBack, String json,String type) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getexamTimes(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BaominginfoBean>(examine_callBack) {
                    @Override
                    public void onNext(BaominginfoBean examinationListBean) {
                        examine_callBack.showexamTimes(examinationListBean,type);
                    }
                });
    }

    @Override
    public void getexamAnalysis(ExaminationMvp.Examination_CallBack examine_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getexamAnalysis(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ExamAnalysisBean>(examine_callBack) {
                    @Override
                    public void onNext(ExamAnalysisBean examinationListBean) {
                        examine_callBack.showexamAnalysis(examinationListBean);
                    }
                });
    }
}
