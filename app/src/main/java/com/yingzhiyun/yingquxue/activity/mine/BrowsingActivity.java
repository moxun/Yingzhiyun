package com.yingzhiyun.yingquxue.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.BrowsingMvp;
import com.yingzhiyun.yingquxue.Mvp.InteractionsMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BrowsingBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.HomePagerJson;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.BrowsingAapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.presenter.BrowsingPrseenter;
import com.yingzhiyun.yingquxue.presenter.InteractionsPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BrowsingActivity extends BaseActicity<BrowsingMvp.Browsing_View, BrowsingPrseenter<BrowsingMvp.Browsing_View>> implements
        BrowsingMvp.Browsing_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.recy_live)
    RecyclerView recyLive;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    private ArrayList<BrowsingBean.ResultBean> resultBeans;
    private BrowsingAapter browsingAapter;

    @Override
    protected void initData() throws ParseException {

        resultBeans = new ArrayList<>();
        browsingAapter = new BrowsingAapter(this, resultBeans);
        recyLive.setLayoutManager(new LinearLayoutManager(this));
        recyLive.setAdapter(browsingAapter);
        mPresentser.get(new Gson().toJson(new HomePagerJson(SharedPreferenceUtils.getUserid()+"",SharedPreferenceUtils.getToken(), MyApp.version,"Android")));

    }

    @Override
    public int createLayoutID() {
        return R.layout.layour_browsing;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setBrowsing(BrowsingBean Browsing) {
        if(Browsing.getStatus()==200){
            if(Browsing.getResult().size()>0&&Browsing.getResult()!=null){
                linearModle.setVisibility(View.GONE);
                recyLive.setVisibility(View.VISIBLE);
                resultBeans.addAll(Browsing.getResult());
                browsingAapter.notifyDataSetChanged();
            }else {
                linearModle.setVisibility(View.VISIBLE);
                recyLive.setVisibility(View.GONE);
            }
        }else if(Browsing.getStatus()==511){
            finish();
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    protected BrowsingPrseenter<BrowsingMvp.Browsing_View> createPresenter() {
        return new BrowsingPrseenter<>();
    }
}
