package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.ExaminationMvp;
import com.yingzhiyun.yingquxue.Mvp.ExamineMvp;
import com.yingzhiyun.yingquxue.OkBean.AcoreQueryBean;
import com.yingzhiyun.yingquxue.OkBean.BaominginfoBean;
import com.yingzhiyun.yingquxue.OkBean.ExamAnalysisBean;
import com.yingzhiyun.yingquxue.OkBean.ExaminationListBean;
import com.yingzhiyun.yingquxue.OkBean.MyExamBean;
import com.yingzhiyun.yingquxue.OkBean.PracticeZuoBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.ExaminationModle;

public class ExaminationPresenter<V extends ExaminationMvp.Examination_View> extends ImlBasePresenter<ExaminationMvp.Examination_View>
        implements ExaminationMvp.Examination_CallBack {
    ExaminationModle examinationModle = new ExaminationModle();

    public  void getExaminationList(String json){
        examinationModle.getExaminationList(this,json);
    }
    public  void getexamDetail(String json){
        examinationModle.getexamDetail(this,json);
    }
    public  void getexamsing(String json){
        examinationModle.getexamsing(this,json);
    }
    public  void getgoExam(String json){
        examinationModle.setgoExam(this,json);
    }
    public  void getexamSubmit(String json){
        examinationModle.getexamSubmit(this,json);
    }
    public  void getexamTimes(String json,String type){
        examinationModle.getexamTimes(this,json,type);
    }
    public  void gwtexam(String json){
        examinationModle.gwtexam(this,json);
    }
    public  void getscorequery(String json){
        examinationModle.getscorequery(this,json);
    }

    public  void getexamAnalysis(String json){
        examinationModle.getexamAnalysis(this,json);
    }
    @Override
    public void showExaminationList(ExaminationListBean examineBean) {
        mView.setExaminationList(examineBean);
    }

    @Override
    public void showexamDetail(BaominginfoBean baominginfoBean) {
        mView.setexamDetail(baominginfoBean);
    }

    @Override
    public void showexamsing(BaominginfoBean baominginfoBean) {
        mView.setexamsing(baominginfoBean);
    }

    @Override
    public void showgoExam(PracticeZuoBean practiceZuoBean) {
        mView.setgoExam(practiceZuoBean);
    }

    @Override
    public void showexamSubmit(BaominginfoBean baominginfoBean) {
        mView.setexamSubmit(baominginfoBean);
    }

    @Override
    public void showexam(MyExamBean myExamBean) {
        mView.setexam(myExamBean);
    }

    @Override
    public void showscorequery(AcoreQueryBean acoreQueryBean) {
        mView.setscorequery(acoreQueryBean);
    }

    @Override
    public void showexamTimes(BaominginfoBean baominginfoBean,String string) {
        mView.setexamTimes(baominginfoBean,string);
    }

    @Override
    public void showexamAnalysis(ExamAnalysisBean examAnalysisBean) {
        mView.setexamAnalysis(examAnalysisBean);
    }

    @Override
    public void setShowProgressbar() {
        mView.showProgressbar();
    }

    @Override
    public void setHideProgressbar() {
        mView.hideProgressbar();
    }

    @Override
    public void setError(String error) {
        mView.showError(error);
    }
}
