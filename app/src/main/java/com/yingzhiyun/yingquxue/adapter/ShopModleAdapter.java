package com.yingzhiyun.yingquxue.adapter;

import com.yingzhiyun.yingquxue.OkBean.ModuleBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class ShopModleAdapter extends BaseAdapter<ModuleBean> {

    public ShopModleAdapter(List<ModuleBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shopmodle;
    }

    @Override
    public void addAll(List<ModuleBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ModuleBean moduleBean, int position) {
            holder.setPic(R.id.item_shopmodle_icon,moduleBean.getImage());
            holder.setText(R.id.item_shopmodle_title,moduleBean.getTitle());
    }
}
