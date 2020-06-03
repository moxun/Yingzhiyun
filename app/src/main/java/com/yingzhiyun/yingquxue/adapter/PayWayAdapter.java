package com.yingzhiyun.yingquxue.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.yingzhiyun.yingquxue.OkBean.CoursePayBean;
import com.yingzhiyun.yingquxue.OkBean.localbean.PayWayBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class PayWayAdapter extends BaseAdapter<CoursePayBean.ResultBean.PayTypeListBean> {
    private int defItem = -1;
    private OnItemListener onItemListener;

    public PayWayAdapter(List<CoursePayBean.ResultBean.PayTypeListBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_payway;
    }
    //在activity调用这个，下标就会赋值到你点的那个条目，然后全部刷新，就会在走onBindViewHolder方法
    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }
    @Override
    public void addAll(List<CoursePayBean.ResultBean.PayTypeListBean> list, int page) {

    }
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick( int pos,CoursePayBean.ResultBean.PayTypeListBean payWayBean);
    }

    @Override
    public void createHolder(ViewHolder holder, CoursePayBean.ResultBean.PayTypeListBean payWayBean, int position) {
        holder.setPic(R.id.pay_image,payWayBean.getIcon());
        holder.setText(R.id.pay_way,payWayBean.getTitle());
        ImageView imageView = holder.itemView.findViewById(R.id.choose);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListener.onClick(position,payWayBean);
            }
        });
        if (defItem != -1) {
            //第二次进来
            if (defItem != position) {
                //选中状态
                imageView.setImageResource(R.mipmap.pay_nochoose);
            } else {

                imageView.setImageResource(R.mipmap.icon_paychoose);

            }
        }
    }
}
