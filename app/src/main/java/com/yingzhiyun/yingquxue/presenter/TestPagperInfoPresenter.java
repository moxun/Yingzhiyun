package com.yingzhiyun.yingquxue.presenter;

import com.yingzhiyun.yingquxue.Mvp.TestPaperInfoMvp;
import com.yingzhiyun.yingquxue.Mvp.TestPaperListMvp;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.base.presenter.ImlBasePresenter;
import com.yingzhiyun.yingquxue.modle.TestPagperInfoMdole;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

public class TestPagperInfoPresenter <V extends TestPaperInfoMvp.TestPaperinfo_View> extends ImlBasePresenter<TestPaperInfoMvp.TestPaperinfo_View>
        implements TestPaperInfoMvp.TestPagperInfo_CallBack  {
    TestPagperInfoMdole testPagperInfoMdole=new TestPagperInfoMdole();
    public  void getTestPagperInfo(String json){
        testPagperInfoMdole.getTestPagperInfo(this,json);
    }
    public void getCollectionti(String json){
        testPagperInfoMdole.getCollectionti(this,json);
    }
    @Override
    public void showTestPaperList(TestPagperInfo testPagperInfo) {
        mView.setTestPaperinfo(testPagperInfo);
    }

    @Override
    public void showCollectionti(CollectionTiBean collectionti) {
        mView.setCollectionti(collectionti);
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
