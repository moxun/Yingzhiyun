package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.MyCollectBean;
import com.yingzhiyun.yingquxue.OkBean.VideoinfoBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface VideoinfoMvp {
    interface Videoinfo_View extends BaseView {

        void setVideoinfo(VideoinfoBean videoinfo);
        void setCollectVideo(CollectBean collectVideo);
        void  setMyCollect(ZiyuanBean collectVideo);
    }

    interface Videoinfo_CallBack extends HttpFinishCallback {
        void showVideoinfo(VideoinfoBean videoinfoBean);
        void showCollectVideo(CollectBean collectVideo);
        void showMyCollect(ZiyuanBean collectBean);
    }

    interface Videoinfo_Modle {
        void getVideoinfo(Videoinfo_CallBack videoinfo_callBack, String json);
        void getCollectVideo(Videoinfo_CallBack videoinfo_callBack,String json);
        void getMyCollect(Videoinfo_CallBack videoinfo_callBack,String json);
    }
}
