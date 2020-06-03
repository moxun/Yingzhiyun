package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.EntranceActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.MoreSelectionActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.SelectionActivity;
import com.yingzhiyun.yingquxue.adapter.homepager.SelectionAdapter;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.util.List;

public class MoreSelectionAdapter extends BaseAdapter<ZiyuanBean.ResultBean> {


    private final Context context;

    public MoreSelectionAdapter(List<ZiyuanBean.ResultBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_selection;
    }

    @Override
    public void addAll(List<ZiyuanBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, ZiyuanBean.ResultBean resultBean, int position) {
        ImageView body;
        TextView title;
        TextView read_value;
        TextView classify;
        body = holder. itemView.findViewById(R.id.iv_clinic_avatar);
        title = holder. itemView.findViewById(R.id.recy_video_title);
        read_value = holder. itemView.findViewById(R.id.read_value);
        classify = holder. itemView.findViewById(R.id.recy_video_duan);
        holder.setPic(R.id.iv_clinic_avatar,resultBean.getImg_url());
        title.setText(resultBean.getTitle());
        read_value.setText(resultBean.getRead_volume());
        classify.setText(resultBean.getAdd_time());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EntranceActivity.class);
                intent.putExtra("bean",new HomePagerBean.ResultBean.MenuBean("", resultBean.getId(),"","" ));
                context.startActivity(intent);
            }
        });
    }
}
