package com.yingzhiyun.yingquxue.adapter;

import android.view.View;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class LableAdapter extends BaseAdapter<String> {

    private  String sss;

    public LableAdapter(List<String> dataList, String type) {
        super(dataList);
        sss =type;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_lable;
    }

    @Override
    public void addAll(List<String> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, String s, int position) {
        holder.setText(R.id.item_lable_title,s);
        TextView item_lable_title = holder.itemView.findViewById(R.id.item_lable_title);
        if(sss.equals("vip")){
            item_lable_title.setBackgroundResource(R.drawable.chengsetuo);
        }else {
            item_lable_title.setBackgroundResource(R.drawable.bluetuo);
        }
    }
}
