package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.WordActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class MyyatiAdapter extends BaseAdapter<ZiyuanBean.ResultBean> {

    private final Context context;

    public MyyatiAdapter(List<ZiyuanBean.ResultBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_mintyati;
    }

    @Override
    public void addAll(List<ZiyuanBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ZiyuanBean.ResultBean resultBean, int position) {
        ImageView viewById = holder.itemView.findViewById(R.id.image_type);
        if (resultBean.getType().equals("word")) {
            viewById.setImageResource(R.mipmap.icon_word);
        } else if (resultBean.getType().equals("pdf")) {
            viewById.setImageResource(R.mipmap.icon_pdf);
        }else if(resultBean.getType().equals("ppt")){
            viewById.setImageResource(R.mipmap.icon_ppt);
        }else if(resultBean.getType().equals("video")){
            viewById.setImageResource(R.mipmap.app_icon_video);
        }
        holder.setText(R.id.item_title, resultBean.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resultBean.getType().equals("video")){
                    context.startActivity(new Intent(context, CourseInfoActivity.class).putExtra("id",resultBean.getId()));
                }else{
                    Log.d("moxun", "onClick: ");
                    context.startActivity(new Intent(context, WordActivity.class).putExtra("id", resultBean.getId()).putExtra("filepath",resultBean.getFile_path()).putExtra("shoucang",resultBean.isCollection()).putExtra("isvip",resultBean.isVip()));

                }

            }
        });
    }
}
