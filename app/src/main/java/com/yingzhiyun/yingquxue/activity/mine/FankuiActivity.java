package com.yingzhiyun.yingquxue.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.InteractionsMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.HudongIfoBean;
import com.yingzhiyun.yingquxue.OkBean.InteractionsListBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.FankuiJson;
import com.yingzhiyun.yingquxue.OkBean.MyInteractionListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.InteractionsPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.StringUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FankuiActivity extends BaseActicity<InteractionsMvp.Interaction_View, InteractionsPresenter<InteractionsMvp.Interaction_View>> implements
        InteractionsMvp.Interaction_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.ed_question)
    EditText edQuestion;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.btn_login)
    TextView btnLogin;

    @Override
    protected void initData() throws ParseException {
        edPhone.setLongClickable(true);
        edQuestion.setLongClickable(true);
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_fankui;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    @Override
    public void setInteractionList(InteractionsListBean interactionList) {

    }

    @Override
    public void setInteractionBean(HudongIfoBean hudongIfoBean) {

    }

    @Override
    public void setMyInteractionList(MyInteractionListBean myInteractionList) {

    }

    @Override
    public void setFanlkui(CollectBean collectBean) {
        finish();
        ToastUtil.makeLongText(this,collectBean.getHint());
    }

    @Override
    protected InteractionsPresenter<InteractionsMvp.Interaction_View> createPresenter() {
        return new InteractionsPresenter<>();
    }



    @OnClick({R.id.finish, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.btn_login:
                String question = edQuestion.getText().toString();
                String phone = edPhone.getText().toString();
                if(!StringUtils.isEmpty(question)){
                    if(!StringUtils.isEmpty(phone)){
                        if(StringUtils.isPhoneNumber(phone)){
                            mPresentser.getFanlkui(new Gson().toJson(new FankuiJson(SharedPreferenceUtils.getUserid(),SharedPreferenceUtils.getToken(), MyApp.version,"Android",phone,question)));
                        }else{
                            ToastUtil.makeLongText(this,"请填入正确的手机号格式");
                        }
                    }else{
                        mPresentser.getFanlkui(new Gson().toJson(new FankuiJson(SharedPreferenceUtils.getUserid(),SharedPreferenceUtils.getToken(), MyApp.version,"Android","",question)));
                    }
                }else{
                    ToastUtil.makeLongText(this,"请填写内容");
                }
                break;
        }
    }
}
