package com.yingzhiyun.yingquxue.adapter;

import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class XingAdapter extends BaseAdapter<String> {
    public XingAdapter(List<String> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_xingshu;
    }

    @Override
    public void addAll(List<String> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, String string, int position) {

    }
}
