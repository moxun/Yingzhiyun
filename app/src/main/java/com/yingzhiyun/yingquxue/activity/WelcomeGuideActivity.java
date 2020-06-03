package com.yingzhiyun.yingquxue.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import androidx.viewpager.widget.ViewPager;

import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.GuideViewPagerAdapter;
import com.yingzhiyun.yingquxue.units.AppConstants;
import com.yingzhiyun.yingquxue.units.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 欢迎页
 * 
 * @author wwj_748
 * 
 */
public class WelcomeGuideActivity extends Activity implements OnClickListener {
	private ViewPager vp;
	private GuideViewPagerAdapter adapter;
	private List<View> views;
	private ImageView startBtn;
	// 引导页图片资源
	private static final int[] pics = { R.layout.guid_view1,
			R.layout.guid_view2, R.layout.guid_view3 };

	// 记录当前选中位置
	private int currentIndex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);

		views = new ArrayList<View>();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //注意要清除 FLAG_TRANSLUCENT_STATUS flag
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
		// 初始化引导页视图列表
		for (int i = 0; i < pics.length; i++) {
			View view = LayoutInflater.from(this).inflate(pics[i], null);
			if (i == pics.length - 1) {
				//到最后一个点击跳转
				startBtn = view.findViewById(R.id.image);
				startBtn.setVisibility(View.VISIBLE);
				startBtn.setTag("enter");
				startBtn.setOnClickListener(this);
			}
			views.add(view);
		}
		vp = (ViewPager) findViewById(R.id.vp_guide);
		// 初始化adapter
		adapter = new GuideViewPagerAdapter(views);
		vp.setAdapter(adapter);
		vp.addOnPageChangeListener((ViewPager.OnPageChangeListener) new PageChangeListener());
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
	protected void onPause() {
		super.onPause();
		// 如果切换到后台，就设置下次不进入功能引导页
	SpUtils.putBoolean(WelcomeGuideActivity.this, AppConstants.FIRST_OPEN, true);
		finish();
	}
	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	/**
	 * 设置当前view
	 * 
	 * @param position
	 */
	private void setCurView(int position) {
		if (position < 0 || position >= pics.length) {
			return;
		}
		vp.setCurrentItem(position);
	}
	@Override
	public void onClick(View v) {
		if (v.getTag().equals("enter")) {
			enterMainActivity();
			return;
		}
		int position = (Integer) v.getTag();
		setCurView(position);
	}
	private void enterMainActivity() {
		Intent intent = new Intent(WelcomeGuideActivity.this,
				SplashActivity.class);
		startActivity(intent);
		SpUtils.putBoolean(WelcomeGuideActivity.this, AppConstants.FIRST_OPEN, true);
		finish();
	}
	private class PageChangeListener implements ViewPager.OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int position) {
		}
		@Override
		public void onPageScrolled(int position, float arg1, int arg2) {
		}
		@Override
		public void onPageSelected(int position) {
		}
	}
}
