package com.yingzhiyun.yingquxue.modle;

import com.yingzhiyun.yingquxue.Mvp.InteractionsMvp;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.HudongIfoBean;
import com.yingzhiyun.yingquxue.OkBean.InteractionsListBean;
import com.yingzhiyun.yingquxue.OkBean.MyInteractionListBean;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class InteractionsModle implements InteractionsMvp.Interaction_Modle {
    FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void getInteractionList(InteractionsMvp.Interaction_CallBack interaction_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getInteractionsList(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<InteractionsListBean>(interaction_callBack) {
                    @Override
                    public void onNext(InteractionsListBean value) {
                        interaction_callBack.showInteractionList(value);
                    }
                });
    }

    @Override
    public void getInteractionBean(InteractionsMvp.Interaction_CallBack interaction_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getInteractionById(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HudongIfoBean>(interaction_callBack) {
                    @Override
                    public void onNext(HudongIfoBean hudongIfoBean) {
                        interaction_callBack.showInteractionBean(hudongIfoBean);
                    }
                });

    }

    @Override
    public void getMyInteractionList(InteractionsMvp.Interaction_CallBack interaction_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getMyInteractionList(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MyInteractionListBean>(interaction_callBack) {
                    @Override
                    public void onNext(MyInteractionListBean myInteractionListBean) {
                        interaction_callBack.showMyInteractionList(myInteractionListBean);
                    }
                });
    }

    @Override
    public void getFanlkui(InteractionsMvp.Interaction_CallBack interaction_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.userFeedback(requestBody)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectBean>(interaction_callBack) {
                    @Override
                    public void onNext(CollectBean myInteractionListBean) {
                        interaction_callBack.showFanlkui(myInteractionListBean);
                    }
                });
    }
}
