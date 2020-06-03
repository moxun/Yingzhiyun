package com.yingzhiyun.yingquxue.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class TiFragmentADapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragments;
    private final FragmentManager fragmentManager;

    public TiFragmentADapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments =fragments;
        fragmentManager =fm;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss();
        return fragment;

    }
    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return PagerAdapter.POSITION_NONE;
    }


//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        Fragment fragment = fragments.get(position);
//        fragmentManager.beginTransaction().hide(fragment).commitAllowingStateLoss();
//    }
}
