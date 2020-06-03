package com.yingzhiyun.yingquxue.adapter;

import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class PopSubjectAdapter extends BaseAdapter<AllsubjectBean.ResultBean.DetailBean> {

    private final String string;
    private setOnClickListener mSetOnClickListener;

    public PopSubjectAdapter(String name, List<AllsubjectBean.ResultBean.DetailBean> dataList) {
        super(dataList);
        string =name;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_pop_subject;
    }

    @Override
    public void addAll(List<AllsubjectBean.ResultBean.DetailBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, AllsubjectBean.ResultBean.DetailBean resultBean, int position) {
        holder.setText(R.id.item_pop_content,resultBean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetOnClickListener.setOnClickListener(resultBean);
            }
        });
    }
    public  interface  setOnClickListener{
        void setOnClickListener(AllsubjectBean.ResultBean.DetailBean musicBean);

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
