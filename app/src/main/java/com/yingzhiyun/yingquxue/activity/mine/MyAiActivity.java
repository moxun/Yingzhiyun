package com.yingzhiyun.yingquxue.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.VideoinfoMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.MyCollectJson;
import com.yingzhiyun.yingquxue.OkBean.VideoinfoBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.adapter.MoreVideoAdapter;
import com.yingzhiyun.yingquxue.adapter.ZiyuanAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.VideoinPresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyAiActivity extends BaseActicity<VideoinfoMvp.Videoinfo_View, VideoinPresenter<VideoinfoMvp.Videoinfo_View>> implements VideoinfoMvp.Videoinfo_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.recy_book)
    RecyclerView recyIntera;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.sendmessage)
    ImageView sendmessage;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    private ArrayList<ZiyuanBean.ResultBean> video;
    private ZiyuanAdapter videoAdapter;
    private List<ZiyuanBean.ResultBean> resultBeans2;
    private MoreVideoAdapter moreSelectionAdapter;

    @Override
    protected void initData() {
        resultBeans2 = new ArrayList<>();
        video = new ArrayList<>();
        toolTitle.setText("我的收藏");
        sendmessage.setVisibility(View.GONE);
        recyIntera.setLayoutManager(new LinearLayoutManager(this));


        videoAdapter = new ZiyuanAdapter(video, this);
        mPresentser.getMyCollect(new Gson().toJson(new MyCollectJson(SharedPreferenceUtils.getUserid(),
                SharedPreferenceUtils.getToken(), "file", MyApp.version, "Android")));
        recyIntera.setAdapter(videoAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresentser.getMyCollect(new Gson().toJson(new MyCollectJson(SharedPreferenceUtils.getUserid(),
                SharedPreferenceUtils.getToken(), "file", MyApp.version, "Android")));
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_hudong;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void setVideoinfo(VideoinfoBean videoinfo) {

    }

    @Override
    public void setCollectVideo(CollectBean collectVideo) {

    }

    @Override
    public void setMyCollect(ZiyuanBean collectVideo) {
        video.clear();
        if(collectVideo.getResult()!=null){
            if (collectVideo.getResult().size() > 0) {
                linearModle.setVisibility(View.GONE);
                recyIntera.setVisibility(View.VISIBLE);
                video.addAll(collectVideo.getResult());
                videoAdapter.notifyDataSetChanged();

            } else {
                linearModle.setVisibility(View.VISIBLE);
                recyIntera.setVisibility(View.GONE);
            }
        }else if(collectVideo.getStatus()==511){
            finish();
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    protected VideoinPresenter<VideoinfoMvp.Videoinfo_View> createPresenter() {
        return new VideoinPresenter<>();
    }

}
