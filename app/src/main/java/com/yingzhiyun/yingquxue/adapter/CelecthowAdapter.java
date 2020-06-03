package com.yingzhiyun.yingquxue.adapter;

import android.util.Log;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.daquexian.flexiblerichtextview.Tokenizer;
import com.yingzhiyun.yingquxue.OkBean.MyTiBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class CelecthowAdapter extends BaseAdapter<MyTiBean.ResultBean.OptionsListBean> {
    public CelecthowAdapter(List<MyTiBean.ResultBean.OptionsListBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_celect_show;
    }

    @Override
    public void addAll(List<MyTiBean.ResultBean.OptionsListBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, MyTiBean.ResultBean.OptionsListBean optionsListBean, int position) {
        holder.setText(R.id.item_celect_key,optionsListBean.getValue()+".");
        StringBuilder stringBuilder = new StringBuilder();


        if (optionsListBean.getText().size() > 0) {
            FlexibleRichTextView content = holder.itemView.findViewById(R.id.item_wrong_content);
            Tokenizer.setImageLabels("[img]\\u[/img]", "<img height=\\h width=\\w>\\u</img>");

            for (int i = 0; i <optionsListBean.getText().size() ; i++) {
                if(optionsListBean.getText().get(i).getContentType().equals("text")){
                    stringBuilder.append(optionsListBean.getText().get(i).getContent());
                }else if (optionsListBean.getText().get(i).getContentType().equals("latex")){
                    stringBuilder.append("$$");
                    stringBuilder.append(optionsListBean.getText().get(i).getContent());
                    stringBuilder.append("$$");
                }else{
                    stringBuilder.append("<img height=");
                    String inputText1=optionsListBean.getText().get(i).getHeight();
                    String inputText2=optionsListBean.getText().get(i).getHeight();
                    int num1= Integer.valueOf(inputText1);
                    int num2= Integer.valueOf(inputText2);
                    num1=num1+num2;

                    String inputText=optionsListBean.getText().get(i).getHeight();
                    String inputText3=optionsListBean.getText().get(i).getWidth();
                    int num= Integer.valueOf(inputText);
                    int num3= Integer.valueOf(inputText3);
                    num=num+num3;
                    stringBuilder.append(num1);
                    int b= Integer.parseInt(optionsListBean.getText().get(i).getWidth()+optionsListBean.getText().get(i).getWidth());
                    stringBuilder.append(" width="+num+">");
                    stringBuilder.append(optionsListBean.getText().get(i).getContent());
                    stringBuilder.append("</img>");
                }
            }

        }

        FlexibleRichTextView content = holder.itemView.findViewById(R.id.item_celect_value);
        content.setText(stringBuilder.toString());
    }
}
