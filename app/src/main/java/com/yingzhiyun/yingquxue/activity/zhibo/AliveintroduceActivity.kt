package com.yingzhiyun.yingquxue.activity.zhibo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yingzhiyun.yingquxue.MyApp.MyApp
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean
import com.yingzhiyun.yingquxue.OkBean.CourseinfoBean
import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.activity.homepagr.course.TeacherinfoActivity
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity
import com.yingzhiyun.yingquxue.activity.pay.FuKuanActivity
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity
import com.yingzhiyun.yingquxue.httpUnits.FristServer
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils
import com.yingzhiyun.yingquxue.units.ToastUtil
import com.yingzhiyun.yingquxue.units.UrlImageUnnits
import kotlinx.android.synthetic.main.activity_aliveintroduce.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject

class AliveintroduceActivity : SimpleActivity() {
    override fun choseeClor(): Int {
        return R.color.white
    }


    override fun initData() {
        finish.setOnClickListener { finish() }
        getDate(intent.getIntExtra("id", 0))


    }
    private fun Baoming(type: Int?) {
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()
        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")
        json.put("courseId", type)
        Log.d("---------",json.toString())
        OkHttpUtils.postString().url(FristServer.URL + "courseSignUp")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<CollectionTiBean> {
                    override fun onError(e: String?) {

                        if (e == "您的账号在别处登录，请重新登录") {
                          finish()
                            startActivity(PwdLoginActivity::class.java)
                        }
                    }

                    override fun onResponse(response: CollectionTiBean) {

                        ToastUtil.makeShortText(this@AliveintroduceActivity, response.getHint())
                        btn_login.text = "已报名"
                    }

                }))


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
                        Log.d("-----", "------")
                        val result = response.result
                        subject_type.text = result.subject
                        course_title.text = result.title
                        course_time.text = result.effective
                        val payprice = result.price;
                        price.text = "￥$payprice"
                        val signUpNumber = result.signUpNumber;
                        val countSignUp = result.countSignUp;
                        if (result.price == 0.0) {
                            free_renshu.text = "$signUpNumber 人报名"
                            free_renshu.visibility = View.VISIBLE
                        } else {
                            fufei_renshu.visibility = View.VISIBLE
                            fufei_renshu.text = "报名满$countSignUp 人开课  已报名$signUpNumber 人"
                        }

                        re_teacher.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putInt("id", result.teacherId)
                            bundle.putString("type", "alive")
                          startActivity(Intent(this@AliveintroduceActivity, TeacherinfoActivity::class.java).putExtras(bundle))
                        }
                        val requestOptions = RequestOptions.circleCropTransform()
                        Glide.with(this@AliveintroduceActivity)
                                .load(result.teacherHead)
                                .apply(requestOptions)
                                .into(course_teacherhead)
                        teacher_name.text = result.teacherName


                        val stringBuilder = StringBuilder()
                        Glide.with(this@AliveintroduceActivity).load(result.courseImg).into(back)
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

                        Log.d("moun", "setcourseInfo: $stringBuilder")
                        recy_info.setText(Html.fromHtml(stringBuilder.toString(), UrlImageUnnits(recy_info, applicationContext), null))
                        if (result.isIsSignUp) {
                            btn_login.text = "已报名"
                        }
                        btn_login.setOnClickListener {
                            if (btn_login.text == "立即报名") {
                                if(result.price==0.0){
                                    Baoming(intent.getIntExtra("id", 0))
                                }else{
                                    val bundle = Bundle()
                                    bundle.putInt("id", intent.getIntExtra("id", 0))
                                    bundle.putString("type", "alive")
                                    finish()
                                    startActivity(FuKuanActivity::class.java, bundle)
                                }

                            } else {
                                ToastUtil.makeLongText(this@AliveintroduceActivity, "您已成功报名，请至“我的课程“观看学习")
                            }
                        }
                    }

                }))
    }

    override fun createLayoutID(): Int {
        return R.layout.activity_aliveintroduce
    }
}
