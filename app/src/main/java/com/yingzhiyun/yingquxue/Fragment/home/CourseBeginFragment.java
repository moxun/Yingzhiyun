package com.yingzhiyun.yingquxue.Fragment.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.yingzhiyun.yingquxue.Mvp.CourseMvp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ClassBeaginBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.OkBean.CourseinfoBean;
import com.yingzhiyun.yingquxue.OkBean.MineCourseBean;
import com.yingzhiyun.yingquxue.OkBean.MineTeacherBean;
import com.yingzhiyun.yingquxue.OkBean.PlayVideoBean;
import com.yingzhiyun.yingquxue.OkBean.TeacherinfoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.activity.homepagr.course.CourseBeaginActivity;
import com.yingzhiyun.yingquxue.activity.homepagr.course.TeacherinfoActivity;
import com.yingzhiyun.yingquxue.adapter.CourseBeginAdapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.CoursePresenter;
import com.yingzhiyun.yingquxue.units.ToastUtil;
import com.yingzhiyun.yingquxue.units.UrlImageUnnits;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.OnClick;

public class CourseBeginFragment extends BaseFragment<CourseMvp.Course_View, CoursePresenter<CourseMvp.Course_View>> implements CourseMvp.Course_View {
    @BindView(R.id.fragment_recy)
    RecyclerView fragmentRecy;
    @BindView(R.id.course_teacherhead)
    ImageView courseTeacherhead;
    @BindView(R.id.teacher_name)
    TextView teacherName;
    @BindView(R.id.teacherLabel)
    TextView teacherLabel;
    @BindView(R.id.teacher_info)
    ImageView teacherInfo;
    @BindView(R.id.teacher_in)
    TextView teacherIn;
    @BindView(R.id.re_teacher)
    RelativeLayout reTeacher;
    private int type;
    private ClassBeaginBean  bean;
    private CourseBeginAdapter courseBeginAdapter;

    public CourseBeginFragment(int i, ClassBeaginBean classBeaginBean) {
        super();
        this.type=i;
        this.bean=classBeaginBean;
    }

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

    }

    @Override
    public void setmyCourse(MineCourseBean courseBean) {

    }

    @Override
    public void setmyFollowTeacher(MineTeacherBean mineTeacherBean) {

    }

    @Override
    public void setPlayVideo(PlayVideoBean playVideo) {

    }

    @Override
    protected CoursePresenter<CourseMvp.Course_View> createPresenter() {
        return new CoursePresenter<>();
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_begin;
    }

    @Override
    protected void initData() throws JSONException {
        if(type==0){
            teacherIn.setVisibility(View.GONE);
            reTeacher.setVisibility(View.GONE);
            fragmentRecy.setVisibility(View.VISIBLE);
        }else{
            teacherIn.setVisibility(View.VISIBLE);
            reTeacher.setVisibility(View.VISIBLE);
            fragmentRecy.setVisibility(View.GONE);
        }
        courseBeginAdapter = new CourseBeginAdapter(bean.getResult().getCourseOutline(), context);
        fragmentRecy.setLayoutManager(new LinearLayoutManager(context));
        fragmentRecy.setAdapter(courseBeginAdapter);
        courseBeginAdapter.OnsetOnClickListener(new CourseBeginAdapter.setOnClickListener() {
            @Override
            public void setOnClickListener(ClassBeaginBean.ResultBean.CourseOutlineBean musicBean, int position) {
//                根据左侧一级分类选中情况，更新背景色
                if(musicBean.getLiveStatus().equals("5")){
//                if(musicBean.getVideoPath()!=null){
                    Log.d("--------------", "setOnClickListener: "+musicBean.getVideoPath());
                    courseBeginAdapter.setSelectedPosition(position);
                    courseBeginAdapter.notifyDataSetChanged();
                    CourseBeaginActivity.instance.updateVideo(position,musicBean.getVideoPath());
                }else{
                    ToastUtil.makeShortText(getContext(),"课程未开始");
                }

            }
        });
        teacherName.setText(bean.getResult().getCourseDetail().getTeacherName());
        teacherLabel.setText(bean.getResult().getCourseDetail().getTeacherLabel());
        StringBuilder stringBuilder = new StringBuilder();

        if(bean.getResult().getCourseDetail().getCourseBriefing()!=null&&bean.getResult().getCourseDetail().getCourseBriefing().size()>0){
            for (int i = 0; i < bean.getResult().getCourseDetail().getCourseBriefing().size(); i++) {
                if (bean.getResult().getCourseDetail().getCourseBriefing().get(i).getContentType().equals("title")) {
                    stringBuilder.append("<font color='#000000'><strong>");
                    stringBuilder.append(bean.getResult().getCourseDetail().getCourseBriefing().get(i).getContent());
                    stringBuilder.append("</strong></font>" + "<br><br>");
                } else if(bean.getResult().getCourseDetail().getCourseBriefing().get(i).getContentType().equals("text")){
                    stringBuilder.append("<font color='#666666'>");
                    stringBuilder.append(bean.getResult().getCourseDetail().getCourseBriefing().get(i).getContent());
                    stringBuilder.append("</font>" + "<br><br>");
                }else{
                    stringBuilder.append("<img src =\"");
                    stringBuilder.append(bean.getResult().getCourseDetail().getCourseBriefing().get(i).getContent()+"\"");
                    stringBuilder.append("/>" + "<br><br>");
                }
            }

            teacherIn.setText(Html.fromHtml(stringBuilder.toString(), new UrlImageUnnits(teacherIn, context.getApplicationContext()), null));
        }else {
            teacherIn.setText("暂无数据");
        }
        if(bean.getResult().getCourseDetail().getTeacherName().equals("")){
            reTeacher.setVisibility(View.GONE);
        }

        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(this).load(bean.getResult().getCourseDetail().getTeacherHead()).apply(requestOptions).into(courseTeacherhead);
    }

    @OnClick(R.id.re_teacher)
    public void onViewClicked() {
        Bundle bundle = new Bundle();
        bundle.putInt("id",bean.getResult().getCourseDetail().getTeacherId());
        bundle.putString("type","course");
        startActivity(TeacherinfoActivity.class,bundle);
    }
}
