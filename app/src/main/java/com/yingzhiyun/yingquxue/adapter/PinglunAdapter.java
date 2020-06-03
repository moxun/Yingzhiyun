package com.yingzhiyun.yingquxue.adapter;

import android.util.Log;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yingzhiyun.yingquxue.OkBean.MsgBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class PinglunAdapter extends BaseAdapter<MsgBean>  {


    public PinglunAdapter(List<MsgBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_pinglun;
    }

    @Override
    public void addAll(List<MsgBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, MsgBean msgBean, int position) {
        holder.setText(R.id.name,msgBean.getSender()+":");
        holder.setText(R.id.content,msgBean.getMsg());
    }
}
