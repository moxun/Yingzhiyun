package com.yingzhiyun.yingquxue.adapter.homepager;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;

import com.bumptech.glide.request.RequestOptions;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.EntranceActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.MoreSelectionActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.SelectionActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.VideoinfoActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class SelectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
//    private final String string;
//
//    private static final int BODY_TYPE = 00002;
//    private static final int FOOT_TYPE = 00003;
//    private final Context context;
//
//    private int footCount = 1;//尾部个数，后续可以自己拓展
//    private LayoutInflater mLayoutInflater;
//    private List<HomePagerBean.ResultBean.SectionDetailBean.DetailBean> listData = new ArrayList<>();
//    private setOnClickListener mSetOnClickListener;
//
//    public SelectionAdapter(Context context, List<HomePagerBean.ResultBean.SectionDetailBean.DetailBean> listData, String teacher) {
//        //this.context=context;
//        mLayoutInflater = LayoutInflater.from(context);
//        this.listData = listData;
//        this.context =context;
//        string =teacher;
//    }
//
//    private int getBodySize() {
//
//        return listData.size();
//    }
//
//
//
//    private boolean isFoot(int position) {
//        return footCount != 0 && (position >= (getBodySize() ));
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if (isFoot(position)) {
//            return FOOT_TYPE;
//        } else {
//            return BODY_TYPE;
//        }
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        switch (viewType) {
//            case BODY_TYPE:
//                return new BodyViewHolder(mLayoutInflater.inflate(R.layout.item_selection, parent, false));
//            case FOOT_TYPE:
//                return new FootViewHolder(mLayoutInflater.inflate(R.layout.item_more, parent, false));
//            default:
//                return null;
//        }
//
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof BodyViewHolder) {
//
//
//            Glide.with(context).load(listData.get(position).getImg_url())
//
//                    .into(((BodyViewHolder) holder).body);
//            ((BodyViewHolder) holder).title.setText(listData.get(position).getTitle());
//            ((BodyViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(SharedPreferenceUtils.getisLogin()){
//                        Intent intent = new Intent(context, EntranceActivity.class);
//                        intent.putExtra("bean",new HomePagerBean.ResultBean.MenuBean("", listData.get(position).getId(),"","" ));
//                        context.startActivity(intent);
//                    }else{
//                        context.startActivity(new Intent(context, PwdLoginActivity.class));
//                    }
//
//                }
//            });
//            ((BodyViewHolder) holder).read_value.setText(listData.get(position).getRead_volume()+"");
//            ((BodyViewHolder) holder).classify.setText(listData.get(position).getAdd_time());
//        } else if (holder instanceof FootViewHolder) {
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mSetOnClickListener.setOnClickListener();
//                }
//            });
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return  getBodySize() + footCount;
//    }
//
//    private static class HeadViewHolder extends RecyclerView.ViewHolder {
//
//        public HeadViewHolder(View itemView) {
//            super(itemView);
//        }
//    }
//
//    private static class BodyViewHolder extends RecyclerView.ViewHolder {
//        ImageView body;
//        private final TextView title;
//        private final TextView read_value;
//        private final TextView classify;
//
//        public BodyViewHolder(View itemView) {
//            super(itemView);
//            body = (ImageView) itemView.findViewById(R.id.iv_clinic_avatar);
//            title = itemView.findViewById(R.id.recy_video_title);
//            read_value = itemView.findViewById(R.id.read_value);
//            classify = itemView.findViewById(R.id.recy_video_duan);
//        }
//    }
//
//    private static class FootViewHolder extends RecyclerView.ViewHolder {
//
//        public FootViewHolder(View itemView) {
//            super(itemView);
//
//        }
//    }
//
//    public  interface  setOnClickListener{
//        void setOnClickListener();
//
//    }
//
//    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
//        mSetOnClickListener = setOnClickListener;
//    }
}
