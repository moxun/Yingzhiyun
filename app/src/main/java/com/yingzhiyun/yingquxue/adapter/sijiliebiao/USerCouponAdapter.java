package com.yingzhiyun.yingquxue.adapter.sijiliebiao;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yingzhiyun.yingquxue.OkBean.DuihuancourseBean;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.adapter.ChooseAdapter;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class USerCouponAdapter extends BaseAdapter<DuihuancourseBean.ResultBean> {

    private final Context context;


    private int defItem = -1;
    private OnItemListener onItemListener;
    public USerCouponAdapter(List<DuihuancourseBean.ResultBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick( int pos,DuihuancourseBean.ResultBean resultBean);
    }
    //在activity调用这个，下标就会赋值到你点的那个条目，然后全部刷新，就会在走onBindViewHolder方法
    public void setDefSelect(int position) {
        this.defItem = position;
        notifyDataSetChanged();
    }
    @Override
    public int getLayoutId() {
        return R.layout.item_duihuan;
    }

    @Override
    public void addAll(List<DuihuancourseBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(BaseAdapter.ViewHolder holder, DuihuancourseBean.ResultBean resultBean, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              onItemListener.onClick(position,resultBean);
            }
        });
        holder.setText(R.id.subject_type,resultBean.getSubject());
        holder.setText(R.id.course_title,resultBean.getTitle());
        holder.setText(R.id.course_time,resultBean.getEffective());

        ImageView choose = holder.itemView.findViewById(R.id.choose);
        //这是第一次进来
        if (defItem != -1) {
            //第二次进来
            if (defItem == position) {
                choose.setVisibility(View.VISIBLE);
            } else {
                choose.setVisibility(View.GONE);

            }
        }
        ImageView courseTeacherhead = holder.itemView.findViewById(R.id.course_teacherhead);

        holder.setText(R.id.teacher_name,resultBean.getTeacherName());
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context)
                .load(resultBean.getTeacherHead())
                .apply(requestOptions)
                .into(courseTeacherhead);


    }
}


