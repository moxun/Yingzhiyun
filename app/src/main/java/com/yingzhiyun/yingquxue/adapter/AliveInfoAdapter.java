package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.activity.zhibo.AliveStatusActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class AliveInfoAdapter  extends BaseAdapter<CourseBean.ResultBean> {

    private final Context context;
    private final String string;
    private final String string1;

    public AliveInfoAdapter(List<CourseBean.ResultBean> dataList, Context context,String type,String dianji) {
        super(dataList);
        this.context =context;
        string =type;
        string1 =dianji;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_alive_my;
    }

    @Override
    public void addAll(List<CourseBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(BaseAdapter.ViewHolder holder, CourseBean.ResultBean s, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(string.equals("退费课程")){
                    return;
                }
                if(string1.equals("alive")){
                    context.startActivity(new Intent(context, AliveStatusActivity.class).putExtra("id",s.getId()));
                }else {
                    context.startActivity(new Intent(context, CourseInfoActivity.class).putExtra("id",s.getId()));
                }

            }
        });
        holder.setText(R.id.subject_type,s.getSubject());
        holder.setText(R.id.course_title,s.getTitle());
        holder.setText(R.id.course_time,s.getEffective());


        TextView hint = holder.itemView.findViewById(R.id.hint);
        if(string.equals("退费课程")){
            hint.setVisibility(View.VISIBLE);
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
