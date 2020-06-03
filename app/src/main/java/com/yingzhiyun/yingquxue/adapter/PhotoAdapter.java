package com.yingzhiyun.yingquxue.adapter;

import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.ImagePreviewActivity;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends BaseAdapter<String> {

    private OnItemListener onItemListener;

    public PhotoAdapter(List<String> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_photo;
    }

    @Override
    public void addAll(List<String> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, String s, int position) {

        ImageView viewById = holder.itemView.findViewById(R.id.item_mineworks);
        holder.setPic(R.id.item_mineworks,s);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListener.onClick(view,position,s,viewById);
            }
        });
    }
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick(View v, int pos, String booklistBean, ImageView imageView);
    }
}
