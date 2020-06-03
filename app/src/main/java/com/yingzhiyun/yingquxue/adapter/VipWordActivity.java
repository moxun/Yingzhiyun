package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.VipCenterBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.WordActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class VipWordActivity  extends BaseAdapter<VipCenterBean.ResultBean.VipCourseListBean> {

    private final Context context;

    public VipWordActivity(List<VipCenterBean.ResultBean.VipCourseListBean> dataList, Context context) {
        super(dataList);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_vipword;
    }

    @Override
    public void addAll(List<VipCenterBean.ResultBean.VipCourseListBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, VipCenterBean.ResultBean.VipCourseListBean vipCourseListBean, int position) {
        holder.setText(R.id.item_vip_title,vipCourseListBean.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, WordActivity.class).putExtra("id", vipCourseListBean.getId()).putExtra("filepath",vipCourseListBean.getFile_path()).putExtra("shoucang",true).putExtra("isvip",true));
            }
        });
    }
}
