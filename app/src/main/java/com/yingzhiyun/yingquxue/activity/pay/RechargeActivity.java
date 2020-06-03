package com.yingzhiyun.yingquxue.activity.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.PayMvp;
import com.yingzhiyun.yingquxue.OkBean.BalanceBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CoursePayBean;
import com.yingzhiyun.yingquxue.OkBean.RecordBean;
import com.yingzhiyun.yingquxue.OkBean.WxPAyBean;
import com.yingzhiyun.yingquxue.OkBean.YatijuanPayBean;
import com.yingzhiyun.yingquxue.OkBean.YitiPayinfo;
import com.yingzhiyun.yingquxue.OkBean.localbean.BalanceJson;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.mine.ChongzhishuomingActivity;
import com.yingzhiyun.yingquxue.adapter.YueAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.PayPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RechargeActivity extends BaseActicity<PayMvp.Pay_View, PayPresenter<PayMvp.Pay_View>> implements PayMvp.Pay_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.balance)
    TextView balancetext;
    @BindView(R.id.recy_balance)
    RecyclerView recyBalance;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.jiaoyijilu)
    TextView jiaoyijilu;
    private YueAdapter banlanceAdapter;
    private int s1 = 0;
    public static RechargeActivity instance = null;

    @Override
    protected void initData() throws ParseException {
        instance = this;
        ArrayList<Integer> strings = new ArrayList<>();
        strings.add(1);
        strings.add(6);
        strings.add(18);
        strings.add(30);
        strings.add(50);
        strings.add(98);
        strings.add(168);
        strings.add(198);
        jiaoyijilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ChongzhishuomingActivity.class);
            }
        });

        banlanceAdapter = new YueAdapter(this, strings);
        recyBalance.setLayoutManager(new GridLayoutManager(this, 3));
        recyBalance.setAdapter(banlanceAdapter);
        banlanceAdapter.setOnItemListener(new YueAdapter.OnItemListener() {
            @Override
            public void onClick(int pos, Integer s) {
                banlanceAdapter.setDefSelect(pos);
                s1 = s;
            }

            @Override
            public void OnEdClick(Integer s) {
                s1 = s;
            }
        });
        mPresentser.getBalance(new Gson().toJson(new BalanceJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), "Android")));
    }


    @Override
    public int createLayoutID() {
        return R.layout.activity_recharge;
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
                if (s1 == 0) {
                    ToastUtil.makeShortText(this, "请选择金额");
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt("number", s1);
                    startActivity(ShouyingActivity.class, bundle);
                }

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresentser.getBalance(new Gson().toJson(new BalanceJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), "Android")));
    }

    @Override
    public void setCoursePay(CoursePayBean coursePayBean) {

    }

    @Override
    public void setWxPay(WxPAyBean wxPay) {

    }

    @Override
    public void setquerypay(WxPAyBean wxPay) {

    }

    @Override
    public void setBalance(BalanceBean balance) {
        if (balance.getStatus() == 200) {
            balancetext.setText(balance.getResult().getBalance() + "");
        } else {
            ToastUtil.makeShortText(this, balance.getHint());
        }
    }

    @Override
    public void setcourseSignUp(CollectionTiBean collectionTiBean) {

    }

    @Override
    public void setRecharge4Android(WxPAyBean wxPAyBean) {

    }

    @Override
    public void setuserWalletRecord(RecordBean recordBean) {

    }

    @Override
    public void setyatipay(WxPAyBean yatijuanPayBean) {

    }

    @Override
    public void setyatiyue(WxPAyBean yatijuanPayBean) {

    }

    @Override
    public void setbetPaymentPage(YitiPayinfo yitiPayinfo) {

    }

    @Override
    protected PayPresenter<PayMvp.Pay_View> createPresenter() {
        return new PayPresenter<>();
    }


}
