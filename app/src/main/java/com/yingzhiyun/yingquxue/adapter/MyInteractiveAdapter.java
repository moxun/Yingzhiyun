package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.MyInteractionListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.tiku.HudongInfoActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class MyInteractiveAdapter extends BaseAdapter<MyInteractionListBean.ResultBean> {

    private final Context context;

    public MyInteractiveAdapter(List<MyInteractionListBean.ResultBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_interactive;
    }

    @Override
    public void addAll(List<MyInteractionListBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, MyInteractionListBean.ResultBean resultBean, int position) {
        holder.setText(R.id.item_context,resultBean.getContentString());
        holder.setText(R.id.answer_time,resultBean.getAddTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, HudongInfoActivity.class).putExtra("id",resultBean.getId()));
            }
        });
    }
}
