package com.yingzhiyun.yingquxue.activity.vip

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yingzhiyun.yingquxue.MyApp.MyApp
import com.yingzhiyun.yingquxue.OkBean.ModuleBean
import com.yingzhiyun.yingquxue.OkBean.VipCenterBean
import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.activity.classfiy.ClassZiyuanActivity
import com.yingzhiyun.yingquxue.activity.homepagr.CourseActivity
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity
import com.yingzhiyun.yingquxue.adapter.VipCouresAdapter
import com.yingzhiyun.yingquxue.adapter.VipModleAdapter
import com.yingzhiyun.yingquxue.adapter.VipTestAdapter
import com.yingzhiyun.yingquxue.adapter.VipWordActivity
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity
import com.yingzhiyun.yingquxue.httpUnits.FristServer
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils
import kotlinx.android.synthetic.main.activity_vipinfo.*
import kotlinx.android.synthetic.main.public_title_bar.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject


class VipinfoActivity :SimpleActivity(), OnRefreshListener {
    override fun choseeClor(): Int {
        return R.color.white
    }

    override fun onResume() {
        super.onResume()
        getList()
    }
    private var recordBeans = ArrayList<ModuleBean>()
    private var vipCourseListBeans = ArrayList<VipCenterBean.ResultBean.VipCourseListBean>()
    private var vipWordActivityListBeans = ArrayList<VipCenterBean.ResultBean.VipCourseListBean>()
    private var viptestpagerList = ArrayList<VipCenterBean.ResultBean.VipCourseListBean>()
    private var recordAdapter: VipModleAdapter? = null
    private var vipCouresAdapter: VipCouresAdapter? = null
    private var vipWordActivity: VipWordActivity? = null
    private var VipTestAdapter: VipTestAdapter? = null
    override fun initData() {
        refresh_layout.setOnRefreshListener(this)
        recordBeans.add(ModuleBean("视频课程",R.drawable.icon_vip_video,0))
        recordBeans.add(ModuleBean("名校试卷",R.drawable.icon_vip_testpager,0))
        recordBeans.add(ModuleBean("专项练习",R.drawable.icon_vip_dowlon,0))
        recordBeans.add(ModuleBean("尊贵标志",R.drawable.icon_vip_biaozhi,0))
        recordAdapter= VipModleAdapter(recordBeans,this)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation= LinearLayoutManager.HORIZONTAL
        finish.setOnClickListener { finish() }
        tool_title.setText("VIP会员中心")
        recy_vipgongn.let {
            it.layoutManager= GridLayoutManager(this,4) as RecyclerView.LayoutManager?
            it.adapter=recordAdapter
        }
        vipCouresAdapter= VipCouresAdapter(vipCourseListBeans,this)
        val linearLayoutManager1 = LinearLayoutManager(this)
        linearLayoutManager1.orientation= LinearLayoutManager.HORIZONTAL
        recy_kcheng.let {
            it.layoutManager=linearLayoutManager1
            it.adapter=vipCouresAdapter
        }
        val linearLayoutManager2 = LinearLayoutManager(this)
        linearLayoutManager2.orientation= LinearLayoutManager.HORIZONTAL
        vipWordActivity= VipWordActivity(vipWordActivityListBeans,this)
        recy_school_pager.let{
            it.layoutManager=linearLayoutManager2
            it.adapter=vipWordActivity
        }
        VipTestAdapter= VipTestAdapter(viptestpagerList)
        recy_testpager.let{
            it.layoutManager=linearLayoutManager
            it.adapter=VipTestAdapter
        }


        record_vip.setOnClickListener {
            startActivity(VipTopupActivity::class.java)
        }
        go_ytouup.setOnClickListener {
            startActivity(VipTopupActivity::class.java)
        }
        course_more.setOnClickListener {
            val intent = Intent(this, CourseActivity::class.java).putExtra("isVip", true)
            startActivity(intent)
        }
        word_more.setOnClickListener { startActivity(ClassZiyuanActivity::class.java) }
    }

    override fun createLayoutID(): Int {
        return   R.layout.activity_vipinfo
    }

    private fun getList() {
        vipCourseListBeans.clear()
        vipWordActivityListBeans.clear()
        viptestpagerList.clear()
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()

        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")



        OkHttpUtils.postString().url(FristServer.URL + "vipModel")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<VipCenterBean> {
                    override fun onError(e: String?) {
                        if (e == "您的账号在别处登录，请重新登录") {
                            finish()
                            startActivity(PwdLoginActivity::class.java)
                        }
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(response: VipCenterBean) {
                        vipCourseListBeans.addAll(response.result.vipCourseList)
                        vipCouresAdapter!!.notifyDataSetChanged()

                        vipWordActivityListBeans.addAll(response.result.vipCoursewareList)
                        vipWordActivity!!.notifyDataSetChanged()


                        if(response.result.vipCourseList.size==0){
                            re_video.visibility=View.GONE
                        }else{
                            re_video.visibility=View.VISIBLE
                        }
                        if(response.result.vipCoursewareList.size==0){
                            re_word.visibility=View.GONE
                        }else{
                            re_word.visibility=View.VISIBLE
                        }
                        if(response.result.vipTestpapaerList.size==0){
                            re_test.visibility=View.GONE
                        }else{
                            re_test.visibility=View.VISIBLE
                        }
                        viptestpagerList.addAll(response.result.vipTestpapaerList)
                        VipTestAdapter!!.notifyDataSetChanged()

                        val requestOptions = RequestOptions.circleCropTransform()
                        Glide.with(this@VipinfoActivity)
                                .load(response.result.head_path)
                                .apply(requestOptions)
                                .into(course_teacherhead)
                        teacher_name.setText(response.result.nickname)
                        if(response.result.isVip){
                            teacherLabel.setText(response.result.vipExpireTime+"到期")
                            record_vip.setText("立即续费")
                        }
                    }

                }))
    }

    override fun onRefresh() {
            getList()
        refresh_layout.isRefreshing=false
    }
}
