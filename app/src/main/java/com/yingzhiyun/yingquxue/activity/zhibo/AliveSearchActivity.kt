package com.yingzhiyun.yingquxue.activity.zhibo

import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.yingzhiyun.yingquxue.MyApp.MyApp
import com.yingzhiyun.yingquxue.OkBean.AliveListBean
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean
import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity
import com.yingzhiyun.yingquxue.adapter.AliveListAdapter
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity
import com.yingzhiyun.yingquxue.httpUnits.FristServer
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils
import com.yingzhiyun.yingquxue.units.StringUtils
import com.yingzhiyun.yingquxue.units.ToastUtil
import kotlinx.android.synthetic.main.activity_search.*

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject

class AliveSearchActivity : SimpleActivity() {
    override fun choseeClor(): Int {
        return R.color.white
    }

    var listsearchs=ArrayList<ZiyuanBean.ResultBean>()
    var aliveListAdapter:AliveListAdapter?=null
    var id=0;
    override fun initData() {

        aliveListAdapter = AliveListAdapter(listsearchs, this)
        tv_search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) { //如果actionId是搜索的id，则进行下一步的操作
                getData(id)
            }
            false
        })
        jilu.visibility = View.GONE
        deleteall.visibility = View.GONE
        search.setOnClickListener {
            getData(id)
        }
    }
    private fun getData(type: Int?) {
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()
        val trim = tv_search.text.trim()

        if (StringUtils.isEmpty(trim)) {
            ToastUtil.makeLongText(this, "请输入搜索内容")
            return
        }
        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")
        json.put("star", type)
        json.put("title",trim.toString())
        Log.d("---------------",json.toString())
        OkHttpUtils.postString().url(FristServer.URL + "liveCourseList")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<AliveListBean> {
                    override fun onError(e: String?) {

                        if (e == "您的账号在别处登录，请重新登录") {
                            finish()
                            startActivity(PwdLoginActivity::class.java)
                        }
                    }

                    override fun onResponse(response: AliveListBean) {

                        if(response.result.size>0){
                            recy_search.visibility= View.VISIBLE

                            listsearchs.addAll(response.result)
                            aliveListAdapter?.notifyDataSetChanged()
                        }else{
                          ToastUtil.makeLongText(this@AliveSearchActivity,"暂无内容")
                        }
                    }

                }))


    }
    override fun createLayoutID(): Int {
       return R.layout.activity_search
    }
}
