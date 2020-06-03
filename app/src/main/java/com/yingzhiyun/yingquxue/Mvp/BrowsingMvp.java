package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.BrowsingBean;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.VersionBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface BrowsingMvp {

    interface Browsing_View extends BaseView {

        void setBrowsing(BrowsingBean Browsing);

    }

    interface Browsing_CallBack extends HttpFinishCallback {
        void showBrowsing(BrowsingBean Browsing);

    }

    interface Browsing_Modle {

        void getBrowsing(BrowsingMvp.Browsing_CallBack Browsing_callBack, String json);
    }
}
