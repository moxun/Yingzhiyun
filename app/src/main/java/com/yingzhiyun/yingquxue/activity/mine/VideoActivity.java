package com.yingzhiyun.yingquxue.activity.mine;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.yingzhiyun.yingquxue.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class VideoActivity extends AppCompatActivity {

    @BindView(R.id.jiaozi)
    JZVideoPlayerStandard mJC;
    private JZVideoPlayer.JZAutoFullscreenListener jzAutoFullscreenListener;
    private String path;
    private SensorManager
            sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        path = intent.getStringExtra("video");
        Log.d("mxoun", "initData: " + path);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        jzAutoFullscreenListener = new JZVideoPlayer.JZAutoFullscreenListener();
        //设置图片为全屏
        mJC.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //内置视频的高度，可以去除黑边
        JZVideoPlayer.setVideoImageDisplayType(JZVideoPlayer.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);
        //播放视频
        JZVideoPlayer.TOOL_BAR_EXIST = true;
        mJC.thumbImageView.setImageResource(R.drawable.bannertest);//先设置一张默认图片
        mJC.setUp(path + ".mp4", JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
        mJC.widthRatio = 16;
        mJC.heightRatio = 9;
        //设置全屏播放
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;  //纵向
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JZVideoPlayer.releaseAllVideos();
    }
    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
