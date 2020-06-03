package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class BashiinfoAdapter extends BaseAdapter<BashiinfoBean.ResultBean.DetailBean> {

    private final Context context;
    private ZiyuanAdapter ziyuanAdapter;

    private MyyatiAdapter myyatiAdapter;

    public BashiinfoAdapter(List<BashiinfoBean.ResultBean.DetailBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_bashiinfo;
    }

    @Override
    public void addAll(List<BashiinfoBean.ResultBean.DetailBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, BashiinfoBean.ResultBean.DetailBean resultBean, int position) {
        holder.setText(R.id.name,resultBean.getName());
        RecyclerView reccy_info = holder.itemView.findViewById(R.id.reccy_info);
        reccy_info.setNestedScrollingEnabled(false);
        ziyuanAdapter = new ZiyuanAdapter(resultBean.getSubjectDetail(), context);
        myyatiAdapter = new MyyatiAdapter(resultBean.getDetail(), context);
        if(resultBean.getSubjectDetail()!=null){
            reccy_info.setLayoutManager(new LinearLayoutManager(context));
            reccy_info.setAdapter(ziyuanAdapter);
        }else {
            reccy_info.setLayoutManager(new LinearLayoutManager(context));
            reccy_info.setAdapter(myyatiAdapter);
        }


    }
}
