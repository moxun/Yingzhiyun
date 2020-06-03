package com.yingzhiyun.yingquxue.activity.zhibo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.yingzhiyun.yingquxue.Fragment.zhibo.AliveListFragment;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.FrgmentAdapter;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TEacherAliveActivity extends SimpleActivity {


    @BindView(R.id.finish)
    ImageButton finish;
    @BindView(R.id.right_button)
    ImageView rightButton;
    @BindView(R.id.tab_WeChat)
    TabLayout tabWeChat;
    @BindView(R.id.vp_weChat)
    ViewPager vpWeChat;

    @Override
    protected void initData() throws ParseException {

        List<String> list = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        list.add("精选课程");
        list.add("推荐课程");
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
        for (int i = 0; i < list.size(); i++) {
            tabWeChat.addTab(tabWeChat.newTab().setText(list.get(i)));

        }
        Bundle bundle = new Bundle();
        bundle.putInt("type",1);
        AliveListFragment aliveListFragment = new AliveListFragment();
        aliveListFragment.setArguments(bundle);
        fragments.add(aliveListFragment);
        tabWeChat.setInlineLabel(true);

        Bundle bundle1 = new Bundle();
        bundle1.putInt("type",0);
        AliveListFragment aliveListFragment1 = new AliveListFragment();
        aliveListFragment1.setArguments(bundle1);
        fragments.add(aliveListFragment1);


        tabWeChat.setupWithViewPager(vpWeChat);

        FrgmentAdapter bookFrgmentAdapter = new FrgmentAdapter(getSupportFragmentManager(), fragments, list);
        vpWeChat.setAdapter(bookFrgmentAdapter);
        vpWeChat.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabWeChat));
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_teacher_alive;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }



    @OnClick({R.id.finish, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.right_button:
                startActivity(AliveSearchActivity.class);
                break;
        }
    }
}
