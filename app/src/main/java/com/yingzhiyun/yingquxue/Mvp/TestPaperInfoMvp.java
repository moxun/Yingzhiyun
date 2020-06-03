package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface TestPaperInfoMvp {
    interface TestPaperinfo_View extends BaseView {

        void setTestPaperinfo(TestPagperInfo testPaperinfo);
        void setCollectionti(CollectionTiBean collectionti);
    }

    interface TestPagperInfo_CallBack extends HttpFinishCallback {
        void showTestPaperList(TestPagperInfo testPagperInfo);
        void showCollectionti(CollectionTiBean collectionti);
    }

    interface TestPagperInfo_Modle {
        void getTestPagperInfo(TestPagperInfo_CallBack testPagperInfo_callBack, String json);
        void getCollectionti(TestPagperInfo_CallBack testPagperInfo_callBack, String json);
    }
}
