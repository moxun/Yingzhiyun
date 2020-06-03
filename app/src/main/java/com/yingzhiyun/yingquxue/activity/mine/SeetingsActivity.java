package com.yingzhiyun.yingquxue.activity.mine;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.units.CommonUtils;
import com.yingzhiyun.yingquxue.units.DataCleanManagerUtils;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeetingsActivity extends SimpleActivity {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.rea_image)
    RelativeLayout reaImage;
    @BindView(R.id.versions)
    TextView versions;
    @BindView(R.id.rea_banben)
    RelativeLayout reaBanben;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.rea_huancun)
    RelativeLayout reaHuancun;
    @BindView(R.id.logout)
    TextView logout;

    @Override
    protected void initData() throws ParseException {
        String totalCacheSize = DataCleanManagerUtils.getTotalCacheSize(this);
        size.setText(totalCacheSize);
        try {
            versions.setText(getVersionName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!SharedPreferenceUtils.getisLogin()){
            logout.setText("登录");
        }
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_seetings;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    private String getVersionName() throws Exception{
        //获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        return packInfo.versionName;
    }

    @OnClick({R.id.finish, R.id.rea_image, R.id.rea_banben, R.id.rea_huancun, R.id.logout,R.id.about_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.rea_image:
                if(SharedPreferenceUtils.getisLogin()){
                    startActivity(UpadtePass.class);
                }else{
                    ToastUtil.makeLongText(this,"请先登录");
                    startActivity(PwdLoginActivity.class);
                }

                break;
            case R.id.rea_banben:
                break;
            case R.id.rea_huancun:
                DataCleanManagerUtils.clearAllCache(this);
                size.setText(DataCleanManagerUtils.getTotalCacheSize(this));
                ToastUtil.makeLongText(this,"清除缓存成功");
                break;
            case R.id.logout:
                if(logout.getText().toString().equals("登录")){
                    startActivity(PwdLoginActivity.class);
                }else{
                    SharedPreferenceUtils.setisLogin(false);
                    SharedPreferenceUtils.setToken("");
                    SharedPreferenceUtils.setusername("");

                    SharedPreferenceUtils.setuserhead("imagehead");

                    SharedPreferenceUtils.setSchool("你还未选择学校");
                    SharedPreferenceUtils.setUserid(0);
                    Intent intent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("type",1);
                    startActivity(intent);
                }

                break;
            case R.id.about_us:
                Bundle bundle = new Bundle();
                bundle.putString("url","http://192.168.0.120/applicationAboutUs/1");
                bundle.putString("name","关于我们");
                startActivity(AboutUSActivity.class,bundle);
                break;
        }
    }
}
