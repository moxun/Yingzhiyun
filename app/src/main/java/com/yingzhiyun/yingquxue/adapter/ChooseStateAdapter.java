package com.yingzhiyun.yingquxue.adapter;

import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.ClassBeaginBean;
import com.yingzhiyun.yingquxue.OkBean.FolderListOptionsBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class ChooseStateAdapter extends BaseAdapter<FolderListOptionsBean.ResultBean> {

    private setOnClickListener mSetOnClickListener;

    public ChooseStateAdapter(List<FolderListOptionsBean.ResultBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_choosestate;
    }

    @Override
    public void addAll(List<FolderListOptionsBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, FolderListOptionsBean.ResultBean resultBean, int position) {
        holder.setText(R.id.hingh,resultBean.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetOnClickListener.setOnClickListener(resultBean,position);
            }
        });
    }
    public  interface  setOnClickListener{
        void setOnClickListener(FolderListOptionsBean.ResultBean resultBean, int position);

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
