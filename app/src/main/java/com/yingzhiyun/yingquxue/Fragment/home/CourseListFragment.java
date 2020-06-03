package com.yingzhiyun.yingquxue.Fragment.home;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yingzhiyun.yingquxue.Mvp.CourseMvp;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ClassBeaginBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.OkBean.CourseListJson;
import com.yingzhiyun.yingquxue.OkBean.CourseinfoBean;
import com.yingzhiyun.yingquxue.OkBean.MineCourseBean;
import com.yingzhiyun.yingquxue.OkBean.MineTeacherBean;
import com.yingzhiyun.yingquxue.OkBean.PlayVideoBean;
import com.yingzhiyun.yingquxue.OkBean.TeacherinfoBean;
import com.yingzhiyun.yingquxue.R;
import com.yingzhiyun.yingquxue.adapter.CourseListAdapter;
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment;
import com.yingzhiyun.yingquxue.presenter.CoursePresenter;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.ToastUtil;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;

public class CourseListFragment extends BaseFragment<CourseMvp.Course_View, CoursePresenter<CourseMvp.Course_View>> implements CourseMvp.Course_View {
    private final boolean isVip;
    @BindView(R.id.fragment_recy)
    PullLoadMoreRecyclerView fragmentRecy;
    @BindView(R.id.linear_modle)
    LinearLayout linearModle;
    private int subjectId;
    private String gradeID;
    int page = 1;
    private ArrayList<CourseBean.ResultBean> list;
    private CourseListAdapter courseListAdapter;

    public CourseListFragment(int subjectId, String s,boolean isVop) {
        super();
        this.subjectId = subjectId;
        this.gradeID = s;
        isVip =isVop;
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initData() throws JSONException {
        list = new ArrayList<>();

        courseListAdapter = new CourseListAdapter(list, context);
        fragmentRecy.setLinearLayout();
        fragmentRecy.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page=1;
                getListCourse();
                fragmentRecy.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                page++;
                getListCourse();
                fragmentRecy.setPullLoadMoreCompleted();
            }
        });

        fragmentRecy.setAdapter(courseListAdapter);


        getListCourse();
    }

    private void getListCourse() {
        if(isVip){
            presenter.getCourseList(new Gson().toJson(new CourseListJson(subjectId, gradeID, SharedPreferenceUtils.getUserid(), page, MyApp.version, "Android", SharedPreferenceUtils.getToken(),1)));
        }else {
            presenter.getCourseList(new Gson().toJson(new CourseListJson(subjectId, gradeID, SharedPreferenceUtils.getUserid(), page, MyApp.version, "Android", SharedPreferenceUtils.getToken(),0)));
        }

    }

    @Override
    public void setCourseList(CourseBean courseList) {

            if(courseList.getStatus()==200){
                if(courseList.getResult().size()>0){
                    linearModle.setVisibility(View.GONE);
                    fragmentRecy.setVisibility(View.VISIBLE);
                    list.clear();
                    list.addAll(courseList.getResult());
                    courseListAdapter.notifyDataSetChanged();
                }else{
                    fragmentRecy.setPushRefreshEnable(false);
                    linearModle.setVisibility(View.VISIBLE);
                    fragmentRecy.setVisibility(View.GONE);
                }
            }


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
}
