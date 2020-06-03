package com.yingzhiyun.yingquxue.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class BanlanceAdapter extends BaseAdapter<Integer> {
    private int defItem = -1;
    private OnItemListener onItemListener;

    public BanlanceAdapter(List<Integer> dataList) {
        super(dataList);
    }
    //在activity调用这个，下标就会赋值到你点的那个条目，然后全部刷新，就会在走onBindViewHolder方法
    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_blance;
    }

    @Override
    public void addAll(List<Integer> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, Integer s, int position) {

        LinearLayout viewById = holder.itemView.findViewById(R.id.back);
        holder.setText(R.id.money,s+"元");
        holder.setText(R.id.qubi,s+"趣学币");
        TextView money = holder.itemView.findViewById(R.id.money);
        if (defItem != -1) {
            //第二次进来
            if (defItem == position) {
                //选中状态
                money.setTextColor(Color.parseColor("#1091E9"));
                viewById.setBackgroundResource(R.mipmap.icon_blance_choose);
            } else {

                money.setTextColor(Color.parseColor("#000000"));
                viewById.setBackgroundResource(R.mipmap.icon_blance_nochoose);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListener.onClick(position,s);
            }
        });

    }
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick( int pos, Integer s);
    }
}
