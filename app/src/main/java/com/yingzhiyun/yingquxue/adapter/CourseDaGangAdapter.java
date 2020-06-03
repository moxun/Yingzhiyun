package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.CourseinfoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class CourseDaGangAdapter extends BaseAdapter<CourseinfoBean.ResultBean.OutlineBean> {
    private final Context context;
    private final String type;
    private OnItemListener onItemListener;

    public CourseDaGangAdapter(List<CourseinfoBean.ResultBean.OutlineBean> dataList, Context context,String type) {
        super(dataList);
        this.context=context;
        this.type=type;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_coursedagang;
    }

    @Override
    public void addAll(List<CourseinfoBean.ResultBean.OutlineBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, CourseinfoBean.ResultBean.OutlineBean s, int position) {

        holder.setText(R.id.time,s.getEffective());
        TextView title = holder.itemView.findViewById(R.id.title);
        title.setText(Html.fromHtml(s.getTitle()));
        TextView login = holder.itemView.findViewById(R.id.login);
        if(type.equals("info")){
            if(s.getIsFreeTask()!=null){
                if(s.getIsFreeTask().equals("0")){
                    login.setVisibility(View.GONE);
                }
            }else {
                login.setVisibility(View.GONE);
            }

        }else {
            login.setVisibility(View.GONE);
        }

     login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemListener.onClick(position,s);
            }
        });
    }
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick( int pos, CourseinfoBean.ResultBean.OutlineBean s);
    }
}
