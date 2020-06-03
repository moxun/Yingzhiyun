//package com.yingzhiyun.yingquxue.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.View;
//
//import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
//import com.yingzhiyun.yingquxue.OkBean.VideoinfoBean;
//import com.yingzhiyun.yingquxue.R;
//import com.yingzhiyun.yingquxue.activity.homepagr.VideoinfoActivity;
//import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
//
//import java.util.List;
//
//public class RecommendAaapter extends BaseAdapter<HomePagerBean.ResultBean.SectionDetailBean.DetailBean> {
//
//    private final Context context;
//
//    public RecommendAaapter(List<HomePagerBean.ResultBean.SectionDetailBean.DetailBean> dataList, Context context) {
//        super(dataList);
//        this.context =context;
//    }
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.item_childex;
//    }
//
//    @Override
//    public void addAll(List<HomePagerBean.ResultBean.SectionDetailBean.DetailBean> list, int page) {
//
//    }
//
//    @Override
//    public void createHolder(ViewHolder holder, HomePagerBean.ResultBean.SectionDetailBean.DetailBean recommendListBean, int position) {
//        holder.setText(R.id.content,recommendListBean.getTitle());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                context.startActivity(new Intent(context, VideoinfoActivity.class).putExtra("bean",recommendListBean));
//            }
//        });
//    }
//}
