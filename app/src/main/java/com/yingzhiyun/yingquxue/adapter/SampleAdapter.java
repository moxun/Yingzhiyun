package com.yingzhiyun.yingquxue.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.ChapterListBean;
import com.yingzhiyun.yingquxue.OkBean.KnowPointerBean;
import com.yingzhiyun.yingquxue.R;

import com.yingzhiyun.yingquxue.units.BaseExpandableRecyclerViewAdapter;


import java.util.ArrayList;
import java.util.List;

import static com.yingzhiyun.yingquxue.adapter.SampleAdapter.*;

public class SampleAdapter extends
        BaseExpandableRecyclerViewAdapter<ChapterListBean.ResultBean, ChapterListBean.ResultBean.DetailBean, SampleAdapter.GroupVH, SampleAdapter.ChildVH> {

    private List<ChapterListBean.ResultBean> mList;
    private setOnClickListener mSetOnClickListener;

    public SampleAdapter(List<ChapterListBean.ResultBean> list) {
        mList = list;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public ChapterListBean.ResultBean getGroupItem(int position) {
        return mList.get(position);
    }

    @Override
    public GroupVH onCreateGroupViewHolder(ViewGroup parent, int groupViewType) {
        return new GroupVH(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_group, parent, false));
    }

    @Override
    public ChildVH onCreateChildViewHolder(ViewGroup parent, int childViewType) {
        return new ChildVH(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_child, parent, false));
    }

    @Override
    public void onBindGroupViewHolder(GroupVH holder, ChapterListBean.ResultBean sampleGroupBean, boolean isExpanding) {
        holder.nameTv.setText(sampleGroupBean.getTitle());
        if (sampleGroupBean.isExpandable()) {
            holder.foldIv.setVisibility(View.VISIBLE);
            holder.foldIv.setImageResource(isExpanding ? R.drawable.ic_arrow_folding : R.drawable.ic_arrow_folding);
        } else {
            holder.foldIv.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onBindChildViewHolder(ChildVH holder, ChapterListBean.ResultBean groupBean, ChapterListBean.ResultBean.DetailBean sampleChildBean) {
        holder.nameTv.setText(sampleChildBean.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetOnClickListener.setOnClickListener(sampleChildBean);
            }
        });
    }

    static class GroupVH extends BaseExpandableRecyclerViewAdapter.BaseGroupViewHolder {
        ImageView foldIv;
        TextView nameTv;

        GroupVH(View itemView) {
            super(itemView);
            foldIv = (ImageView) itemView.findViewById(R.id.group_item_indicator);
            nameTv = (TextView) itemView.findViewById(R.id.group_item_name);
        }

        @Override
        protected void onExpandStatusChanged(RecyclerView.Adapter relatedAdapter, boolean isExpanding) {
            foldIv.setImageResource(isExpanding ? R.drawable.ic_arrow_folding : R.drawable.ic_arrow_folding);
        }


    }

    static class ChildVH extends RecyclerView.ViewHolder {
        TextView nameTv;

        ChildVH(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.child_item_name);
        }
    }
    public  interface  setOnClickListener{
        void setOnClickListener(ChapterListBean.ResultBean.DetailBean musicBean);
    }
    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
