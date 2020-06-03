package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.TikuMvp;
import com.yingzhiyun.yingquxue.Mvp.ZujianMvp;
import com.yingzhiyun.yingquxue.OkBean.KnowledgeBean;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.OkBean.TestPaperBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.ZujianModle;

public class ZujuanPresenter <V extends ZujianMvp.Zujian_View> extends ImlBasePresenter<ZujianMvp.Zujian_View> implements ZujianMvp.Zujian_CallBack {
    ZujianModle zujianModle=new ZujianModle();
    public  void getKnowledge(String json){
        zujianModle.getKnowledge(this,json);
    }
    @Override
    public void showKnowledge(KnowledgeBean knowledgeBean) {
        mView.setKnowledge(knowledgeBean);
    }

    public void getTestPaper(String json){
        zujianModle.getTestPaper(this,json);
    }

    @Override
    public void showTestPaPer(TestPagperInfo testPaperBean) {
        mView.setTestPaper(testPaperBean);
    }

    @Override
    public void setShowProgressbar() {

    }

    @Override
    public void setHideProgressbar() {

    }

    @Override
    public void setError(String error) {
        mView.showError(error);
    }
}
