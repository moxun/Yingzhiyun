package com.yingzhiyun.yingquxue.activity.zhibo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.RatingBar
import android.widget.Toast
import cn.jmessage.support.qiniu.android.utils.Dns.getAddress
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yingzhiyun.yingquxue.MyApp.MyApp
import com.yingzhiyun.yingquxue.OkBean.CourseinfoBean
import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.activity.homepagr.course.TeacherinfoActivity
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity
import com.yingzhiyun.yingquxue.httpUnits.FristServer
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils
import com.yingzhiyun.yingquxue.units.StringUtils
import com.yingzhiyun.yingquxue.units.ToastUtil
import com.yingzhiyun.yingquxue.units.UrlImageUnnits

import kotlinx.android.synthetic.main.activity_pingfen.*
import kotlinx.android.synthetic.main.public_title_bar.*

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.util.*


class PingfenActivity : SimpleActivity() {
    override fun choseeClor(): Int {
        return R.color.white
    }

    var xing:Int=0
    var isni:Int=0

    override fun initData() {

        finish.setOnClickListener { finish() }
        ratingbar.setOnRatingBarChangeListener(RatingBar.OnRatingBarChangeListener { ratingBar, rating, fromUser -> xing=rating.toInt() })
        tool_title.text="添加评论"
        edit_pingl.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(s?.length!! >100){
                        edit_pingl.setFocusable(false)
                        ToastUtil.makeLongText(this@PingfenActivity,"最多只能输入100个字哦")
                    }else{
                        now_size.text=s.length.toString()
                    }
            }

        })
        btn_login.setOnClickListener {
            if (StringUtils.isEmpty(edit_pingl.text.toString())){
                ToastUtil.makeLongText(this,"请输入内容")
            }else{
                getUp();
            }

        }
        img_niming.setOnClickListener {
            if(isni==0){
                isni=1;
                img_niming.setImageDrawable(resources.getDrawable(R.drawable.icon_niming))
            }else{
                isni=0;
                img_niming.setImageDrawable(resources.getDrawable(R.drawable.icon_buniming))
            }
        }
    }

    private fun getUp() {
        val id = intent.extras.getInt("id")
        val teacher = intent.extras.getInt("teacherid")
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()

        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")
        json.put("teacherId",teacher)
        json.put("courseId",id)
        json.put("score",xing)
        json.put("content",edit_pingl.text.toString())
        json.put("anonymous",isni)

        Log.d("-----", json.toString())
        OkHttpUtils.postString().url(FristServer.URL + "teacherScoring")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<CourseinfoBean> {
                    override fun onError(e: String?) {
                        if (e == "您的账号在别处登录，请重新登录") {
                            finish()
                            startActivity(PwdLoginActivity::class.java)
                        }
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(response: CourseinfoBean) {
                        finish()
                        ToastUtil.makeLongText(this@PingfenActivity,"提交成功")
                    }

                }))
    }

    override fun createLayoutID(): Int {
       return R.layout.activity_pingfen
    }



}
