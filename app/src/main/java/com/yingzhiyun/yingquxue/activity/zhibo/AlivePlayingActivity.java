package com.yingzhiyun.yingquxue.activity.zhibo;

import android.content.Context;
import android.os.Bundle;

import com.tencent.liteav.demo.play.SuperPlayerGlobalConfig;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayConfig;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlivePlayingActivity extends SimpleActivity implements ITXLivePlayListener {
    @BindView(R.id.video_view)
    TXCloudVideoView videoView;
    private TXLivePlayer mLivePlayer;
    private TXLivePlayConfig mLivePlayConfig;

    @Override
    protected void initData() throws ParseException {
        initLivePlayer(this);
    }
    /**
     * 初始化直播播放器
     *
     * @param context
     */
    private void initLivePlayer(Context context) {
        if (mLivePlayer != null)
            return;
        mLivePlayer = new TXLivePlayer(context);
        SuperPlayerGlobalConfig config = SuperPlayerGlobalConfig.getInstance();
        mLivePlayConfig = new TXLivePlayConfig();
        mLivePlayer.setConfig(mLivePlayConfig);
        mLivePlayer.setRenderMode(config.renderMode);
        mLivePlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_PORTRAIT);
        mLivePlayer.setPlayListener(this);
        mLivePlayer.enableHardwareDecode(config.enableHWAcceleration);
    }
    @Override
    public int createLayoutID() {
        return R.layout.activity_alive_play;
    }

    @Override
    public int choseeClor() {
        return R.color.black;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onPlayEvent(int i, Bundle bundle) {

    }

    @Override
    public void onNetStatus(Bundle bundle) {

    }
}
