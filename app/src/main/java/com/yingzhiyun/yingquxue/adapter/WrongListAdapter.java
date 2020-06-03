package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.WrongtitleBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.tiku.TiShoucangActivity;
import com.yingzhiyun.yingquxue.activity.tiku.WrongListActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class WrongListAdapter extends BaseAdapter<WrongtitleBean.ResultBean.DetailBean> {

    private final Context context;
    private final String string;

    public WrongListAdapter(List<WrongtitleBean.ResultBean.DetailBean> dataList, Context context, String string) {
        super(dataList);
        this.context =context;
        this.string =string;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_wronglist;
    }

    @Override
    public void addAll(List<WrongtitleBean.ResultBean.DetailBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, WrongtitleBean.ResultBean.DetailBean detailBean, int position) {
        holder.setPic(R.id.item_image_record,detailBean.getImgPath());
        holder.setText(R.id.item_title,detailBean.getName());
        holder.setText(R.id.wrong_size,detailBean.getSize());
        TextView viewById = holder.itemView.findViewById(R.id.wrong_size);

        if(string.equals("错题本")){

        }else{
            viewById.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(string.equals("错题本")){
                    context.startActivity(new Intent(context, WrongListActivity.class).putExtra("id",detailBean.getSubjectId()));
                }else{
                    context.startActivity(new Intent(context, TiShoucangActivity.class).putExtra("id",detailBean.getSubjectId()));
                }

            }
        });
    }
}
