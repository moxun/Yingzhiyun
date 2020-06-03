package com.yingzhiyun.yingquxue.adapter;

import android.view.View;
import android.widget.ImageView;

import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.daobean.HistoryHelper;
import com.yingzhiyun.yingquxue.OkBean.daobean.SearchHistory;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends BaseAdapter<SearchHistory> {

    public  List<SearchHistory> list;
    private setOnClickListener mSetOnClickListener;

    public SearchAdapter(List<SearchHistory> dataList) {
        super(dataList);
        this.list=dataList;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_search;
    }

    @Override
    public void addAll(List<SearchHistory> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, SearchHistory s, int position) {
        holder.setText(R.id.item_search_title,s.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetOnClickListener.setOnClickListener(s);
            }
        });


    }
    public  interface  setOnClickListener{
        void setOnClickListener(SearchHistory s);

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
