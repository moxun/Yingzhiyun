package com.yingzhiyun.yingquxue.activity.conversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yingzhiyun.yingquxue.MyApp.MyApp
import com.yingzhiyun.yingquxue.OkBean.AliveListBean
import com.yingzhiyun.yingquxue.OkBean.DuihuancourseBean
import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity
import com.yingzhiyun.yingquxue.adapter.UserExchangeAdapter
import com.yingzhiyun.yingquxue.adapter.sijiliebiao.USerCouponAdapter
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity
import com.yingzhiyun.yingquxue.httpUnits.FristServer
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils
import com.yingzhiyun.yingquxue.units.StringUtils
import com.yingzhiyun.yingquxue.units.ToastUtil
import kotlinx.android.synthetic.main.activity_conversion.*
import kotlinx.android.synthetic.main.activity_conversionentrance.*
import kotlinx.android.synthetic.main.activity_conversionentrance.course_list
import kotlinx.android.synthetic.main.activity_conversionentrance.re_list
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.finish
import kotlinx.android.synthetic.main.public_title_bar.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject

class ConversionActivity : SimpleActivity() {
    override fun choseeClor(): Int {
        return  R.color.white
    }
    var couponId=0
var course=0
    var list=ArrayList<DuihuancourseBean.ResultBean>()
    var userExchangeAdapter: USerCouponAdapter?=null
    override fun initData() {
        tool_title.setText("兑换")
        finish.setOnClickListener {finish()}

        userExchangeAdapter= USerCouponAdapter(list,this)
        course_list.let {
            it.layoutManager= LinearLayoutManager(this) as RecyclerView.LayoutManager?
            it.adapter=userExchangeAdapter
        }
        duihuan.setOnClickListener { getData() }
        userExchangeAdapter!!.setOnItemListener { pos, resultBean ->
            userExchangeAdapter!!.setDefSelect(pos)
            couponId = resultBean.couponId;
           course = resultBean.id
        }
        bt_sure.setOnClickListener { submitcourse() }
    }

    override fun createLayoutID(): Int {
        return R.layout.activity_conversion
    }

    private fun getData() {

        val econpon = edit_conpon.text.toString()

        if(StringUtils.isEmpty(econpon)){
            ToastUtil.makeLongText(this,"请输入正确的兑换券")
            return
        }
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()



        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")
        json.put("code",econpon)


        OkHttpUtils.postString().url(FristServer.URL + "verCouponCode")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<DuihuancourseBean> {
                    override fun onError(e: String?) {

                        if (e == "您的账号在别处登录，请重新登录") {
                            finish()
                            startActivity(PwdLoginActivity::class.java)
                        }
                        ToastUtil.makeLongText(this@ConversionActivity,e)
                    }

                    override fun onResponse(response: DuihuancourseBean) {

                        if(response.result.size>0){
                            re_list.visibility= View.VISIBLE
                            list.addAll(response.result)
                            userExchangeAdapter!!.notifyDataSetChanged()
                            course_size.setText("注:课程兑换${response.result.size}选1")
                        }
                    }

                }))


    }
    private fun submitcourse() {



        if(couponId==0){
            ToastUtil.makeLongText(this,"请选择想要的课程")
            return
        }
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()



        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")
        json.put("couponId",couponId)
        json.put("courseId",course)


        OkHttpUtils.postString().url(FristServer.URL + "exchangeCourse")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<DuihuancourseBean> {
                    override fun onError(e: String?) {

                        if (e == "您的账号在别处登录，请重新登录") {
                            finish()
                            startActivity(PwdLoginActivity::class.java)
                        }
                        ToastUtil.makeLongText(this@ConversionActivity,e)
                    }

                    override fun onResponse(response: DuihuancourseBean) {
                        ToastUtil.makeLongText(this@ConversionActivity,"兑换成功")
                        finish()
                    }

                }))


    }
}
