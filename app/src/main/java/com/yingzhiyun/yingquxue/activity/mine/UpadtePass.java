package com.yingzhiyun.yingquxue.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.LoginMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CodeBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.ChangePwdJson;
import com.yingzhiyun.yingquxue.OkBean.LoginSuccesBean;
import com.yingzhiyun.yingquxue.OkBean.RegisterBean;
import com.yingzhiyun.yingquxue.OkBean.UpdatePassBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.LoginPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.StringUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpadtePass extends BaseActicity<LoginMvp.Login_View, LoginPresenter<LoginMvp.Login_View>> implements LoginMvp.Login_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.old_pass)
    EditText oldPass;
    @BindView(R.id.new_pwd)
    EditText newPwd;
    @BindView(R.id.agin_pwd)
    EditText aginPwd;
    @BindView(R.id.btn_login)
    TextView btnLogin;

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

    }

    @Override
    public void setUpdatePass(UpdatePassBean updatePass) {

    }

    @Override
    public void setpwdLogin(LoginSuccesBean loginSucces) {

    }

    @Override
    public void setupdapas(CollectBean collectBean) {
        ToastUtil.makeLongText(this,collectBean.getHint());
        if(collectBean.getStatus()==200){
            SharedPreferenceUtils.setisLogin(false);
            SharedPreferenceUtils.setToken("");
            SharedPreferenceUtils.setusername("");

            SharedPreferenceUtils.setuserhead("imagehead");

            SharedPreferenceUtils.setSchool("你还未选择学校");
            SharedPreferenceUtils.setUserid(0);
            finish();
            startActivity(PwdLoginActivity.class);
        }

    }

    @Override
    public void setTPLogin(LoginSuccesBean loginSucces) {

    }

    @Override
    protected LoginPresenter<LoginMvp.Login_View> createPresenter() {
        return new LoginPresenter<>();
    }

    @Override
    protected void initData() throws ParseException {

    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_updatepassword;
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
                hideSoftKeyboard(this);
                String oldpass= oldPass.getText().toString();
                String newpass= newPwd.getText().toString();
                String aginpass= aginPwd.getText().toString();
                boolean isEmpty = StringUtils.isEmpty(oldpass)  &&StringUtils.isEmpty(newpass)&&StringUtils.isEmpty(aginpass);
                if(isEmpty){
                    ToastUtil.makeLongText(this,"请填写内容");
                }else{
                    if(oldpass.equals(newpass)){
                        ToastUtil.makeLongText(this,"新密码不得与旧密码相同");
                    }else{
                        if(newpass.equals(aginpass)){
                            mPresentser.getupdapwd(new Gson().toJson(new ChangePwdJson(SharedPreferenceUtils.getUserid(),SharedPreferenceUtils.getToken(),oldpass,newpass, MyApp.version,"Android")));

                        }else{
                            ToastUtil.makeLongText(this,"两次密码不相同");
                        }
                    }
                }
                break;
        }
    }
}
