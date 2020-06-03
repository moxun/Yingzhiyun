package com.yingzhiyun.yingquxue.adapter;

import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.localbean.TestBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class TestAdapter extends BaseAdapter<TestBean> {

    private setOnClickListener mSetOnClickListener;

    public TestAdapter(List<TestBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_text;
    }

    @Override
    public void addAll(List<TestBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, TestBean testBean, int position) {
        holder.setPic(R.id.item_image_test,testBean.getImage());
        holder.setText(R.id.item_title,testBean.getTitle());
        holder.setText(R.id.item_content,testBean.getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetOnClickListener.setOnClickListener(testBean,position);
            }
        });
    }
    public  interface  setOnClickListener{
        void setOnClickListener(TestBean testBean,int position);

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
