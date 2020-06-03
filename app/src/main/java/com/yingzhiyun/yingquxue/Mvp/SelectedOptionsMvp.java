package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.FolderListOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.SubjectInfoBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.OkBean.skillCourseListBeam;
import com.yingzhiyun.yingquxue.OkBean.skillTypeListBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface SelectedOptionsMvp {
    interface SelectedOptions_View extends BaseView {

        void setSelectedOptions(SelectedOptionsBean selectedOptions);
        void setZiyuan(ZiyuanBean ziyuan);
        void setCoursewareList(ZiyuanBean coursewareList);
        void setskillTypeList(skillTypeListBean skillTypeListBean);
        void setskillCourseList(skillCourseListBeam skillCourseListBeam);
        void setfolderListOptions(FolderListOptionsBean folderListOptionsBean);

        void setfolderDetail(BashiinfoBean bashiinfoBean);
    }

    interface SelectedOptions_CallBack extends HttpFinishCallback {
        void showSelectedOptions(SelectedOptionsBean selectedOptionsBean);
        void showZiyuan(ZiyuanBean ziyuan);
        void showCoursewareList(ZiyuanBean coursewareList);
        void showskillTypeList(skillTypeListBean skillTypeListBean);
        void showskillCourseList(skillCourseListBeam skillCourseListBeam);
        void showfolderListOptions(FolderListOptionsBean folderListOptionsBean);
        void showfolderDetail(BashiinfoBean bashiinfoBean);
    }

    interface SelectedOptions_Modle {
        void getSelectedOptions(SelectedOptions_CallBack selectedOptions_callBack, String json);
        void getZiyuan(SelectedOptions_CallBack selectedOptions_callBack, String json);
        void getCoursewareList(SelectedOptions_CallBack classify_callBack,String json);
        void getskillTypeList(SelectedOptions_CallBack classify_callBack,String json);
        void getskillCourseList(SelectedOptions_CallBack classify_callBack,String json);
        void getfolderListOptions(SelectedOptions_CallBack classify_callBack);
        void getfolderDetail(SelectedOptions_CallBack classify_callBack,String json);
    }
}
