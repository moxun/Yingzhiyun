package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;

public interface SaveAnswer {

    void sendMathMessage(TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean msg,String key,int position,String type);

    void backIntent(int th);

    void sendWenMessage(TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean.StemListBean msg,String key,int positon);
}
