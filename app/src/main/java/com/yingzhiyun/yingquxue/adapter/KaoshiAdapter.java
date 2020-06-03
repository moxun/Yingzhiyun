package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.StudentinfoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.score.ScoreInfoActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class KaoshiAdapter extends BaseAdapter<StudentinfoBean.BatchListBean> {

    private final Context context;
    private final StudentinfoBean bean;

    public KaoshiAdapter(List<StudentinfoBean.BatchListBean> dataList, Context context,StudentinfoBean studentinfoBean) {
        super(dataList);
        this.context =context;
        this.bean=studentinfoBean;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_kaoshi;
    }

    @Override
    public void addAll(List<StudentinfoBean.BatchListBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, StudentinfoBean.BatchListBean s, int position) {
        holder.setText(R.id.item_kaoshi_title,s.getName());
        holder.setText(R.id.item_time,s.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ScoreInfoActivity.class)
                .putExtra("title",s.getName()).putExtra("bean",bean).putExtra("id",s.getBatchId()));
            }
        });
    }
}
