package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.yingzhiyun.yingquxue.OkBean.MineTeacherBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.course.TeacherinfoActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.List;

public class MineTeacherAdapter extends BaseAdapter<MineTeacherBean.ResultBean> {

    private final Context context;

    public MineTeacherAdapter(List<MineTeacherBean.ResultBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_teacher;
    }

    @Override
    public void addAll(List<MineTeacherBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, MineTeacherBean.ResultBean resultBean, int position) {
        holder.setText(R.id.teacher_name,resultBean.getTeacherName());
        holder.setText(R.id.teacherLabel,resultBean.getTeacherLabel());
        ImageView viewById = holder.itemView.findViewById(R.id.course_teacherhead);

        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context)
                .load(resultBean.getTeacherHead())
                .apply(requestOptions)
                .into(viewById);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id",resultBean.getId());
                bundle.putString("type","course");
                context.startActivity(new Intent(context,TeacherinfoActivity.class).putExtras(bundle));
            }
        });
    }
}
