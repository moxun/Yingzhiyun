package com.yingzhiyun.yingquxue.adapter;

import android.view.View;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.skillTypeListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.model.SecondClassItem;
import com.yingzhiyun.yingquxue.model.ThreeClassItem;

import java.util.List;

public class ThreeListAdapter extends BaseAdapter<skillTypeListBean.ResultBean.TypeBean.DetailBeanX.DetailBean> {

    private setOnClickListener mSetOnClickListener;

    public ThreeListAdapter(List<skillTypeListBean.ResultBean.TypeBean.DetailBeanX.DetailBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.left_listview_item;
    }

    @Override
    public void addAll(List<skillTypeListBean.ResultBean.TypeBean.DetailBeanX.DetailBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, skillTypeListBean.ResultBean.TypeBean.DetailBeanX.DetailBean threeClassItem, int position) {
        TextView title = holder.itemView.findViewById(R.id.left_item_name);

       title.setText(threeClassItem.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetOnClickListener.setOnClickListener(threeClassItem,position);
            }
        });
    }
    public  interface  setOnClickListener{
        void setOnClickListener(skillTypeListBean.ResultBean.TypeBean.DetailBeanX.DetailBean musicBean, int position);

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
