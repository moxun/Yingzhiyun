package com.yingzhiyun.yingquxue.adapter;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.localbean.UserinAdapterBean;
import com.yingzhiyun.yingquxue.R;

import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class UserinfoAdapter extends BaseAdapter<UserinAdapterBean> {

    public  List<UserinAdapterBean> userinAdapterBeans;
    private setOnClickListener mSetOnClickListener;

    public UserinfoAdapter(List<UserinAdapterBean> dataList) {
        super(dataList);
        userinAdapterBeans =dataList;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_updateinfo;
    }

    @Override
    public void addAll(List<UserinAdapterBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, UserinAdapterBean userinfoAdapter, int position) {
            holder.setText(R.id.tiltle,userinfoAdapter.getTitle());
        TextView editText = holder.itemView.findViewById(R.id.info_content);

        editText.setText(userinfoAdapter.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetOnClickListener.setOnClickListener(userinfoAdapter);
            }
        });
    }
    public  interface  setOnClickListener{
        void setOnClickListener(UserinAdapterBean userinAdapterBean);

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }

}
