package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.ChapterListBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.MyTiBean;
import com.yingzhiyun.yingquxue.OkBean.WrongtitleBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface WrongMvp {
    interface Wrong_View extends BaseView {

       void setWronglist(WrongtitleBean wronglist);
       void setWrong(MyTiBean myTiBean);
       void setdeleteWrong(CollectionTiBean collectionTiBean);
        void setCollection(MyTiBean myTiBean);
        void setCollectionti(CollectionTiBean collectionti);
        void setTiRecordlist(WrongtitleBean wronglist);
    }

    interface Wrong_CallBack extends HttpFinishCallback {
        void showWronglist(WrongtitleBean wronglist);
        void showWrong(MyTiBean myTiBean);
        void showdeleteWrong(CollectionTiBean collectionTiBean);
        void showCollection(MyTiBean myTiBean);
        void showCollectionti(CollectionTiBean collectionti);
        void showTiRecordlist(WrongtitleBean wronglist);
    }

    interface Wrong_Modle {
        void getWronglist(Wrong_CallBack wrong_callBack,String json);
        void getWrong(Wrong_CallBack wrong_callBack,String json);
        void getdeleteWrong(Wrong_CallBack wrong_callBack,String json);
        void getCollection(Wrong_CallBack wrong_callBack,String json);
        void getCollectionti(Wrong_CallBack testPagperInfo_callBack, String json);
        void getTiRecordlist(Wrong_CallBack testPagperInfo_callBack, String json);
    }
}
