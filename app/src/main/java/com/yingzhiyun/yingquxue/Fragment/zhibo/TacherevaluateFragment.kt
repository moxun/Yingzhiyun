package com.yingzhiyun.yingquxue.Fragment.zhibo


import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.tencent.liteav.demo.play.SuperPlayerConst
import com.tencent.liteav.demo.play.utils.TCTimeUtil
import com.tencent.liteav.demo.play.utils.TCVideoGestureUtil.VideoGestureListener
import com.yingzhiyun.yingquxue.Mvp.CourseMvp
import com.yingzhiyun.yingquxue.Mvp.CourseMvp.Course_View
import com.yingzhiyun.yingquxue.MyApp.MyApp
import com.yingzhiyun.yingquxue.OkBean.*
import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity
import com.yingzhiyun.yingquxue.adapter.PingLunListAdapter
import com.yingzhiyun.yingquxue.adapter.XingAdapter
import com.yingzhiyun.yingquxue.base.fragment.BaseFragment
import com.yingzhiyun.yingquxue.presenter.CoursePresenter
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils
import kotlinx.android.synthetic.main.fragment_tacherevaluate.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TacherevaluateFragment : BaseFragment<CourseMvp.Course_View, CoursePresenter<CourseMvp.Course_View>>(), Course_View {
    var list= arrayListOf<String>("1","1","1","1","1")
    var pinglunList=ArrayList<TeacherinfoBean.ResultBean.BriefingBean>()
    var xingAdapter:XingAdapter?=null
    var pingLunListAdapter:PingLunListAdapter?=null
    override fun initData() {
        xingAdapter= XingAdapter(list)


        val id = arguments?.getInt("id")
        pingLunListAdapter=PingLunListAdapter(pinglunList)

        recy_pinglun.layoutManager=LinearLayoutManager(mActivity)
        recy_pinglun.adapter=pingLunListAdapter
        presenter.getTeacherinfo(Gson().toJson(TeacherinfoJson(SharedPreferenceUtils.getUserid(), id!!, MyApp.version, "Android", SharedPreferenceUtils.getToken())))
    }


    override fun createLayoutId(): Int {
        return R.layout.fragment_tacherevaluate
    }

    override fun createPresenter(): CoursePresenter<Course_View> {
        return CoursePresenter()
    }

    override fun setTeacherinfo(teacherinfo: TeacherinfoBean) {
        if(teacherinfo.status==200){
            val avgScore = teacherinfo.result.avgScore
            recy_xing.star= teacherinfo.result.avgScore.toFloat()
            fenhsu.text="$avgScore 分"
            val size = teacherinfo.result.evaluateList.size
            all_erevalute.text="共$size 条评价"
            pinglunList.addAll(teacherinfo.result.evaluateList)
            pingLunListAdapter?.notifyDataSetChanged()
        }else if(teacherinfo.status==511){
            mActivity.finish()
            startActivity(PwdLoginActivity::class.java)
        }
    }

    override fun setcourseSignUp(collectionTiBean: CollectionTiBean?) {

    }

    override fun setmyCourse(courseBean: MineCourseBean?) {

    }

    override fun setclassBegins(classBeaginBean: ClassBeaginBean?) {

    }

    override fun setAllSubject(allSubject: AllsubjectBean?) {

    }

    override fun setmyFollowTeacher(mineTeacherBean: MineTeacherBean?) {

    }

    override fun setcourseInfo(courseinfoBean: CourseinfoBean?) {

    }

    override fun setPlayVideo(playVideo: PlayVideoBean?) {

    }

    override fun setCourseList(courseList: CourseBean?) {

    }

    override fun setfollowTeacher(collectionTiBean: CollectionTiBean?) {

    }


}
