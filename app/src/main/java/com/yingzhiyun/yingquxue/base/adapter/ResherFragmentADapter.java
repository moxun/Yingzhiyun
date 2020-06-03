package com.yingzhiyun.yingquxue.base.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;

import java.util.List;


/**
 * fragment适配器
 */
public class ResherFragmentADapter extends FragmentStatePagerAdapter {

    private final FragmentManager mFragmentManager;
    private final List<Fragment> fragments;
    private final List<AllsubjectBean.ResultBean.DetailBean> tabTitle;
    public ResherFragmentADapter(FragmentManager fm, List<Fragment> fragments, List<AllsubjectBean.ResultBean.DetailBean> strings) {
        super(fm);
        this.mFragmentManager = fm;
        this.fragments=fragments;
        this.tabTitle=strings;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {//最主要就是加了这个方法。
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle.get(position).getName();
    }

}