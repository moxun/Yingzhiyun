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
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.SelectionActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.VideoinfoActivity;
import com.yingzhiyun.yingquxue.adapter.homepager.SelectionAdapter;

import java.util.ArrayList;
import java.util.List;

public class KeyWordAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int BODY_TYPE = 00002;
    private static final int FOOT_TYPE = 00003;
    private final Context context;

    private int footCount = 1;//尾部个数，后续可以自己拓展
    private LayoutInflater mLayoutInflater;
    private List<String> listData = new ArrayList<>();
    private setOnClickListener mSetOnClickListener;

    public KeyWordAdapter(Context context, List<String> listData) {
        //this.context=context;
        mLayoutInflater = LayoutInflater.from(context);
        this.listData = listData;
        this.context =context;

    }

    private int getBodySize() {
        Log.d("moxun", "getBodySize: "+listData.size());
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
                return new BodyViewHolder(mLayoutInflater.inflate(R.layout.item_keyword, parent, false));
            case FOOT_TYPE:
                return new FootViewHolder(mLayoutInflater.inflate(R.layout.item_keyimage, parent, false));
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BodyViewHolder) {
            ((BodyViewHolder) holder).title.setText(listData.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mSetOnClickListener.setOnClickListener(listData.get(position));
                }
            });
        } else if (holder instanceof FootViewHolder) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mSetOnClickListener.setfootClickListener();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return  getBodySize() + footCount;
    }



    private static class BodyViewHolder extends RecyclerView.ViewHolder {
        ImageView body;
        private final TextView title;


        public BodyViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.key_title);

        }
    }

    private static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);

        }
    }

    public  interface  setOnClickListener{
        void setOnClickListener(String s);
        void setfootClickListener();

    }

    public  void OnsetOnClickListener(setOnClickListener setOnClickListener){
        mSetOnClickListener = setOnClickListener;
    }
}
