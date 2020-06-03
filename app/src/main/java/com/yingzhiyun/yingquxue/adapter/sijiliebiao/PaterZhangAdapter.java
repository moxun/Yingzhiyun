package com.yingzhiyun.yingquxue.adapter.sijiliebiao;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.KnowPointerBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.tiku.Chooseknowledge;
import com.yingzhiyun.yingquxue.adapter.MyExtendableListViewAdapter;
import com.yingzhiyun.yingquxue.units.BaseExpandableRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class PaterZhangAdapter  extends
        BaseExpandableRecyclerViewAdapter<KnowPointerBean.ResultBean.ListBeanXX, KnowPointerBean.ResultBean.ListBeanXX.ListBeanX, PaterZhangAdapter.GroupVH, PaterZhangAdapter.ChildVH> {

    private final Context con;
    private List<KnowPointerBean.ResultBean.ListBeanXX> mList;

    private ArrayList<KnowPointerBean.ResultBean.ListBeanXX.ListBeanX> listBeanXES;


    public PaterZhangAdapter(List<KnowPointerBean.ResultBean.ListBeanXX> list,Context context) {
        mList = list;
        con =context;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public KnowPointerBean.ResultBean.ListBeanXX getGroupItem(int groupIndex) {
        return mList.get(groupIndex);
    }

    @Override
    public GroupVH onCreateGroupViewHolder(ViewGroup parent, int groupViewType) {
        return new GroupVH(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_parter_group, parent, false));
    }

    @Override
    public void onBindGroupViewHolder(GroupVH holder, KnowPointerBean.ResultBean.ListBeanXX groupBean, boolean isExpand) {
        holder.nameTv.setText(groupBean.getTitle());
        ImageView imageView = holder.itemView.findViewById(R.id.jiantou);
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
                        .inflate(R.layout.item_child_child, parent, false));
    }

    @Override
    public void onBindChildViewHolder(ChildVH holder, KnowPointerBean.ResultBean.ListBeanXX groupBean, KnowPointerBean.ResultBean.ListBeanXX.ListBeanX detailBean) {

        holder.nameTv.setText(detailBean.getTitle());

        boolean is=false;

        for (int i = 0; i <Chooseknowledge.instance.listBeans.size() ; i++) {
            if(Chooseknowledge.instance.listBeans.get(i).getTitle().equals(detailBean.getTitle())){
                is=true;
            }
        }
        if(is){

            holder.nameTv.setBackgroundColor(Color.parseColor("#7AC5FF"));
            holder.nameTv.setTextColor(Color.parseColor("#ffffff"));
        }else {
            holder.nameTv.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.nameTv.setTextColor(Color.parseColor("#000000"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(detailBean.isIsboole()){
                    detailBean.setIsboole(false);
                    holder.nameTv.setBackgroundColor(Color.parseColor("#ffffff"));
                    holder.nameTv.setTextColor(Color.parseColor("#000000"));
                    Chooseknowledge.instance.listBeans.remove(detailBean);
                }else{
                    detailBean.setIsboole(true);
                    holder.nameTv.setBackgroundColor(Color.parseColor("#7AC5FF"));
                    holder.nameTv.setTextColor(Color.parseColor("#ffffff"));
                    Chooseknowledge.instance.listBeans.add(detailBean);
                }

            }
        });

    }


    static class GroupVH extends BaseExpandableRecyclerViewAdapter.BaseGroupViewHolder {
        ImageView foldIv;
        TextView nameTv;

        GroupVH(View itemView) {
            super(itemView);

            nameTv = (TextView) itemView.findViewById(R.id.parentGroupTV);
        }

        @Override
        protected void onExpandStatusChanged(RecyclerView.Adapter relatedAdapter, boolean isExpanding) {

        }


    }

    static class ChildVH extends RecyclerView.ViewHolder {
        TextView nameTv;

        ChildVH(View itemView) {
            super(itemView);
            nameTv =  itemView.findViewById(R.id.text);

        }
    }
}
