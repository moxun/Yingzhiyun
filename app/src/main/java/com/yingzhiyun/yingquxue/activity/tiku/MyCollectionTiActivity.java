package com.yingzhiyun.yingquxue.activity.tiku;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.WrongMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.WrongListJson;
import com.yingzhiyun.yingquxue.OkBean.MyTiBean;
import com.yingzhiyun.yingquxue.OkBean.WrongtitleBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.WrongListAdapter;
import com.yingzhiyun.yingquxue.adapter.WrongtitleAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.WrongPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCollectionTiActivity extends BaseActicity<WrongMvp.Wrong_View, WrongPresenter<WrongMvp.Wrong_View>> implements WrongMvp.Wrong_View {

    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.tv_senior)
    TextView tvSenior;
    @BindView(R.id.tv_junior)
    TextView tvJunior;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;
    @BindView(R.id.recy_text)
    RecyclerView recyText;
    private ArrayList<WrongtitleBean.ResultBean.DetailBean> highsubject;
    private ArrayList<WrongtitleBean.ResultBean.DetailBean> middlesubject;
    private ArrayList<WrongtitleBean.ResultBean.DetailBean> detailBeans;
    private WrongListAdapter wrongListAdapter;
    private int id;

    @Override
    public void setWronglist(WrongtitleBean wronglist) {

    }

    @Override
    public void setWrong(MyTiBean myTiBean) {

    }

    @Override
    public void setdeleteWrong(CollectionTiBean collectionTiBean) {

    }

    @Override
    public void setCollection(MyTiBean myTiBean) {

    }

    @Override
    public void setCollectionti(CollectionTiBean collectionti) {

    }

    @Override
    public void setTiRecordlist(WrongtitleBean wronglist) {
        if (wronglist.getStatus() == 200) {
            for (int i = 0; i < wronglist.getResult().size(); i++) {

                if (wronglist.getResult().get(i).getName().equals("初中")) {
                    middlesubject.addAll(wronglist.getResult().get(i).getDetail());
                } else {
                    highsubject.addAll(wronglist.getResult().get(i).getDetail());
                }
            }
            detailBeans.addAll(highsubject);
            wrongListAdapter.notifyDataSetChanged();
        } else {
            finish();
            ToastUtil.makeShortText(this, "身份过期");
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    protected WrongPresenter<WrongMvp.Wrong_View> createPresenter() {
        return new WrongPresenter<>();
    }

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id", 0);
        toolTitle.setText("收藏");
        detailBeans = new ArrayList<>();
        middlesubject = new ArrayList<>();
        highsubject = new ArrayList<>();
        wrongListAdapter = new WrongListAdapter(detailBeans, this, "收藏");
        recyText.setLayoutManager(new LinearLayoutManager(this));
        recyText.setAdapter(wrongListAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        middlesubject.clear();
        highsubject.clear();
        detailBeans.clear();
        mPresentser.getTiRecordlist(new Gson().toJson(new WrongListJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), id, MyApp.version, "Android")));
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_wrong;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    private int p=0;
    @OnClick({R.id.finish, R.id.tv_senior, R.id.tv_junior})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.tv_senior:
                if(p==0){
                    return;
                }
                p=0;
                tvSenior.setBackgroundResource(R.drawable.chooseleft);
                tvJunior.setBackgroundResource(R.drawable.righttuoyuan);
                tvSenior.setTextColor(Color.parseColor("#ffffff"));
                tvJunior.setTextColor(Color.parseColor("#1091E9"));
                detailBeans.clear();
                detailBeans.addAll(highsubject);
                wrongListAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_junior:
                if(p==1){
                    return;
                }
                p=1;
                tvSenior.setBackgroundResource(R.drawable.lefttuoyuan);
                tvJunior.setBackgroundResource(R.drawable.chosseright);
                tvSenior.setTextColor(Color.parseColor("#1091E9"));
                tvJunior.setTextColor(Color.parseColor("#ffffff"));
                detailBeans.clear();
                detailBeans.addAll(middlesubject);
                wrongListAdapter.notifyDataSetChanged();
                break;
        }
    }
}