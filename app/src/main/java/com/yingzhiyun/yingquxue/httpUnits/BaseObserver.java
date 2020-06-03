package com.yingzhiyun.yingquxue.httpUnits;



import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;


import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by Administrator on 2018/12/21.
 */

public abstract class BaseObserver<T> implements Observer<T> {
    private HttpFinishCallback mHttpFinishCallback;
    public BaseObserver(HttpFinishCallback httpFinishCallback) {
        this.mHttpFinishCallback = httpFinishCallback;
    }

    //管理内存网络请求
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    @Override
    public void onSubscribe(Disposable d) {

        compositeDisposable.add(d);
    }

    @Override
    public void onError(Throwable e) {

        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        if (mHttpFinishCallback != null) {
            if (e instanceof SeveryEcception) {
                SeveryEcception severyEcception = (SeveryEcception) e;
                mHttpFinishCallback.setError(severyEcception.getMessage());
            }else{
                mHttpFinishCallback.setError("网络错误");
            }
            mHttpFinishCallback.setHideProgressbar();

        }
    }
    @Override
    public void onComplete() {
        if(compositeDisposable!=null){
            compositeDisposable.clear();
            mHttpFinishCallback.setHideProgressbar();
        }
    }


}
