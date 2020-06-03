package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.OkBean.SubjectBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

import java.text.ParseException;

public interface ExamineMvp  {

    interface Examine_View extends BaseView {

        void setExamineBean(ExamineBean examineBean) throws ParseException;
        void setCollectionti(CollectionTiBean collectionti);
    }

    interface Examine_CallBack extends HttpFinishCallback {
        void showExamine(ExamineBean examineBean) throws ParseException;
        void showCollectionti(CollectionTiBean collectionti);
    }

    interface Examine_Modle {
        void getExamine(Examine_CallBack examine_callBack, String json,String url);
        void getCollectionti(Examine_CallBack examine_callBack, String json);
    }
}
