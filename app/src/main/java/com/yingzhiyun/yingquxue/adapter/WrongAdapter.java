package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.daquexian.flexiblerichtextview.Tokenizer;
import com.yingzhiyun.yingquxue.OkBean.MyTiBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.tiku.ExamineShowActivity;
import com.yingzhiyun.yingquxue.activity.tiku.TiShoucangActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WrongAdapter extends BaseAdapter<MyTiBean.ResultBean> {

    private final Context context;
    private final List<MyTiBean.ResultBean> resultBeans;
    private final String string;
    private CelecthowAdapter celecthowAdapter;
    private  int  idi;

    public WrongAdapter(List<MyTiBean.ResultBean> dataList, Context context,int id,String type) {
        super(dataList);
        this.context = context;
        resultBeans = dataList;
        idi=id;
        string =type;
    }


    @Override
    public int getLayoutId() {
        return R.layout.item_wrong;
    }

    @Override
    public void addAll(List<MyTiBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, MyTiBean.ResultBean stemBeanListBean, int position) {
        LinearLayout viewById = holder.itemView.findViewById(R.id.lul);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.startActivity(new Intent(context, ExamineShowActivity.class).putExtra("list", (Serializable) resultBeans).putExtra("position", position).putExtra("id",idi).putExtra("type",string));
            }
        });

        holder.setText(R.id.item_wrong_time, stemBeanListBean.getAddTime());
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.item_wrong_recy);
        celecthowAdapter = new CelecthowAdapter(stemBeanListBean.getOptionsList());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(celecthowAdapter);


            FlexibleRichTextView content = holder.itemView.findViewById(R.id.item_wrong_content);

            Tokenizer.setImageLabels("[img]\\u[/img]", "<img height=\\h width=\\w>\\u</img>");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i <stemBeanListBean.getStemContents().size() ; i++) {
                if(stemBeanListBean.getStemContents().get(i).getContentType().equals("text")){
                    stringBuilder.append(stemBeanListBean.getStemContents().get(i).getContent());
                }else if (stemBeanListBean.getStemContents().get(i).getContentType().equals("latex")){
                    stringBuilder.append("$$");
                    stringBuilder.append(stemBeanListBean.getStemContents().get(i).getContent());
                    stringBuilder.append("$$");
                }else{
                    stringBuilder.append("<img height=");
                    String inputText1=stemBeanListBean.getStemContents().get(i).getHeight();
                    String inputText2=stemBeanListBean.getStemContents().get(i).getHeight();
                    int num1= Integer.valueOf(inputText1);
                    int num2= Integer.valueOf(inputText2);
                    num1=num1+num2;

                    String inputText=stemBeanListBean.getStemContents().get(i).getHeight();
                    String inputText3=stemBeanListBean.getStemContents().get(i).getWidth();
                    int num= Integer.valueOf(inputText);
                    int num3= Integer.valueOf(inputText3);
                    num=num+num3;
                    stringBuilder.append(num1);
                    int b= Integer.parseInt(stemBeanListBean.getStemContents().get(i).getWidth()+stemBeanListBean.getStemContents().get(i).getWidth());
                    stringBuilder.append(" width="+num+">");
                    stringBuilder.append(stemBeanListBean.getStemContents().get(i).getContent());
                    stringBuilder.append("</img>");
                }

            String s = stringBuilder.toString();


            content.setText(s);

        }
    }

}

