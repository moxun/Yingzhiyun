package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.BalanceBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CoursePayBean;
import com.yingzhiyun.yingquxue.OkBean.RecordBean;
import com.yingzhiyun.yingquxue.OkBean.SearchcontentBean;
import com.yingzhiyun.yingquxue.OkBean.WxPAyBean;
import com.yingzhiyun.yingquxue.OkBean.YatijuanPayBean;
import com.yingzhiyun.yingquxue.OkBean.YitiPayinfo;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface PayMvp {

    interface Pay_View extends BaseView {

        void setCoursePay(CoursePayBean coursePayBean);
        void setWxPay(WxPAyBean wxPay);
        void setquerypay(WxPAyBean wxPay);
        void setBalance(BalanceBean balance);
        void setcourseSignUp(CollectionTiBean collectionTiBean);
        void setRecharge4Android(WxPAyBean wxPAyBean);
        void setuserWalletRecord(RecordBean recordBean);

        void setyatipay(WxPAyBean yatijuanPayBean);
        void setyatiyue(WxPAyBean yatijuanPayBean);
        void setbetPaymentPage(YitiPayinfo yitiPayinfo);
    }

    interface Pay_CallBack extends HttpFinishCallback {
        void showCoursePay(CoursePayBean coursePayBean);
        void showWxPay(WxPAyBean wxPAyBean);
        void showquerypay(WxPAyBean wxPay);
        void showBalance(BalanceBean balance);
        void showcourseSignUp(CollectionTiBean collectionTiBean);
        void showRecharge4Android(WxPAyBean wxPAyBean);
        void setuserWalletRecord(RecordBean recordBean);
        void showyatipay(WxPAyBean yatijuanPayBean);
        void showbetPaymentPage(YitiPayinfo yitiPayinfo);
        void showyatiyue(WxPAyBean yatijuanPayBean);
    }

    interface Pay_Modle {
        void getCoursePay(Pay_CallBack sContent_callBack, String json);
        void getWxPay(Pay_CallBack sContent_callBack,String json);
        void getquerypay(Pay_CallBack sContent_callBack,String json);
        void getBalance(Pay_CallBack sContent_callBack,String json);
        void getcourseSignUp(Pay_CallBack sContent_callBack,String json);
        void getRecharge4Android(Pay_CallBack sContent_callBack,String json);
        void getuserWalletRecord(Pay_CallBack sContent_callBack,String json);
        void getyatipay(Pay_CallBack sContent_callBack,String json);
        void getbetPaymentPage(Pay_CallBack sContent_callBack,String json);
        void getyatiyue(Pay_CallBack pay_callBack,String json);
    }
}
