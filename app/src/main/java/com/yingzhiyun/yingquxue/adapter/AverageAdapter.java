package com.yingzhiyun.yingquxue.adapter;

import android.view.View;
import android.widget.ProgressBar;

import com.yingzhiyun.yingquxue.OkBean.AverageBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class AverageAdapter extends BaseAdapter<AverageBean> {
    public AverageAdapter(List<AverageBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_progess;
    }

    @Override
    public void addAll(List<AverageBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, AverageBean averageBean, int position) {
        ProgressBar progressbar = holder.itemView.findViewById(R.id.progressbar_pingjun);
        progressbar.setMax((int) averageBean.getAll_Average());
        progressbar.setProgress((int) averageBean.getMine_Average());
        holder.setText(R.id.progress_tltle,averageBean.getAverage_type());
        holder.setText(R.id.average_score,averageBean.getAll_Average()+"");
    }
}
