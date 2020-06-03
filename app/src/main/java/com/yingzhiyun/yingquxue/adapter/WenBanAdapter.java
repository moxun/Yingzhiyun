package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.Mvp.SaveAnswer;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.tiku.DatiKaActivity;
import com.yingzhiyun.yingquxue.activity.tiku.ExamineActivity;
import com.yingzhiyun.yingquxue.activity.tiku.LiberalartZutiActivity;
import com.yingzhiyun.yingquxue.activity.tiku.WenExamineActivity;
import com.yingzhiyun.yingquxue.activity.tiku.ZuTiActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class WenBanAdapter extends BaseAdapter<ExamineBean.ResultBean.DaTiBeanListBean.StemBeanListBean.StemListBean> {

    private final Context context;
    private final SaveAnswer saveAnswer;
    private final String string;

    public WenBanAdapter(List<ExamineBean.ResultBean.DaTiBeanListBean.StemBeanListBean.StemListBean> dataList, Context  context,String type) {
        super(dataList);
        this.context =context;
        saveAnswer = (LiberalartZutiActivity) LiberalartZutiActivity.instance;
        string =type;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_bank_select;
    }

    @Override
    public void addAll(List<ExamineBean.ResultBean.DaTiBeanListBean.StemBeanListBean.StemListBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ExamineBean.ResultBean.DaTiBeanListBean.StemBeanListBean.StemListBean stemListBean, int position) {
        TextView textView = holder.itemView.findViewById(R.id.item_tx_bank);
        textView.setText(position+1+"");
        if(string.equals("card")){
            if(stemListBean.getStatus()!= null){

                if(stemListBean.getStatus().equals("empty")){
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
            if(stemListBean.getStatus()!= null){

                if(stemListBean.getStatus().equals("empty")){
                    textView.setTextColor(Color.parseColor("#1091E9"));
                    textView.setBackgroundResource(R.mipmap.icon_defalut);
                }else if(stemListBean.getStatus().equals("false")){
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
                saveAnswer.backIntent(stemListBean.getTh());
                if(string.equals("card")){
                    DatiKaActivity.instance.finish();
                }else{
                    WenExamineActivity.instance.finish();
                }

            }
        });
    }
}
