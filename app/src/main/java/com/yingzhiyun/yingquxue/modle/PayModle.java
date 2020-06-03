package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.PayMvp;
import com.yingzhiyun.yingquxue.Mvp.SContentMvp;
import com.yingzhiyun.yingquxue.OkBean.BalanceBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CoursePayBean;
import com.yingzhiyun.yingquxue.OkBean.RecordBean;
import com.yingzhiyun.yingquxue.OkBean.SearchcontentBean;
import com.yingzhiyun.yingquxue.OkBean.WxPAyBean;
import com.yingzhiyun.yingquxue.OkBean.YatijuanPayBean;
import com.yingzhiyun.yingquxue.OkBean.YitiPayinfo;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PayModle implements PayMvp.Pay_Modle {
    public  static FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    FristServer PayServer= HttpManager.getInstance().getServer(FristServer.PayURL, FristServer.class);
    @Override
    public void getCoursePay(PayMvp.Pay_CallBack sContent_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.coursePaymentPage(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CoursePayBean>(sContent_callBack) {
                    @Override
                    public void onNext(CoursePayBean value) {
                        sContent_callBack.showCoursePay(value);
                    }
                });
    }

    @Override
    public void getWxPay(PayMvp.Pay_CallBack sContent_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        PayServer.unifiedOrder(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WxPAyBean>(sContent_callBack) {
                    @Override
                    public void onNext(WxPAyBean value) {
                        sContent_callBack.showWxPay(value);
                    }
                });
    }

    @Override
    public void getquerypay(PayMvp.Pay_CallBack sContent_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        PayServer.orderQuery(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WxPAyBean>(sContent_callBack) {
                    @Override
                    public void onNext(WxPAyBean value) {
                        sContent_callBack.showquerypay(value);
                    }
                });
    }

    @Override
    public void getBalance(PayMvp.Pay_CallBack sContent_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.userWallet(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BalanceBean>(sContent_callBack) {
                    @Override
                    public void onNext(BalanceBean value) {
                        sContent_callBack.showBalance(value);
                    }
                });
    }

    @Override
    public void getcourseSignUp(PayMvp.Pay_CallBack sContent_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.courseSignUp(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectionTiBean>(sContent_callBack) {
                    @Override
                    public void onNext(CollectionTiBean value) {
                        sContent_callBack.showcourseSignUp(value);
                    }
                });
    }

    @Override
    public void getRecharge4Android(PayMvp.Pay_CallBack sContent_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        PayServer.Recharge4Android(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WxPAyBean>(sContent_callBack) {
                    @Override
                    public void onNext(WxPAyBean value) {
                        sContent_callBack.showRecharge4Android(value);
                    }
                });
    }

    @Override
    public void getuserWalletRecord(PayMvp.Pay_CallBack sContent_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.userWalletRecord(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<RecordBean>(sContent_callBack) {
                    @Override
                    public void onNext(RecordBean value) {
                        sContent_callBack.setuserWalletRecord(value);
                    }
                });
    }

    @Override
    public void getyatipay(PayMvp.Pay_CallBack sContent_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        PayServer.betBuy(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WxPAyBean>(sContent_callBack) {
                    @Override
                    public void onNext(WxPAyBean value) {
                        sContent_callBack.showyatipay(value);
                    }
                });
    }

    @Override
    public void getbetPaymentPage(PayMvp.Pay_CallBack sContent_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.betPaymentPage(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<YitiPayinfo>(sContent_callBack) {
                    @Override
                    public void onNext(YitiPayinfo value) {
                        sContent_callBack.showbetPaymentPage(value);
                    }
                });
    }

    @Override
    public void getyatiyue(PayMvp.Pay_CallBack pay_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.betBuy(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WxPAyBean>(pay_callBack) {
                    @Override
                    public void onNext(WxPAyBean value) {
                        pay_callBack.showyatiyue(value);
                    }
                });
    }
}
