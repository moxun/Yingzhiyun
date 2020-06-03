package com.yingzhiyun.yingquxue.adapter;


import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2019/2/13.
 */

public class FrgmentAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mList;
    private final List<String> mData;
    private final FragmentManager fm;

    public FrgmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> strings) {

        super(fm);
        this.fm=fm;
        this.mList=fragments;
        this.mData=strings;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position);
    }
    @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            fm.beginTransaction().show(fragment).commitAllowingStateLoss();
            return fragment;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Fragment fragment = mList.get(position);
            fm.beginTransaction().hide(fragment).commitAllowingStateLoss();
        }
}
