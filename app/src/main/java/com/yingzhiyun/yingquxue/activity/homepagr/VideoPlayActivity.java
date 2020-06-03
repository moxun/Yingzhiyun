package com.yingzhiyun.yingquxue.activity.homepagr;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.yingzhiyun.yingquxue.units.IVideoView;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoPlayActivity extends SimpleActivity {


    @BindView(R.id.videoView)
    IVideoView videoView;
    @BindView(R.id.finish)
    ImageButton finish;

    @Override
    protected void initData() throws ParseException {
        // 全屏、隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String path = getIntent().getExtras().getString("path");

        videoView.setVideoPath(path);

        //创建MediaController对象
        MediaController mediaController = new MediaController(this);

        //VideoView与MediaController建立关联
        videoView.setMediaController(mediaController);

        //让VideoView获取焦点
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {

                    videoView.start();

            }
        });


    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_videoplay;
    }

    @Override
    public int choseeClor() {
        return R.color.bg_black;
    }




    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }

}
