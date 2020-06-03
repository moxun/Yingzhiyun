package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.CoursewareListBean;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.SubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface ClassifyMvp {
    interface Classify_View extends BaseView {

        void setSubject(SubjectBean subject);
        void setCoursewareList(ZiyuanBean coursewareList);
    }

    interface Classify_CallBack extends HttpFinishCallback {
        void showSubject(SubjectBean subjectBean);
        void showCoursewareList(ZiyuanBean coursewareList);
    }

    interface Classify_Modle {
        void getSubject(Classify_CallBack classify_callBack,String schoolType);
        void getCoursewareList(Classify_CallBack classify_callBack,String json);
    }
}
