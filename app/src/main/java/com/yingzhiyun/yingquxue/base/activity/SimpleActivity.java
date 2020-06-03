package com.yingzhiyun.yingquxue.base.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.tencent.rtmp.TXLivePlayConfig;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.units.ActivityCollector;
import com.yingzhiyun.yingquxue.units.DialogUtil;
import com.yingzhiyun.yingquxue.units.DisplayUtil;
import com.yingzhiyun.yingquxue.units.StatusBarUtil;
import com.yingzhiyun.yingquxue.units.StringUtils;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/12/21.
 */

public abstract class SimpleActivity extends AppCompatActivity {

    public final int TSATUSBAR_TYPE_WHITE_TEXT = 1;
    public final int TSATUSBAR_TYPE_BLACK_TEXT = 2;
    private Unbinder mBind;
    private Activity mActivity;
    private LayoutInflater baseInflater;

    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createLayoutID());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //注意要清除 FLAG_TRANSLUCENT_STATUS flag
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(choseeClor()));
        }
        StatusBarUtil.setRootViewFitsSystemWindows(this,false);

        //这里注意下 因为在评论区发现有网友调用setRootViewFitsSystemWindows 里面 winContent.getChildCount()=0 导致代码无法继续
        //是因为你需要在setContentView之后才可以调用 setRootViewFitsSystemWindows

        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this,0x55000000);
        }
        mBind = ButterKnife.bind(this);
        ActivityCollector.INSTANCE.addActivity(this);
        mActivity = this;
        baseInflater = LayoutInflater.from(this);
        viewCreated();
        try {
            initData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void viewCreated() {

    }

    protected abstract void initData() throws ParseException;

    public abstract int createLayoutID();

    public abstract int choseeClor();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.INSTANCE.removeActivity(this);

        if(mBind!=null){
            mBind.unbind();
        }
    }
    protected Dialog dialogPrompt(String title, String content, String rightBtnName,String leftname) {
        View dialogView = baseInflater.inflate(R.layout.dialog_issave, null);
        final Dialog dialog = DialogUtil.showDialogCenter(this, dialogView, 300);
        TextView tvTitle = (TextView) dialogView.findViewById(R.id.title);
        TextView tvContent = (TextView) dialogView.findViewById(R.id.content);
        TextView rigntBtn = (TextView) dialogView.findViewById(R.id.dialog_right_btn);
        TextView leftBtn = (TextView) dialogView.findViewById(R.id.dialog_left_btn);
        if (!StringUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        if (!StringUtils.isEmpty(content)) {
            tvContent.setText(content);
            tvContent.setVisibility(View.VISIBLE);
        }
        if (!StringUtils.isEmpty(rightBtnName)) {
            rigntBtn.setText(rightBtnName);
        }
        rigntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dialogRightBtn();
            }
        });
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialogLeftBtn();
            }
        });
        return dialog;
    }
    protected Dialog dialogHint(String title,  String rightBtnName) {
        View dialogView = baseInflater.inflate(R.layout.diolag_hint, null);
        final Dialog dialog = DialogUtil.showDialogCenter(this, dialogView, 300);
        TextView tvTitle = (TextView) dialogView.findViewById(R.id.title);

        TextView rigntBtn = (TextView) dialogView.findViewById(R.id.dialog_right_btn);

        if (!StringUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }

        if (!StringUtils.isEmpty(rightBtnName)) {
            rigntBtn.setText(rightBtnName);
        }
        rigntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        return dialog;
    }
    protected void dialogLeftBtn() {

    }

    protected void dialogRightBtn() {

    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(SimpleActivity.this, clz));
    }

    /**
     * 携带数据页面跳转
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 携带数据页面跳转并且有请求码
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
    //判断有没有安装微信
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 判断qq是否可用
     *
     * @param context
     * @return
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return  false;
    }
}
