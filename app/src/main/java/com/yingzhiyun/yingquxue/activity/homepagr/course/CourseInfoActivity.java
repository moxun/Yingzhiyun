package com.yingzhiyun.yingquxue.activity.homepagr.course;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.Mvp.CourseMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.BaomingJson;
import com.yingzhiyun.yingquxue.OkBean.ClassBeaginBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.OkBean.CourseinfoBean;
import com.yingzhiyun.yingquxue.OkBean.CourseinfoJson;
import com.yingzhiyun.yingquxue.OkBean.JsonBean.PlayVideoJson;
import com.yingzhiyun.yingquxue.OkBean.MineCourseBean;
import com.yingzhiyun.yingquxue.OkBean.MineTeacherBean;
import com.yingzhiyun.yingquxue.OkBean.PlayVideoBean;
import com.yingzhiyun.yingquxue.OkBean.TeacherinfoBean;
import com.yingzhiyun.yingquxue.OkBean.VipHintBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.VideoPlayActivity;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.pay.FuKuanActivity;
import com.yingzhiyun.yingquxue.activity.vip.VipTopupActivity;
import com.yingzhiyun.yingquxue.adapter.CourseDaGangAdapter;
import com.yingzhiyun.yingquxue.base.activity.BaseActicity;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils;
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack;
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback;
import com.yingzhiyun.yingquxue.presenter.CoursePresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.yingzhiyun.yingquxue.units.UrlImageUnnits;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;

