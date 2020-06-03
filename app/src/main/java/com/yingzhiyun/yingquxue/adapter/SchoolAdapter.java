package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.WordActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.List;

public class SchoolAdapter extends BaseAdapter<ZiyuanBean.ResultBean> {

    private final Context context;
    private final List<ZiyuanBean.ResultBean> listBeans;

    public SchoolAdapter(List<ZiyuanBean.ResultBean> dataList, Context context) {
        super(dataList);
        this.context =context;
        listBeans =dataList;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_school;
    }

    @Override
    public void addAll(List<ZiyuanBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ZiyuanBean.ResultBean listBean, int position) {
        holder.setText(R.id.item_school_title,listBean.getTitle());
        holder.setText(R.id.item_school_addtime,listBean.getAdd_time());
        holder.setText(R.id.item_school_size,listBean.getFile_size());
        View viewById = holder.itemView.findViewById(R.id.fen);
        if(position==listBeans.size()-1){
            viewById.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SharedPreferenceUtils.getisLogin()){
                    //点击轮播图
                    context.startActivity(new Intent(context, WordActivity.class).putExtra("id", listBean.getId()).putExtra("filepath",listBean.getFile_path()).putExtra("shoucang",listBean.isCollection()));
                }else{
                    context.startActivity(new Intent(context,PwdLoginActivity.class));
                }

            }
        });
    }
}
