package com.yingzhiyun.yingquxue.adapter;

import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class VideoitemAdapter extends BaseAdapter<String> {

    public VideoitemAdapter(List<String> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_video_info;
    }

    @Override
    public void addAll(List<String> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, String s, int position) {
        holder.setText(R.id.item_context,s);


    }
}
