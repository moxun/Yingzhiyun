package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.SchoolBean;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.UserinfoBean;
import com.yingzhiyun.yingquxue.OkBean.YatiBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface userinfoMvp {

    interface userinfo_View extends BaseView {

        void setuserinfo(UserinfoBean userinfoBean);

        void setSchool(SchoolBean school);

        void setupdateinfo(CollectBean collectBean);

        void setmyBetList(BetListBean betListBean);

        void setmyBetFiles(YatiBean bashiinfoBean);
    }

    interface userinfo_CallBack extends HttpFinishCallback {
        void showuserinfo(UserinfoBean selectedOptionsBean);

        void showSchool(SchoolBean schoolBean);

        void showupdateinfo(CollectBean collectBean);

        void showmyBetList(BetListBean betListBean);

        void showmyBetFiles(YatiBean bashiinfoBean);
    }

    interface userinfo_Modle {
        void getuserinfo(userinfo_CallBack userinfo_callBack, String json);

        void getSchool(userinfo_CallBack userinfo_callBack, String json);

        void getupdateinfo(userinfo_CallBack userinfo_callBack, String json);

        void getmyBetList(userinfo_CallBack userinfo_callBack, String json);

        void getmyBetFiles(userinfo_CallBack userinfo_callBack, String json);
    }
}
