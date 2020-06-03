package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.CodeBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.LoginSuccesBean;
import com.yingzhiyun.yingquxue.OkBean.RegisterBean;
import com.yingzhiyun.yingquxue.OkBean.UpdatePassBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface BangdingMvp {

    interface Bangding_View extends BaseView {

        void setTPLogin(LoginSuccesBean loginSucces);
        void setTPLoginMatchesPhoneNum(CodeBean codeBean);
        void setTPLoginBinPhone(LoginSuccesBean loginSuccesBean);
        void setPwdByOpenid(LoginSuccesBean loginSucces);

    }

    interface Bangding_CallBack extends HttpFinishCallback {
        void showTPLogin(LoginSuccesBean codeBean);
        void showTPLoginMatchesPhoneNum(CodeBean codeBean);
        void showTPLoginBinPhone(LoginSuccesBean codeLogin);
        void showPwdByOpenid(LoginSuccesBean loginSucces);
    }

    interface Bangding_Modle {
        void getTPLogin(Bangding_CallBack login_callBack, String json,int type);
        void getTPLoginMatchesPhoneNum(Bangding_CallBack login_callBack, String json,int type);
        void getTPLoginBinPhone(Bangding_CallBack login_callBack, String json,int type);
        void getPwdByOpenid(Bangding_CallBack login_callBack, String json,int type);
    }
}