public class CourseInfoActivity extends BaseActicity<CourseMvp.Course_View, CoursePresenter<CourseMvp.Course_View>> implements CourseMvp.Course_View {
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.subject_type)
    TextView subjectType;
    @BindView(R.id.course_title)
    TextView courseTitle;
    @BindView(R.id.course_time)
    TextView courseTime;
    @BindView(R.id.renshu)
    TextView renshu;
    @BindView(R.id.shouke)
    TextView shouke;
    @BindView(R.id.course_teacherhead)
    ImageView courseTeacherhead;
    @BindView(R.id.teacher_name)
    TextView teacherName;
    @BindView(R.id.teacher_info)
    ImageView teacherInfo;
    @BindView(R.id.dagang)
    TextView dagang;
    @BindView(R.id.recy_dagang)
    RecyclerView recyDagang;
    @BindView(R.id.course_info)
    TextView courseInfo;
    @BindView(R.id.recy_info)
    TextView recyInfo;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.re_teacher)
    RelativeLayout reTeacher;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.teacherLabel)
    TextView teacherLabel;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.fen)
    View fen;
    @BindView(R.id.no_vip_look)
    TextView noVipLook;
    @BindView(R.id.vip_look)
    TextView vipLook;
    @BindView(R.id.vip_show)
    LinearLayout vipShow;
    private ArrayList<CourseinfoBean.ResultBean.OutlineBean> courlist;
    private ArrayList<CourseinfoBean.ResultBean.CourseBriefingBean> jianjieList;
    private CourseDaGangAdapter courseDaGangAdapter;
    private CourseinfoBean.ResultBean result;
    private int id;
    private double price1;

    @Override
    protected void initData() throws ParseException {
        id = getIntent().getIntExtra("id", 0);
        courlist = new ArrayList<>();
        jianjieList = new ArrayList<>();
        courseDaGangAdapter = new CourseDaGangAdapter(courlist, this, "info");
        recyDagang.setLayoutManager(new LinearLayoutManager(this));
        recyDagang.setAdapter(courseDaGangAdapter);
        recyDagang.setNestedScrollingEnabled(false);

        mPresentser.getcourseInfo(new Gson().toJson(new CourseinfoJson(SharedPreferenceUtils.getUserid(), id, SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnLogin.getText().equals("立即报名")) {
                    if (price1 == 0.0) {
                        mPresentser.getcourseSignUp(new Gson().toJson(new BaomingJson(SharedPreferenceUtils.getUserid(), id, SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", id);
                        bundle.putString("type", "course");
                        finish();
                        startActivity(FuKuanActivity.class, bundle);
                    }
                } else {
                    mPresentser.getclassBegins(new Gson().toJson(new BaomingJson(SharedPreferenceUtils.getUserid(), id, SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
                }
            }
        });
        courseDaGangAdapter.setOnItemListener(new CourseDaGangAdapter.OnItemListener() {
            @Override
            public void onClick(int pos, CourseinfoBean.ResultBean.OutlineBean s) {

                mPresentser.getPlayVideo(new Gson().toJson(new PlayVideoJson(SharedPreferenceUtils.getUserid(), SharedPreferenceUtils.getToken(), s.getId())));
            }
        });
    }


    @Override
    public int choseeClor() {
        return R.color.white;
    }


    @OnClick({R.id.finish, R.id.re_teacher,R.id.no_vip_look, R.id.vip_look, R.id.vip_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.re_teacher:
                if (result != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", result.getTeacherId());
                    bundle.putString("type", "course");
                    startActivity(TeacherinfoActivity.class, bundle);
                }

                break;

            case R.id.no_vip_look:
                if (btnLogin.getText().equals("立即报名")) {
                    if (price1 == 0.0) {
                        mPresentser.getcourseSignUp(new Gson().toJson(new BaomingJson(SharedPreferenceUtils.getUserid(), id, SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", id);
                        bundle.putString("type", "course");
                        finish();
                        startActivity(FuKuanActivity.class, bundle);
                    }
                } else {
                    mPresentser.getclassBegins(new Gson().toJson(new BaomingJson(SharedPreferenceUtils.getUserid(), id, SharedPreferenceUtils.getToken(), MyApp.version, "Android")));
                }
                break;
            case R.id.vip_look:
                VipBegins(id);
                break;
            case R.id.vip_show:
                break;
        }
    }

    @Override
    public void setCourseList(CourseBean courseList) {

    }

    @Override
    public void setAllSubject(AllsubjectBean allSubject) {

    }

    @Override
    public int createLayoutID() {
        return R.layout.activity_courseinfo;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setcourseInfo(CourseinfoBean courseinfoBean) {

        if (courseinfoBean.getStatus() == 200) {
            result = courseinfoBean.getResult();
            subjectType.setText(result.getSubject());
            courseTitle.setText(result.getTitle());
            courseTime.setText(result.getEffective());
            price1 = result.getPrice();
            if(courseinfoBean.getResult().isVip()){
                vipShow.setVisibility(View.VISIBLE);
            }else {
                btnLogin.setVisibility(View.VISIBLE);
            }
            if (result.getPrice() == 0.0) {
                this.price.setText("免费");
            } else {
                this.price.setText("￥" + result.getPrice());
            }

            renshu.setText(result.getSignUpNumber() + "人报名");

            if (result.getTeacherName().equals("")) {
                shouke.setVisibility(View.GONE);
                reTeacher.setVisibility(View.GONE);
            } else {
                RequestOptions requestOptions = RequestOptions.circleCropTransform();
                Glide.with(this)
                        .load(result.getTeacherHead())
                        .apply(requestOptions)
                        .into(courseTeacherhead);
                teacherName.setText(result.getTeacherName());
                teacherLabel.setText(result.getTeacherLabel());
            }


            courlist.addAll(result.getOutline());
            courseDaGangAdapter.notifyDataSetChanged();
            StringBuilder stringBuilder = new StringBuilder();
            Glide.with(this).load(result.getCourseImg()).into(back);
            if (result.getCourseBriefing() != null) {
                for (int i = 0; i < result.getCourseBriefing().size(); i++) {
                    if (result.getCourseBriefing().get(i).getContentType().equals("title")) {
                        stringBuilder.append("<font color='#000000'><strong>");
                        stringBuilder.append(result.getCourseBriefing().get(i).getContent());
                        stringBuilder.append("</strong></font>" + "<br><br>");
                    } else if (result.getCourseBriefing().get(i).getContentType().equals("text")) {
                        stringBuilder.append("<font color='#666666'>");
                        stringBuilder.append(result.getCourseBriefing().get(i).getContent());
                        stringBuilder.append("</font>" + "<br><br>");
                    } else {

                        stringBuilder.append("<img src =\"");
                        stringBuilder.append(result.getCourseBriefing().get(i).getContent() + "\"");
                        stringBuilder.append("height=\"" + result.getCourseBriefing().get(i).getHeight() + "\"");
                        stringBuilder.append("width=\"" + result.getCourseBriefing().get(i).getWidth() + "\"");
                        stringBuilder.append("/>" + "<br><br>");
                    }
                }


            } else {
                courseInfo.setVisibility(View.GONE);
            }

            Log.d("moun", "setcourseInfo: " + stringBuilder.toString());
            recyInfo.setText(Html.fromHtml(stringBuilder.toString(), new UrlImageUnnits(recyInfo, getApplicationContext()), null));
            if (result.isIsSignUp()) {
                btnLogin.setText("开始上课");
                noVipLook.setText("开始上课");
            }

        } else if (courseinfoBean.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, "身份过期，请重新登录");
            startActivity(PwdLoginActivity.class);
        }
    }

    @Override
    public void setcourseSignUp(CollectionTiBean collectionTiBean) {
        if (collectionTiBean.getHint().equals("报名成功")) {
            btnLogin.setText("开始上课");
            noVipLook.setText("开始上课");
        }
        ToastUtil.makeShortText(this, collectionTiBean.getHint());
    }

    @Override
    public void setTeacherinfo(TeacherinfoBean teacherinfo) {

    }

    @Override
    public void setfollowTeacher(CollectionTiBean collectionTiBean) {

    }

    @Override
    public void setclassBegins(ClassBeaginBean classBeaginBean) {
        if (classBeaginBean.getStatus() == 511) {
            finish();
            ToastUtil.makeShortText(this, classBeaginBean.getHint());
            startActivity(PwdLoginActivity.class);
        } else if(classBeaginBean.getStatus() == 200){
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            startActivity(CourseBeaginActivity.class, bundle);
        }else {
            ToastUtil.makeShortText(this, classBeaginBean.getHint());
        }
    }

    @Override
    public void setmyCourse(MineCourseBean courseBean) {

    }

    @Override
    public void setmyFollowTeacher(MineTeacherBean mineTeacherBean) {

    }
    public void VipBegins(int id) {


        MediaType mediaType = MediaType.parse("application/json");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_user_id", SharedPreferenceUtils.getUserid());
            jsonObject.put("token", SharedPreferenceUtils.getToken());
            jsonObject.put("courseId", id+"");
            jsonObject.put("version", MyApp.version);
            jsonObject.put("device", "Android");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.postString()
                .url(FristServer.URL + "classBegins")
                .mediaType(mediaType)
                .content(jsonObject.toString())
                .build()
                .execute(new ResultModelCallback(this, new ResponseCallBack<ClassBeaginBean>() {
                    @Override
                    public void onError(String e) {
                        startActivity(VipTopupActivity.class);
                    }

                    @Override
                    public void onResponse(ClassBeaginBean response) throws JSONException {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", id);
                        startActivity(CourseBeaginActivity.class, bundle);

                    }
                }));

    }
    @Override
    public void setPlayVideo(PlayVideoBean playVideo) {
        if (playVideo.getStatus() == 200) {
            if (playVideo.getResult().getVideoUrl() != null) {
                Bundle bundle = new Bundle();
                bundle.putString("path", playVideo.getResult().getVideoUrl());
                startActivity(VideoPlayActivity.class, bundle);
            }
        }
    }

    @Override
    protected CoursePresenter<CourseMvp.Course_View> createPresenter() {
        return new CoursePresenter<>();
    }





}
