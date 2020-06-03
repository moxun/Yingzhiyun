package com.yingzhiyun.yingquxue.adapter;

import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class ShopListAdapter extends BaseAdapter<String> {
    public ShopListAdapter(List<String> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shoopinglist;
    }

    @Override
    public void addAll(List<String> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, String s, int position) {

    }
}
