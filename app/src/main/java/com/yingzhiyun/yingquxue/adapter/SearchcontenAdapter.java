package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.SearchcontentBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.VideoinfoActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.WordActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class SearchcontenAdapter extends BaseAdapter<HomePagerBean.ResultBean.SectionDetailBean.DetailBean> {

    private final Context context;

    public SearchcontenAdapter(List<HomePagerBean.ResultBean.SectionDetailBean.DetailBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_ziyuan_info;
    }

    @Override
    public void addAll(List<HomePagerBean.ResultBean.SectionDetailBean.DetailBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, HomePagerBean.ResultBean.SectionDetailBean.DetailBean resultBean, int position) {
        holder.setText(R.id.item_title, resultBean.getTitle());


        holder.setText(R.id.item_size,resultBean.getFile_size());
        holder.setText(R.id.read_value,resultBean.getRead_volume()+"人阅读");
        ImageView viewById = holder.itemView.findViewById(R.id.image_type);
        ImageView vipback = holder.itemView.findViewById(R.id.vip_back);
        if(resultBean.isVip()){
            vipback.setVisibility(View.VISIBLE);
            if (resultBean.getType().equals("word")) {
                viewById.setImageResource(R.drawable.icon_vip_word);
            } else if (resultBean.getType().equals("pdf")) {
                viewById.setImageResource(R.drawable.icon_vip_pdf);
            }else if(resultBean.getType().equals("ppt")){
                viewById.setImageResource(R.drawable.icon_vip_ppt);
            }else if(resultBean.getType().equals("video")){
                viewById.setImageResource(R.drawable.icon_vip_vi);
            }
            RecyclerView recyclerView = holder.itemView.findViewById(R.id.item_recy_label);
            recyclerView.setNestedScrollingEnabled(false);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(new LableAdapter(resultBean.getLabelList(),"vip"));
        }else {
            vipback.setVisibility(View.GONE);
            if (resultBean.getType().equals("word")) {
                viewById.setImageResource(R.mipmap.icon_word);
            } else if (resultBean.getType().equals("pdf")) {
                viewById.setImageResource(R.mipmap.icon_pdf);
            }else if(resultBean.getType().equals("ppt")){
                viewById.setImageResource(R.mipmap.icon_ppt);
            }else if(resultBean.getType().equals("video")){
                viewById.setImageResource(R.mipmap.app_icon_video);
            }
            RecyclerView recyclerView = holder.itemView.findViewById(R.id.item_recy_label);
            recyclerView.setNestedScrollingEnabled(false);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(new LableAdapter(resultBean.getLabelList(),"novip"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resultBean.getType().equals("video")){
                    context.startActivity(new Intent(context, CourseInfoActivity.class).putExtra("id",resultBean.getId()));
                }else{

                    context.startActivity(new Intent(context, WordActivity.class).putExtra("id", resultBean.getId()).putExtra("filepath",resultBean.getFile_path()).putExtra("shoucang",resultBean.isCollection()).putExtra("isvip",resultBean.isVip()));

                }

            }
        });
    }
}
