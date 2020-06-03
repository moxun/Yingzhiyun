package com.yingzhiyun.yingquxue.adapter.homepager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.ModuleBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.MainActivity;
import com.yingzhiyun.yingquxue.activity.QueryscoreActivity;
import com.yingzhiyun.yingquxue.activity.examination.ExaminationActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.CourseActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.EntranceActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.JiaocaiActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.MustTestActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.PracticetestActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.QuestionActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.QuestionListActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.TeachingActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.tiku.TestPagperinfoActivity;
import com.yingzhiyun.yingquxue.activity.zhibo.TEacherAliveActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.util.List;

public class HomeTypeAdapter extends BaseAdapter<HomePagerBean.ResultBean.MenuBean> {

    private final Context context;

    public HomeTypeAdapter(List<HomePagerBean.ResultBean.MenuBean> dataList, Context context) {
        super(dataList);
        this.context =context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_module;
    }

    @Override
    public void addAll(List<HomePagerBean.ResultBean.MenuBean> list, int page) {

    }

    @Override
    public void createHolder(BaseAdapter.ViewHolder holder, HomePagerBean.ResultBean.MenuBean moduleBean, int position) {
        holder.setPic(R.id.ite_module_src,moduleBean.getImg_url());
        holder.setText(R.id.ite_module_title,moduleBean.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SharedPreferenceUtils.getisLogin()){
                    Log.d("0000000000", "onClick: "+moduleBean.getType());
                    if(moduleBean.getType().equals("courseModel")){
                        Intent intent = new Intent(context, CourseActivity.class).putExtra("isVip",false);

                        context.startActivity(intent);
                    }else if(moduleBean.getType().equals("folderList")) {
                        Intent intent = new Intent(context, EntranceActivity.class);
                        intent.putExtra("bean",moduleBean);
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
                        intent.putExtra("bean",moduleBean);
                        context.startActivity(intent);
                    }else  if(moduleBean.getType().equals("massiveItemBank")){
                        Intent intent = new Intent(context, QuestionActivity.class);
                        intent.putExtra("bean",moduleBean);
                        context.startActivity(intent);
                    }else if(moduleBean.getType().equals("onlineTest")){
                        Intent intent = new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("type",2);

                        context.startActivity(intent);
                    }else  if(moduleBean.getType().equals("doIt")){
                        context.startActivity(new Intent(context, TestPagperinfoActivity.class).putExtra("id", moduleBean.getId()).putExtra("juantype","testPaperCheck"));
                    }else if(moduleBean.getType().equals("mustDo")){
                        context.startActivity(new Intent(context,MustTestActivity.class).putExtra("id",moduleBean.getId()).putExtra("gradetype",0));
                    }else if(moduleBean.getType().equals("liveCourseModel")){
                        context.startActivity(new Intent(context, TEacherAliveActivity.class));
                    }



                }else{
                    Intent intent = new Intent(context, PwdLoginActivity.class);

                    context.startActivity(intent);
                }

            }
        });
    }

}
