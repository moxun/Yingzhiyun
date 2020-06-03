package com.yingzhiyun.yingquxue.activity.homepagr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.pay.FprecastPayActivity;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;

import java.io.Serializable;
import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForecastInfoActivity extends SimpleActivity {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.back_test)
    ImageView backTest;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private BetListBean.ResultBean bean;

    @Override
    protected void initData() throws ParseException {
        bean = (BetListBean.ResultBean) getIntent().getSerializableExtra("bean");
        Glide.with(this)
                .load(bean.getImg())
                .apply(new RequestOptions().override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).placeholder(R.color.white).error(R.color.white).dontAnimate())
                .into(backTest);
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_forecast;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.finish, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.btn_login:
                Bundle bundle = new Bundle();
                bundle.putInt("id",bean.getId());
                startActivity(FprecastPayActivity.class,bundle);
                break;
        }
    }
}
