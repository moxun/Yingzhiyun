package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.ClassBeaginBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.model.FirstClassItem;

import java.util.List;

public class CourseBeginAdapter extends BaseAdapter<ClassBeaginBean.ResultBean.CourseOutlineBean> {

    private final Context context;
    private setOnClickListener mSetOnClickListener;

    public CourseBeginAdapter(List<ClassBeaginBean.ResultBean.CourseOutlineBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_coursedagang;
    }

    @Override
    public void addAll(List<ClassBeaginBean.ResultBean.CourseOutlineBean> list, int page) {

    }
    private int selectedPosition = 0;

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }
    @Override
    public void createHolder(ViewHolder holder, ClassBeaginBean.ResultBean.CourseOutlineBean courseOutlineBean, int position) {


        holder.setText(R.id.time,courseOutlineBean.getStartTime());
        TextView title = holder.itemView.findViewById(R.id.title);
        title.setText(Html.fromHtml(courseOutlineBean.getTitle()));
        TextView viewById = holder.itemView.findViewById(R.id.login);
        viewById.setVisibility(View.GONE);
        //选中和没选中时，设置不同的颜色
        if (position == selectedPosition){

            title.setTextColor(context.getResources().getColor(R.color.mainColor));
        }else{
            if(courseOutlineBean.getLiveStatus().equals("5")){
                title.setTextColor(context.getResources().getColor(R.color.Color333333));
            }else {
                title.setTextColor(context.getResources().getColor(R.color.Color999999));
            }

//            if(courseOutlineBean.getVideoPath()!=null){
//                title.setTextColor(context.getResources().getColor(R.color.Color333333));
//            }else {
//                title.setTextColor(context.getResources().getColor(R.color.Color999999));
//            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetOnClickListener.setOnClickListener(courseOutlineBean,position);
            }
        });
    }
    public  interface  setOnClickListener{
        void setOnClickListener(ClassBeaginBean.ResultBean.CourseOutlineBean musicBean, int position);

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
