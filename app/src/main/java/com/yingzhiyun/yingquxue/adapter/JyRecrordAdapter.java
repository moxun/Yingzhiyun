package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.RecordBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.pay.RecordInfoActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class JyRecrordAdapter extends BaseAdapter<RecordBean.ResultBean> {

    private final Context context;

    public JyRecrordAdapter(List<RecordBean.ResultBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_czrecord;
    }

    @Override
    public void addAll(List<RecordBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, RecordBean.ResultBean resultBean, int position) {
        holder.setText(R.id.item_record_tltle,resultBean.getBody());
        holder.setText(R.id.item_record_time,resultBean.getTime());
        holder.setText(R.id.item_record_tltle,resultBean.getBody());
        TextView mingxi = holder.itemView.findViewById(R.id.item_record_mingxi);
        if(resultBean.getTransactionType()==1||resultBean.getTransactionType()==3){

            if(resultBean.getRefundStatus()==null){
                mingxi.setTextColor(Color.parseColor("#ff000000"));
                if(resultBean.getType()==1){
                    mingxi.setText("-"+resultBean.getTotalFree()+"趣学币");
                }else{
                    mingxi.setText("-"+resultBean.getTotalFree()+"元");
                }
            }else {
                if(resultBean.getRefundStatus().equals("1")){
                    holder.setText(R.id.item_record_jieguo,"退款成功");
                    mingxi.setTextColor(Color.parseColor("#ffdb253b"));
                    if(resultBean.getType()==1){
                        mingxi.setText("+"+resultBean.getTotalFree()+"趣学币");
                    }else{
                        mingxi.setText("+"+resultBean.getTotalFree()+"元");
                    }

                }else {
                    holder.setText(R.id.item_record_jieguo,"退款处理中");
                    mingxi.setTextColor(Color.parseColor("#cccccc"));
                    if(resultBean.getType()==1){
                        mingxi.setText("+"+resultBean.getTotalFree()+"趣学币");
                    }else{
                        mingxi.setText("+"+resultBean.getTotalFree()+"元");
                    }
                }
            }



        }else if(resultBean.getTransactionType()==2){
            mingxi.setTextColor(Color.parseColor("#ffdb253b"));
            if(resultBean.getType()==1){
                mingxi.setText("+"+resultBean.getTotalFree()+"趣学币");
            }else{
                mingxi.setText("+"+resultBean.getTotalFree()+"元");
            }
        }else if(resultBean.getTransactionType()==4){
            mingxi.setTextColor(Color.parseColor("#ff000000"));
            if(resultBean.getType()==1){
                mingxi.setText("-"+resultBean.getTotalFree()+"趣学币");
            }else if(resultBean.getType()==2){
                mingxi.setText("-"+resultBean.getTotalFree()+"元");
            }


        }else {
            mingxi.setTextColor(Color.parseColor("#ff000000"));

            mingxi.setText(resultBean.getTotalFree()+"趣学币");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, RecordInfoActivity.class).putExtra("bean",resultBean));
            }
        });
    }
}
