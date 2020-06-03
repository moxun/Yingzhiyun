package com.yingzhiyun.yingquxue.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.BangdingMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CodeBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.SeetingsPwdJson;
import com.yingzhiyun.yingquxue.OkBean.LoginSuccesBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.BangdingPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.StringUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeetingsPwdActivity extends BaseActicity<BangdingMvp.Bangding_View, BangdingPresenter<BangdingMvp.Bangding_View>> implements BangdingMvp.Bangding_View {


    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.yan)
    ImageView yan;
    private int type;
    private String openid;
    private boolean Yans=true;

    @Override
    public void setTPLogin(LoginSuccesBean loginSucces) {

    }

    @Override
    public void setTPLoginMatchesPhoneNum(CodeBean codeBean) {

    }

    @Override
    public void setTPLoginBinPhone(LoginSuccesBean loginSuccesBean) {

    }

    @Override
    public void setPwdByOpenid(LoginSuccesBean loginSucces) {
        Log.d("moxun", "setTPLogin: " + loginSucces.getStatus());
        if (loginSucces.getStatus() == 200) {
            SharedPreferenceUtils.setisLogin(true);
            SharedPreferenceUtils.setToken(loginSucces.getResult().getToken());
            SharedPreferenceUtils.setusername(loginSucces.getResult().getNickname());
            if (loginSucces.getResult().getHead_path() != null) {
                SharedPreferenceUtils.setuserhead(loginSucces.getResult().getHead_path());
            } else {
                SharedPreferenceUtils.setuserhead("imagehead");
            }
            SharedPreferenceUtils.setSchool(loginSucces.getResult().getSchool());
            SharedPreferenceUtils.setUserid(loginSucces.getResult().getUser_id());
            Intent intent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("type", 1);
            startActivity(intent);
        }
    }


    @Override
    protected void initData() throws ParseException {
        type = getIntent().getExtras().getInt("type");
        openid = getIntent().getExtras().getString("openid");
        edUsername.setLongClickable(true);

    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_seetingpwd;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    @Override
    protected BangdingPresenter<BangdingMvp.Bangding_View> createPresenter() {
        return new BangdingPresenter<>();
    }


    @OnClick({R.id.finish, R.id.btn_login,R.id.yan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.btn_login:

                String telphone = edUsername.getText().toString().trim();

                boolean isEmpty = StringUtils.isEmpty(telphone);
                if (isEmpty) {
                    ToastUtil.makeLongText(this, "填写信息不能为空");
                } else {
                    if (telphone.length() >= 6) {
                        mPresentser.getPwdByOpenid(type, new Gson().toJson(new SeetingsPwdJson(openid, telphone, MyApp.version,"Android")));
                    } else {
                        ToastUtil.makeLongText(this, "请输入6位以上的密码");
                    }

                }
                break;

            case R.id.yan:
                if (Yans) {
                    Yans = false;
                    //选择状态 显示明文--设置为可见的密码
                    edUsername.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    yan.setImageResource(R.mipmap.icon_openyan);
                } else {

                    Yans = true;
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    edUsername.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    yan.setImageResource(R.mipmap.icon_closeeye);
                }
                break;
        }
    }



}
