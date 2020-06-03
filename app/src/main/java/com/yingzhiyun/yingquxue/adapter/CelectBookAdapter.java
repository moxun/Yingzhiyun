package com.yingzhiyun.yingquxue.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yingzhiyun.yingquxue.Mvp.ZujianMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.ChapterListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.modle.ZjieTestModle;

import java.util.List;

public class CelectBookAdapter extends BaseAdapter<BooklistBean.ResultBean> {

    public List<BooklistBean.ResultBean> resultBeans;
    private OnItemListener onItemListener;

    public CelectBookAdapter(List<BooklistBean.ResultBean> dataList) {
        super(dataList);
        resultBeans =dataList;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_choosebook;
    }

    @Override
    public void addAll(List<BooklistBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, BooklistBean.ResultBean booklistBean, int position) {
        holder.setText(R.id.item_book_title,booklistBean.getTitle());
        RelativeLayout viewById = holder.itemView.findViewById(R.id.back);
        TextView textView = holder.itemView.findViewById(R.id.item_book_title);
        ImageView imageView = holder.itemView.findViewById(R.id.item_book_image);
        Glide.with(MyApp.getMyApp()).load(booklistBean.getImg()).into(imageView);
        if(booklistBean.isChoose()){
            textView.setTextColor(Color.parseColor("#1091E9"));
            viewById.setBackgroundResource(R.drawable.back_blue);
        }else{
            textView.setTextColor(Color.parseColor("#ff333333"));
            viewById.setBackgroundColor(Color.parseColor("#ffffff"));;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListener.onClick(view,position,booklistBean);
            }
        });

    }
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick(View v, int pos,  BooklistBean.ResultBean booklistBean );
    }
}
