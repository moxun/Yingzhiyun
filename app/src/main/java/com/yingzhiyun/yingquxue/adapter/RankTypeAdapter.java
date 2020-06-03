package com.yingzhiyun.yingquxue.adapter;

import android.view.View;
import android.widget.ImageView;

import com.yingzhiyun.yingquxue.OkBean.RankTypeBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class RankTypeAdapter extends BaseAdapter<RankTypeBean> {
    public RankTypeAdapter(List<RankTypeBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_ranking;
    }

    @Override
    public void addAll(List<RankTypeBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, RankTypeBean rankTypeAdapter, int position) {
        holder.setText(R.id.mine_rank,rankTypeAdapter.getMine_rank());
        holder.setText(R.id.all_rank,rankTypeAdapter.getAll_rank());
        holder.setText(R.id.rank_type,rankTypeAdapter.getRank_type());
        ImageView viewById = holder.itemView.findViewById(R.id.zhixian);
        if(position==2){
            viewById.setVisibility(View.GONE);
        }
    }
}
