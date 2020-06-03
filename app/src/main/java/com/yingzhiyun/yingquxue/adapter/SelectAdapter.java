package com.yingzhiyun.yingquxue.adapter;

import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class SelectAdapter extends BaseAdapter<SelectedOptionsBean.ResultBean> {

    private setOnClickListener mSetOnClickListener;

    public SelectAdapter(List<SelectedOptionsBean.ResultBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_select;
    }

    @Override
    public void addAll(List<SelectedOptionsBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, SelectedOptionsBean.ResultBean resultBean, int position) {
        if(resultBean.getName()==null){
            holder.setText(R.id.item_select_title,resultBean.getTitle());
        }else {
            holder.setText(R.id.item_select_title,resultBean.getName());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetOnClickListener.setOnClickListener(resultBean);
            }
        });
    }
    public  interface  setOnClickListener{
        void setOnClickListener(SelectedOptionsBean.ResultBean resultBean);

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
