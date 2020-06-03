package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.ChapterListBean;
import com.yingzhiyun.yingquxue.OkBean.KnowPointerBean;
import com.yingzhiyun.yingquxue.R;

import java.util.ArrayList;



import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ChapterListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.sijiliebiao.PaterZhangAdapter;
import com.yingzhiyun.yingquxue.units.BaseExpandableRecyclerViewAdapter;

import java.util.List;

public class MyExtendableListViewAdapter  extends
        BaseExpandableRecyclerViewAdapter<KnowPointerBean.ResultBean, KnowPointerBean.ResultBean.ListBeanXX, MyExtendableListViewAdapter.GroupVH, MyExtendableListViewAdapter.ChildVH> {

    private final Context con;
    private List<KnowPointerBean.ResultBean> mList;

    private PaterZhangAdapter paterZhangAdapter;
    private ArrayList<KnowPointerBean.ResultBean.ListBeanXX> listBeanXXES;


    public MyExtendableListViewAdapter(List<KnowPointerBean.ResultBean> list,Context context) {
        mList = list;
        con =context;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public KnowPointerBean.ResultBean getGroupItem(int groupIndex) {
        return mList.get(groupIndex);
    }

    @Override
    public GroupVH onCreateGroupViewHolder(ViewGroup parent, int groupViewType) {
        return new GroupVH(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_parenter, parent, false));
    }

    @Override
    public void onBindGroupViewHolder(GroupVH holder, KnowPointerBean.ResultBean groupBean, boolean isExpand) {
        holder.nameTv.setText(groupBean.getTitle()+"");

        if(groupBean.isIsboole()){
            groupBean.setIsboole(false);
        }else{
            groupBean.setIsboole(true);
        }


    }

    @Override
    public ChildVH onCreateChildViewHolder(ViewGroup parent, int childViewType) {
        return new ChildVH(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_child, parent, false));
    }

    @Override
    public void onBindChildViewHolder(ChildVH holder, KnowPointerBean.ResultBean groupBean, KnowPointerBean.ResultBean.ListBeanXX detailBean) {
        holder.nameTv.setLayoutManager(new LinearLayoutManager(con));
        listBeanXXES = new ArrayList<>();
        paterZhangAdapter = new PaterZhangAdapter(listBeanXXES, con);
        holder.nameTv.setAdapter(paterZhangAdapter);
        listBeanXXES.add(detailBean);
        paterZhangAdapter.notifyDataSetChanged();



    }

    static class GroupVH extends BaseExpandableRecyclerViewAdapter.BaseGroupViewHolder {

        TextView nameTv;

        GroupVH(View itemView) {
            super(itemView);

            nameTv = (TextView) itemView.findViewById(R.id.label_group_normal);
        }

        @Override
        protected void onExpandStatusChanged(RecyclerView.Adapter relatedAdapter, boolean isExpanding) {

        }


    }

    static class ChildVH extends RecyclerView.ViewHolder {
        RecyclerView nameTv;

        ChildVH(View itemView) {
            super(itemView);
            nameTv =  itemView.findViewById(R.id.eList);

        }
    }
}
