package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.OkBean.MyAliveBean;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyALiceAdapter extends BaseAdapter<MyAliveBean.ResultBean> {

    private final Context context;
    private OnItemListener onItemListener;
    private String string;

    public void setType(String type){
        string =type;
    }
    public MyALiceAdapter(List<MyAliveBean.ResultBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }
    public interface OnItemListener {
        void onClick(View v, int pos, TestPagperInfo.ResultBean.DaTiBeanListBean.StemBeanListBean.OptionsListBean projectc, String s);
    }
    @Override
    public int getLayoutId() {
        return R.layout.item_mylvie;
    }

    @Override
    public void addAll(List<MyAliveBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, MyAliveBean.ResultBean resultBean, int position) {
        holder.setText(R.id.name,resultBean.getTitle());
        Log.d("---------",resultBean.getTitle());
        RecyclerView reccy_info = holder.itemView.findViewById(R.id.reccy_info);
        reccy_info.setNestedScrollingEnabled(false);
        reccy_info.setLayoutManager(new LinearLayoutManager(context));
        List<CourseBean.ResultBean> detail = resultBean.getDetail();
        ArrayList<CourseBean.ResultBean> list = new ArrayList<>();
        list.addAll(detail);
        AliveInfoAdapter  beganAdapter = new AliveInfoAdapter(list, context,resultBean.getTitle(),string);
        reccy_info.setAdapter(beganAdapter);
    }

}
