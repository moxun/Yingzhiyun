package com.yingzhiyun.yingquxue.Fragment.zhibo


import android.media.AudioManager
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yingzhiyun.yingquxue.MyApp.MyApp
import com.yingzhiyun.yingquxue.OkBean.AliveListBean
import com.yingzhiyun.yingquxue.OkBean.ZiyuanBean
import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity
import com.yingzhiyun.yingquxue.adapter.AliveListAdapter
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment
import com.yingzhiyun.yingquxue.httpUnits.FristServer
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils
import kotlinx.android.synthetic.main.fragment_recyle.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private val AliveListFragment.i: Int?
    get() {
        val type = arguments?.getInt("type")
        return type
    }

/**
 * A simple [Fragment] subclass.
 *
 */

@Suppress("UNREACHABLE_CODE")
class AliveListFragment() : SimpleFragment() {
    var aliveLists = ArrayList<ZiyuanBean.ResultBean>()


    private val aliveListAdapter: AliveListAdapter
        get() {
            var aliveListAdapter = AliveListAdapter(aliveLists, mActivity)
            return aliveListAdapter
        }

    override fun initData() {

        recy_intera.layoutManager = LinearLayoutManager(mActivity)

        recy_intera.adapter = aliveListAdapter

        getData(i)

    }

    override fun onResume() {
        super.onResume()
        getData(i)
    }

    private fun getData(type: Int?) {
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()
        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")
        json.put("star", type)
        Log.d("---------",json.toString())
        OkHttpUtils.postString().url(FristServer.URL + "liveCourseList")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(mActivity, object : ResponseCallBack<AliveListBean> {
                    override fun onError(e: String?) {

                        if (e == "您的账号在别处登录，请重新登录") {
                            mActivity.finish()
                            startActivity(PwdLoginActivity::class.java)
                        }
                    }

                    override fun onResponse(response: AliveListBean) {
                        aliveLists.clear()
                        if (response.result.size > 0) {
                            recy_intera.visibility = View.VISIBLE
                            linear_modle.visibility = View.GONE
                            aliveLists.addAll(response.result)
                            aliveListAdapter.notifyDataSetChanged()
                        } else {
                            recy_intera.visibility = View.GONE
                            linear_modle.visibility = View.VISIBLE
                        }
                    }

                }))


    }

    override fun createLayoutId(): Int {
        return R.layout.fragment_recyle
    }

    override fun onDestroy() {
        super.onDestroy()
        aliveListAdapter.cleartime()
    }

}
