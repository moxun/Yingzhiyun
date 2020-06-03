package com.yingzhiyun.yingquxue.httpUnits;


import com.yingzhiyun.yingquxue.OkBean.AcoreQueryBean;
import com.yingzhiyun.yingquxue.OkBean.AllsubjectBean;
import com.yingzhiyun.yingquxue.OkBean.BalanceBean;
import com.yingzhiyun.yingquxue.OkBean.BaominginfoBean;
import com.yingzhiyun.yingquxue.OkBean.BashiinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BetDetailBean;
import com.yingzhiyun.yingquxue.OkBean.BetListBean;
import com.yingzhiyun.yingquxue.OkBean.BookinfoBean;
import com.yingzhiyun.yingquxue.OkBean.BooklistBean;
import com.yingzhiyun.yingquxue.OkBean.BrowsingBean;
import com.yingzhiyun.yingquxue.OkBean.ChapterListBean;
import com.yingzhiyun.yingquxue.OkBean.ClassBeaginBean;
import com.yingzhiyun.yingquxue.OkBean.CodeBean;
import com.yingzhiyun.yingquxue.OkBean.CollectBean;
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean;
import com.yingzhiyun.yingquxue.OkBean.CourseBean;
import com.yingzhiyun.yingquxue.OkBean.CoursePayBean;
import com.yingzhiyun.yingquxue.OkBean.CourseinfoBean;
import com.yingzhiyun.yingquxue.OkBean.CoursewareListBean;
import com.yingzhiyun.yingquxue.OkBean.ExamAnalysisBean;
import com.yingzhiyun.yingquxue.OkBean.ExaminationListBean;
import com.yingzhiyun.yingquxue.OkBean.ExamineBean;
import com.yingzhiyun.yingquxue.OkBean.FolderListOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.HomePagerBean;
import com.yingzhiyun.yingquxue.OkBean.HudongIfoBean;
import com.yingzhiyun.yingquxue.OkBean.InteractionsListBean;
import com.yingzhiyun.yingquxue.OkBean.KnowPointerBean;
import com.yingzhiyun.yingquxue.OkBean.KnowledgeBean;
import com.yingzhiyun.yingquxue.OkBean.LeftBean;
import com.yingzhiyun.yingquxue.OkBean.LoginSuccesBean;
import com.yingzhiyun.yingquxue.OkBean.MineCourseBean;
import com.yingzhiyun.yingquxue.OkBean.MineTeacherBean;
import com.yingzhiyun.yingquxue.OkBean.MyCollectBean;
import com.yingzhiyun.yingquxue.OkBean.MyExamBean;
import com.yingzhiyun.yingquxue.OkBean.MyInteractionListBean;
import com.yingzhiyun.yingquxue.OkBean.MyTiBean;
import com.yingzhiyun.yingquxue.OkBean.PlayVideoBean;
import com.yingzhiyun.yingquxue.OkBean.PracticeZuoBean;
import com.yingzhiyun.yingquxue.OkBean.RecordBean;
import com.yingzhiyun.yingquxue.OkBean.RegisterBean;
import com.yingzhiyun.yingquxue.OkBean.RightLeft;
import com.yingzhiyun.yingquxue.OkBean.SchoolBean;
import com.yingzhiyun.yingquxue.OkBean.SearchcontentBean;
import com.yingzhiyun.yingquxue.OkBean.SelectedOptionsBean;
import com.yingzhiyun.yingquxue.OkBean.SubjectBean;
import com.yingzhiyun.yingquxue.OkBean.SubjectInfoBean;
import com.yingzhiyun.yingquxue.OkBean.TeacherinfoBean;
import com.yingzhiyun.yingquxue.OkBean.TeachingShaixuanBean;
import com.yingzhiyun.yingquxue.OkBean.TestPagperInfo;
import com.yingzhiyun.yingquxue.OkBean.TestPaperBean;
import com.yingzhiyun.yingquxue.OkBean.TestPaperListBean;
import com.yingzhiyun.yingquxue.OkBean.UpdatePassBean;
import com.yingzhiyun.yingquxue.OkBean.UserinfoBean;
import com.yingzhiyun.yingquxue.OkBean.VersionBean;
import com.yingzhiyun.yingquxue.OkBean.VideoinfoBean;
import com.yingzhiyun.yingquxue.OkBean.WrongtitleBean;
import com.yingzhiyun.yingquxue.OkBean.WxPAyBean;
import com.yingzhiyun.yingquxue.OkBean.YatiBean;
import com.yingzhiyun.yingquxue.OkBean.YatijuanPayBean;
import com.yingzhiyun.yingquxue.OkBean.YitiPayinfo;
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean;
import com.yingzhiyun.yingquxue.OkBean.ZutijiluBean;
import com.yingzhiyun.yingquxue.OkBean.skillCourseListBeam;
import com.yingzhiyun.yingquxue.OkBean.skillTypeListBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2019/1/17.
 */

