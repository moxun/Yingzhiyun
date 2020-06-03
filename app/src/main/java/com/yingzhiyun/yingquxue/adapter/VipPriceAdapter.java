package com.yingzhiyun.yingquxue.adapter;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.OkBean.VipOpenBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class VipPriceAdapter extends BaseAdapter<VipOpenBean.ResultBean.VipTypeListBean> {
    private int defItem = -1;
    private OnItemListener onItemListener;

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick(int pos, VipOpenBean.ResultBean.VipTypeListBean projectc);
    }  public VipPriceAdapter(List<VipOpenBean.ResultBean.VipTypeListBean> dataList) {
        super(dataList);
    }
    //在activity调用这个，下标就会赋值到你点的那个条目，然后全部刷新，就会在走onBindViewHolder方法
    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }
    @Override
    public int getLayoutId() {
        return R.layout.item_vipprice;
    }

    @Override
    public void addAll(List<VipOpenBean.ResultBean.VipTypeListBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, VipOpenBean.ResultBean.VipTypeListBean vipTypeListBean, int position) {
        holder.setText(R.id.item_vip_month,vipTypeListBean.getTitle());

        TextView item_vip_info = holder.itemView.findViewById(R.id.item_vip_info);
        if(vipTypeListBean.getDiscount()==null){
            holder.setText(R.id.item_vip_price,vipTypeListBean.getPrice());
            holder.setText(R.id.item_vip_info,vipTypeListBean.getInfo()+"");
        }else {
            item_vip_info.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
            holder.setText(R.id.item_vip_price,vipTypeListBean.getDiscount());
            holder.setText(R.id.item_vip_info,vipTypeListBean.getPrice()+"");
        }


        LinearLayout ll_price = holder.itemView.findViewById(R.id.ll_prcice);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onClick(position,vipTypeListBean);
            }
        });
        if (defItem != -1) {
            //第二次进来
            if (defItem == position) {
                ll_price.setBackgroundResource(R.drawable.vip_price_yes);
            } else {
                ll_price.setBackgroundResource(R.drawable.vipprice_no);

            }
        }else {
            if(position==0){
                ll_price.setBackgroundResource(R.drawable.vip_price_yes);
            }
        }
    }
}
