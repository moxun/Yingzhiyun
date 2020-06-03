package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.skillTypeListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.model.FirstClassItem;
import com.yingzhiyun.yingquxue.model.SecondClassItem;

import java.util.List;

public class TwoLiatAdapter extends BaseAdapter<skillTypeListBean.ResultBean.TypeBean.DetailBeanX> {

    private final Context context;
    private setOnClickListener mSetOnClickListener;

    public TwoLiatAdapter(List<skillTypeListBean.ResultBean.TypeBean.DetailBeanX> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.left_listview_item;
    }

    @Override
    public void addAll(List<skillTypeListBean.ResultBean.TypeBean.DetailBeanX> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, skillTypeListBean.ResultBean.TypeBean.DetailBeanX twoLiatAdapter, int position) {
        TextView title = holder.itemView.findViewById(R.id.left_item_name);
        int size = twoLiatAdapter.getDetail().size();
        Log.d("ceshi", "createHolder: "+size);
        if(twoLiatAdapter.getDetail().size()!=0){

            //选中和没选中时，设置不同的颜色
            if (position == selectedPosition){
                title.setBackgroundResource(R.color.white);

            }else{
                title.setBackgroundResource(R.color.ColorEEEEEE);

            }
        }else{
            title.setBackgroundResource(R.color.ColorEEEEEE);
        }

        title.setText(twoLiatAdapter.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetOnClickListener.setOnClickListener(twoLiatAdapter,position);

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
        void setOnClickListener(skillTypeListBean.ResultBean.TypeBean.DetailBeanX musicBean, int position);

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
