package com.yingzhiyun.yingquxue.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yingzhiyun.yingquxue.OkBean.AliveListBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.activity.zhibo.AliveintroduceActivity;
import com.yingzhiyun.yingquxue.base.adapter.BaseAdapter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class AliveListAdapter extends BaseAdapter<ZiyuanBean.ResultBean> {

    private final Context context;
    private CountDownTimer timer;

    public AliveListAdapter(List<ZiyuanBean.ResultBean> dataList, Context context) {
        super(dataList);
        this.context=context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_alivelist;
    }

    @Override
    public void addAll(List<ZiyuanBean.ResultBean> list, int page) {

    }
    public void cleartime(){
        if(timer!=null){
            timer.cancel();
        }

    }
    @Override
    public void createHolder(ViewHolder holder, ZiyuanBean.ResultBean s, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("====", "onClick: ."+s.getId());
                context.startActivity(new Intent(context, AliveintroduceActivity.class).putExtra("id",s.getId()));
            }
        });
        holder.setText(R.id.subject_type,s.getSubject());
        holder.setText(R.id.course_title,s.getTitle());
        holder.setText(R.id.course_time,s.getEffective());


        if(s.getPrice()==0){
            holder.setText(R.id.renshu,"已报名" + s.getSignUpNumber()+"人");
        }else {
            holder.setText(R.id.renshu,"报名满"+s.getCountSignUp()+"人开课   已报名"+s.getSignUpNumber()+"人");
        }

            holder.setText(R.id.price,"￥"+s.getPrice());


        ImageView courseTeacherhead = holder.itemView.findViewById(R.id.course_teacherhead);
        TextView teacher_name = holder.itemView.findViewById(R.id.teacher_name);
        holder.setText(R.id.teacher_name,s.getTeacherName());
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context)
                .load(s.getTeacherHead())
                .apply(requestOptions)
                .into(courseTeacherhead);

        holder.setText(R.id.time,formatTime(s.getTime() * 1000L));

        timer = new CountDownTimer(s.getTime() * 1000L, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                String secondToDayHourMinutes = formatDuring( millisUntilFinished);


                holder.setText(R.id.time,secondToDayHourMinutes);
            }

            @Override
            public void onFinish() {

                timer.cancel();
            }
        };
        timer.start();

    }
    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;

        return days + "天" + hours + "时" + minutes + "分 "
                + seconds + " 秒 ";
    }

    /*
     * 毫秒转化
     */
    public static String formatTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

        return strDay + " 分钟 " + strHour + " 秒";
    }

}