public interface FristServer {
    //s   47.104.161.192:65110
  String URL = "http://192.168.0.120/yzy/app/";
//    String URL = "http://192.168.0.120/yzy/app/";
    String TestUrl="http://192.168.0.120/yzy/app/";
    //   String URL = "http://192.168.0.120/yzy/app/";
// String PayURL = "https://testApp.ruiyunqu.com/yzy/pay/";
    String PayURL = "http://192.168.0.120/yzy/pay/";


    @POST("getIndexData")
    @Headers("Content-Type:application/json")
    Observable<HomePagerBean> getHomepager(@Body RequestBody requestBody);


    @GET("getSubjectList")
    Observable<SubjectBean> getItem(@Query("schoolType") String schoolType);

    @GET("getTeachingMaterialList")
    Observable<SubjectInfoBean> getSubjectInfo(@Query("subjectId") String subjectId);

    @POST("getSubjectListAll")
    @Headers("Content-Type:application/json")
    Observable<AllsubjectBean> getAllSubject(@Body RequestBody requestBody);

    @POST("getIndexListSelectedOptions")
    @Headers("Content-Type:application/json")
    Observable<SelectedOptionsBean> getSelectedOptions(@Body RequestBody requestBody);

    @POST("getListTypeDetail")
    @Headers("Content-Type:application/json")
    Observable<ZiyuanBean> getZiyuan(@Body RequestBody requestBody);


    @POST("matchesPhoneNum")
    @Headers("Content-Type:application/json")
    Observable<CodeBean> getCode(@Body RequestBody requestBody);

    @POST("userRegister")
    @Headers("Content-Type:application/json")
    Observable<RegisterBean> getRegister(@Body RequestBody requestBody);


    @POST("getCode4Login")
    @Headers("Content-Type:application/json")
    Observable<CodeBean> getCodeLogin(@Body RequestBody requestBody);

    @POST("codeLogin")
    @Headers("Content-Type:application/json")
    Observable<LoginSuccesBean> getLoginSucces(@Body RequestBody requestBody);


    @POST("getCode4ForgetPwd")
    @Headers("Content-Type:application/json")
    Observable<CodeBean> getCodeForget(@Body RequestBody requestBody);

    @POST("forgetThePassword")
    @Headers("Content-Type:application/json")
    Observable<UpdatePassBean> getUpdatePass(@Body RequestBody requestBody);


    @POST("pwdLogin")
    @Headers("Content-Type:application/json")
    Observable<LoginSuccesBean> getpwdlogin(@Body RequestBody requestBody);


    @POST("getKnowledgePointsAndNumber")
    @Headers("Content-Type:application/json")
    Observable<KnowledgeBean> getKnowledge(@Body RequestBody requestBody);

    @POST("itemBankCombination")
    @Headers("Content-Type:application/json")
    Observable<TestPagperInfo> getTestpaper(@Body RequestBody requestBody);


    @POST("getVideo4IndexList")
    @Headers("Content-Type:application/json")
    Observable<VideoinfoBean> getVideoinfo(@Body RequestBody requestBody);

