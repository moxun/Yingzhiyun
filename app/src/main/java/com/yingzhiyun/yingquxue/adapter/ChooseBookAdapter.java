package com.yingzhiyun.yingquxue.adapter;

import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.ChapterListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class ChooseBookAdapter extends BaseAdapter<BooklistBean.ResultBean> {

    private setOnClickListener mSetOnClickListener;

    public ChooseBookAdapter(List<BooklistBean.ResultBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_book;
    }

    @Override
    public void addAll(List<BooklistBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, BooklistBean.ResultBean resultBean, int position) {
        holder.setText(R.id.book_title,resultBean.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetOnClickListener.setOnClickListener(resultBean);
            }
        });
    }
    public  interface  setOnClickListener{
        void setOnClickListener(BooklistBean.ResultBean musicBean);

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
