package com.yingzhiyun.yingquxue.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.yingzhiyun.yingquxue.OkBean.BrowsingBean;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.WordActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.adapter.homepager.VideoAdapter;

import java.util.ArrayList;
import java.util.List;

public class BrowsingAapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int Course_TYPE = 00002;
    private static final int Word_TYPE = 00003;
    private final Context context;


    private LayoutInflater mLayoutInflater;
    private List<BrowsingBean.ResultBean> listData = new ArrayList<>();


    public BrowsingAapter(Context context, List<BrowsingBean.ResultBean> listData) {
        //this.context=context;
        mLayoutInflater = LayoutInflater.from(context);
        this.listData = listData;
        this.context =context;
    }

    private int getBodySize() {
        return listData.size();
    }





    @Override
    public int getItemViewType(int position) {
        if (listData.get(position).getType().equals("course")) {
            return Course_TYPE;
        } else {
            return Word_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Course_TYPE:
                return new BrowsingAapter.BodyViewHolder(mLayoutInflater.inflate(R.layout.item_course, parent, false));
            case Word_TYPE:
                return new BrowsingAapter.FootViewHolder(mLayoutInflater.inflate(R.layout.item_ziyuan_info, parent, false));
            default:
                return null;
        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BrowsingBean.ResultBean resultBean = listData.get(position);
        if (holder instanceof BodyViewHolder) {

            ((BodyViewHolder) holder).title.setText(listData.get(position).getTitle());
            ((BodyViewHolder) holder).time.setText(resultBean.getEffective());
            ((BodyViewHolder) holder).teacher_name.setText(resultBean.getTeacherName());
            ((BodyViewHolder) holder).renshu.setText(resultBean.getSignUpNumber()+"人报名");
            ((BodyViewHolder) holder).subject_type.setText(resultBean.getSubject());
            ((BodyViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, CourseInfoActivity.class).putExtra("id",resultBean.getId()));
                }
            });
            if(resultBean.isVip()){
                ((BodyViewHolder) holder).vip_free.setVisibility(View.VISIBLE);
            }else {
                ((BodyViewHolder) holder).vip_free.setVisibility(View.GONE);
            }
            if(listData.get(position).getPrice()==0.0){
                ((BodyViewHolder) holder).price.setText("免费");
            }else{
                ((BodyViewHolder) holder).price.setText("￥"+listData.get(position).getPrice());
            }
            ImageView courseTeacherhead = holder.itemView.findViewById(R.id.course_teacherhead);

            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            Glide.with(context)
                    .load(resultBean.getTeacherHead())
                    .apply(requestOptions)
                    .into(courseTeacherhead);
        } else if (holder instanceof FootViewHolder) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(resultBean.getType().equals("video")){
                        context.startActivity(new Intent(context, CourseInfoActivity.class).putExtra("id",resultBean.getId()));
                    }else{

                        context.startActivity(new Intent(context, WordActivity.class).putExtra("id", resultBean.getId()).putExtra("filepath",resultBean.getFile_path()).putExtra("shoucang",resultBean.isCollection()));

                    }
                }
            });
            ((FootViewHolder) holder).title.setText(resultBean.getTitle());
            if(resultBean.isVip()){
                ((FootViewHolder) holder).vip_back.setVisibility(View.VISIBLE);
                if (resultBean.getType().equals("word")) {
                    ((FootViewHolder) holder).image_type.setImageResource(R.drawable.icon_vip_word);
                } else if (resultBean.getType().equals("pdf")) {
                    ((FootViewHolder) holder).image_type.setImageResource(R.drawable.icon_vip_pdf);
                }else if(resultBean.getType().equals("ppt")){
                    ((FootViewHolder) holder).image_type.setImageResource(R.drawable.icon_vip_ppt);
                }else if(resultBean.getType().equals("video")){
                    ((FootViewHolder) holder).image_type.setImageResource(R.drawable.icon_vip_vi);
                }
                RecyclerView recyclerView = holder.itemView.findViewById(R.id.item_recy_label);
                recyclerView.setNestedScrollingEnabled(false);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(new LableAdapter(resultBean.getLabelList(),"vip"));
            }else {

                ((FootViewHolder) holder).vip_back.setVisibility(View.GONE);
                ((FootViewHolder) holder).item_size.setText(resultBean.getFile_size());
                if (resultBean.getType().equals("word")) {
                    ((FootViewHolder) holder).image_type.setImageResource(R.mipmap.icon_word);
                } else if (resultBean.getType().equals("pdf")) {
                    ((FootViewHolder) holder).image_type.setImageResource(R.mipmap.icon_pdf);
                }else if(resultBean.getType().equals("ppt")){
                    ((FootViewHolder) holder).image_type.setImageResource(R.mipmap.icon_ppt);
                }else if(resultBean.getType().equals("video")){
                    ((FootViewHolder) holder).image_type.setImageResource(R.mipmap.app_icon_video);
                }
                RecyclerView recyclerView = holder.itemView.findViewById(R.id.item_recy_label);
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
                recyclerView.setAdapter(new LableAdapter(resultBean.getLabelList(),"novip"));
            }

        }

    }

    @Override
    public int getItemCount() {
        return  getBodySize() ;
    }



    private static class BodyViewHolder extends RecyclerView.ViewHolder {
        ImageView course_teacherhead;
        private final TextView title;
        private final TextView time;
        private final TextView teacher_name;
        private final TextView renshu;
        private final  TextView price;
        private final TextView subject_type;
        private final View vip_free;

        public BodyViewHolder(View itemView) {
            super(itemView);
            course_teacherhead = (ImageView) itemView.findViewById(R.id.course_teacherhead);
            subject_type = itemView.findViewById(R.id.subject_type);
            title = itemView.findViewById(R.id.course_title);
            time = itemView.findViewById(R.id.course_time);
            teacher_name = itemView.findViewById(R.id.teacher_name);
            renshu=itemView.findViewById(R.id.renshu);
            price=itemView.findViewById(R.id.price);
            vip_free = itemView.findViewById(R.id.vip_free);
        }
    }

    private static class FootViewHolder extends RecyclerView.ViewHolder {
        ImageView image_type;
        private final TextView title;
        private final TextView time;
        private final RecyclerView item_recy_label;
        private final TextView item_size;
        private final ImageView vip_back;

        public FootViewHolder(View itemView) {
            super(itemView);
            image_type = (ImageView) itemView.findViewById(R.id.image_type);
            title = itemView.findViewById(R.id.item_title);
            time = itemView.findViewById(R.id.item_time);
            item_recy_label = itemView.findViewById(R.id.item_recy_label);
            item_size=itemView.findViewById(R.id.item_size);
            vip_back = itemView.findViewById(R.id.vip_back);

        }
    }

    public  interface  setOnClickListener{
        void setOnClickListener();

    }


}
