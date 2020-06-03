package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.SelectionActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.VideoinfoActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;


public class MoreVideoAdapter extends BaseAdapter<ZiyuanBean.ResultBean> {


    private final Context context;

    public MoreVideoAdapter(List<ZiyuanBean.ResultBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_morevideo;
    }

    @Override
    public void addAll(List<ZiyuanBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(BaseAdapter.ViewHolder holder, ZiyuanBean.ResultBean resultBean, int position) {
        ImageView body;
        TextView title;
        TextView read_value;
        TextView classify;
        body = holder. itemView.findViewById(R.id.iv_clinic_avatar);
        title = holder. itemView.findViewById(R.id.recy_video_title);
        read_value = holder. itemView.findViewById(R.id.read_value);
        classify = holder. itemView.findViewById(R.id.recy_video_duan);
        holder.setPic(R.id.iv_clinic_avatar,resultBean.getCourseImg());
        title.setText(resultBean.getTitle());

        read_value.setText(resultBean.getSignUpNumber()+"人报名");
        if(resultBean.getPrice()==0.0){
            classify.setText("免费");
        }else{
            classify.setText("￥"+resultBean.getPrice());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CourseInfoActivity.class).putExtra("id",resultBean.getId()));
            }
        });
    }
}
