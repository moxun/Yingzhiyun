package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.skillTypeListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.tiku.ZuTiActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.model.FirstClassItem;

import java.util.List;

public class FirstListADapter extends BaseAdapter<skillTypeListBean.ResultBean.TypeBean> {

    private final Context context;
    private setOnClickListener mSetOnClickListener;

    public FirstListADapter(List<skillTypeListBean.ResultBean.TypeBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.left_listview_item;
    }

    @Override
    public void addAll(List<skillTypeListBean.ResultBean.TypeBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, skillTypeListBean.ResultBean.TypeBean firstListADapter, int position) {
        TextView title = holder.itemView.findViewById(R.id.left_item_name);
        //选中和没选中时，设置不同的颜色
        if (position == selectedPosition){
            title.setBackgroundResource(R.color.ColorE1E1E1);
            title.setTextColor(context.getResources().getColor(R.color.mainColor));
        }else{
            title.setBackgroundResource(R.color.ColorEEEEEE);
            title.setTextColor(context.getResources().getColor(R.color.bg_black));
        }
        title.setText(firstListADapter.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetOnClickListener.setOnClickListener(firstListADapter,position);
            }
        });
    }
    private int selectedPosition = 0;

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public  interface  setOnClickListener{
        void setOnClickListener(skillTypeListBean.ResultBean.TypeBean musicBean,int position);

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
