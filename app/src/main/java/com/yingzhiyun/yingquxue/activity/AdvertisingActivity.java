package com.yingzhiyun.yingquxue.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yingzhiyun.yingquxue.OkBean.AdvertisingBean;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.BashiinfoActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.CourseActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.EntranceActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.ForecastTestActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.JiaocaiActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.MoreSelectionActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.MustTestActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.PracticetestActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.QuestionListActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.TeachingActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseInfoActivity;
import com.yingzhiyun.yingquxue.activity.mine.MoreTeacherActivity;
import com.yingzhiyun.yingquxue.activity.tiku.TestPagperinfoActivity;
import com.yingzhiyun.yingquxue.activity.zhibo.TEacherAliveActivity;
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

public class AdvertisingActivity extends SimpleActivity {
    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    @BindView(R.id.text_daojishi)
    TextView textDaojishi;
    private TimeCount time;//倒计时
    private int durationTime = 5 * 1000;
    private String type;
    private int id;
    private String gradeType;
    private String title;
    private boolean collection;

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() throws ParseException {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AdvertisingBean.ResultBean bean = (AdvertisingBean.ResultBean) getIntent().getSerializableExtra("bean");
        Glide.with(this).load(bean.getUrl()).into(ivSplash);
        type = bean.getType();
        id = bean.getId();
        gradeType = bean.getGradeType();
        title = bean.getTitle();
        collection = bean.isCollection();
        time = new TimeCount(durationTime, 1000);
        time.start();
        textDaojishi.setText(5 + "  跳过");
    }

    @Override
    public int createLayoutID() {
        return R.layout.advertising;
    }

    @Override
    public int choseeClor() {
        return R.color.colorRed2;
    }

    /**
     * @param activity
     * @param
     */
    public static void setNavigationBar(Activity activity, int visible) {
        View decorView = activity.getWindow().getDecorView();
        //显示NavigationBar
        if (View.GONE == visible) {
            int option = SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(option);
        }
    }



    @OnClick({R.id.iv_splash, R.id.text_daojishi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_splash:
                if(type==null){
                    return;
                }
                if(type .equals("list")){
                    Intent intent = new Intent(this, JiaocaiActivity.class);
                    intent.putExtra("bean", new HomePagerBean.ResultBean.MenuBean( "",id,"",type ));
                    startActivity(intent);
                }else if(type.equals("course")){
                    startActivity(new Intent(this, CourseInfoActivity.class).putExtra("id",id));
                }else if(type.equals("courseList")){
                    Intent intent = new Intent(this, MoreTeacherActivity.class);
                    intent.putExtra("bean", 26);
                    intent.putExtra("name", "名师辅导");
                }else if(type.equals("collectionList")){
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id);
                    startActivity(MoreSelectionActivity.class, bundle);
                }else if(type.equals("courseModel")){
                    Intent intent = new Intent(this, CourseActivity.class).putExtra("isVip",false);

                    this.startActivity(intent);
                }else if(type.equals("vocationalTraining")){
                    Intent intent = new Intent(this, JiaocaiActivity.class);
                    intent.putExtra("bean",new HomePagerBean.ResultBean.MenuBean("",17,"职业培训","vocationalTraining"));
                    this.startActivity(intent);
                }else if(type.equals("folderList")) {
                    Intent intent = new Intent(this, EntranceActivity.class);
                    intent.putExtra("bean",new HomePagerBean.ResultBean.MenuBean("",id,"",type));
                    this.startActivity(intent);
                }else if(type.equals("marking")){
                    Intent intent = new Intent(this, QueryscoreActivity.class);

                    this.startActivity(intent);
                }else  if(type.equals("scoreQuery")){
                    Intent intent = new Intent(this, QueryscoreActivity.class);

                    this.startActivity(intent);
                }else  if(type.equals("img")){

                }else  if(type.equals("bookList") ){
                    Intent intent = new Intent(this, TeachingActivity.class);

                    startActivity(intent);
                }else if(type.equals("onlineTest")){
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("type",2);

                   startActivity(intent);
                }else  if(type.equals("doIt")){
                    startActivity(new Intent(this, TestPagperinfoActivity.class).putExtra("id", id).putExtra("juantype","testPaperCheck"));
                }else if(type.equals("mustDo")){
                    startActivity(new Intent(this,MustTestActivity.class).putExtra("id",id).putExtra("gradetype",gradeType));
                }else if(type.equals("bet")){
                    startActivity(new Intent(this, ForecastTestActivity.class).putExtra("gradetype",gradeType));
                }else if(type.equals("folderList-noOpt")) {
                    Intent intent = new Intent(this, EntranceActivity.class);
                    HomePagerBean.ResultBean.MenuBean menuBean = new HomePagerBean.ResultBean.MenuBean("",id, "",type);
                    intent.putExtra("bean",menuBean);
                   startActivity(intent);
                }else if(type.equals("folderList-province")) {
                    Intent intent = new Intent(this, QuestionListActivity.class);
                    intent.putExtra("bean",new HomePagerBean.ResultBean.MenuBean("",id,"",type));
                    startActivity(intent);
                }else if(type.equals("liveCourseModel")){
                   startActivity(new Intent(this, TEacherAliveActivity.class));
                }else if(type.equals("folder")){
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id);
                    bundle.putString("title",title);
                    bundle.putBoolean("is",collection);
                    startActivity(BashiinfoActivity.class, bundle);
                }



                break;
            case R.id.text_daojishi:
                time.cancel();
                finish();

                startActivity(MainActivity.class);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        time.cancel();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(MainActivity.class);
    }

    //    @OnClick(R.id.text_daojishi)
//    public void onViewClicked() {
//        time.cancel();
//        finish();
//
//        startActivity(MainActivity.class);
//    }
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            finish();
            startActivity(MainActivity.class);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            if (textDaojishi != null) {
                textDaojishi.setText(millisUntilFinished / 1000 + "  跳过");
            }
        }
    }

}
