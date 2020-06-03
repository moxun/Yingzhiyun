package com.yingzhiyun.yingquxue.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.yingzhiyun.yingquxue.Fragment.mine.MydownloadFragment;
import com.yingzhiyun.yingquxue.Mvp.VideoinfoMvp;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.VideoinfoBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.FrgmentAdapter;
import com.yingzhiyun.yingquxue.adapter.ZiyuanAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.VideoinPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyfavtourActivity extends BaseActicity<VideoinfoMvp.Videoinfo_View, VideoinPresenter<VideoinfoMvp.Videoinfo_View>> implements VideoinfoMvp.Videoinfo_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.tab_WeChat)
    TabLayout tabWeChat;
    @BindView(R.id.vp_weChat)
    ViewPager vpWeChat;
    @BindView(R.id.imag)
    ImageView imag;

    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    private ArrayList<ZiyuanBean.ResultBean> video;
    private ZiyuanAdapter videoAdapter;

    @Override
    protected void initData() {
        toolTitle.setText("我的收藏");
        List<String> list = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        list.add("单科试卷");
        list.add("套题试卷");

        for (int i = 0; i < list.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("type", i);
            MydownloadFragment mydownloadFragment = new MydownloadFragment();
            mydownloadFragment.setArguments(bundle);
            fragments.add(mydownloadFragment);
        }

        tabWeChat.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpWeChat.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabWeChat.setupWithViewPager(vpWeChat);
        FrgmentAdapter bookFrgmentAdapter = new FrgmentAdapter(getSupportFragmentManager(), fragments, list);
        vpWeChat.setAdapter(bookFrgmentAdapter);
        vpWeChat.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabWeChat));

        video = new ArrayList<>();


    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_interactive;
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

    }


    @Override
    protected VideoinPresenter<VideoinfoMvp.Videoinfo_View> createPresenter() {
        return new VideoinPresenter<>();
    }


}
