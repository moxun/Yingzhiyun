package com.yingzhiyun.yingquxue.Fragment.home;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.CourseMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ClassBeaginBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.OkBean.CourseinfoBean;
import com.yingzhiyun.yingquxue.OkBean.MineCourseBean;
import com.yingzhiyun.yingquxue.OkBean.MineTeacherBean;
import com.yingzhiyun.yingquxue.OkBean.PlayVideoBean;
import com.yingzhiyun.yingquxue.OkBean.TeacherinfoBean;
import com.yingzhiyun.yingquxue.OkBean.TeacherinfoJson;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.CourseListAdapter;
import com.yingzhiyun.yingquxue.adapter.TeacherJieAdapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment;
import com.yingzhiyun.yingquxue.presenter.CoursePresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.UrlImageUnnits;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;

public class TeacherInfoFragment  extends BaseFragment<CourseMvp.Course_View, CoursePresenter<CourseMvp.Course_View>> implements CourseMvp.Course_View {
    private final int anInt;
    @BindView(R.id.fragment_recy)
    PullLoadMoreRecyclerView fragmentRecy;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    @BindView(R.id.teacher_info)
    TextView teacherInfo;
    private int page;
    private CourseListAdapter courseListAdapter;
    private ArrayList<CourseBean.ResultBean> list;


    public TeacherInfoFragment(int i, int id) {
        super();
        this.page = i;
        anInt =id;
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initData() throws JSONException {
        list = new ArrayList<>();
        presenter.getTeacherinfo(new Gson().toJson(new TeacherinfoJson(SharedPreferenceUtils.getUserid(),anInt, MyApp.version,"Android",SharedPreferenceUtils.getToken())));
        courseListAdapter = new CourseListAdapter(list, context);
        fragmentRecy.setAdapter(courseListAdapter);
        fragmentRecy.setPullRefreshEnable(false);
        fragmentRecy.setPushRefreshEnable(false);
        fragmentRecy.setLinearLayout();
        if (page == 1) {
            fragmentRecy.setVisibility(View.VISIBLE);
            teacherInfo.setVisibility(View.GONE);
        } else {
            fragmentRecy.setVisibility(View.GONE);
            teacherInfo.setVisibility(View.VISIBLE);
        }
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
        if(teacherinfo.getStatus()==200){
            list.addAll(teacherinfo.getResult().getCourseList());
            courseListAdapter.notifyDataSetChanged();
            StringBuilder stringBuilder = new StringBuilder();
            if(teacherinfo.getResult().getBriefing()!=null){
                for (int i = 0; i < teacherinfo.getResult().getBriefing().size(); i++) {
                    if (teacherinfo.getResult().getBriefing().get(i).getContentType().equals("title")) {
                        stringBuilder.append("<font color='#000000' size='14'><strong>");
                        stringBuilder.append(teacherinfo.getResult().getBriefing().get(i).getContent());
                        stringBuilder.append("</strong></font>" + "<br><br>");
                    } else if(teacherinfo.getResult().getBriefing().get(i).getContentType().equals("text")){
                        stringBuilder.append("<font color='#666666'>");
                        stringBuilder.append(teacherinfo.getResult().getBriefing().get(i).getContent());
                        stringBuilder.append("</font>" + "<br><br>");
                    }else{
                        stringBuilder.append("<img src =\"");
                        stringBuilder.append(teacherinfo.getResult().getBriefing().get(i).getContent()+"\"");
                        stringBuilder.append("/>" + "<br><br>");
                    }
                }
                if(teacherInfo!=null&&Html.fromHtml(stringBuilder.toString(), new UrlImageUnnits(teacherInfo, context.getApplicationContext()), null)!=null){
                    teacherInfo.setText(Html.fromHtml(stringBuilder.toString(), new UrlImageUnnits(teacherInfo, context.getApplicationContext()), null));
                }

            }else {
                teacherInfo.setText("暂无数据");
            }


        }else{
            Log.d("moxun", "setTeacherinfo: "+teacherinfo.getStatus());
        }
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
}
