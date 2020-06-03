package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.ParperinfoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.examination.ChangeScoreActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class ScoreListAdapter extends BaseAdapter<ParperinfoBean.DataBean> {

    private final Context context;

    public ScoreListAdapter(List<ParperinfoBean.DataBean> dataList, Context context) {
        super(dataList);
        this.context=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_scorehistory;
    }

    @Override
    public void addAll(List<ParperinfoBean.DataBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ParperinfoBean.DataBean parperinfoBean, int position) {
        holder.setText(R.id.paper_text2,parperinfoBean.getTestNo());


        holder.setText(R.id.score,parperinfoBean.getDoScore()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("paperId",parperinfoBean.getPaperId());
                bundle.putString("testNo",parperinfoBean.getTestNo());
                bundle.putInt("testId",parperinfoBean.getId());
                bundle.putString("Img",parperinfoBean.getTestImg());
                context.startActivity(new Intent(context, ChangeScoreActivity.class).putExtras(bundle));
            }
        });
    }
}
