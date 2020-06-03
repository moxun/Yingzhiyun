package com.yingzhiyun.yingquxue.activity.homepagr;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.Mvp.VideoinfoMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.Collectjson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.VideoInfoJson;
import com.yingzhiyun.yingquxue.OkBean.MyCollectBean;
import com.yingzhiyun.yingquxue.OkBean.VideoinfoBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.OkBean.daobean.VideoDao;
import com.yingzhiyun.yingquxue.OkBean.daobean.VidoeHelper;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;

import com.yingzhiyun.yingquxue.adapter.VideoitemAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.VideoinPresenter;
import com.yingzhiyun.yingquxue.units.DialogUtil;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import pub.devrel.easypermissions.EasyPermissions;

public class VideoinfoActivity extends BaseActicity<VideoinfoMvp.Videoinfo_View, VideoinPresenter<VideoinfoMvp.Videoinfo_View>> implements VideoinfoMvp.Videoinfo_View,
        EasyPermissions.PermissionCallbacks {


    @Override
    public void setVideoinfo(VideoinfoBean videoinfo) {

    }

    @Override
    public void setCollectVideo(CollectBean collectVideo) {

    }

    @Override
    public void setMyCollect(ZiyuanBean collectVideo) {

    }

    @Override
    protected VideoinPresenter<VideoinfoMvp.Videoinfo_View> createPresenter() {
        return null;
    }

    @Override
    protected void initData() throws ParseException {

    }

    @Override
    public int createLayoutID() {
        return 0;
    }

    @Override
    public int choseeClor() {
        return 0;
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}
