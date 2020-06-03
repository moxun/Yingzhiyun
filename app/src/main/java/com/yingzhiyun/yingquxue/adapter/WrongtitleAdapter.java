package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.WrongtitleBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.tiku.MyCollectionTiActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class WrongtitleAdapter extends BaseAdapter<WrongtitleBean.ResultBean> {

    private final Context context;
    private final String string;

    public WrongtitleAdapter(List<WrongtitleBean.ResultBean> dataList, Context context,String type) {
        super(dataList);
        this.context =context;
        string =type;
    }


    @Override
    public int getLayoutId() {
        return R.layout.item_wrongtitle;
    }

    @Override
    public void addAll(List<WrongtitleBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, WrongtitleBean.ResultBean resultBean, int position) {
        holder.setText(R.id.title,resultBean.getName());
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.recy_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        recyclerView.setAdapter(new WrongListAdapter(resultBean.getDetail(),context,string));
    }
}
