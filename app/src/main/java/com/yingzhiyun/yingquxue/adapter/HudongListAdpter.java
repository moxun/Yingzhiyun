package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.bumptech.glide.request.RequestOptions;
import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.yingzhiyun.yingquxue.OkBean.InteractionsListBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.tiku.HudongInfoActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.List;

public class HudongListAdpter extends BaseAdapter<InteractionsListBean.ResultBean> {

    private final Context context;

    public HudongListAdpter(List<InteractionsListBean.ResultBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_question;
    }

    @Override
    public void addAll(List<InteractionsListBean.ResultBean> list, int page) {

    }

    @Override
    public void createHolder(ViewHolder holder, InteractionsListBean.ResultBean resultBean, int position) {
      FlexibleRichTextView flexibleRichTextView= holder.itemView.findViewById(R.id.question_content);
      holder.setText(R.id.question_time,resultBean.getAddTime());
      holder.setText(R.id.item_name,resultBean.getUserNickname());
        ImageView viewById = holder.itemView.findViewById(R.id.imagehead);
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context).load(resultBean.getUserHeadImgPath()) .apply(requestOptions).
               into(viewById);
//        for (int i = 0; i <resultBean.getContent().getContent().size() ; i++) {
//            if(resultBean.getContent().getContent().get(i).getType().equals("text")){
//                stringBuilder.append(resultBean.getContent().getContent().get(i).getValue());
//            }else{
//                stringBuilder.append("$$");
//                stringBuilder.append(resultBean.getContent().getContent().get(i).getValue());
//                stringBuilder.append("$$");
//            }
//        }
        if(resultBean.getContentString()!=null){
            flexibleRichTextView.setText(resultBean.getContentString());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, HudongInfoActivity.class).putExtra("id",resultBean.getId()));
            }
        });
    }
}
