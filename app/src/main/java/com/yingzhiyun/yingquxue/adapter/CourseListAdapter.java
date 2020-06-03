package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class CourseListAdapter extends BaseAdapter<CourseBean.ResultBean> {

    private final Context context;

    public CourseListAdapter(List<CourseBean.ResultBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_course;
    }

    @Override
    public void addAll(List<CourseBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, CourseBean.ResultBean s, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CourseInfoActivity.class).putExtra("id",s.getId()));
            }
        });
        holder.setText(R.id.subject_type,s.getSubject());
        holder.setText(R.id.course_title,s.getTitle());
        holder.setText(R.id.course_time,s.getEffective());
        ImageView vip_free = holder.itemView.findViewById(R.id.vip_free);
        if(s.isVip()){
            vip_free.setVisibility(View.VISIBLE);
        }else {
            vip_free.setVisibility(View.GONE);
        }
        holder.setText(R.id.renshu,s.getSignUpNumber()+"人报名");
        if(s.getPrice()==0.0){
            holder.setText(R.id.price,"免费");
        }else{
            holder.setText(R.id.price,"￥"+s.getPrice());
        }

        ImageView courseTeacherhead = holder.itemView.findViewById(R.id.course_teacherhead);
        TextView teacher_name = holder.itemView.findViewById(R.id.teacher_name);
        holder.setText(R.id.teacher_name,s.getTeacherName());
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context)
                .load(s.getTeacherHead())
                .apply(requestOptions)
                .into(courseTeacherhead);

        if(s.getTeacherName().length()>0){
            courseTeacherhead.setVisibility(View.VISIBLE);
            teacher_name.setVisibility(View.VISIBLE);
        }


    }
}
