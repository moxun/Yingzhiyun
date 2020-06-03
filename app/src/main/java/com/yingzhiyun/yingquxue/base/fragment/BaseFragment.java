package com.yingzhiyun.yingquxue.base.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.presenter.BasePresenter;
import com.yingzhiyun.yingquxue.base.view.BaseView;
import com.yingzhiyun.yingquxue.units.MMLoading;
import com.yingzhiyun.yingquxue.units.ToastUtil;


/**
 * Created by Administrator on 2018/12/21.
 */

public abstract class BaseFragment<V,P extends BasePresenter<V>>  extends  SimpleFragment implements BaseView {
    private static final String TAG = "moxun";
    public P presenter;
    private MMLoading mmLoading;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView((V) this);
        }
        load();
        super.onViewCreated(view, savedInstanceState);
    }
    private AlertDialog alertDialog;
    public void showLoadingDialog() {
        alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        alertDialog.setCancelable(false);
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK)
                    return true;
                return false;
            }
        });
        alertDialog.show();
        alertDialog.setContentView(R.layout.loading_alert);
        alertDialog.setCanceledOnTouchOutside(false);
    }

    public void dismissLoadingDialog() {
        if (null != alertDialog && alertDialog.isShowing()) {
            Log.d(TAG, "dismissLoadingDialog: ");
            alertDialog.dismiss();
        }
    }
    protected abstract P createPresenter();

    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.detachView();
            presenter=null;
        }
        super.onDestroyView();
    }

    //懒加载
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(presenter!=null){
                load();
            }
        }
    }

    //进行懒加载操作
    public void load() {

    }

    @Override
    public void showProgressbar() {
        showLoading();
    }

    @Override
    public void hideProgressbar() {
       
        hideLoading();
    }
    protected void showLoading() {
        if (mmLoading == null) {
            MMLoading.Builder builder = new MMLoading.Builder(getContext())
                    .setMessage("加载中...")
                    .setCancelable(false)
                    .setCancelOutside(true);
            mmLoading = builder.create();
        }else {
            mmLoading.dismiss();
            MMLoading.Builder builder = new MMLoading.Builder(getContext())
                    .setMessage("加载中...")
                    .setCancelable(false)
                    .setCancelOutside(true);
            mmLoading = builder.create();
        }
        mmLoading.show();
    }

    protected void showLoading(String msg) {
//        if (mmLoading == null) {
//            MMLoading.Builder builder = new MMLoading.Builder(getContext())
//                    .setMessage(msg)
//                    .setCancelable(false)
//                    .setCancelOutside(true);
//            mmLoading = builder.create();
//        }else {
//            mmLoading.dismiss();
//            MMLoading.Builder builder = new MMLoading.Builder(getContext())
//                    .setMessage(msg)
//                    .setCancelable(false)
//                    .setCancelOutside(false);
//            mmLoading = builder.create();
//        }
//        mmLoading.show();
    }

    protected void hideLoading() {
//        if (mmLoading != null && mmLoading.isShowing()) {
//            mmLoading.dismiss();
//        }
    }
    @Override
    public void showError(String error) {
//        if(getActivity().getSupportFragmentManager()!=null){
//            //如果是用的v4的包，则用getActivity().getSuppoutFragmentManager();
//            FragmentManager fm = getActivity().getSupportFragmentManager();
//            //注意v4包的配套使用
//            Fragment fragment = new ErrorFragment();
//            fm.beginTransaction().replace(R.id.fragment_group,fragment).commit();
//        }
        ToastUtil.makeLongText(getContext(),error);
    }
}
