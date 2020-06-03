package com.yingzhiyun.yingquxue.activity

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.api.BasicCallback
import com.tencent.imsdk.TIMCallBack
import com.tencent.imsdk.TIMManager
import com.yingzhiyun.yingquxue.MyApp.MyApp
import com.yingzhiyun.yingquxue.OkBean.BaominginfoBean
import com.yingzhiyun.yingquxue.OkBean.MyAliveBean
import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity
import com.yingzhiyun.yingquxue.adapter.MyALiceAdapter
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity
import com.yingzhiyun.yingquxue.httpUnits.FristServer
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils
import kotlinx.android.synthetic.main.activity_my_alive.*
import kotlinx.android.synthetic.main.public_title_bar.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class MyAliveActivity : SimpleActivity(), OnRefreshListener {
    override fun choseeClor(): Int {
        return R.color.white
    }


    /**
     * 初始化地理位置
     */
    @SuppressLint("MissingPermission")
    fun initLocation() {
        Thread(Runnable {
            val serviceString = Context.LOCATION_SERVICE// 获取的是位置服务
            val locationManager = getSystemService(serviceString) as LocationManager
            val provider = LocationManager.NETWORK_PROVIDER// 指定LocationManager的定位方法
            val location = locationManager.getLastKnownLocation(provider)
            var address = getAddress(location)
            runOnUiThread {
                Log.d("------------",address)
            }
        }).start()
    }
    /**
     * 通过经纬度获取位置信息
     */
    private fun getAddress(location: Location?): String {//一定要异步，否则获取不到
        //用来接收位置的详细信息
        var result: List<Address>? = null
        var addressLine = ""
        try {
            if (location != null) {
                val gc = Geocoder(this, Locale.getDefault())
                result = gc.getFromLocation(location.latitude, location.longitude, 1)
                if (result.isNotEmpty()) {
                    try {
                        addressLine = result[0].getAddressLine(0) + result[0].getAddressLine(1)
                    } catch (e: java.lang.Exception) {
                        addressLine = result[0].getAddressLine(0)
                    }
                }
            }
            addressLine=addressLine.replace("null","")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return addressLine
    }

    fun usrLogin(name: String, userSig: String) {
        // identifier 为用户名，userSig 为用户登录凭证

        JMessageClient.login(name, userSig, object : BasicCallback() {
            override fun gotResult(p0: Int, p1: String?) {
                Log.d("--------------",p1)
            }


        });
    }
    fun geSign() {
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()

        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")
        json.put("userId", "4")

        Log.d("-----", json.toString())
        OkHttpUtils.postString().url(FristServer.URL + "jmessageLoginInfo")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<BaominginfoBean> {
                    override fun onError(e: String?) {
                        if (e == "您的账号在别处登录，请重新登录") {
                            finish()
                            startActivity(PwdLoginActivity::class.java)
                        }
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(response: BaominginfoBean) {
                        usrLogin(response.result.username, response.result.password)
                    }

                }))
    }
    var type=0;
    var aliveList=ArrayList<MyAliveBean.ResultBean>();
    var aliveListAdapter:MyALiceAdapter?=null
    override fun initData() {
        refresh_layout.setOnRefreshListener(this)

        geSign()
//        initLocation()
        aliveListAdapter= MyALiceAdapter(aliveList,this)
        recy_myalive.layoutManager=LinearLayoutManager(this)
        recy_myalive.adapter=aliveListAdapter
        tv_senior.setOnClickListener {
            if (type == 0) {
                return@setOnClickListener
            }

            tv_senior.setBackgroundResource(R.drawable.chooseleft)
            tv_junior.setBackgroundResource(R.drawable.righttuoyuan)
            tv_senior.setTextColor(Color.parseColor("#ffffff"))
            tv_junior.setTextColor(Color.parseColor("#1091E9"))
            aliveList.clear()
            type=0
            getDate(type)
        }
        tv_junior.setOnClickListener {
            if (type == 1) {
                return@setOnClickListener
            }

            tv_senior.setBackgroundResource(R.drawable.lefttuoyuan)
            tv_junior.setBackgroundResource(R.drawable.chosseright)
            tv_senior.setTextColor(Color.parseColor("#1091E9"))
            tv_junior.setTextColor(Color.parseColor("#ffffff"))
            type=1
            aliveList.clear()
            getDate(type)
        }
        getDate(type)
        back.setOnClickListener {
            finish()
        }
    }

    override fun createLayoutID(): Int {
        return R.layout.activity_my_alive
    }
    private fun getDate(id: Int) {
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()

        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")
        if(id==0){
            json.put("courseType", id)
        }

        Log.d("-----", json.toString())
        OkHttpUtils.postString().url(FristServer.URL + "myCourse")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<MyAliveBean> {
                    override fun onError(e: String?) {
                        if (e == "您的账号在别处登录，请重新登录") {
                            finish()
                            startActivity(PwdLoginActivity::class.java)
                        }
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(response: MyAliveBean) {
                        aliveList.clear()
                        if(id==0){
                            aliveListAdapter!!.setType("alive")
                            aliveList.addAll(response.result)
                            aliveListAdapter!!.notifyDataSetChanged()
                        }else{
                            aliveListAdapter!!.setType("course")
                            for(bean in response.result){
                                if(bean.detail.size>0){
                                    aliveList.add(bean)
                                }
                            }
                            aliveListAdapter!!.notifyDataSetChanged()
                        }
                    }

                }))
    }

    override fun onRefresh() {
        aliveList.clear()
        getDate(type)
        refresh_layout.isRefreshing=false
    }

    override fun onResume() {
        super.onResume()
        getDate(type)
    }


}
