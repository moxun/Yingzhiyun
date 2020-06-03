package com.yingzhiyun.yingquxue.adapter;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.BaogaoItem;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class BaoGaoAdapter  extends BaseAdapter<BaogaoItem> {
    public BaoGaoAdapter(List<BaogaoItem> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_baogao;
    }

    @Override
    public void addAll(List<BaogaoItem> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, BaogaoItem baogaoItem, int position) {

        TextView item_info = holder.itemView.findViewById(R.id.item_info);
        item_info.setText(Html.fromHtml(baogaoItem.getInfo()));

        holder.setText(R.id.item_type,baogaoItem.getType());
    }
}
