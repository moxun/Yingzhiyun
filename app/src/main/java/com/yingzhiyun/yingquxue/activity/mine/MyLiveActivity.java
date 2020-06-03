package com.yingzhiyun.yingquxue.activity.mine;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyLiveActivity extends SimpleActivity {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.recy_live)
    RecyclerView recyLive;

    @Override
    protected void initData() {

    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_mylive;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }



    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }
}
