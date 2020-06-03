package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface TikuMvp {
    interface Tiku_View extends BaseView {

        void setAllSubject(AllsubjectBean allSubject);

    }

    interface Tiku_CallBack extends HttpFinishCallback {
        void showAllSubject(AllsubjectBean allsubjectBean);

    }

    interface Tiku_Modle {
        void getAllSubject(Tiku_CallBack tiku_callBack, String json);

    }
}
