package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.ExamAnalysisBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class ExamAnalysisAdapter extends BaseAdapter<ExamAnalysisBean.ResultBean.ExamDetailBean> {

    private final int context;

    public ExamAnalysisAdapter(List<ExamAnalysisBean.ResultBean.ExamDetailBean> dataList, int context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_examanalysis;
    }

    @Override
    public void addAll(List<ExamAnalysisBean.ResultBean.ExamDetailBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ExamAnalysisBean.ResultBean.ExamDetailBean resultBean, int position) {

        ImageView viewById = holder.itemView.findViewById(R.id.src_back);

        Glide.with(MyApp.getMyApp())
                .load(resultBean.getAnalysis())
                .apply(new RequestOptions().override(context, resultBean.getImgHeight()*2).placeholder(R.color.white).error(R.color.white).dontAnimate())
                .into(viewById);
    }
}
