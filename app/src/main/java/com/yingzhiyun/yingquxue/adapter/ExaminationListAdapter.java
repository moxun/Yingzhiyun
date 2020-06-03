package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.ExaminationListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.BaomingActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.PracticeZuoActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.util.List;

public class ExaminationListAdapter extends BaseAdapter<ExaminationListBean.ResultBean> {
    private final Context context;

    public ExaminationListAdapter(List<ExaminationListBean.ResultBean> dataList, Context context) {
        super(dataList);
        this.context=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_examin;
    }

    @Override
    public void addAll(List<ExaminationListBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ExaminationListBean.ResultBean resultBean, int position) {
        holder.setText(R.id.time,resultBean.getTimeRanges());
        holder.setText(R.id.title,resultBean.getTitle());

        if (resultBean.isStart()) {
            if (resultBean.isEnd()) {
                holder.setText(R.id.login,"已提交");

            }else {
                if (resultBean.isSign()) {
                    holder.setText(R.id.login,"去考试");

                }else {
                    holder.setText(R.id.login,"未报名");

                }
            }
        }else {//考试没开始
            if (resultBean.isSign()) {
                holder.setText(R.id.login,"已报名");

            }else {
                holder.setText(R.id.login,"去报名");

            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (resultBean.isStart()) {
                    if (resultBean.isEnd()) {

                        ToastUtil.makeShortText(context,"您已经提交过卷子");
                    }else {
                        if (resultBean.isSign()) {

                            context.startActivity(new Intent(context, PracticeZuoActivity.class).putExtra("id",resultBean.getId()).putExtra("title",resultBean.getTitle()));
                        }else {

                            ToastUtil.makeShortText(context,"您没有报名此次考试");
                        }
                    }
                }else {//考试没开始
                    if (resultBean.isSign()) {

                        context.startActivity(new Intent(context, BaomingActivity.class).putExtra("id",resultBean.getId()));
                    }else {

                        context.startActivity(new Intent(context, BaomingActivity.class).putExtra("id",resultBean.getId()));
                    }
                }
            }
        });
    }
}
