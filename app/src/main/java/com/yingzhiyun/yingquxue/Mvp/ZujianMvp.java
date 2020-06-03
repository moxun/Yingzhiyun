package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.KnowledgeBean;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.OkBean.TestPaperBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface ZujianMvp {


    interface Zujian_View extends BaseView {

        void setKnowledge(KnowledgeBean knowledge);
        void setTestPaper(TestPagperInfo testPaper);
    }

    interface Zujian_CallBack extends HttpFinishCallback {
        void showKnowledge(KnowledgeBean knowledgeBean);
        void showTestPaPer(TestPagperInfo testPaperBean);
    }

    interface Zujian_Modle {
        void getKnowledge(Zujian_CallBack zujian_callBack, String json);
        void getTestPaper(Zujian_CallBack zujian_callBack,String json);
    }
}
