package com.yingzhiyun.yingquxue.adapter;

import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class ProvinceAdapter extends BaseAdapter<SelectedOptionsBean.ResultBean> {

    private setOnClickListener mSetOnClickListener;

    public ProvinceAdapter(List<SelectedOptionsBean.ResultBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_province;
    }

    @Override
    public void addAll(List<SelectedOptionsBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, SelectedOptionsBean.ResultBean resultBean, int position) {
        holder.setText(R.id.province,resultBean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetOnClickListener.setOnClickListener(resultBean,position);
            }
        });

    }
    public  interface  setOnClickListener{
        void setOnClickListener(SelectedOptionsBean.ResultBean resultBean, int position);

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