    @POST("getBookList")
    @Headers("Content-Type:application/json")
    Observable<BooklistBean> getBookList(@Body RequestBody requestBody);

    @POST("getChapterList")
    @Headers("Content-Type:application/json")
    Observable<ChapterListBean> getChapterList(@Body RequestBody requestBody);

    @POST("coursewareCollection")
    @Headers("Content-Type:application/json")
    Observable<CollectBean> getCollect(@Body RequestBody requestBody);

    @POST("getUserCollectionList")
    @Headers("Content-Type:application/json")
    Observable<ZiyuanBean> getMyCollect(@Body RequestBody requestBody);


    /*
    搜索
     */
    @POST("indexQuery")
    @Headers("Content-Type:application/json")
    Observable<SearchcontentBean> getcontent(@Body RequestBody requestBody);


    /*
        获取试卷
    */
    @POST("getTestPaperList")
    @Headers("Content-Type:application/json")
    Observable<TestPaperListBean> getTestPaperList(@Body RequestBody requestBody);


    @POST("getTestPaper")
    @Headers("Content-Type:application/json")
    Observable<TestPagperInfo> getTestPaperinfo(@Body RequestBody requestBody);

    @POST()
    @Headers("Content-Type:application/json")
    Observable<ExamineBean> gettestPaperCheck(@Body RequestBody requestBody, @Url String url);

    @POST("getWrongSubjectList")
    @Headers("Content-Type:application/json")
    Observable<WrongtitleBean> getWrongSubjectList(@Body RequestBody requestBody);

    @POST("getInteractionsList")
    @Headers("Content-Type:application/json")
    Observable<InteractionsListBean> getInteractionsList(@Body RequestBody requestBody);


    @POST("getInteractionById")
    @Headers("Content-Type:application/json")
    Observable<HudongIfoBean> getInteractionById(@Body RequestBody requestBody);

    @POST("getCoursewareList")
    @Headers("Content-Type:application/json")
    Observable<ZiyuanBean> getCoursewareList(@Body RequestBody requestBody);

    @POST("folderListOptions")
    @Headers("Content-Type:application/json")
    Observable<FolderListOptionsBean> getfolderListOptions(@Body RequestBody requestBody);

    @POST("getMyInteractionList")
    @Headers("Content-Type:application/json")
    Observable<MyInteractionListBean> getMyInteractionList(@Body RequestBody requestBody);


    @POST("userItemBankCollection")
    @Headers("Content-Type:application/json")
    Observable<CollectionTiBean> userItemBankCollection(@Body RequestBody requestBody);


    @POST("wrongTitleList")
    @Headers("Content-Type:application/json")
    Observable<MyTiBean> wrongTitleList(@Body RequestBody requestBody);


    @POST("userItemCollectionList")
    @Headers("Content-Type:application/json")
    Observable<MyTiBean> userItemCollectionList(@Body RequestBody requestBody);

    @POST("wrongDelete")
    @Headers("Content-Type:application/json")
    Observable<CollectionTiBean> wrongDelete(@Body RequestBody requestBody);


    @POST("getKnowPointer")
    @Headers("Content-Type:application/json")
    Observable<KnowPointerBean> getKnowPointer(@Body RequestBody requestBody);


    @POST("getTestPaperCollectionSubjectList")
    @Headers("Content-Type:application/json")
    Observable<AllsubjectBean> getTestPaperCollectionSubjectList(@Body RequestBody requestBody);


    @POST("getTestPaperCollectionSubjectList")
    @Headers("Content-Type:application/json")
    Observable<WrongtitleBean> getCollectionSubjectList(@Body RequestBody requestBody);

    @POST("userTestPaperCollectionList")
    @Headers("Content-Type:application/json")
    Observable<ZutijiluBean> userTestPaperCollectionList(@Body RequestBody requestBody);


