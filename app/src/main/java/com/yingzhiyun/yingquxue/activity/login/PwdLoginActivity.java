package com.yingzhiyun.yingquxue.activity.login;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.LoginMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CodeBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.PwdJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.TPLoginJson;
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
import cn.jiguang.share.android.api.AuthListener;
import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.model.BaseResponseInfo;
import cn.jiguang.share.android.model.UserInfo;
import cn.jiguang.share.android.utils.Logger;
import cn.jiguang.share.qqmodel.QQ;
import cn.jiguang.share.wechat.Wechat;
import cn.jpush.android.api.JPushInterface;

public class PwdLoginActivity extends BaseActicity<LoginMvp.Login_View, LoginPresenter<LoginMvp.Login_View>> implements LoginMvp.Login_View {
    @BindView(R.id.finsh)
    ImageView finsh;
    @BindView(R.id.btn_register)
    TextView btnRegister;
    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.bt_code_login)
    TextView btCodeLogin;
    @BindView(R.id.btn_forget_pass)
    TextView btnForgetPass;
    @BindView(R.id.btn_wechat)
    LinearLayout btnWechat;
    @BindView(R.id.btn_qq)
    LinearLayout btnQq;
    @BindView(R.id.yan)
    ImageView yan;
    private String openid;
    private int id;
    private boolean Yans = true;

    @Override
    protected void initData() {
//        edUsername.setLongClickable(true);
        edPassword.setLongClickable(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RemoverJshaper(Wechat.Name);
        RemoverJshaper(QQ.Name);
    }

    @Override
    public int createLayoutID() {
        return R.layout.acticity_passlogin;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.finsh, R.id.btn_register, R.id.btn_login, R.id.bt_code_login, R.id.btn_forget_pass, R.id.btn_wechat, R.id.btn_qq, R.id.yan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finsh:
                finish();
                break;
            case R.id.btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.btn_login:
                hideSoftKeyboard(this);
                String telphone = edUsername.getText().toString().trim();

                String password = edPassword.getText().toString().trim();
                boolean isEmpty = StringUtils.isEmpty(telphone) && StringUtils.isEmpty(password);
                if (isEmpty) {
                    ToastUtil.makeLongText(this, "填写信息不能为空");
                } else {
                    String registrationID = JPushInterface.getRegistrationID(PwdLoginActivity.this);
                    Log.d("moun", "onComplete: "+registrationID);
                    if(JPushInterface.getRegistrationID(this)!=null){
                        mPresentser.getpwdLogin(new Gson().toJson(new PwdJson(telphone, password, JPushInterface.getRegistrationID(this), MyApp.version,"Android")));
                    }else{
                        mPresentser.getpwdLogin(new Gson().toJson(new PwdJson(telphone, password, "", MyApp.version,"Android")));
                    }

                }
                break;
            case R.id.bt_code_login:

                startActivity(new Intent(this, CodeLoginActivity.class));
                break;
            case R.id.btn_forget_pass:
                startActivity(new Intent(this, UpdatePassActivity.class));
                break;
            case R.id.btn_wechat:
                if (isWeixinAvilible(this)) {

                    SangfaDenglu(Wechat.Name, 1);
                } else {
                    ToastUtil.makeLongText(this, "您的手机未检测出微信");
                }

                break;
            case R.id.btn_qq:
                if (isQQClientAvailable(this)) {

                    SangfaDenglu(QQ.Name, 2);
                } else {
                    ToastUtil.makeLongText(this, "您的手机未安装QQ");
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

    private void SangfaDenglu(String name, int type) {

        JShareInterface.getUserInfo(name, new AuthListener() {


            @Override
            public void onComplete(Platform platform, int action, BaseResponseInfo data) {

                String toastMsg = null;

                        if (data instanceof UserInfo) {      //第三方个人信息
                            //openid

                            hideLoading();
                            openid = ((UserInfo) data).getOpenid();
                            String name = ((UserInfo) data).getName();  //昵称
                            String imageUrl = ((UserInfo) data).getImageUrl();  //头像url
                            int gender = ((UserInfo) data).getGender();//性别, 1表示男性；2表示女性
                            //个人信息原始数据，开发者可自行处理
                            String originData = data.getOriginData();
                            toastMsg = "获取个人信息成功:" + data.toString();
                            Logger.dd("moxun", "openid:" + openid + ",name:" + name + ",gender:" + gender + ",imageUrl:" + imageUrl);
                            Logger.dd("moxun", "originData:" + originData);
                            id = type;
                            hideLoading();

                            if(JPushInterface.getRegistrationID(PwdLoginActivity.this)!=null){
                                mPresentser.getTPLogin(new Gson().toJson(new TPLoginJson(openid,JPushInterface.getRegistrationID(PwdLoginActivity.this), MyApp.version,"Android")), type);
                            }else{
                                mPresentser.getTPLogin(new Gson().toJson(new TPLoginJson(openid,"", MyApp.version,"Android")), type);
                            }



                }
            }

            @Override
            public void onError(Platform platform, int action, int errorCode, Throwable error) {
                Logger.dd("moxun", "onError:" + platform + ",action:" + action + ",error:" + error);
                String toastMsg = null;
                switch (action) {
                    case Platform.ACTION_USER_INFO:
                        toastMsg = "获取个人信息失败";
                        hideLoading();

                        break;
                }
            }

            @Override
            public void onCancel(Platform platform, int action) {
                Logger.dd("moxun", "onCancel:" + platform + ",action:" + action);
                String toastMsg = null;
                switch (action) {
                    case Platform.ACTION_USER_INFO:
                        toastMsg = "取消获取个人信息";
                        hideLoading();


                        break;
                }
            }
        });
    }

    @Override
    public void setCode(CodeBean code) {

    }

    private void RemoverJshaper(String type) {
        JShareInterface.removeAuthorize(type, new AuthListener() {
            @Override
            public void onComplete(Platform platform, int action, BaseResponseInfo data) {

                String toastMsg = null;
                switch (action) {
                    case Platform.ACTION_REMOVE_AUTHORIZING:
                        toastMsg = "删除授权成功";
                        break;
                }
            }

            @Override
            public void onError(Platform platform, int action, int errorCode, Throwable error) {

                String toastMsg = null;
                switch (action) {
                    case Platform.ACTION_REMOVE_AUTHORIZING:
                        toastMsg = "删除授权失败";
                        break;
                }
            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
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

    }

    @Override
    public void setUpdatePass(UpdatePassBean updatePass) {

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
        } else if (loginSucces.getStatus() == 514) {
            Bundle bundle = new Bundle();
            bundle.putInt("type", id);
            bundle.putString("openid", openid);
            startActivity(BangdingActivity.class, bundle);
        }
    }

    @Override
    protected LoginPresenter<LoginMvp.Login_View> createPresenter() {
        return new LoginPresenter<>();
    }


}
