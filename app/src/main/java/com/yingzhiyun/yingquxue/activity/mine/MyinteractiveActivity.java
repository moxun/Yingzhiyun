package com.yingzhiyun.yingquxue.activity.mine;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.yingzhiyun.yingquxue.Fragment.mine.MyinteractiveFragment;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.tiku.SendQuestionActivity;
import com.yingzhiyun.yingquxue.adapter.FrgmentAdapter;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("Registered")
public class MyinteractiveActivity extends SimpleActivity {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tab_WeChat)
    TabLayout tabWeChat;
    @BindView(R.id.vp_weChat)
    ViewPager viewpager;
    @BindView(R.id.imag)
    ImageView imag;

    @Override
    protected void initData() {
        List<String> list = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        list.add("已回复");
        list.add("待回复");
        tabWeChat.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for (int i = 0; i < list.size(); i++) {
            tabWeChat.addTab(tabWeChat.newTab().setText(list.get(i)));
            fragments.add(new MyinteractiveFragment(i));
        }
        tabWeChat.setInlineLabel(true);


        tabWeChat.setupWithViewPager(viewpager);

        FrgmentAdapter bookFrgmentAdapter = new FrgmentAdapter(getSupportFragmentManager(), fragments, list);
        viewpager.setAdapter(bookFrgmentAdapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabWeChat));
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_interactive;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }



    @OnClick({R.id.finish, R.id.imag})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.imag:
                startActivity(SendQuestionActivity.class);
                break;
        }
    }
}
