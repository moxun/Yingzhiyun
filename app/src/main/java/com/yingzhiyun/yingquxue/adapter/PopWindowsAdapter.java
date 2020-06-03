package com.yingzhiyun.yingquxue.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ChapterListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.units.BaseExpandableRecyclerViewAdapter;

import java.util.List;

public class PopWindowsAdapter  extends
        BaseExpandableRecyclerViewAdapter<AllsubjectBean.ResultBean, AllsubjectBean.ResultBean.DetailBean, PopWindowsAdapter.GroupVH, PopWindowsAdapter.ChildVH> {

    private List<AllsubjectBean.ResultBean> mList;
    private setOnClickListener mSetOnClickListener;

    public PopWindowsAdapter(List<AllsubjectBean.ResultBean> list) {
        mList = list;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public AllsubjectBean.ResultBean getGroupItem(int groupIndex) {
        return mList.get(groupIndex);
    }

    @Override
    public GroupVH onCreateGroupViewHolder(ViewGroup parent, int groupViewType) {
        return new GroupVH(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_pop_fasubject, parent, false));
    }

    @Override
    public void onBindGroupViewHolder(GroupVH holder, AllsubjectBean.ResultBean groupBean, boolean isExpand) {
        holder.nameTv.setText(groupBean.getName());
        RelativeLayout relativeLayout = holder.itemView.findViewById(R.id.real);
        if(!groupBean.getName().equals("初中")){
            relativeLayout.setBackgroundResource(R.drawable.shape_corner10_downblack);
        }


    }

    @Override
    public ChildVH onCreateChildViewHolder(ViewGroup parent, int childViewType) {
        return new ChildVH(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_pop_subject, parent, false));
    }

    @Override
    public void onBindChildViewHolder(ChildVH holder, AllsubjectBean.ResultBean groupBean, AllsubjectBean.ResultBean.DetailBean detailBean) {
        holder.nameTv.setText(detailBean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("moxum", "onClick: ");
                mSetOnClickListener.setOnClickListener(detailBean);
            }
        });

    }
    public  interface  setOnClickListener{
        void setOnClickListener(AllsubjectBean.ResultBean.DetailBean musicBean);


    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
    static class GroupVH extends BaseExpandableRecyclerViewAdapter.BaseGroupViewHolder {
        ImageView foldIv;
        TextView nameTv;

        GroupVH(View itemView) {
            super(itemView);

            nameTv = (TextView) itemView.findViewById(R.id.item_pop_content);
        }

        @Override
        protected void onExpandStatusChanged(RecyclerView.Adapter relatedAdapter, boolean isExpanding) {

        }


    }

    static class ChildVH extends RecyclerView.ViewHolder {
        TextView nameTv;

        ChildVH(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.item_pop_content);

        }
    }
}
