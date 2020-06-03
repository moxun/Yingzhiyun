package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ZutijiluBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class TiRecordAdapter extends BaseAdapter<ZutijiluBean.ResultBean.DetailBean> {

    private setOnClickListener mSetOnClickListener;

    public TiRecordAdapter(List<ZutijiluBean.ResultBean.DetailBean> dataList, Context context) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tirecord;
    }

    @Override
    public void addAll(List<ZutijiluBean.ResultBean.DetailBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ZutijiluBean.ResultBean.DetailBean detailBean, int position) {

        holder.setText(R.id.item_subject,detailBean.getSubject());
        holder.setText(R.id.item_title,detailBean.getTitle());
        holder.setText(R.id.item_schooltype,detailBean.getSchoolType());
        holder.setText(R.id.info, "共" + detailBean.getTotalSize()  + "道    对" + detailBean.getTotalRight() + "道");
        holder.setText(R.id.item_time,detailBean.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetOnClickListener.setOnClickListener(detailBean);
            }
        });
    }

    public  interface  setOnClickListener{
        void setOnClickListener(ZutijiluBean.ResultBean.DetailBean musicBean);

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
