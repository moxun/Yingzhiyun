package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.HudongIfoBean;
import com.yingzhiyun.yingquxue.OkBean.InteractionsListBean;
import com.yingzhiyun.yingquxue.OkBean.MyCollectBean;
import com.yingzhiyun.yingquxue.OkBean.MyInteractionListBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface InteractionsMvp {
    interface Interaction_View extends BaseView {

        void setInteractionList(InteractionsListBean interactionList);
        void setInteractionBean(HudongIfoBean hudongIfoBean);
        void setMyInteractionList(MyInteractionListBean myInteractionList);
        void setFanlkui(CollectBean collectBean);
    }

    interface Interaction_CallBack extends HttpFinishCallback {
        void showInteractionList(InteractionsListBean interactionsListBean);
        void showInteractionBean(HudongIfoBean hudongIfoBean);
        void showMyInteractionList(MyInteractionListBean myInteractionList);
        void showFanlkui(CollectBean collectBean);
    }

    interface Interaction_Modle {
        void getInteractionList(Interaction_CallBack interaction_callBack, String json);
        void getInteractionBean(Interaction_CallBack interaction_callBack,String json);
        void getMyInteractionList(Interaction_CallBack interaction_callBack,String json);
        void getFanlkui(Interaction_CallBack interaction_callBack,String json);
    }
}
