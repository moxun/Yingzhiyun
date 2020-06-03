package com.yingzhiyun.yingquxue.adapter.sijiliebiao;

import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.SchoolBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.CelectBookAdapter;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class  SchoolSearchAdapter extends BaseAdapter<SchoolBean.ResultBean> {

    private OnItemListener onItemListener;

    public SchoolSearchAdapter(List<SchoolBean.ResultBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_schoole;
    }

    @Override
    public void addAll(List<SchoolBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, SchoolBean.ResultBean resultBean, int position) {
        holder.setText(R.id.school_type,resultBean.getName());
        holder.setText(R.id.school_address,"("+resultBean.getAddress()+")");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListener.onClick(resultBean);
            }
        });
    }
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick(SchoolBean.ResultBean resultBean );
    }
}
