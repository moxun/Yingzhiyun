package com.yingzhiyun.yingquxue.activity.tiku;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.InteractionsMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.HudongIfoBean;
import com.yingzhiyun.yingquxue.OkBean.InteractionsListBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.BaseJson;
import com.yingzhiyun.yingquxue.OkBean.MyInteractionListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.HudongListAdpter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.InteractionsPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.yingzhiyun.yingquxue.units.VerticalSwipeRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HudongActivity extends BaseActicity<InteractionsMvp.Interaction_View, InteractionsPresenter<InteractionsMvp.Interaction_View>> implements
        InteractionsMvp.Interaction_View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.recy_book)
    RecyclerView recyBook;
    @BindView(R.id.sendmessage)
    ImageView sendmessage;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.refresh_layout)
    VerticalSwipeRefreshLayout refreshLayout;
    private ArrayList<InteractionsListBean.ResultBean> resultBeans;
    private HudongListAdpter hudongListAdpter;

    @Override
    protected void initData() {
        refreshLayout.setOnRefreshListener(this);
        recyBook.setEnabled(false);
        resultBeans = new ArrayList<>();
        hudongListAdpter = new HudongListAdpter(resultBeans, this);
        recyBook.setLayoutManager(new LinearLayoutManager(this));
        recyBook.setAdapter(hudongListAdpter);
        String s = new Gson().toJson(new BaseJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), MyApp.version, "Android"));
        mPresentser.getInteractionList(s);
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_hudong;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.finish, R.id.sendmessage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.sendmessage:
                if (SharedPreferenceUtils.getisLogin()) {
                    startActivity(SendQuestionActivity.class);
                } else {
                    startActivity(PwdLoginActivity.class);
                }
                break;
        }
    }

    @Override
    public void setInteractionList(InteractionsListBean interactionList) {
        if (interactionList.getStatus() == 200) {
            if (interactionList.getResult().size() > 0) {
                recyBook.setVisibility(View.VISIBLE);
                linearModle.setVisibility(View.GONE);
                resultBeans.addAll(interactionList.getResult());
                hudongListAdpter.notifyDataSetChanged();
            } else {
                recyBook.setVisibility(View.GONE);
                linearModle.setVisibility(View.VISIBLE);
            }
        } else if (interactionList.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, interactionList.getHint());
            startActivity(PwdLoginActivity.class);
        }

    }

    @Override
    public void setInteractionBean(HudongIfoBean hudongIfoBean) {

    }

    @Override
    public void setMyInteractionList(MyInteractionListBean myInteractionList) {

    }

    @Override
    public void setFanlkui(CollectBean collectBean) {

    }

    @Override
    protected InteractionsPresenter<InteractionsMvp.Interaction_View> createPresenter() {
        return new InteractionsPresenter<>();
    }


    @Override
    public void onRefresh() {
        resultBeans.clear();
        String s = new Gson().toJson(new BaseJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), MyApp.version, "Android"));
        mPresentser.getInteractionList(s);
        refreshLayout.setRefreshing(false);
    }
}
