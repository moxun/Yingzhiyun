package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.daquexian.flexiblerichtextview.Tokenizer;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.OkBean.localbean.PracChooseBean;
import com.yingzhiyun.yingquxue.R;

import java.util.ArrayList;
import java.util.List;

public class PracChooseAdapter extends RecyclerView.Adapter<PracChooseAdapter.MyViewHolder>{
    private final String type;
    private Context context;
    private List<PracChooseBean> list;

    private int defItem = -1;
    private PracChooseAdapter.OnItemListener onItemListener;

    public PracChooseAdapter(Context context, List<PracChooseBean> list, String type) {
        this.context=context;
        this.list=list;
        this.type=type;
    }
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick(View v, int pos, PracChooseBean projectc, String s,String type);
    }
    //在activity调用这个，下标就会赋值到你点的那个条目，然后全部刷新，就会在走onBindViewHolder方法
    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }


    @Override
    public PracChooseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PracChooseAdapter.MyViewHolder holder = new PracChooseAdapter.MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_pracchoose, parent,
                false));
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        StringBuilder stringBuilder = new StringBuilder();

            holder.tv.setText(list.get(position).getKeys());

        //这是第一次进来
        if (defItem != -1) {
            //第二次进来
            if (defItem == position) {
                //选中状态
                holder.tv.setTextColor(Color.parseColor("#ffffff"));
                holder.tv.setBackgroundResource(R.drawable.yes_select);
            } else {

                holder.tv.setTextColor(Color.parseColor("#1091E9"));
                holder.tv.setBackgroundResource(R.drawable.buclect_white);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemListener != null) {

                    if(!type.equals("RadioSelect")){
                        if(list.get(position).isChoose()){
                            list.get(position).setChoose(false);
                            holder.tv.setTextColor(Color.parseColor("#1091E9"));
                            holder.tv.setBackgroundResource(R.drawable.buclect_white);
                        }else {
                            list.get(position).setChoose(true);
                            holder.tv.setTextColor(Color.parseColor("#ffffff"));
                            holder.tv.setBackgroundResource(R.drawable.yes_select);
                        }
                    }
                    ArrayList<String> strings = new ArrayList<>();
                    for (int i = 0; i <list.size() ; i++) {
                        if(list.get(i).isChoose()){
                            strings.add(list.get(i).getKeys());
                        }
                    }
                    String s = listToString1(strings);
                    onItemListener.onClick(v,position,list.get(position),s,type);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * ViewHolder的类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;


        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.xuanxiang);


        }


    }
    public static String listToString1(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

}
