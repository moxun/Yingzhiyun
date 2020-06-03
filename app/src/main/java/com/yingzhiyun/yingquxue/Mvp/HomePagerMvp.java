package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.LeftBean;
import com.yingzhiyun.yingquxue.OkBean.RightLeft;
import com.yingzhiyun.yingquxue.OkBean.VersionBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface HomePagerMvp {
    interface HomePager_View extends BaseView {

        void setHomePager(HomePagerBean homePager);
        void setVersion(VersionBean version);
        void setLeft(LeftBean leftBean);
        void setRight(RightLeft rightLeft);
    }

    interface HomePager_CallBack extends HttpFinishCallback {
        void showHomePager(HomePagerBean homePager);
        void showVersion(VersionBean version);
        void showLeft(LeftBean leftBean);
        void showRight(RightLeft rightLeft);
    }

    interface HomePager_Modle {
        void showHomePager(HomePager_CallBack homePager_callBack,String json);
        void getVersion(HomePager_CallBack homePager_callBack,String json);
        void getLeft(HomePager_CallBack homePager_callBack,String json);
        void getRight(HomePager_CallBack homePager_callBack,String json);
    }
}
