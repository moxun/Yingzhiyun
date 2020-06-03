package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.SubjectAdapterBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class SubjectAdapter extends BaseAdapter<SubjectAdapterBean> {

    private final Context context;

    public SubjectAdapter(List<SubjectAdapterBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_subject;
    }

    @Override
    public void addAll(List<SubjectAdapterBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, SubjectAdapterBean subjectAdapterBean, int position) {
        holder.setText(R.id.item_subject_title,subjectAdapterBean.getTitle());
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.item_subject_recy);
        SubjectinfoAdapter subjectinfoAdapter = new SubjectinfoAdapter(subjectAdapterBean.getList(),context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        recyclerView.setAdapter(subjectinfoAdapter);

    }
}
