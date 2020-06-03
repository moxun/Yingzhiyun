package com.yingzhiyun.yingquxue.adapter;

import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.daobean.CourseSearchBean;
import com.yingzhiyun.yingquxue.OkBean.daobean.SearchHistory;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class CourseSearchAdapter extends BaseAdapter<CourseSearchBean>{

    public List<CourseSearchBean> list;
    private setOnClickListener mSetOnClickListener;

    public CourseSearchAdapter(List<CourseSearchBean> dataList) {
        super(dataList);
        this.list=dataList;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_search;
    }

    @Override
    public void addAll(List<CourseSearchBean> list, int page) {

    }

    @Override
    public void createHolder(BaseAdapter.ViewHolder holder, CourseSearchBean s, int position) {
        holder.setText(R.id.item_search_title,s.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetOnClickListener.setOnClickListener(s);
            }
        });


    }
    public  interface  setOnClickListener{
        void setOnClickListener(CourseSearchBean s);

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