    @POST("userTestPaper")
    @Headers("Content-Type:application/json")
    Observable<ExamineBean> userTestPaper(@Body RequestBody requestBody);

    @POST("getUserInfo")
    @Headers("Content-Type:application/json")
    Observable<UserinfoBean> getUserInfo(@Body RequestBody requestBody);


    @POST("searchSchool")
    @Headers("Content-Type:application/json")
    Observable<SchoolBean> searchSchool(@Body RequestBody requestBody);

    @POST("updateUserInfo")
    @Headers("Content-Type:application/json")
    Observable<CollectBean> updateUserInfo(@Body RequestBody requestBody);

    @POST("version")
    @Headers("Content-Type:application/json")
    Observable<VersionBean> version(@Body RequestBody requestBody);


    @POST("userFeedback")
    @Headers("Content-Type:application/json")
    Observable<CollectBean> userFeedback(@Body RequestBody requestBody);


    @POST("modifyPwd")
    @Headers("Content-Type:application/json")
    Observable<CollectBean> modifyPwd(@Body RequestBody requestBody);


    @POST("TPLogin/{type}")
    @Headers("Content-Type:application/json")
    Observable<LoginSuccesBean> TPLogin(@Path("type") int id, @Body RequestBody requestBody);

    @POST("TPLoginMatchesPhoneNum/{type}")
    @Headers("Content-Type:application/json")
    Observable<CodeBean> TPLoginMatchesPhoneNum(@Path("type") int id, @Body RequestBody requestBody);


    @POST("TPLoginBinPhone/{type}")
    @Headers("Content-Type:application/json")
    Observable<LoginSuccesBean> TPLoginBinPhone(@Path("type") int id, @Body RequestBody requestBody);


    @POST("setPwdByOpenid/{type}")
    @Headers("Content-Type:application/json")
    Observable<LoginSuccesBean> setPwdByOpenid(@Path("type") int id, @Body RequestBody requestBody);


    @POST("courseList")
    @Headers("Content-Type:application/json")
    Observable<CourseBean> courseList(@Body RequestBody requestBody);

    @POST("courseInfo")
    @Headers("Content-Type:application/json")
    Observable<CourseinfoBean> courseInfo(@Body RequestBody requestBody);

    @POST("courseSignUp")
    @Headers("Content-Type:application/json")
    Observable<CollectionTiBean> courseSignUp(@Body RequestBody requestBody);

    @POST("followTeacher")
    @Headers("Content-Type:application/json")
    Observable<CollectionTiBean> followTeacher(@Body RequestBody requestBody);

    @POST("teacherInfo")
    @Headers("Content-Type:application/json")
    Observable<TeacherinfoBean> teacherInfo(@Body RequestBody requestBody);


    @POST("folderDetail")
    @Headers("Content-Type:application/json")
    Observable<BashiinfoBean> folderDetail(@Body RequestBody requestBody);

    @POST("classBegins")
    @Headers("Content-Type:application/json")
    Observable<ClassBeaginBean> classBegins(@Body RequestBody requestBody);


    @POST("myCourse")
    @Headers("Content-Type:application/json")
    Observable<MineCourseBean> myCourse(@Body RequestBody requestBody);


    @POST("myFollowTeacher")
    @Headers("Content-Type:application/json")
    Observable<MineTeacherBean> myFollowTeacher(@Body RequestBody requestBody);


    @POST("skillTypeList")
    @Headers("Content-Type:application/json")
    Observable<skillTypeListBean> skillTypeList(@Body RequestBody requestBody);

    @POST("skillCourseList")
    @Headers("Content-Type:application/json")
    Observable<skillCourseListBeam> skillCourseList(@Body RequestBody requestBody);


    @POST("userHistoricRecord")
    @Headers("Content-Type:application/json")
    Observable<BrowsingBean> userHistoricRecord(@Body RequestBody requestBody);

    @POST("coursePaymentPage")
    @Headers("Content-Type:application/json")
    Observable<CoursePayBean> coursePaymentPage(@Body RequestBody requestBody);


