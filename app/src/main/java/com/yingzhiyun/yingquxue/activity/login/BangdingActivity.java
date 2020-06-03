package com.yingzhiyun.yingquxue.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.BangdingMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CodeBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.CodeLojinJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.CoedeJsonBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TPLoginMatchesPhoneNumJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.getTPLoginBinPhoneJson;
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

public class BangdingActivity extends BaseActicity<BangdingMvp.Bangding_View, BangdingPresenter<BangdingMvp.Bangding_View>> implements BangdingMvp.Bangding_View {


    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.hint)
    TextView hint;
    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.ed_code)
    EditText edCode;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    private TimeCount time;//倒计时
    private int durationTime = 60 * 1000;
    private int type;
    private String openid;

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
    protected void initData() throws ParseException {
        type = getIntent().getExtras().getInt("type");
        openid = getIntent().getExtras().getString("openid");
        time = new TimeCount(durationTime, 1000);
        edUsername.setLongClickable(true);
        edCode.setLongClickable(true);
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_bangding;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    @Override
    public void setTPLogin(LoginSuccesBean loginSucces) {

    }

    @Override
    public void setTPLoginMatchesPhoneNum(CodeBean codeBean) {
        if(codeBean.getStatus()==200){
            time.start();
            ToastUtil.makeLongText(this,"验证码发送成功");
        }
    }

    @Override
    public void setTPLoginBinPhone(LoginSuccesBean loginSucces) {
        Log.d("moxun", "setTPLogin: "+loginSucces.getStatus());
        if(loginSucces.getStatus()==200){
            SharedPreferenceUtils.setisLogin(true);
            SharedPreferenceUtils.setToken(loginSucces.getResult().getToken());
            SharedPreferenceUtils.setusername(loginSucces.getResult().getNickname());
            if(loginSucces.getResult().getHead_path()!=null){
                SharedPreferenceUtils.setuserhead(loginSucces.getResult().getHead_path());
            }else{
                SharedPreferenceUtils.setuserhead("imagehead");
            }
            SharedPreferenceUtils.setSchool(loginSucces.getResult().getSchool());
            SharedPreferenceUtils.setUserid(loginSucces.getResult().getUser_id());
            Intent intent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("type",1);
            startActivity(intent);
        }else  if(loginSucces.getStatus()==516){
            Bundle bundle = new Bundle();
            bundle.putInt("type",type);
            bundle.putString("openid",openid);
            startActivity(SeetingsPwdActivity.class,bundle);
        }
    }

    @Override
    public void setPwdByOpenid(LoginSuccesBean loginSucces) {

    }

    @Override
    protected BangdingPresenter<BangdingMvp.Bangding_View> createPresenter() {
        return new BangdingPresenter<>();
    }


    @OnClick({R.id.finish, R.id.login, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.login:
                hideSoftKeyboard(this);
                String phone = edUsername.getText().toString().trim();
                if (!StringUtils.isPhoneNumber(phone)) {
                    ToastUtil.makeLongText(this,"请输入正确的手机号格式");

                    return;
                }
                mPresentser.getTPLoginMatchesPhoneNum(type,new Gson().toJson(new TPLoginMatchesPhoneNumJson(openid,phone, MyApp.version,"Android")));


                break;
            case R.id.btn_login:
                String telphone = edUsername.getText().toString().trim();
                String code = edCode.getText().toString().trim();

                boolean isEmpty = StringUtils.isEmpty(telphone) &&StringUtils.isEmpty(code) ;
                if(isEmpty){
                    ToastUtil.makeLongText(this,"填写信息不能为空");
                }else{
                    mPresentser.getTPLoginBinPhone(type,new Gson().toJson(new getTPLoginBinPhoneJson(openid,telphone,code, MyApp.version,"Android")));
                }
                break;
        }
    }
}
