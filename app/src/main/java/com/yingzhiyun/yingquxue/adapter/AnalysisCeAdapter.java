package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.R;

import java.util.List;


public  class AnalysisCeAdapter extends RecyclerView.Adapter<AnalysisCeAdapter.MyViewHolder> {
    private Context context;
    private List<ExamineBean.ResultBean.DaTiBeanListBean.StemBeanListBean.OptionsListBean> list;

    private int defItem = -1;


    public AnalysisCeAdapter(Context context, List<ExamineBean.ResultBean.DaTiBeanListBean.StemBeanListBean.OptionsListBean> list) {
        this.context=context;
        this.list=list;
    }


    //在activity调用这个，下标就会赋值到你点的那个条目，然后全部刷新，就会在走onBindViewHolder方法
    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_celect, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("$$");
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

        Log.d("moxun", "onBindViewHolder: "+stringBuilder.toString());
        holder.viewById.setText(stringBuilder.toString());
        holder.tv.setText(list.get(position).getValue());


        if(list.get(position).isUserSelected()){
            if(!list.get(position).isRight()){
                holder.tv.setTextColor(Color.parseColor("#ffffff"));
                holder.tv.setBackgroundResource(R.mipmap.icon_wrong);
            }else{
                holder.tv.setTextColor(Color.parseColor("#ffffff"));
                holder.tv.setBackgroundResource(R.mipmap.icon_right);
            }
        }else {
            if(list.get(position).isRight()){
                holder.tv.setTextColor(Color.parseColor("#ffffff"));
                holder.tv.setBackgroundResource(R.mipmap.icon_right);
            }
        }
//        //这是第一次进来
//        if (defItem != -1) {
//            //第二次进来
//            if (defItem == position) {
//                //选中状态
//                holder.tv.setTextColor(Color.parseColor("#ffffff"));
//                holder.tv.setBackgroundResource(R.drawable.yes_select);
//            } else {
//
//                holder.tv.setTextColor(Color.parseColor("#1091E9"));
//                holder.tv.setBackgroundResource(R.drawable.bu_select);
//            }
//        }

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
}
