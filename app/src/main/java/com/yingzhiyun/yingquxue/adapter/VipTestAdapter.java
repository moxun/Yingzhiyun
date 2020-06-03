package com.yingzhiyun.yingquxue.adapter;

import com.yingzhiyun.yingquxue.OkBean.VipCenterBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class VipTestAdapter extends BaseAdapter<VipCenterBean.ResultBean.VipCourseListBean> {

    public VipTestAdapter(List<VipCenterBean.ResultBean.VipCourseListBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_vip_testpager;
    }

    @Override
    public void addAll(List<VipCenterBean.ResultBean.VipCourseListBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, VipCenterBean.ResultBean.VipCourseListBean vipCoursewareListBean, int position) {
        holder.setText(R.id.item_test_title,vipCoursewareListBean.getTitle());
        holder.setText(R.id.item_test_scope,vipCoursewareListBean.getScope());

    }
}
