package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.BookinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.ChapterListBean;
import com.yingzhiyun.yingquxue.OkBean.KnowPointerBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.OkBean.VideoinfoBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface ZjietestMvp {
    interface Zjietest_View extends BaseView {

        void setChapterList(ChapterListBean chapterListBean,String title);
        void setBookList(BooklistBean booklistBean);
        void setKnowPointer(KnowPointerBean knowPointer);
        void setfilterItem(TeachingShaixuanBean teachingShaixuanBean);
        void setbookinfo(BookinfoBean bookinfoBean);
    }

    interface Zjietest_CallBack extends HttpFinishCallback {
        void showChapterList(ChapterListBean chapterListBean,String title);
        void showBookList(BooklistBean booklistBean);
        void showKnowPointer(KnowPointerBean knowPointer);
        void showfilterItem(TeachingShaixuanBean teachingShaixuanBean);
        void showbookinfo(BookinfoBean bookinfoBean);
    }

    interface Zjietest_Modle {
        void getChapterList(Zjietest_CallBack zjietest_callBack, String json,String title);
        void getBookList(Zjietest_CallBack zjietest_callBack, String json);
        void getKnowPointer(Zjietest_CallBack zjietest_callBack, String json);
        void getfilterItem(Zjietest_CallBack zjietest_callBack, String json);
        void getBookinfo(Zjietest_CallBack zjietest_callBack, String json);
    }
}
