package com.yingzhiyun.yingquxue.activity.conversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.yingzhiyun.yingquxue.MyApp.MyApp
import com.yingzhiyun.yingquxue.OkBean.DuihuancourseBean
import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity
import com.yingzhiyun.yingquxue.adapter.UserExchangeAdapter
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity
import com.yingzhiyun.yingquxue.httpUnits.FristServer
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils
import com.yingzhiyun.yingquxue.units.StringUtils
import com.yingzhiyun.yingquxue.units.ToastUtil
import kotlinx.android.synthetic.main.activity_conversionentrance.*

import kotlinx.android.synthetic.main.public_title_bar.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject

class Conversionentrance : SimpleActivity() {
    override fun choseeClor(): Int {
       return R.color.white
    }

    var list=ArrayList<DuihuancourseBean.ResultBean>()
    var userExchangeAdapter:UserExchangeAdapter?=null
    override fun initData() {
        tool_title.setText("兑换")
        finish.setOnClickListener {finish()}
        rukou.setOnClickListener { startActivity(ConversionActivity::class.java) }
        userExchangeAdapter= UserExchangeAdapter(list,this)
        course_list.let {
            it.layoutManager=LinearLayoutManager(this)
            it.adapter=userExchangeAdapter
        }

    }

    override fun onResume() {
        super.onResume()
        getData()
    }
    override fun createLayoutID(): Int {
        return R.layout.activity_conversionentrance
    }

    private fun getData() {
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()



        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")

        list.clear()
        OkHttpUtils.postString().url(FristServer.URL + "userExchangeCourseList")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<DuihuancourseBean> {
                    override fun onError(e: String?) {

                        if (e == "您的账号在别处登录，请重新登录") {
                            finish()
                            startActivity(PwdLoginActivity::class.java)
                        }

                    }

                    override fun onResponse(response: DuihuancourseBean) {

                        if(response.result.size>0){
                            re_list.visibility= View.VISIBLE
                            linear_modle.visibility=View.GONE
                            list.addAll(response.result)
                            userExchangeAdapter!!.notifyDataSetChanged()

                        }else{
                            re_list.visibility= View.GONE
                            linear_modle.visibility=View.VISIBLE
                        }
                    }

                }))


    }
}
