package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.OkBean.skillCourseListBeam;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class VocationalAdapter extends BaseAdapter<skillCourseListBeam .ResultBean> {

    private final Context context;

    public VocationalAdapter(List<skillCourseListBeam .ResultBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_vocational;
    }

    @Override
    public void addAll(List<skillCourseListBeam .ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, skillCourseListBeam .ResultBean resultBean, int position) {
        holder.setText(R.id.recy_video_title,resultBean.getTitle());
        holder.setText(R.id.recy_video_duan,resultBean.getPrice());
        holder.setPic(R.id.iv_clinic_avatar,resultBean.getImg());
        holder.setText(R.id.size,resultBean.getEnrolmentNum()+"人报名");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CourseInfoActivity.class).putExtra("id",resultBean.getId()));
            }
        });
    }
}
