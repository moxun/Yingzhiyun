package com.yingzhiyun.yingquxue.adapter;

import com.yingzhiyun.yingquxue.OkBean.CourseinfoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class CourseinfoAdapter extends BaseAdapter<CourseinfoBean.ResultBean.CourseBriefingBean> {
    public CourseinfoAdapter(List<CourseinfoBean.ResultBean.CourseBriefingBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_courseinfo;
    }

    @Override
    public void addAll(List<CourseinfoBean.ResultBean.CourseBriefingBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, CourseinfoBean.ResultBean.CourseBriefingBean s, int position) {

    }
}
