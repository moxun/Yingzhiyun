package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.AcoreQueryBean;
import com.yingzhiyun.yingquxue.OkBean.BaominginfoBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.ExamAnalysisBean;
import com.yingzhiyun.yingquxue.OkBean.ExaminationListBean;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.OkBean.MyExamBean;
import com.yingzhiyun.yingquxue.OkBean.PracticeZuoBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

import java.text.ParseException;

public interface ExaminationMvp {

    interface Examination_View extends BaseView {

        void setExaminationList(ExaminationListBean examineBean);
        void setexamDetail(BaominginfoBean baominginfoBean);
        void setexamsing(BaominginfoBean baominginfoBean);
        void setgoExam(PracticeZuoBean practiceZuoBean);
        void setexamSubmit(BaominginfoBean baominginfoBean);
        void setexamTimes(BaominginfoBean baominginfoBean,String type);
        void setexam(MyExamBean myExamBean);
        void setscorequery(AcoreQueryBean acoreQueryBean);
        void setexamAnalysis(ExamAnalysisBean examAnalysisBean);
    }

    interface Examination_CallBack extends HttpFinishCallback {
        void showExaminationList(ExaminationListBean examineBean);
        void showexamDetail(BaominginfoBean baominginfoBean);
        void showexamsing(BaominginfoBean baominginfoBean);
        void showgoExam(PracticeZuoBean practiceZuoBean);
        void showexamSubmit(BaominginfoBean baominginfoBean);
        void showexam(MyExamBean myExamBean);
        void showscorequery(AcoreQueryBean acoreQueryBean);
        void showexamTimes(BaominginfoBean baominginfoBean,String type);
        void showexamAnalysis(ExamAnalysisBean examAnalysisBean);
    }

    interface Examination_Modle {
        void getExaminationList(Examination_CallBack examine_callBack, String json);
        void getexamDetail(Examination_CallBack examine_callBack, String json);
        void getexamsing(Examination_CallBack examine_callBack, String json);
        void setgoExam(Examination_CallBack examine_callBack,String json);
        void getexamSubmit(Examination_CallBack examine_callBack,String json);
        void gwtexam(Examination_CallBack examine_callBack,String json);
        void getscorequery(Examination_CallBack examine_callBack,String json);
        void getexamTimes(Examination_CallBack examine_callBack,String json,String type);
        void getexamAnalysis(Examination_CallBack examine_callBack,String json);
    }
}
