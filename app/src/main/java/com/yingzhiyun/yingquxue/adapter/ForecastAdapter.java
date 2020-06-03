package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.ForecastInfoActivity;
import com.yingzhiyun.yingquxue.activity.mine.MyyatiinfoActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.util.List;

public class ForecastAdapter extends BaseAdapter<BetListBean.ResultBean> {

    private final Context context;
    private  String onrtype;
    private final String gradeid;

    public ForecastAdapter(List<BetListBean.ResultBean> dataList, Context context, String type, String gradetype) {
        super(dataList);
        this.context =context;
        onrtype =type;
        gradeid = gradetype;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_yati;
    }

    @Override
    public void addAll(List<BetListBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, BetListBean.ResultBean string, int position) {

        holder.setText(R.id.recy_video_title,string.getTitle());
        holder.setText(R.id.read_value,string.getDocumentNum()+"份文档");
        TextView price = holder.itemView.findViewById(R.id.follow);
        ImageView yigoumai = holder.itemView.findViewById(R.id.yihoumai);
        price.setText("￥"+string.getPrice());
        if(gradeid.equals("1")){
            holder.setText(R.id.recy_video_duan,"初三");
        }else {
            holder.setText(R.id.recy_video_duan,"高三");
        }
        if(string.isIsSign()){
            price.setVisibility(View.GONE);
            yigoumai.setVisibility(View.VISIBLE);
        }else {
            price.setVisibility(View.VISIBLE);
            yigoumai.setVisibility(View.GONE);
        }
        if(onrtype.equals("mine")){
            price.setVisibility(View.GONE);
            yigoumai.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onrtype.equals("mine")){
                    context.startActivity(new Intent(context, MyyatiinfoActivity.class).putExtra("bean",string));
                }else {
                    if(!string.isIsSign()){
                        context.startActivity(new Intent(context, ForecastInfoActivity.class).putExtra("bean",string));
                    }
                    else {
                        ToastUtil.makeShortText(context,"请勿重复购买哦");
                    }
                }

            }
        });
    }
}
