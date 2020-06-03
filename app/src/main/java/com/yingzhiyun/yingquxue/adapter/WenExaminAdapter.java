package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class WenExaminAdapter extends BaseAdapter<ExamineBean.ResultBean.DaTiBeanListBean> {

    private final Context context;
    private final String card;

    public WenExaminAdapter(List<ExamineBean.ResultBean.DaTiBeanListBean> dataList, Context context,String card) {
        super(dataList);
        this.context =context;
        this.card=card;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_examine_pager;
    }

    @Override
    public void addAll(List<ExamineBean.ResultBean.DaTiBeanListBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ExamineBean.ResultBean.DaTiBeanListBean daTiBeanListBean, int position) {
        holder.setText(R.id.bank_title, daTiBeanListBean.getTitle());
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.bank_recy);
        WenBanAdapter bankAdapter = new WenBanAdapter(daTiBeanListBean.getStemBeanList().get(0).getStemList(),context,card);
        recyclerView.setLayoutManager(new GridLayoutManager(context,5));
        recyclerView.setAdapter(bankAdapter);
    }
}
