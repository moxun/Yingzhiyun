package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.OkBean.MyTiBean;
import com.yingzhiyun.yingquxue.OkBean.WrongtitleBean;
import com.yingzhiyun.yingquxue.OkBean.ZutijiluBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface TiRecordMvp  {
    interface TiRecord_View extends BaseView {

        void setTiRecordlist(AllsubjectBean wronglist);
        void setjiLu(ZutijiluBean myTiBean);
        void setuserTestPaper(ExamineBean examineBean);

    }

    interface TiRecord_CallBack extends HttpFinishCallback {
        void showTiRecordlist(AllsubjectBean wronglist);
        void showjiLu(ZutijiluBean myTiBean);
        void showuserTestPaper(ExamineBean examineBean);
    }

    interface TiRecord_Modle {
        void getTiRecordlist(TiRecord_CallBack wrong_callBack, String json);
        void getjiLu(TiRecord_CallBack wrong_callBack, String json);
        void getuserTestPaper(TiRecord_CallBack wrong_callBack, String json);
    }
}
