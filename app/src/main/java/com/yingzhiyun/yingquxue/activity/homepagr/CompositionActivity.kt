package com.yingzhiyun.yingquxue.activity.homepagr

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import cn.jpush.im.android.api.ChatRoomManager
import cn.jpush.im.android.api.callback.RequestCallback
import cn.jpush.im.android.api.model.Conversation
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.yingzhiyun.yingquxue.Fragment.zhibo.AliveinfoFragment
import com.yingzhiyun.yingquxue.Fragment.zhibo.DanmuFragment
import com.yingzhiyun.yingquxue.Fragment.zhibo.JiaoshuFragment
import com.yingzhiyun.yingquxue.MyApp.MyApp
import com.yingzhiyun.yingquxue.OkBean.AliveBeaginBean
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean
import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity
import com.yingzhiyun.yingquxue.adapter.ZiyuanAdapter
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity
import com.yingzhiyun.yingquxue.httpUnits.FristServer
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils
import kotlinx.android.synthetic.main.activity_alive_play.*
import kotlinx.android.synthetic.main.activity_composition.*
import kotlinx.android.synthetic.main.public_title_bar.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.net.IDN


class CompositionActivity : SimpleActivity() {
    override fun choseeClor(): Int {
        return R.color.white
    }


    var ziyuanAdapter: ZiyuanAdapter?=null
    var list =ArrayList<ZiyuanBean.ResultBean>()
    override fun initData() {
        val id = intent?.extras?.getInt("id");
        tool_title.setText("满分作文")
        finish.setOnClickListener { finish() }
        score_tab.addTab(score_tab.newTab().setText("语文"))
        score_tab.addTab(score_tab.newTab().setText("英语"))
        ziyuanAdapter= ZiyuanAdapter(list,this)
        recy_ccomposition.let {
            it.layoutManager=LinearLayoutManager(this)
            it.adapter=ziyuanAdapter
        }
        getData(id!!,9)
        score_tab.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                Log.d("----------------",p0?.position.toString())
                if(p0?.position==0){
                    getData(id!!,9)
                }else{
                    getData(id!!,7)
                }
            }

        })

    }

    override fun createLayoutID(): Int {
        return  R.layout.activity_composition
    }
    private fun getData(id: Int,subjectid:Int) {

        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()
        Log.d("-------------",""+id);
        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")
        json.put("indexListTypeId", id)
        json.put("subject", subjectid)

        OkHttpUtils.postString().url(FristServer.URL + "getListTypeDetail")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<ZiyuanBean> {
                    override fun onError(e: String?) {
                        if (e == "您的账号在别处登录，请重新登录") {
                            finish()
                            startActivity(PwdLoginActivity::class.java)
                        }
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(response: ZiyuanBean) {
                        list.clear()
                        if(response.result.size>0){
                            recy_ccomposition.visibility=View.VISIBLE
                            linear_modle.visibility=View.GONE
                            list.addAll(response.result)
                            ziyuanAdapter?.notifyDataSetChanged()
                        }else{
                            recy_ccomposition.visibility=View.GONE
                            linear_modle.visibility=View.VISIBLE
                        }
                    }

                }))
    }
}
