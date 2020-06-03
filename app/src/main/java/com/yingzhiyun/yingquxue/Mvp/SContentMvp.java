package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.SearchcontentBean;
import com.yingzhiyun.yingquxue.OkBean.SubjectBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface SContentMvp {
    interface SContent_View extends BaseView {

        void setSContent(SearchcontentBean sContent);
    }

    interface SContent_CallBack extends HttpFinishCallback {
        void showSContent(SearchcontentBean searchcontentBean);
    }

    interface SContent_Modle {
        void getSContent(SContent_CallBack sContent_callBack, String json);
    }
}