    @POST("unifiedOrder")
    @Headers("Content-Type:application/json")
    Observable<WxPAyBean> unifiedOrder(@Body RequestBody requestBody);


    @POST("orderQuery")
    @Headers("Content-Type:application/json")
    Observable<WxPAyBean> orderQuery(@Body RequestBody requestBody);


    @POST("userWallet")
    @Headers("Content-Type:application/json")
    Observable<BalanceBean> userWallet(@Body RequestBody requestBody);


    @POST("Recharge4Android")
    @Headers("Content-Type:application/json")
    Observable<WxPAyBean> Recharge4Android(@Body RequestBody requestBody);


    @POST("userWalletRecord")
    @Headers("Content-Type:application/json")
    Observable<RecordBean> userWalletRecord(@Body RequestBody requestBody);

    @POST("playerCourseVideo")
    @Headers("Content-Type:application/json")
    Observable<PlayVideoBean> playerCourseVideo(@Body RequestBody requestBody);

    @POST("filterItem")
    @Headers("Content-Type:application/json")
    Observable<TeachingShaixuanBean> filterItem(@Body RequestBody requestBody);

    @POST("bookInfo")
    @Headers("Content-Type:application/json")
    Observable<BookinfoBean> getBookinfo(@Body RequestBody requestBody);


    @POST("examinationList")
    @Headers("Content-Type:application/json")
    Observable<ExaminationListBean> getexaminationList(@Body RequestBody requestBody);


    @POST("examDetail")
    @Headers("Content-Type:application/json")
    Observable<BaominginfoBean> getexamDetail(@Body RequestBody requestBody);

    @POST("examSign")
    @Headers("Content-Type:application/json")
    Observable<BaominginfoBean> getexamSign(@Body RequestBody requestBody);


    @POST("goExam")
    @Headers("Content-Type:application/json")
    Observable<PracticeZuoBean> getgoExam(@Body RequestBody requestBody);

    @POST("examSubmit")
    @Headers("Content-Type:application/json")
    Observable<BaominginfoBean> getexamSubmit(@Body RequestBody requestBody);


    @POST("examTimes")
    @Headers("Content-Type:application/json")
    Observable<BaominginfoBean> getexamTimes(@Body RequestBody requestBody);


    @POST("myExam")
    @Headers("Content-Type:application/json")
    Observable<MyExamBean> getmyExam(@Body RequestBody requestBody);


    @POST("scoreQuery")
    @Headers("Content-Type:application/json")
    Observable<AcoreQueryBean> getscoreQuery(@Body RequestBody requestBody);

    @POST("examAnalysis")
    @Headers("Content-Type:application/json")
    Observable<ExamAnalysisBean> getexamAnalysis(@Body RequestBody requestBody);


    @POST("indexListReload")
    @Headers("Content-Type:application/json")
    Observable<LeftBean> indexListReloadLeft(@Body RequestBody requestBody);

    @POST("indexListReload")
    @Headers("Content-Type:application/json")
    Observable<RightLeft> indexListReloadRoght(@Body RequestBody requestBody);

    @POST("betList")
    @Headers("Content-Type:application/json")
    Observable<BetListBean> betList(@Body RequestBody requestBody);

    @POST("betDetail")
    @Headers("Content-Type:application/json")
    Observable<BetDetailBean> betDetail(@Body RequestBody requestBody);

    @POST("betBuy")
    @Headers("Content-Type:application/json")
    Observable<WxPAyBean> betBuy(@Body RequestBody requestBody);

    @POST("betPaymentPage")
    @Headers("Content-Type:application/json")
    Observable<YitiPayinfo> betPaymentPage(@Body RequestBody requestBody);


    @POST("myBetList")
    @Headers("Content-Type:application/json")
    Observable<BetListBean> myBetList(@Body RequestBody requestBody);


    @POST("myBetFiles")
    @Headers("Content-Type:application/json")
    Observable<YatiBean> myBetFiles(@Body RequestBody requestBody);
}
