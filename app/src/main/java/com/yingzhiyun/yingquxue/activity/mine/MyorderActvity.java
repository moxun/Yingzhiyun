package com.yingzhiyun.yingquxue.activity.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.yingzhiyun.yingquxue.Fragment.mine.MineOrderFragment;
import com.yingzhiyun.yingquxue.Fragment.mine.MyinteractiveFragment;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.FrgmentAdapter;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyorderActvity extends SimpleActivity {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tab_WeChat)
    TabLayout tabWeChat;
    @BindView(R.id.vp_weChat)
    ViewPager vpWeChat;
    private List<String> list;

    @Override
    protected void initData() {
        toolTitle.setText("我的订单");
        list = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        list.add("全部订单");
        list.add("已付款");
        list.add("未付款");

        for (int i = 0; i < list.size(); i++) {
//            tabWeChat.addTab(tabWeChat.newTab().setText(list.get(i)));
            fragments.add(new MineOrderFragment());
        }
        tabWeChat.setInlineLabel(true);

        FrgmentAdapter bookFrgmentAdapter = new FrgmentAdapter(getSupportFragmentManager(), fragments, list);
        vpWeChat.setAdapter(bookFrgmentAdapter);
        vpWeChat.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabWeChat));

        tabWeChat.setupWithViewPager(vpWeChat);
        for (int i = 0; i < tabWeChat.getTabCount(); i++) {
            TabLayout.Tab tab = tabWeChat.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
            }
        }
        tabWeChat.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //在这里可以设置选中状态下  tab字体显示样式
                 vpWeChat.setCurrentItem(tab.getPosition());
                View view = tab.getCustomView();
                if (null != view && view instanceof TextView) {
                    ((TextView) view).setTextSize(19);
                    ((TextView) view).setTextColor(ContextCompat.getColor(MyorderActvity.this, R.color.ColorDCDCDC));
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null != view && view instanceof TextView) {
                    ((TextView) view).setTextSize(14);
                    ((TextView) view).setTextColor(ContextCompat.getColor(MyorderActvity.this, R.color.mainColor));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }
    @Override
    public int createLayoutID() {
        return R.layout.activity_mycourse;
    }
    @Override
    public int choseeClor() {
        return R.color.white;
    }
    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }
    /**
     * 自定义Tab的View
     * @param currentPosition
     * @return
     */
    private View getTabView(int currentPosition) {
        if(currentPosition==0){
            View view = LayoutInflater.from(this).inflate(R.layout.layout_tab, null);
            TextView textView = (TextView) view.findViewById(R.id.tab_item_textview);
            textView.setTextSize(19);
            textView.setTextColor(ContextCompat.getColor(MyorderActvity.this, R.color.ColorDCDCDC));
            textView.setText(list.get(currentPosition));
            return view;
        }else {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_tab, null);
            TextView textView = (TextView) view.findViewById(R.id.tab_item_textview);
            textView.setText(list.get(currentPosition));
            return view;
        }
    }
}
