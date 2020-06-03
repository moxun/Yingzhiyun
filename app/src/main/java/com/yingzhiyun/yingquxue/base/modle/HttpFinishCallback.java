package com.yingzhiyun.yingquxue.base.modle;

/**
 * Created by Administrator on 2018/12/21.
 */

public interface HttpFinishCallback {
    void setShowProgressbar();
    void setHideProgressbar();
    void setError(String error);
}
