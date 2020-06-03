package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.daobean.VideoDao;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.mine.VideoActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class DownAdapter extends BaseAdapter<VideoDao> {

    private final Context context;

    public DownAdapter(List<VideoDao> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_ziyuan_info;
    }

    @Override
    public void addAll(List<VideoDao> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, VideoDao sdVideoBean, int position) {
        holder.setText(R.id.item_title,sdVideoBean.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra("video",sdVideoBean.getPath());
                context.startActivity(intent);
            }
        });
    }
}
