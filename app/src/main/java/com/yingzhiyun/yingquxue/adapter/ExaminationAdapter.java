package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.JobListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.examination.YueJuanActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class ExaminationAdapter extends BaseAdapter<JobListBean.DataBean> {

    private final Context context;

    public ExaminationAdapter(List<JobListBean.DataBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_examination;
    }

    @Override
    public void addAll(List<JobListBean.DataBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, JobListBean.DataBean s, int position) {
        holder.setText(R.id.course_title,s.getPaperName());
        holder.setText(R.id.fuze,"负责小题："+s.getTestList());
        holder.setText(R.id.subject,s.getSubjectName());
        holder.setText(R.id.grade,s.getGradeName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id",s.getPaperId());
                bundle.putString("testlist",s.getTestList());
                bundle.putInt("groupid",s.getGroupId());

                context.startActivity(new Intent(context, YueJuanActivity.class).putExtras(bundle));
            }
        });

    }
}
