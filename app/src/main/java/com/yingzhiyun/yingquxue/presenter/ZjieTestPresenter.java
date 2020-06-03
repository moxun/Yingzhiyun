package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.ZjietestMvp;
import com.yingzhiyun.yingquxue.Mvp.ZujianMvp;
import com.yingzhiyun.yingquxue.OkBean.BookinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.ChapterListBean;
import com.yingzhiyun.yingquxue.OkBean.KnowPointerBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.ClassifyModle;
import com.yingzhiyun.yingquxue.modle.ZjieTestModle;

public class ZjieTestPresenter<V extends ZjietestMvp.Zjietest_View> extends ImlBasePresenter<ZjietestMvp.Zjietest_View> implements ZjietestMvp.Zjietest_CallBack {

    public ZjieTestModle zjieTestModle=new ZjieTestModle();
    public void  getBookList(String json){
        zjieTestModle.getBookList(this,json);

    }
    public void  getfilterItem(String json){
        zjieTestModle.getfilterItem(this,json);

    }
    public void  getChapterList(String json,String title){

        zjieTestModle.getChapterList(this,json,title);
    }
    public void  getBookinfo(String json){

        zjieTestModle.getBookinfo(this,json);
    }
    public  void get(String json){
        zjieTestModle.getKnowPointer(this,json);
    }
    @Override
    public void showChapterList(ChapterListBean chapterListBean,String string) {
        mView.setChapterList(chapterListBean,string);
    }

    @Override
    public void showBookList(BooklistBean booklistBean) {
        mView.setBookList(booklistBean);
    }

    @Override
    public void showKnowPointer(KnowPointerBean knowPointer) {
        mView.setKnowPointer(knowPointer);
    }

    @Override
    public void showfilterItem(TeachingShaixuanBean teachingShaixuanBean) {
        mView.setfilterItem(teachingShaixuanBean);
    }

    @Override
    public void showbookinfo(BookinfoBean bookinfoBean) {
        mView.setbookinfo(bookinfoBean);
    }

    @Override
    public void setShowProgressbar() {
        mView.showProgressbar();
    }

    @Override
    public void setHideProgressbar() {
        mView.hideProgressbar();
    }

    @Override
    public void setError(String error) {
        mView.showError(error);
    }
}
