package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class ExamineAdapter extends BaseAdapter<ExamineBean.ResultBean.DaTiBeanListBean> {

    private final Context context;
    private final String string;

    public ExamineAdapter(List<ExamineBean.ResultBean.DaTiBeanListBean> dataList, Context context,String adapter) {
        super(dataList);
        this.context =context;
        string =adapter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_examine_pager;
    }

    @Override
    public void addAll(List<ExamineBean.ResultBean.DaTiBeanListBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ExamineBean.ResultBean.DaTiBeanListBean stemBeanListBean, int position) {
        holder.setText(R.id.bank_title, stemBeanListBean.getTitle());
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.bank_recy);
        BankAdapter bankAdapter = new BankAdapter(stemBeanListBean.getStemBeanList(),context,string);
        recyclerView.setLayoutManager(new GridLayoutManager(context,5));
        recyclerView.setAdapter(bankAdapter);

    }
}
