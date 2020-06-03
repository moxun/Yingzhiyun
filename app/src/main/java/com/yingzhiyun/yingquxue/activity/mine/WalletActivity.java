package com.yingzhiyun.yingquxue.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.pay.RechargeActivity;
import com.yingzhiyun.yingquxue.activity.pay.RecordActivity;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.PayPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalletActivity extends BaseActicity<PayMvp.Pay_View, PayPresenter<PayMvp.Pay_View>> implements PayMvp.Pay_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.jiaoyijilu)
    TextView jiaoyijilu;
    @BindView(R.id.blance_number)
    TextView blanceNumber;
    @BindView(R.id.chongzhi)
    ImageView chongzhi;

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
        if(balance.getStatus()==200){
            blanceNumber.setText(balance.getResult().getBalance()+"");
        }else if(balance.getStatus()==511){
            finish();
            startActivity(PwdLoginActivity.class);
            ToastUtil.makeShortText(this,balance.getHint());
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

    @Override
    protected void initData() throws ParseException {
        mPresentser.getBalance(new Gson().toJson(new BalanceJson(SharedPreferenceUtils.getUserid(),SharedPreferenceUtils.getToken(),"Android")));
    }

    @Override
    public int createLayoutID() {
        return R.layout.wallet;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }



    @OnClick({R.id.finish, R.id.jiaoyijilu, R.id.chongzhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.jiaoyijilu:
                startActivity(RecordActivity.class);
                break;
            case R.id.chongzhi:
                startActivity(RechargeActivity.class);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresentser.getBalance(new Gson().toJson(new BalanceJson(SharedPreferenceUtils.getUserid(),SharedPreferenceUtils.getToken(),"Android")));

    }
}
