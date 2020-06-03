package com.yingzhiyun.yingquxue.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.LoginMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CodeBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.CoedeJsonBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.PwdJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.UpdatepassJson;
import com.yingzhiyun.yingquxue.OkBean.LoginSuccesBean;
import com.yingzhiyun.yingquxue.OkBean.RegisterBean;
import com.yingzhiyun.yingquxue.OkBean.UpdatePassBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.LoginPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.StringUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class UpdatePassActivity extends BaseActicity<LoginMvp.Login_View, LoginPresenter<LoginMvp.Login_View>> implements LoginMvp.Login_View {
    @BindView(R.id.finsh)
    ImageView finsh;
    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.ed_code)
    EditText edCode;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.yan)
    ImageView yan;
    private TimeCount time;//倒计时
    private int durationTime = 60 * 1000;
    private String telphone;
    private String password;
    private boolean Yans=true;


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            if (login != null) {
                login.setText("重新发送");
                login.setClickable(true);
            }
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            if (login != null) {
                login.setClickable(false);//防止重复点击
                login.setText(millisUntilFinished / 1000 + "s");
            }
        }
    }

    @Override
    public void setCode(CodeBean code) {

    }

    @Override
    public void setRegister(RegisterBean register) {

    }

    @Override
    public void setCodeLogin(CodeBean codeLogin) {

    }

    @Override
    public void setLoginSucces(LoginSuccesBean loginSucces) {

    }

    @Override
    public void setCodeForgrt(CodeBean codeLogin) {
        if (codeLogin.getStatus() == 200) {
            time.start();

            ToastUtil.makeLongText(this, "发送成功，请注意查收");
        } else {
            if (codeLogin.getStatus() == 507) {
                finish();
                startActivity(RegisterActivity.class);
            }
            ToastUtil.makeLongText(this, codeLogin.getHint());
        }
    }

    @Override
    public void setUpdatePass(UpdatePassBean updatePass) {
        if (updatePass.getStatus() == 200) {
            if(JPushInterface.getRegistrationID(this)!=null){
                mPresentser.getpwdLogin(new Gson().toJson(new PwdJson(telphone, password, JPushInterface.getRegistrationID(this), MyApp.version,"Android")));
            }else{
                mPresentser.getpwdLogin(new Gson().toJson(new PwdJson(telphone, password, "", MyApp.version,"Android")));
            }
        } else {

            ToastUtil.makeLongText(this, updatePass.getHint());
        }
    }

    @Override
    public void setpwdLogin(LoginSuccesBean loginSucces) {
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
        } else {
            ToastUtil.makeLongText(this, loginSucces.getHint());
        }
    }

    @Override
    public void setupdapas(CollectBean collectBean) {

    }

    @Override
    public void setTPLogin(LoginSuccesBean loginSucces) {

    }

    @Override
    protected LoginPresenter<LoginMvp.Login_View> createPresenter() {
        return new LoginPresenter<>();
    }

    @Override
    protected void initData() {
        time = new TimeCount(durationTime, 1000);
        edUsername.setLongClickable(true);
        edPassword.setLongClickable(true);
        edCode.setLongClickable(true);
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_updatepass;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.finsh, R.id.login, R.id.btn_login,R.id.yan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finsh:
                finish();
                break;
            case R.id.login:

                hideSoftKeyboard(this);
                String phone = edUsername.getText().toString().trim();
                if (!StringUtils.isPhoneNumber(phone)) {
                    ToastUtil.makeLongText(this, "请输入正确的手机号格式");

                    return;
                }

                mPresentser.getCodeForget(new Gson().toJson(new CoedeJsonBean(phone, MyApp.version,"Android")));
                break;
            case R.id.btn_login:
                telphone = edUsername.getText().toString().trim();
                String code = edCode.getText().toString().trim();
                password = edPassword.getText().toString().trim();
                boolean isEmpty = StringUtils.isEmpty(telphone) && StringUtils.isEmpty(code) && StringUtils.isEmpty(password);
                if (isEmpty) {
                    ToastUtil.makeLongText(this, "填写信息不能为空");
                } else {
                    Log.d("moxun", "onViewClicked: " + code);
                    mPresentser.getUpdatePass(new Gson().toJson(new UpdatepassJson(telphone, password, code, MyApp.version,"Android")));
                }
                break;
            case R.id.yan:
                if (Yans) {
                    Yans = false;
                    //选择状态 显示明文--设置为可见的密码
                    edPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    yan.setImageResource(R.mipmap.icon_openyan);
                } else {
                    Yans = true;
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    edPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    yan.setImageResource(R.mipmap.icon_closeeye);
                }
                break;
        }
    }
}
