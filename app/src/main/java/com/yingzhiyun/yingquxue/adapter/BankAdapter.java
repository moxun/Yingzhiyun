package com.yingzhiyun.yingquxue.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.Mvp.SaveAnswer;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.tiku.DatiKaActivity;
import com.yingzhiyun.yingquxue.activity.tiku.ExamineActivity;
import com.yingzhiyun.yingquxue.activity.tiku.ZuTiActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import org.w3c.dom.NamedNodeMap;

import java.util.List;

public class BankAdapter extends BaseAdapter<ExamineBean.ResultBean.DaTiBeanListBean.StemBeanListBean> {

    private final Context context;
    private final String string;
    private SaveAnswer saveAnswer;

    public BankAdapter(List<ExamineBean.ResultBean.DaTiBeanListBean.StemBeanListBean> dataList, Context context,String string) {
        super(dataList);
        this.context =context;
        saveAnswer= (ZuTiActivity) ZuTiActivity.instance;
        this.string =string;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_bank_select;
    }

    @Override
    public void addAll(List<ExamineBean.ResultBean.DaTiBeanListBean.StemBeanListBean> list, int page) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void createHolder(ViewHolder holder, ExamineBean.ResultBean.DaTiBeanListBean.StemBeanListBean optionsListBean, int position) {
        TextView textView = holder.itemView.findViewById(R.id.item_tx_bank);
        textView.setText(position+1+"");
        Log.d("moxun", "createHolder: "+string);
        if(string.equals("card")){
            if(optionsListBean.getStatus()!= null){
                if(optionsListBean.getStatus().equals("empty")){
                    textView.setTextColor(Color.parseColor("#1091E9"));
                    textView.setBackgroundResource(R.mipmap.icon_defalut);
                }else{
                    textView.setTextColor(Color.parseColor("#ffffff"));
                    textView.setBackgroundResource(R.mipmap.icon_right);
                }
            }else {
                textView.setTextColor(Color.parseColor("#1091E9"));
                textView.setBackgroundResource(R.mipmap.icon_defalut);
            }
        }else{
            if(optionsListBean.getStatus()!= null){
                if(optionsListBean.getStatus().equals("empty")){
                    textView.setTextColor(Color.parseColor("#1091E9"));
                    textView.setBackgroundResource(R.mipmap.icon_defalut);
                }else if(optionsListBean.getStatus().equals("false")){
                    textView.setTextColor(Color.parseColor("#ffffff"));
                    textView.setBackgroundResource(R.mipmap.icon_wrong);
                }else{
                    textView.setTextColor(Color.parseColor("#ffffff"));
                    textView.setBackgroundResource(R.mipmap.icon_right);
                }
            }else {
                textView.setTextColor(Color.parseColor("#1091E9"));
                textView.setBackgroundResource(R.mipmap.icon_defalut);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(string.equals("card")){
                    ZuTiActivity.instance.backIntent(optionsListBean.getTh());
                    DatiKaActivity.instance.finish();
                }else{

                }


            }
        });
    }
}
