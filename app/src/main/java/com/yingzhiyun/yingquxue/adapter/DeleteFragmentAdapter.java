package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;


public  class DeleteFragmentAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragments = null;
    private Context context;

    public DeleteFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        //this.context = context;
        this.fragments = fragments;
        // notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return fragments.size();//;NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        //return ArrayListFragment.newInstance(position);
        return fragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return PagerAdapter.POSITION_NONE;
    }
}

