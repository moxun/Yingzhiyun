package com.yingzhiyun.yingquxue.activity.pay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.PayMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BalanceBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CoursePayBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.HomePagerJson;
import com.yingzhiyun.yingquxue.OkBean.RecordBean;
import com.yingzhiyun.yingquxue.OkBean.RegisterBean;
import com.yingzhiyun.yingquxue.OkBean.WxPAyBean;
import com.yingzhiyun.yingquxue.OkBean.YatijuanPayBean;
import com.yingzhiyun.yingquxue.OkBean.YitiPayinfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.JyRecrordAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils;
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack;
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback;
import com.yingzhiyun.yingquxue.presenter.PayPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;

public class RecordActivity extends BaseActicity<PayMvp.Pay_View, PayPresenter<PayMvp.Pay_View>> implements PayMvp.Pay_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.recy_record)
    PullLoadMoreRecyclerView rlRoot;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private ArrayList<RecordBean.ResultBean> recordBeans;
    private JyRecrordAdapter jyRecrordAdapter;

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
        if (recordBean.getStatus() == 200) {
            if(recordBean.getResult().size()>0){
                rlRoot.setVisibility(View.VISIBLE);
                linearModle.setVisibility(View.GONE);
                recordBeans.addAll(recordBean.getResult());
                jyRecrordAdapter.notifyDataSetChanged();
            }else {
                rlRoot.setVisibility(View.GONE);
                linearModle.setVisibility(View.VISIBLE);
            }
        } else if(recordBean.getStatus()==511){
            ToastUtil.makeShortText(this,"身份信息过期请重新登录");
            finish();
            startActivity(PwdLoginActivity.class);
        }else{
            ToastUtil.makeShortText(this,recordBean.getHint());
        }
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
    private int page=1;
    @Override
    protected void initData() throws ParseException {
        mPresentser.getuserWalletRecord(new Gson().toJson(new HomePagerJson(SharedPreferenceUtils.getUserid() + "", SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
        recordBeans = new ArrayList<>();
        jyRecrordAdapter = new JyRecrordAdapter(recordBeans,this);
        rlRoot.setLinearLayout();
        rlRoot.setAdapter(jyRecrordAdapter);
        rlRoot.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page=1;
                getList(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                getList(page);
            }
        });
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_czrecord;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }
    private void getList(int page) {

        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());

            jsonObject.put("pageNum", page);
            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString()
                .url(FristServer.URL + "userWalletRecord")
                .mediaType(mediaType)
                .content(jsonObject.toString())
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<RecordBean>() {
                    @Override
                    public void onError(String e) {
                        Toast.makeText(RecordActivity.this, e, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(RecordBean response) throws org.json.JSONException {
                        if (response.getStatus() == 200) {
                            rlRoot.setPullLoadMoreCompleted();

                            if (page == 1) {
                                if (response.getResult().size() > 0) {
                                    recordBeans.clear();
                                    linearModle.setVisibility(View.GONE);
                                    rlRoot.setVisibility(View.VISIBLE);

                                    recordBeans.addAll(response.getResult());
                                    jyRecrordAdapter.notifyDataSetChanged();
                                } else {
                                    linearModle.setVisibility(View.VISIBLE);
                                    rlRoot.setVisibility(View.GONE);
                                }
                            } else {
                                if (response.getResult().size() > 0) {

                                    recordBeans.addAll(response.getResult());
                                    jyRecrordAdapter.notifyDataSetChanged();
                                } else {
                                    rlRoot.setPullRefreshEnable(false);
                                }
                            }


                        }
                    }
                }));
    }

}

