package com.yingzhiyun.yingquxue.activity.pay;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.Mvp.PayMvp;
import com.yingzhiyun.yingquxue.OkBean.BalanceBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CoursePayBean;
import com.yingzhiyun.yingquxue.OkBean.RecordBean;
import com.yingzhiyun.yingquxue.OkBean.WxPAyBean;
import com.yingzhiyun.yingquxue.OkBean.YatijuanPayBean;
import com.yingzhiyun.yingquxue.OkBean.YitiPayinfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.PayPresenter;

import java.io.Serializable;
import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordInfoActivity extends BaseActicity<PayMvp.Pay_View, PayPresenter<PayMvp.Pay_View>> implements PayMvp.Pay_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.pay_tiltle)
    TextView payTiltle;
    @BindView(R.id.pay_number)
    TextView mingxi;
    @BindView(R.id.payzhuangtai)
    TextView payzhuangtai;
    @BindView(R.id.payway)
    TextView payway;
    @BindView(R.id.paytime)
    TextView paytime;
    @BindView(R.id.payorder)
    TextView payorder;

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

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() throws ParseException {
        RecordBean.ResultBean resultBean = (RecordBean.ResultBean) getIntent().getSerializableExtra("bean");
        assert resultBean != null;
        payTiltle.setText(resultBean.getBody());
        if(resultBean.getTransactionType()==1||resultBean.getTransactionType()==3){

            if(resultBean.getRefundStatus()==null){
                mingxi.setTextColor(Color.parseColor("#ff000000"));
                if(resultBean.getType()==1){
                    mingxi.setText("-"+resultBean.getTotalFree()+"趣学币");
                }else{
                    mingxi.setText("-"+resultBean.getTotalFree()+"元");
                }
            }else {
                if(resultBean.getRefundStatus().equals("1")){

                    mingxi.setTextColor(Color.parseColor("#ffdb253b"));
                    if(resultBean.getType()==1){
                        mingxi.setText("+"+resultBean.getTotalFree()+"趣学币");
                    }else{
                        mingxi.setText("+"+resultBean.getTotalFree()+"元");
                    }

                }else {

                    mingxi.setTextColor(Color.parseColor("#cccccc"));
                    if(resultBean.getType()==1){
                        mingxi.setText("+"+resultBean.getTotalFree()+"趣学币");
                    }else{
                        mingxi.setText("+"+resultBean.getTotalFree()+"元");
                    }
                }
            }



        }else  if(resultBean.getTransactionType()==2){
            mingxi.setTextColor(Color.parseColor("#ffdb253b"));
            if(resultBean.getType()==1){
                mingxi.setText("+"+resultBean.getTotalFree()+"趣学币");
            }else{
                mingxi.setText("+"+resultBean.getTotalFree()+"元");
            }
        }else if(resultBean.getTransactionType()==4){
            mingxi.setTextColor(Color.parseColor("#ff000000"));

            if(resultBean.getType()==1){
                mingxi.setText("-"+resultBean.getTotalFree()+"趣学币");
            }else if(resultBean.getType()==2){
                mingxi.setText("-"+resultBean.getTotalFree()+"元");
            }


        }else {
            mingxi.setTextColor(Color.parseColor("#ff000000"));

            mingxi.setText(resultBean.getTotalFree()+"趣学币");
        }
       if(resultBean.getType()==1){
           payway.setText("钱包支付");
       }else if(resultBean.getType()==2){
           payway.setText("微信支付");
       }else if(resultBean.getType()==3){
           payway.setText("支付宝支付");
       }else
       {
           payway.setText("苹果支付");
       }
       paytime.setText(resultBean.getTime());
       payorder.setText(resultBean.getOutTradeNo());
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_recordinfo;
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
