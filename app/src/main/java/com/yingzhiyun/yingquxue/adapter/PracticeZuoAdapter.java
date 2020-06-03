package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.PracticeZuoBean;
import com.yingzhiyun.yingquxue.OkBean.localbean.PracChooseBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class PracticeZuoAdapter extends BaseAdapter<PracticeZuoBean.ResultBean.ExamDetailBean> {
    private final Context context;
    private OnItemListener onItemListener;

    public PracticeZuoAdapter(List<PracticeZuoBean.ResultBean.ExamDetailBean> dataList, Context context) {
        super(dataList);
        this.context=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_practicezuo;
    }

    @Override
    public void addAll(List<PracticeZuoBean.ResultBean.ExamDetailBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, PracticeZuoBean.ResultBean.ExamDetailBean resultBean, int position) {
        holder.setText(R.id.testnumber,resultBean.getTh()+".");
        RecyclerView recy_prac = holder.itemView.findViewById(R.id.recy_prac);
        String type="MultiSelect";
        if(!resultBean.isMultiple()){
            type="RadioSelect";
        }
        ArrayList<PracChooseBean> pracChooseBeans = new ArrayList<>();
        for (int i = 0; i < resultBean.getKeys().size(); i++) {
            pracChooseBeans.add(new PracChooseBean(resultBean.getKeys().get(i),false));
        }

        PracChooseAdapter radioSelect = new PracChooseAdapter(context, pracChooseBeans, type);
        radioSelect.setOnItemListener(new PracChooseAdapter.OnItemListener() {
            @Override
            public void onClick(View v, int pos, PracChooseBean projectc, String s, String type) {
                if(type.equals("RadioSelect")){
                    radioSelect.setDefSelect(pos);

                }

                onItemListener.onClick(v,pos,projectc,s,type,resultBean);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recy_prac.setLayoutManager(linearLayoutManager);
        recy_prac.setAdapter(radioSelect);
    }
    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
    public interface OnItemListener {
        void onClick(View v, int pos, PracChooseBean projectc, String s,String type,PracticeZuoBean.ResultBean.ExamDetailBean examineshowAdapter);
    }
}
