package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yingzhiyun.yingquxue.OkBean.DuihuancourseBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseBeaginActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class UserExchangeAdapter extends BaseAdapter<DuihuancourseBean.ResultBean> {

    private final Context context;

    public UserExchangeAdapter(List<DuihuancourseBean.ResultBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_duihuan;
    }

    @Override
    public void addAll(List<DuihuancourseBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, DuihuancourseBean.ResultBean resultBean, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", resultBean.getId());
                Intent intent = new Intent(context, CourseBeaginActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.setText(R.id.subject_type,resultBean.getSubject());
        holder.setText(R.id.course_title,resultBean.getTitle());
        holder.setText(R.id.course_time,"有效期至："+resultBean.getEffective());



        ImageView courseTeacherhead = holder.itemView.findViewById(R.id.course_teacherhead);
        TextView teacher_name = holder.itemView.findViewById(R.id.teacher_name);
        holder.setText(R.id.teacher_name,resultBean.getTeacherName());
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context)
                .load(resultBean.getTeacherHead())
                .apply(requestOptions)
                .into(courseTeacherhead);


    }
}
