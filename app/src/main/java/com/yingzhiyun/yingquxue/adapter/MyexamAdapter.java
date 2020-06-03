package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.MyExamBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.BaogaoActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.BaomingActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import java.util.List;

public class MyexamAdapter extends BaseAdapter<MyExamBean.ResultBean> {


    private final Context context;

    public MyexamAdapter(List<MyExamBean.ResultBean> dataList, Context context) {
        super(dataList);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_examin;
    }

    @Override
    public void addAll(List<MyExamBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, MyExamBean.ResultBean resultBean, int position) {
        holder.setText(R.id.time, resultBean.getUpdateTime());
        holder.setText(R.id.title, resultBean.getTitle());
        holder.setText(R.id.login, "查看报告");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(resultBean.isHasAchievements()){
                    context.startActivity(new Intent(context, BaogaoActivity.class).putExtra("id",resultBean.getId()));
//                }else {
//                    ToastUtil.makeShortText(context,"考试进行中，请结束后查看成绩报告");
//                }

            }
        });
    }
}
