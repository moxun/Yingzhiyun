package com.yingzhiyun.yingquxue.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.activity.QueryscoreActivity;
import com.yingzhiyun.yingquxue.activity.examination.ExaminationActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.BashiinfoActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.CourseActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.EntranceActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.ForecastTestActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.JiaocaiActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.MustTestActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.QuestionActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.QuestionListActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.TeachingActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.tiku.TestPagperinfoActivity;
import com.yingzhiyun.yingquxue.activity.zhibo.TEacherAliveActivity;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.youth.banner.Banner;

import java.util.List;

public class HomeJinpinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<HomePagerBean.ResultBean.RightBean.DetailBeanX> detailBeanXES;

    public HomeJinpinAdapter(Context context, List<HomePagerBean.ResultBean.RightBean.DetailBeanX> dataList) {
        this.context = context;
        detailBeanXES =dataList;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        Log.d("==========", "onCreateViewHolder: "+viewType);
        if (viewType == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_newcourse, null);
            Log.d("==============", "onCreateViewHolder: ");
            holder = new TwoViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_newhomelist, null);
            holder = new OneViewHolder(view);
        }
        return holder;
    }
    //根据条件返回条目的类型
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OneViewHolder){
            OneViewHolder oneViewHolder = (OneViewHolder) holder;//强转成子类
            Glide.with(context)
                    .load(detailBeanXES.get(position).getMini_img_url())
                    .apply(new RequestOptions().override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).placeholder(R.color.white).error(R.color.white).dontAnimate())
                    .into(oneViewHolder.iv_clinic_avatar);
        }else if(holder instanceof  TwoViewHolder){
            TwoViewHolder twoViewHolder = (TwoViewHolder) holder;//强转成子类
            twoViewHolder.title.setText(detailBeanXES.get(position).getTitle());
            twoViewHolder.renshu.setText(detailBeanXES.get(position).getSignUpNumber()+"人报名");
            if(detailBeanXES.get(position).getPrice()==0.0){
                twoViewHolder.price.setText("免费");
            }else{
                twoViewHolder.price.setText("￥"+detailBeanXES.get(position).getPrice());
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharedPreferenceUtils.getisLogin()){
                    HomePagerBean.ResultBean.RightBean.DetailBeanX moduleBean = detailBeanXES.get(position);
                    Log.d("-----------", "onClick: "+moduleBean.getType());
                    if(moduleBean.getType().equals("courseModel")){
                        Intent intent = new Intent(context, CourseActivity.class).putExtra("isVip",false);

                        context.startActivity(intent);
                    }else if(moduleBean.getType().equals("folderList")) {
                        Intent intent = new Intent(context, EntranceActivity.class);
                        HomePagerBean.ResultBean.MenuBean menuBean = new HomePagerBean.ResultBean.MenuBean(moduleBean.getImg_url(), moduleBean.getId(), moduleBean.getTitle(), moduleBean.getType());
                        intent.putExtra("bean",menuBean);
                        context.startActivity(intent);
                    } else if(moduleBean.getType().equals("list")||moduleBean.getType().equals("vocationalTraining")){
                        Intent intent = new Intent(context, JiaocaiActivity.class);
                        intent.putExtra("bean",moduleBean);
                        context.startActivity(intent);
                    }else if(moduleBean.getType().equals("marking")){

                        Intent intent = new Intent(context, ExaminationActivity.class);

                        context.startActivity(intent);
                    }else if(moduleBean.getType().equals("scoreQuery")){
                        Intent intent = new Intent(context, QueryscoreActivity.class);

                        context.startActivity(intent);
                    }else  if(moduleBean.getType().equals("bookList") ){
                        Intent intent = new Intent(context, TeachingActivity.class);

                        context.startActivity(intent);
                    }else  if(moduleBean.getType().equals("massiveItemBank")){
                        Intent intent = new Intent(context, QuestionActivity.class);
                        intent.putExtra("bean",new HomePagerBean.ResultBean.MenuBean(moduleBean.getImg_url(),moduleBean.getId(),moduleBean.getTitle(),moduleBean.getType()));
                        context.startActivity(intent);
                    }else if(moduleBean.getType().equals("onlineTest")){
                        Intent intent = new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("type",2);

                        context.startActivity(intent);
                    }else  if(moduleBean.getType().equals("doIt")){
                        context.startActivity(new Intent(context, TestPagperinfoActivity.class).putExtra("id", moduleBean.getId()).putExtra("juantype","testPaperCheck"));
                    }else if(moduleBean.getType().equals("mustDo")){
                        context.startActivity(new Intent(context,MustTestActivity.class).putExtra("id",moduleBean.getId()).putExtra("gradetype",moduleBean.getGradeType()));
                    }else  if(moduleBean.getType().equals("course")){
                        context.startActivity(new Intent(context, CourseInfoActivity.class).putExtra("id", moduleBean.getId()));
                    }else if(moduleBean.getType().equals("bet")){
                        context.startActivity(new Intent(context, ForecastTestActivity.class).putExtra("gradetype",moduleBean.getGradeType()));
                    }else if(moduleBean.getType().equals("folderList-noOpt")) {
                        Intent intent = new Intent(context, EntranceActivity.class);
                        HomePagerBean.ResultBean.MenuBean menuBean = new HomePagerBean.ResultBean.MenuBean(moduleBean.getImg_url(), moduleBean.getId(), moduleBean.getTitle(), moduleBean.getType());
                        intent.putExtra("bean",menuBean);
                        context.startActivity(intent);
                    }else if(moduleBean.getType().equals("folderList-province")) {
                        Intent intent = new Intent(context, QuestionListActivity.class);
                        HomePagerBean.ResultBean.MenuBean menuBean = new HomePagerBean.ResultBean.MenuBean(moduleBean.getImg_url(), moduleBean.getId(), moduleBean.getTitle(), moduleBean.getType());
                        intent.putExtra("bean",menuBean);
                        context.startActivity(intent);
                    }else if(moduleBean.getType().equals("liveCourseModel")){
                        context.startActivity(new Intent(context, TEacherAliveActivity.class));
                    }else if(moduleBean.getType().equals("folder")){
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", moduleBean.getId());
                        bundle.putString("title", moduleBean.getTitle());
                        bundle.putBoolean("is",moduleBean.isCollection());
                        context.startActivity(new Intent(context,BashiinfoActivity.class), bundle);
                    }



                }else{
                    Intent intent = new Intent(context, PwdLoginActivity.class);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return detailBeanXES.size();
    }
    public class OneViewHolder extends RecyclerView.ViewHolder {


        private final ImageView iv_clinic_avatar;

        public OneViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_clinic_avatar = itemView.findViewById(R.id.iv_clinic_avatar);
        }
    }

    public class TwoViewHolder extends RecyclerView.ViewHolder {


        private final TextView title;
        private final TextView renshu;
        private final TextView price;

        public TwoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            renshu = itemView.findViewById(R.id.renshu);
            price = itemView.findViewById(R.id.price);
        }
    }
}
