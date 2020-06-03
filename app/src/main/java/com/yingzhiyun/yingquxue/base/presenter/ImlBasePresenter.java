package com.yingzhiyun.yingquxue.base.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/12/21.
 */

public class ImlBasePresenter<V> implements BasePresenter<V> {
    private WeakReference<V> mWeakReference;
    public V  mView;
    @Override
    public void attachView(V view) {
        mWeakReference=new WeakReference<V>(view);
        mView = mWeakReference.get();
    }

    @Override
    public void detachView() {
        if(mWeakReference!=null&&mWeakReference.get()!=null){
            mWeakReference.clear();
            mWeakReference=null;
        }
    }
}
