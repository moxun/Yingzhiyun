package com.yingzhiyun.yingquxue.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;

import com.yingzhiyun.yingquxue.Fragment.NewHomeFragment;
import com.yingzhiyun.yingquxue.Fragment.classfiy.ClassFiyFragment;
import com.yingzhiyun.yingquxue.Fragment.mine.MineFragment;
import com.yingzhiyun.yingquxue.Fragment.tiku.TikuFragment;
import com.yingzhiyun.yingquxue.Jsonnn;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.YueBean;
import com.yingzhiyun.yingquxue.activity.homepagr.PracticetestActivity;
import com.yingzhiyun.yingquxue.activity.tiku.SendQuestionActivity;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.base.presenter.BasePresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.SourceExport;
import com.yingzhiyun.yingquxue.units.StatusBarUtil;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActicity {

    @BindView(R.id.content_layout)
    FrameLayout contentLayout;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private int mIndex = 0;
    private NewHomeFragment homePagerFragment;
    private PracticetestActivity classfiyFragment;
    private MineFragment mineFragment;
    private TikuFragment tikuFragment;
    private boolean mIsExit;
    String[] perms = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.WRITE_SETTINGS};

    public static String getDateToString(long milSecond) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 获取系统时间戳
     *
     * @return
     */
    public long getCurTimeLong() {
        long time = System.currentTimeMillis();
        return time;
    }
    @SuppressLint({"MissingPermission"})
    public final void initLocation() {
        (new Thread((Runnable)(new Runnable() {
            public final void run() {
                String serviceString = Context.LOCATION_SERVICE;// 获取的是位置服务
                LocationManager var10000 = (LocationManager) MainActivity.this.getSystemService(serviceString);

                LocationManager locationManager = (LocationManager)var10000;
                String provider = LocationManager.NETWORK_PROVIDER;// 指定LocationManager的定位方法
                Location location = locationManager.getLastKnownLocation(provider);
                String address = MainActivity.this.getAddress(location);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("------------",address);
                    }
                });

            }
        }))).start();
    }

    private final String getAddress(Location location) {
        List<Address> result = new ArrayList<>();
        String addressLine = "";
        try {
            if (location != null) {
                Geocoder gc = new Geocoder((Context)this, Locale.getDefault());
                result = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);


                if (!result.isEmpty()) {
                    try {
                        addressLine = ((Address)result.get(0)).getAddressLine(0) + ((Address)result.get(0)).getAddressLine(1);
                    } catch (Exception var7) {
                        addressLine=result.get(0).getAddressLine(0);
                    }
                }
            }

            addressLine =addressLine.replace("null","");
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return addressLine;
    }
    @SuppressLint("WrongThread")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


    @Override
    protected void initData() throws ParseException {
        ChangColor(R.color.white);
        Intent intent = getIntent();
//        initLocation();
        homePagerFragment = new NewHomeFragment();
        classfiyFragment = new PracticetestActivity();
        tikuFragment = new TikuFragment();
        mineFragment = new MineFragment();
        mFragments.add(homePagerFragment);
        mFragments.add(classfiyFragment);
        mFragments.add(tikuFragment);
        mFragments.add(mineFragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (intent.getIntExtra("type", 0) == 1) {

            mIndex = 3;
            bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(3).getItemId());
            ft.add(R.id.content_layout, mineFragment).commit();
        } else if (intent.getIntExtra("type", 0) == 2) {
            mIndex = 1;
//            ChangColor(R.color.white);
            bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(1).getItemId());
            ft.add(R.id.content_layout, classfiyFragment).commit();
        } else {
            ft.add(R.id.content_layout, homePagerFragment).commit();
        }

        setButtomNavigationView();
        if (SharedPreferenceUtils.getusercont() == 0) {
            getPermission();
        }

    }


    @Override
    public int createLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter() {
            @Override
            public void attachView(Object view) {

            }

            @Override
            public void detachView() {

            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ChangColor(int color) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //注意要清除 FLAG_TRANSLUCENT_STATUS flag
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(color));
        }
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);

        //这里注意下 因为在评论区发现有网友调用setRootViewFitsSystemWindows 里面 winContent.getChildCount()=0 导致代码无法继续
        //是因为你需要在setContentView之后才可以调用 setRootViewFitsSystemWindows

        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //获取权限
    @SuppressLint("WrongConstant")
    private void getPermission() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            //已经打开权限

        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的使用权限", 1, perms);
        }
        SharedPreferenceUtils.setusercont(1);
    }

    private void setButtomNavigationView() {

        adjustNavigationIcoSize(bottomNavigationView);
        //取消默认的导航栏颜色
//       bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                hideFragment(ft);
                switch (item.getItemId()) {
                    case R.id.tab_main_pager:
                        setIndexSelected(0);
//                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//
//                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
//                        }
                        break;
                    case R.id.tab_classfiy:
                        setIndexSelected(1);
//                        ChangColor(R.color.mainColor);

                        break;
                    case R.id.tab_tiku:
//                        ChangColor(R.color.white);
                        setIndexSelected(2);

                        break;
                    case R.id.tab_mine:
//                        ChangColor(R.color.white);
                        setIndexSelected(3);

                        break;
                    default:
                        break;
                }

                return true;
            }
        });
    }

    private void setIndexSelected(int index) {

        if (mIndex == index) {
            return;
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏
        ft.hide(mFragments.get(mIndex));
        //判断是否添加
        if (!mFragments.get(index).isAdded()) {
            ft.add(R.id.content_layout, mFragments.get(index)).show(mFragments.get(index));
        } else {
            ft.show(mFragments.get(index));
        }

        ft.commit();
        //再次赋值
        mIndex = index;


    }


    /**
     * 隐藏所有的fragment
     *
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (homePagerFragment != null) {
            transaction.hide(homePagerFragment);
        }
        if (classfiyFragment != null) {
            transaction.hide(classfiyFragment);
        }
        if (tikuFragment != null) {
            transaction.hide(tikuFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }

    @Override
    /**
     * 双击返回键退出
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();

            } else {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mIsExit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void adjustNavigationIcoSize(BottomNavigationView navigation) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(R.id.icon);
            View viewById = menuView.getChildAt(i).findViewById(R.id.text);

            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i == 1) {

                layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, displayMetrics);
                layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, displayMetrics);

            } else {
                layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 22, displayMetrics);
                layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 22, displayMetrics);
            }

            iconView.setLayoutParams(layoutParams);
        }
    }


}
