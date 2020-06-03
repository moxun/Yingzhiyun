package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;

import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.LoginSuccesBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.ImagePreviewActivity;
import com.yingzhiyun.yingquxue.activity.tiku.HudongInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class TakePhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BODY_TYPE = 00002;
    private static final int FOOT_TYPE = 00003;
    private final Context context;

    private int footCount = 1;//尾部个数，后续可以自己拓展
    private LayoutInflater mLayoutInflater;
    private List<String> listData = new ArrayList<>();
    private setOnClickListener mSetOnClickListener;

    public TakePhotoAdapter(Context context, List<String> listData) {
        //this.context=context;
        mLayoutInflater = LayoutInflater.from(context);
        this.listData = listData;
        this.context =context;
    }

    private int getBodySize() {
        return listData.size();
    }



    private boolean isFoot(int position) {
        return footCount != 0 && (position >= (getBodySize() ));
    }

    @Override
    public int getItemViewType(int position) {
        if (isFoot(position)) {
            return FOOT_TYPE;
        } else {
            return BODY_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BODY_TYPE:
                return new BodyViewHolder(mLayoutInflater.inflate(R.layout.item_photo_body, parent, false));
            case FOOT_TYPE:
                return new FootViewHolder(mLayoutInflater.inflate(R.layout.item_photo_foot, parent, false));
            default:
                return null;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         if (holder instanceof BodyViewHolder) {
             Glide.with(context).load(listData.get(position)).into(((BodyViewHolder) holder).body);
             ((BodyViewHolder) holder).delete.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     mSetOnClickListener.deleteImage(position);
                 }
             });
             ((BodyViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     Intent intent = new Intent(context, ImagePreviewActivity.class);
                     intent.putStringArrayListExtra("imageList", (ArrayList<String>) listData);
                     intent.putExtra(P.START_ITEM_POSITION, position);
                     intent.putExtra(P.START_IAMGE_POSITION, position);
//                ActivityOptions compat = ActivityOptions.makeSceneTransitionAnimation(HudongInfoActivity.this, imageView, imageView.getTransitionName());
                     context.startActivity(intent);
                 }
             });
        } else if (holder instanceof FootViewHolder) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mSetOnClickListener.setOnClickListener();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return  getBodySize() + footCount;
    }

    private static class HeadViewHolder extends RecyclerView.ViewHolder {

        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static class BodyViewHolder extends RecyclerView.ViewHolder {
        ImageView body;
        private final ImageView delete;

        public BodyViewHolder(View itemView) {
            super(itemView);
            body = (ImageView) itemView.findViewById(R.id.item_mineworks);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    private static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);

        }
    }

    public  interface  setOnClickListener{
        void setOnClickListener();
        void deleteImage(int position);
    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
