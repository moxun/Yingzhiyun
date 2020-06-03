package com.yingzhiyun.yingquxue.Mvp;

import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.ClassBeaginBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.OkBean.CourseinfoBean;
import com.yingzhiyun.yingquxue.OkBean.MineCourseBean;
import com.yingzhiyun.yingquxue.OkBean.MineTeacherBean;
import com.yingzhiyun.yingquxue.OkBean.PlayVideoBean;
import com.yingzhiyun.yingquxue.OkBean.SubjectBean;
import com.yingzhiyun.yingquxue.OkBean.TeacherinfoBean;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.base.modle.HttpFinishCallback;
import com.yingzhiyun.yingquxue.base.view.BaseView;

public interface CourseMvp {

    interface Course_View extends BaseView {

        void setCourseList(CourseBean courseList);
        void setAllSubject(AllsubjectBean allSubject);
        void setcourseInfo(CourseinfoBean courseinfoBean);
        void setcourseSignUp(CollectionTiBean collectionTiBean);
        void setTeacherinfo(TeacherinfoBean teacherinfo);
        void setfollowTeacher(CollectionTiBean collectionTiBean);
        void setclassBegins(ClassBeaginBean classBeaginBean);
        void setmyCourse(MineCourseBean courseBean);
        void setmyFollowTeacher(MineTeacherBean mineTeacherBean);
        void setPlayVideo(PlayVideoBean playVideo);
    }

    interface Course_CallBack extends HttpFinishCallback {
        void showCourseList(CourseBean courseList);
        void showAllSubject(AllsubjectBean allsubjectBean);
        void showcourseInfo(CourseinfoBean courseinfoBean);
        void showcourseSignUp(CollectionTiBean collectionTiBean);
        void showTeacherinfo(TeacherinfoBean teacherinfo);
        void showfollowTeacher(CollectionTiBean collectionTiBean);
        void showclassBegins(ClassBeaginBean classBeaginBean);
        void showmyCourse(MineCourseBean courseBean);
        void showmyFollowTeacher(MineTeacherBean mineTeacherBean);
        void showPlayVideo(PlayVideoBean playVideo);
    }

    interface Course_Modle {
        void getCourseList(Course_CallBack course_callBack, String json);
        void getAllSubject(Course_CallBack course_callBack, String json);
        void getcourseInfo(Course_CallBack course_callBack, String json);
        void getcourseSignUp(Course_CallBack course_callBack,String json);
        void getTeacherinfo(Course_CallBack course_callBack,String json);
        void getfollowTeacher(Course_CallBack course_callBack,String json);
        void getclassBegins(Course_CallBack course_callBack,String json);
        void getmyCourse(Course_CallBack course_callBack,String json);
        void getmyFollowTeacher(Course_CallBack course_callBack,String json);
        void getPlayVideo(Course_CallBack course_callBack,String json);
    }
}
