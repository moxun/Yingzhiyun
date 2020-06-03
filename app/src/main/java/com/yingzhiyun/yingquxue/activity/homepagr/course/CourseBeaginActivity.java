package com.yingzhiyun.yingquxue.activity.homepagr.course;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Fragment.home.CourseBeginFragment;
import com.yingzhiyun.yingquxue.Fragment.home.TeacherInfoFragment;
import com.yingzhiyun.yingquxue.Mvp.CourseMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.BaomingJson;
import com.yingzhiyun.yingquxue.OkBean.ClassBeaginBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.OkBean.CourseinfoBean;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.PlayVideoJson;
import com.yingzhiyun.yingquxue.OkBean.MineCourseBean;
import com.yingzhiyun.yingquxue.OkBean.MineTeacherBean;
import com.yingzhiyun.yingquxue.OkBean.PlayVideoBean;
import com.yingzhiyun.yingquxue.OkBean.TeacherinfoBean;
import com.yingzhiyun.yingquxue.OkBean.TeacherinfoJson;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.tiku.ZuTiActivity;
import com.yingzhiyun.yingquxue.adapter.FrgmentAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.presenter.CoursePresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class CourseBeaginActivity extends BaseActicity<CourseMvp.Course_View, CoursePresenter<CourseMvp.Course_View>> implements CourseMvp.Course_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.tool_title)
    TextView toolTitle;
    @BindView(R.id.tool_relative)
    RelativeLayout toolRelative;

    @BindView(R.id.subject_type)
    TextView subjectType;
    @BindView(R.id.course_title)
    TextView courseTitle;
    @BindView(R.id.course_time)
    TextView courseTime;
    @BindView(R.id.score_tab)
    TabLayout tabWeChat;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.view)
    ViewPager viewpager;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    private int id;
    private ArrayList<String> strings;
    private ArrayList<Fragment> fragments;
    private FrgmentAdapter bookFrgmentAdapter;
    private SensorManager sensorManager;
    private JZVideoPlayer.JZAutoFullscreenListener jzAutoFullscreenListener;
    public static CourseBeaginActivity instance = null;
    private ClassBeaginBean bean;
    private JZVideoPlayerStandard mJC;
    private int geshu;

    @Override
    public void setCourseList(CourseBean courseList) {

    }

    @Override
    public void setAllSubject(AllsubjectBean allSubject) {

    }

    @Override
    public void setcourseInfo(CourseinfoBean courseinfoBean) {

    }

    @Override
    public void setcourseSignUp(CollectionTiBean collectionTiBean) {

    }

    @Override
    public void setTeacherinfo(TeacherinfoBean teacherinfo) {

    }

    @Override
    public void setfollowTeacher(CollectionTiBean collectionTiBean) {

    }

    @Override
    public void setclassBegins(ClassBeaginBean classBeaginBean) {
        if(classBeaginBean.getStatus()!=200){
            return;
        }
        bean =classBeaginBean;
        fragments.add(new CourseBeginFragment(0, classBeaginBean));
        fragments.add(new CourseBeginFragment(1, classBeaginBean));
        bookFrgmentAdapter.notifyDataSetChanged();
        toolTitle.setText(classBeaginBean.getResult().getCourseTitle());
        subjectType.setText(classBeaginBean.getResult().getSubject());
        courseTitle.setText(classBeaginBean.getResult().getCourseTitle());
        courseTime.setText(classBeaginBean.getResult().getEffective());

        if(classBeaginBean.getResult().getCourseOutline().size()>0){
       mPresentser.getPlayVideo(new Gson().toJson(new PlayVideoJson(SharedPreferenceUtils.getUserid(),SharedPreferenceUtils.getToken(),bean.getResult().getCourseOutline().get(0).getId())));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mJC.thumbImageView != null
                            ) {
                                Glide.with(getApplicationContext()).load(classBeaginBean.getResult().getCourseImg()).into(mJC.thumbImageView);
                            }
                        }
                    });
                }
            }).start();
        }

    }

    @Override
    public void setmyCourse(MineCourseBean courseBean) {

    }

    @Override
    public void setmyFollowTeacher(MineTeacherBean mineTeacherBean) {

    }

    @Override
    public void setPlayVideo(PlayVideoBean playVideo) {
        if(playVideo.getStatus()==200){
            if(playVideo.getResult().getVideoUrl()!=null){
                if(geshu==0){

                    mJC.setUp(playVideo.getResult().getVideoUrl(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
                }else {
                    mJC.release();
                    mJC.setUp(playVideo.getResult().getVideoUrl(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
                }

            }
        }
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(jzAutoFullscreenListener);
        JZVideoPlayer.releaseAllVideos();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //播放器重力感应
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(jzAutoFullscreenListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected CoursePresenter<CourseMvp.Course_View> createPresenter() {
        return new CoursePresenter<>();
    }

    @Override
    protected void initData() throws ParseException {
        instance=this;
        strings = new ArrayList<>();
        id = getIntent().getExtras().getInt("id");
        Log.d("moxun", "initData: "+new Gson().toJson(new BaomingJson(SharedPreferenceUtils.getUserid(), id,SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
        mPresentser.getclassBegins(new Gson().toJson(new BaomingJson(SharedPreferenceUtils.getUserid(), id,SharedPreferenceUtils.getToken(), MyApp.version,"Android")));
        strings.add("目录");
        strings.add("详情");

        //初始化
        mJC = (JZVideoPlayerStandard) findViewById(R.id.mJC);
        fragments = new ArrayList<>();

        for (int i = 0; i < strings.size(); i++) {
            tabWeChat.addTab(tabWeChat.newTab().setText(strings.get(i)));

        }
        tabWeChat.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabWeChat.setInlineLabel(true);


        tabWeChat.setupWithViewPager(viewpager);


        bookFrgmentAdapter = new FrgmentAdapter(getSupportFragmentManager(), fragments, strings);
        viewpager.setAdapter(bookFrgmentAdapter);


        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabWeChat));
        //用于实现重力感应下切换横竖屏
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        jzAutoFullscreenListener = new JZVideoPlayer.JZAutoFullscreenListener();
        //设置图片为全屏
        mJC.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //内置视频的高度，可以去除黑边
        JZVideoPlayer.setVideoImageDisplayType(JZVideoPlayer.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);
        //播放视频
        JZVideoPlayer.TOOL_BAR_EXIST = true;
        mJC.thumbImageView.setImageResource(R.drawable.bannertest);//先设置一张默认图片

        mJC.widthRatio = 16;
        mJC.heightRatio = 9;


        //视频的缩略图地址
        //Glide.with(getApplicationContext()).load("http://p0.so.qhmsg.com/bdr/_240_/t01c10808f98a39bd4f.jpg").into(mJC.thumbImageView);

        //设置全屏播放
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;  //纵向
    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_classbegin;
    }

    @Override
    public int choseeClor() {
        return R.color.white;
    }

    public void  updateVideo(int position,String videopath){
        geshu = position;
       mPresentser.getPlayVideo(new Gson().toJson(new PlayVideoJson(SharedPreferenceUtils.getUserid(),SharedPreferenceUtils.getToken(),bean.getResult().getCourseOutline().get(position).getId())));
//        if(videopath!=null){
//            Log.d("-----------", "updateVideo: ");
//            mJC.release();
//            mJC.setUp(videopath, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
//        }


    }

    @OnClick(R.id.finish)
    public void onViewClicked() {
        finish();
    }
}
