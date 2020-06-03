package com.yingzhiyun.yingquxue.activity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.video_view)
    TXCloudVideoView videoView;
    private TXLivePlayer mLivePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_video);
        ButterKnife.bind(this);
//创建 player 对象
        mLivePlayer = new TXLivePlayer(this);

//关键 player 对象与界面 view
        mLivePlayer.setPlayerView(videoView);
      testOkhttpGet();
    }
    /**
     * 测试okhttp的get方法
     */
    private void testOkhttpGet() {
        String url = "http://192.168.0.120/yzy/app/getPlayerStreamUrl";
        okhttp3.Request request = new okhttp3.Request.Builder().url(url).get().build();
        OkHttpClient okHttpClient = new OkHttpClient();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("======", "run: "+string);
                        mLivePlayer.startPlay(string, TXLivePlayer.PLAY_TYPE_LIVE_FLV); //推荐 FLV
                    }
                });
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay(true); // true 代表清除最后一帧画面
        videoView.onDestroy();
    }
}
