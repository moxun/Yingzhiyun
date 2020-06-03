package com.yingzhiyun.yingquxue.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.FolderListOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class BashiAdapter extends BaseAdapter<ZiyuanBean.ResultBean> {

    private setOnClickListener mSetOnClickListener;

    public BashiAdapter(List<ZiyuanBean.ResultBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_bashi;
    }

    @Override
    public void addAll(List<ZiyuanBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ZiyuanBean.ResultBean resultBean, int position) {
        holder.setText(R.id.recy_video_title,resultBean.getTitle());
        holder.setText(R.id.recy_video_duan,resultBean.getAdd_time());
        holder.setText(R.id.read_value,resultBean.getRead_volume());
        ImageView iv_clinic_avatar = holder.itemView.findViewById(R.id.iv_clinic_avatar);
        if(resultBean.getImg_url()!=null){
            holder.setPic(R.id.iv_clinic_avatar,resultBean.getImg_url());
        }else {
            iv_clinic_avatar.setImageResource(R.mipmap.icon_wenjianjia);
        }
        TextView viewById = holder.itemView.findViewById(R.id.follow);
        if(resultBean.isFolderCollection()){
            viewById.setText("已收藏");
            viewById.setBackgroundResource(R.drawable.yiguanzhu);

        }else {
            viewById.setText("收藏");
            viewById.setBackgroundResource(R.drawable.shoucang);

        }

        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetOnClickListener.setCollListener(resultBean,position,viewById);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetOnClickListener.setOnClickListener(resultBean,position);
            }
        });
    }
    public  interface  setOnClickListener{
        void setOnClickListener(ZiyuanBean.ResultBean resultBean, int position);
        void setCollListener(ZiyuanBean.ResultBean resultBean, int position,TextView textView);
    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
