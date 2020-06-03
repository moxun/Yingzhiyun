package com.yingzhiyun.yingquxue.activity.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaySuccseActivity extends SimpleActivity {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.image_succeed)
    ImageView imageSucceed;
    @BindView(R.id.wanch)
    TextView wanch;
    @BindView(R.id.pay_size)
    TextView paySize;
    @BindView(R.id.benci)
    LinearLayout benci;
    @BindView(R.id.blance)
    TextView blance;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    @Override
    protected void initData() throws ParseException {

    }

    @Override
    public int createLayoutID() {
        return R.layout.paysucse;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }



    @OnClick({R.id.finish, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                break;
            case R.id.btn_login:
                break;
        }
    }
}
