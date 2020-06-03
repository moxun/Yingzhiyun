package com.yingzhiyun.yingquxue.activity.zhibo

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.yingzhiyun.yingquxue.MyApp.MyApp
import com.yingzhiyun.yingquxue.OkBean.CourseinfoBean
import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.activity.homepagr.VideoPlayActivity
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity
import com.yingzhiyun.yingquxue.httpUnits.FristServer
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils
import com.yingzhiyun.yingquxue.units.ToastUtil
import com.yingzhiyun.yingquxue.units.UrlImageUnnits
import kotlinx.android.synthetic.main.activity_alive_status.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject

class AliveStatusActivity : SimpleActivity() {
    override fun choseeClor(): Int {
        return R.color.white
    }


    var id = 0;
    override fun initData() {


        finish.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        id = intent.getIntExtra("id", 0)
        getDate(id)
    }
    private fun getDate(id: Int) {
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()

        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")
        json.put("courseId", id)
        Log.d("-----", json.toString())
        OkHttpUtils.postString().url(FristServer.URL + "liveCourseDetail")
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

                        val result = response.result
                        subject_type.text = result.subject
                        course_title.text = result.title
                        course_time.text = result.effective


                        val effective = result.effective
                        if (result.courseType == "1") {
                            btn_login.visibility = View.GONE
                            alive_statue.text = "直播时间：$effective"
                        } else if (result.courseType == "2") {
                            btn_login.visibility = View.GONE
                            alive_statue.text = "正在直播"
                        } else if (result.courseType == "3") {
                            alive_statue.text = "直播已结束"
                            btn_login.visibility = View.VISIBLE

                        }else if(result.courseType=="4"){
                            if(result.playbackLink==null){
                                alive_statue.text = "回放正在生成中"
                            }else{
                                alive_statue.text = "观看回放"
                            }
                        }



                        btn_login.setOnClickListener { pingjiaTeacher(id, result.teacherId) }
                        val stringBuilder = StringBuilder()
//                        Glide.with(this@AliveStatusActivity).load(result.courseImg).into(back)
                        Glide.with(this@AliveStatusActivity).asBitmap().load(result.courseImg)//签到整体 背景
                                .into(object :SimpleTarget<Bitmap>(500,300){
                                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                        var drawable = BitmapDrawable(resource);
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                            back.background=drawable
                                        }

                                    }

                                })
                        if (result.courseBriefing != null) {
                            for (i in result.courseBriefing.indices) {
                                if (result.courseBriefing[i].contentType == "title") {
                                    stringBuilder.append("<font color='#000000'><strong>")
                                    stringBuilder.append(result.courseBriefing[i].content)
                                    stringBuilder.append("</strong></font>" + "<br><br>")
                                } else if (result.courseBriefing[i].contentType == "text") {
                                    stringBuilder.append("<font color='#666666'>")
                                    stringBuilder.append(result.courseBriefing[i].content)
                                    stringBuilder.append("</font>" + "<br><br>")
                                } else {
                                    stringBuilder.append("<img src =\"")
                                    stringBuilder.append(result.courseBriefing[i].content + "\"")
                                    stringBuilder.append("height=\"" + result.courseBriefing[i].height + "\"")
                                    stringBuilder.append("width=\"" + result.courseBriefing[i].width + "\"")
                                    stringBuilder.append("/>" + "<br><br>")
                                }
                            }
                        } else {
                            course_info.visibility = View.GONE
                        }


                        recy_info.setText(Html.fromHtml(stringBuilder.toString(), UrlImageUnnits(recy_info, applicationContext), null))

                        alive_statue.setOnClickListener {
                            if(result.courseType == "2"){
                                var bundle = Bundle()
                                bundle.putInt("id", id)
                                bundle.putString("img",stringBuilder.toString());

                                startActivity(AlivePlayActivity::class.java, bundle)
                            }else if(result.courseType == "4"){
                                if(result.playbackLink!=null){
                                    val bundle = Bundle()
                                    bundle.putString("path",result.playbackLink)
                                    startActivity(VideoPlayActivity::class.java, bundle)
                                }
                            }

                        }
                        img_alive.setOnClickListener {
                            if(result.courseType == "2"){
                                var bundle = Bundle()
                                bundle.putInt("id", id)
                                bundle.putString("img",stringBuilder.toString());

                                startActivity(AlivePlayActivity::class.java, bundle)
                            }else if(result.courseType == "4"){
                                if(result.playbackLink!=null){
                                    val bundle = Bundle()
                                    bundle.putString("path",result.playbackLink)
                                    startActivity(VideoPlayActivity::class.java, bundle)
                                }
                            }

                        }
                    }

                }))
    }

    private fun pingjiaTeacher(id: Int, teacherid: Int) {
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()

        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")
        json.put("courseId", id)
        json.put("teacherId", teacherid)
        Log.d("-----", json.toString())
        OkHttpUtils.postString().url(FristServer.URL + "goScoring")
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
                        if(response.result.score==null){
                            var bundle = Bundle()
                            bundle.putInt("id", id)
                            bundle.putInt("teacherid", teacherid)
                            startActivity(PingfenActivity::class.java, bundle)
                        }else{
                            ToastUtil.makeLongText(this@AliveStatusActivity,"你已经评价过了哦")
                        }



                    }

                }))
    }

    override fun createLayoutID(): Int {
        return R.layout.activity_alive_status
    }
}
