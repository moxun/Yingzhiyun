package com.yingzhiyun.yingquxue.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.TeacherinfoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.units.SeeMoreView;
import com.yingzhiyun.yingquxue.units.StringUtils;

import java.util.List;

public class PingLunListAdapter extends BaseAdapter<TeacherinfoBean.ResultBean.BriefingBean> {
    public PingLunListAdapter(List<TeacherinfoBean.ResultBean.BriefingBean> dataList) {
        super(dataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_teacher_pinjia;
    }

    @Override
    public void addAll(List<TeacherinfoBean.ResultBean.BriefingBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, TeacherinfoBean.ResultBean.BriefingBean string, int position) {
        TextView seeMoreView = holder.itemView.findViewById(R.id.seemore);
        seeMoreView.setText(string.getContent());
        RequestOptions requestOptions = RequestOptions.circleCropTransform().error(R.mipmap.icon_userhead);
        Glide.with(MyApp.getMyApp())
                .load(string.getAppUserHead())
                .apply(requestOptions)
                .into((ImageView) holder.itemView.findViewById(R.id.course_teacherhead));

        holder.setText(R.id.teacher_name,string.getAppUserName());
        LinearLayout llansewr = holder.itemView.findViewById(R.id.ll_answer);
        if(StringUtils.isEmpty(string.getAnswer())){
            llansewr.setVisibility(View.GONE);
        }else {
            holder.setText(R.id.teacher_answer,string.getAnswer());
        }
        holder.setText(R.id.teacher_info,string.getTime());
        if(string.getSchoolName()!=null){
            holder.setText(R.id.teacherLabel,string.getSchoolName());
        }

    }
}
