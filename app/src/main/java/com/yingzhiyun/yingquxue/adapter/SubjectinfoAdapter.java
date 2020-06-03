package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.SubjectInfoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.classfiy.ClassZiyuanActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.List;

public class SubjectinfoAdapter extends BaseAdapter<SubjectInfoBean.ResultBean.DetailBean> {

    private final Context context;

    public SubjectinfoAdapter(List<SubjectInfoBean.ResultBean.DetailBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_subjectinfo;
    }

    @Override
    public void addAll(List<SubjectInfoBean.ResultBean.DetailBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, SubjectInfoBean.ResultBean.DetailBean detailBean, int position) {
        holder.setText(R.id.item_subinfo_title,detailBean.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SharedPreferenceUtils.getisLogin()){
                    context.startActivity(new Intent(context, ClassZiyuanActivity.class).putExtra("bean",detailBean));
                }else{
                    context.startActivity(new Intent(context, PwdLoginActivity.class));
                }

            }
        });
    }
}
