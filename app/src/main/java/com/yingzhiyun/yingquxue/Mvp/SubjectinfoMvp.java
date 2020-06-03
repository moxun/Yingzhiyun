package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.SubjectInfoBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface SubjectinfoMvp {
    interface Subjectinfo_View extends BaseView {

        void setSubjectinfo(SubjectInfoBean subjectInfoBean);
    }

    interface Subjectinfo_CallBack extends HttpFinishCallback {
        void showSubjectinfo(SubjectInfoBean subjectInfoBean);
    }

    interface Subjectinfo_Modle {
        void getSubjectinfo(Subjectinfo_CallBack subjectinfo_callBack, String subjectid);
    }
}
