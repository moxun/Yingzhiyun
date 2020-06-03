package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class WenKeChooseAdapter extends RecyclerView.Adapter<WenKeChooseAdapter.MyViewHolder> {
    private final String type;
    private Context context;
    private List<TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean.StemListBean.OptionsListBean> list;

    private int defItem = -1;
    private OnItemListener onItemListener;

    public WenKeChooseAdapter(Context context, List<TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean.StemListBean.OptionsListBean> list,String type) {
        this.context=context;
        this.list=list;
        this.type=type;
    }
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick(View v, int pos, TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean.StemListBean.OptionsListBean projectc,String s);
    }
    //在activity调用这个，下标就会赋值到你点的那个条目，然后全部刷新，就会在走onBindViewHolder方法
    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }


    @Override
    public WenKeChooseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_celect, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(WenKeChooseAdapter.MyViewHolder holder, int position) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < list.get(position).getText().size(); i++) {

            if(list.get(position).getText().get(i).getContentType().equals("text")){
                stringBuilder.append(list.get(position).getText().get(i).getContent());
            }else if (list.get(position).getText().get(i).getContentType().equals("latex")){
                stringBuilder.append("$$");
                stringBuilder.append(list.get(position).getText().get(i).getContent());
                stringBuilder.append("$$");
            }else{
                stringBuilder.append("<img height=");
                String inputText1=list.get(position).getText().get(i).getHeight();
                String inputText2=list.get(position).getText().get(i).getHeight();
                int num1= Integer.valueOf(inputText1);
                int num2= Integer.valueOf(inputText2);
                num1=num1+num2;

                String inputText=list.get(position).getText().get(i).getHeight();
                String inputText3=list.get(position).getText().get(i).getWidth();
                int num= Integer.valueOf(inputText);
                int num3= Integer.valueOf(inputText3);
                num=num+num3;
                stringBuilder.append(num1);
                int b= Integer.parseInt(list.get(position).getText().get(i).getWidth()+list.get(position).getText().get(i).getWidth());
                stringBuilder.append(" width="+num+">");
                stringBuilder.append(list.get(position).getText().get(i).getContent());
                stringBuilder.append("</img>");
            }
        }

        holder.viewById.setText(stringBuilder.toString());
        holder.tv.setText(list.get(position).getValue());
        //这是第一次进来
        if (defItem != -1) {
            //第二次进来
            if (defItem == position) {
                //选中状态
                holder.tv.setTextColor(Color.parseColor("#ffffff"));
                holder.tv.setBackgroundResource(R.drawable.yes_select);
            } else {

                holder.tv.setTextColor(Color.parseColor("#1091E9"));
                holder.tv.setBackgroundResource(R.drawable.bu_select);
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
                            holder.tv.setBackgroundResource(R.drawable.bu_select);
                        }else {
                            list.get(position).setChoose(true);
                            holder.tv.setTextColor(Color.parseColor("#ffffff"));
                            holder.tv.setBackgroundResource(R.drawable.yes_select);
                        }
                    }
                    ArrayList<String> strings = new ArrayList<>();
                    for (int i = 0; i <list.size() ; i++) {
                        if(list.get(i).isChoose()){
                            strings.add(list.get(i).getValue());
                        }
                    }
                    String s = listToString1(strings);
                    onItemListener.onClick(v,position,list.get(position),s);
                }
            }
        });
        holder.viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemListener != null) {

                    if(!type.equals("RadioSelect")){
                        if(list.get(position).isChoose()){
                            list.get(position).setChoose(false);
                            holder.tv.setTextColor(Color.parseColor("#1091E9"));
                            holder.tv.setBackgroundResource(R.drawable.bu_select);
                        }else {
                            list.get(position).setChoose(true);
                            holder.tv.setTextColor(Color.parseColor("#1091E9"));
                            holder.tv.setBackgroundResource(R.drawable.bu_select);
                        }
                    }
                    ArrayList<String> strings = new ArrayList<>();
                    for (int i = 0; i <list.size() ; i++) {
                        if(list.get(i).isChoose()){
                            strings.add(list.get(i).getValue());
                        }
                    }
                    String s = listToString1(strings);
                    onItemListener.onClick(view,position,list.get(position),s);
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
        private final FlexibleRichTextView viewById;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.xuanxiang);
            viewById = view.findViewById(R.id.content);

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
