package com.yingzhiyun.yingquxue.modle;

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
import com.yingzhiyun.yingquxue.adapter.P;
import com.yingzhiyun.yingquxue.httpUnits.BaseObserver;
import com.yingzhiyun.yingquxue.httpUnits.FristServer;
import com.yingzhiyun.yingquxue.httpUnits.HttpManager;
import com.yingzhiyun.yingquxue.httpUnits.RxUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CourseModle implements CourseMvp.Course_Modle {
    FristServer fristServer= HttpManager.getInstance().getServer(FristServer.URL, FristServer.class);
    @Override
    public void getCourseList(CourseMvp.Course_CallBack course_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.courseList(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CourseBean>(course_callBack) {
                    @Override
                    public void onNext(CourseBean value) {
                        course_callBack.showCourseList(value);
                    }
                });
    }

    @Override
    public void getAllSubject(CourseMvp.Course_CallBack course_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.getAllSubject(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<AllsubjectBean>(course_callBack) {
                    @Override
                    public void onNext(AllsubjectBean value) {
                        course_callBack.showAllSubject(value);
                    }
                });
    }

    @Override
    public void getcourseInfo(CourseMvp.Course_CallBack course_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.courseInfo(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CourseinfoBean>(course_callBack) {
                    @Override
                    public void onNext(CourseinfoBean value) {
                        course_callBack.showcourseInfo(value);
                    }
                });
    }

    @Override
    public void getcourseSignUp(CourseMvp.Course_CallBack course_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.courseSignUp(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectionTiBean>(course_callBack) {
                    @Override
                    public void onNext(CollectionTiBean value) {
                        course_callBack.showcourseSignUp(value);
                    }
                });
    }

    @Override
    public void getTeacherinfo(CourseMvp.Course_CallBack course_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.teacherInfo(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<TeacherinfoBean>(course_callBack) {
                    @Override
                    public void onNext(TeacherinfoBean value) {
                        course_callBack.showTeacherinfo(value);
                    }
                });
    }

    @Override
    public void getfollowTeacher(CourseMvp.Course_CallBack course_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.followTeacher(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectionTiBean>(course_callBack) {
                    @Override
                    public void onNext(CollectionTiBean value) {
                        course_callBack.showfollowTeacher(value);
                    }
                });
    }

    @Override
    public void getclassBegins(CourseMvp.Course_CallBack course_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.classBegins(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ClassBeaginBean>(course_callBack) {
                    @Override
                    public void onNext(ClassBeaginBean value) {
                        course_callBack.showclassBegins(value);
                    }
                });
    }

    @Override
    public void getmyCourse(CourseMvp.Course_CallBack course_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.myCourse(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MineCourseBean>(course_callBack) {
                    @Override
                    public void onNext(MineCourseBean value) {
                        course_callBack.showmyCourse(value);
                    }
                });
    }

    @Override
    public void getmyFollowTeacher(CourseMvp.Course_CallBack course_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.myFollowTeacher(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MineTeacherBean>(course_callBack) {
                    @Override
                    public void onNext(MineTeacherBean value) {
                        course_callBack.showmyFollowTeacher(value);
                    }
                });
    }

    @Override
    public void getPlayVideo(CourseMvp.Course_CallBack course_callBack, String json) {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json,charset-UTF-8"),json);
        fristServer.playerCourseVideo(requestBody).compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<PlayVideoBean>(course_callBack) {
                    @Override
                    public void onNext(PlayVideoBean value) {
                        course_callBack.showPlayVideo(value);
                    }
                });
    }
}
