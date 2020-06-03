package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.BetDetailBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.KnowledgeBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.OkBean.TestPaperBean;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface TestPaperListMvp {

    interface TestPaperList_View extends BaseView {

        void setTestPaperList(TestPaperListBean testPaperList);
        void setfilterItem(TeachingShaixuanBean teachingShaixuanBean);
        void setZiyuan(ZiyuanBean ziyuan);
        void setbetList(BetListBean betListBean);
        void setbetDetail(BetDetailBean betDetailBean);
    }

    interface TestPaperList_CallBack extends HttpFinishCallback {
        void showTestPaperList(TestPaperListBean testPaperListBean);
        void showfilterItem(TeachingShaixuanBean teachingShaixuanBean);
        void showZiyuan(ZiyuanBean ziyuan);
        void showbetList(BetListBean betListBean);
        void showbetDetail(BetDetailBean betDetailBean);
    }

    interface TestPaperList_Modle {
        void getTestPaperList(TestPaperList_CallBack testPaperList_callBack, String json);
        void getfilterItem(TestPaperList_CallBack zjietest_callBack, String json);
        void getZiyuan(TestPaperList_CallBack zjietest_callBack, String json);
        void getbetList(TestPaperList_CallBack zjietest_callBack, String json);
        void getbetDetail(TestPaperList_CallBack zjietest_callBack, String json);
    }
}
