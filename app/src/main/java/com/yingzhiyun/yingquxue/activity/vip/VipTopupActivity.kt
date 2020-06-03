package com.yingzhiyun.yingquxue.activity.vip

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.yingzhiyun.yingquxue.MyApp.MyApp
import com.yingzhiyun.yingquxue.OkBean.CoursePayBean
import com.yingzhiyun.yingquxue.OkBean.ModuleBean
import com.yingzhiyun.yingquxue.OkBean.VipOpenBean
import com.yingzhiyun.yingquxue.OkBean.WxPAyBean
import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity
import com.yingzhiyun.yingquxue.activity.mine.AboutUSActivity
import com.yingzhiyun.yingquxue.adapter.PayWayAdapter
import com.yingzhiyun.yingquxue.adapter.VipModleAdapter
import com.yingzhiyun.yingquxue.adapter.VipPriceAdapter
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity
import com.yingzhiyun.yingquxue.httpUnits.FristServer
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils
import com.yingzhiyun.yingquxue.units.StatusBarUtil
import com.yingzhiyun.yingquxue.units.ToastUtil
import kotlinx.android.synthetic.main.activity_vip_topup.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject

class VipTopupActivity : SimpleActivity() {
    override fun choseeClor(): Int {
        return  R.color.white
    }


    var vipPriceAdapter:VipPriceAdapter?=null
    var vippriceList=ArrayList<VipOpenBean.ResultBean.VipTypeListBean>()
    private var type=0
    private var payWayAdapter: PayWayAdapter? = null
    private val payWayBeans: ArrayList<CoursePayBean.ResultBean.PayTypeListBean> = ArrayList<CoursePayBean.ResultBean.PayTypeListBean>()
    var id:Int=1
    private var iwxapi: IWXAPI? = null
    private var req: PayReq? = null
    private val payid = 0
    private var partnerid: String? = null

    private var recordBeans = ArrayList<ModuleBean>()

    private var recordAdapter: VipModleAdapter? = null
    override fun initData() {
        StatusBarUtil.setColor(this, resources.getColor(R.color.chengse), 0)
        vipPriceAdapter= VipPriceAdapter(vippriceList)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation=RecyclerView.HORIZONTAL
        recy_price.let {
            it.layoutManager= linearLayoutManager
            it.adapter=vipPriceAdapter
        }
        iwxapi = WXAPIFactory.createWXAPI(MyApp.getMyApp(), null)
        instance=this
        //将应用的appid注册到微信
        //将应用的appid注册到微信
        iwxapi!!.registerApp("wx07c1fa2b28ba0dfa")
        finish.setOnClickListener { finish() }
//        info_text.paint.flags= Paint.STRIKE_THRU_TEXT_FLAG.and(Paint.ANTI_ALIAS_FLAG)
        vipPriceAdapter?.setOnItemListener { pos, projectc ->
             id= projectc.id

            vipPriceAdapter?.setDefSelect(pos)
            if(projectc.moreInfo==null){
                info_text.setText(projectc.info)
            }else{
                info_text.setText(projectc.moreInfo)
            }
        }
        payWayAdapter= PayWayAdapter(payWayBeans)
        pay_type.let {
            it.layoutManager=LinearLayoutManager(this)
            it.adapter=payWayAdapter
        }
        payWayAdapter!!.setOnItemListener { pos, payWayBean ->
            type=payWayBean.id
            payWayAdapter!!.setDefSelect(pos)
        }
        bt_record.setOnClickListener {
            //                startActivity(RechargeActivity.class);
            if(type==0){
                ToastUtil.makeLongText(this,"请选择支付方式")

            }else{
                dialogPrompt("确认支付", "确认购买会员吗", "确定", "取消").show()
            }


        }
        vip_xieyi.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("url", "http://192.168.0.120:50001/vipAgreement")
            bundle.putString("name", "会员服务协议")
            startActivity(AboutUSActivity::class.java, bundle)
        }
        recordBeans.add(ModuleBean("视频课程",R.drawable.icon_vip_video,0))
        recordBeans.add(ModuleBean("名校试卷",R.drawable.icon_vip_testpager,0))
        recordBeans.add(ModuleBean("专项练习",R.drawable.icon_vip_dowlon,0))
        recordBeans.add(ModuleBean("尊贵标志",R.drawable.icon_vip_biaozhi,0))
        recordAdapter= VipModleAdapter(recordBeans,this)
        recy_vipgongn.let {
            it.layoutManager=GridLayoutManager(this,4)
            it.adapter=recordAdapter
        }
        getList()
    }
    override fun dialogRightBtn() {
        super.dialogRightBtn()
        if(type==1){
            vipOpenOrRefill(id)
        }else if(type==3){
            getPayInfo(id)
        }

    }
    private fun getList() {
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()

        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")



        OkHttpUtils.postString().url(FristServer.URL + "vipOpenPage")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<VipOpenBean> {
                    override fun onError(e: String?) {
                        if (e == "您的账号在别处登录，请重新登录") {
                            finish()
                            startActivity(PwdLoginActivity::class.java)
                        }
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(response: VipOpenBean) {
                        if(response.result.vipTypeList.size>0){
                            if(response.result.vipTypeList.get(0).moreInfo==null){
                                info_text.setText(response.result.vipTypeList.get(0).moreInfo)
                            }else{
                                info_text.setText(response.result.vipTypeList.get(0).moreInfo)
                            }
                            id=response.result.vipTypeList.get(0).id
                        }

                        vippriceList.addAll(response.result.vipTypeList)
                        vipPriceAdapter?.notifyDataSetChanged()

                        for(i in response.result.payTypeList){

                                payWayBeans.add(i)

                        }
                        payWayAdapter!!.notifyDataSetChanged()
                        val requestOptions = RequestOptions.circleCropTransform()
                        Glide.with(this@VipTopupActivity)
                                .load(response.result.head_img)
                                .error(R.mipmap.icon_userhead)
                                .apply(requestOptions)
                                .into(course_teacherhead)
                        teacher_name.setText(response.result.nickname)
                    }

                }))
    }
    private fun vipOpenOrRefill(id:Int) {


        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()

        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")
        json.put("vipTypeId",id)



        OkHttpUtils.postString().url(FristServer.URL + "vipOpenOrRefill")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<VipOpenBean> {
                    override fun onError(e: String?) {

                        if (e == "您的账号在别处登录，请重新登录") {
                            finish()
                            startActivity(PwdLoginActivity::class.java)
                        }else if(e=="钱包余额不足"){
                            ToastUtil.makeLongText(this@VipTopupActivity,"余额不足，请充值")
//                            startActivity(RechargeActivity::class.java)
                        }
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(response: VipOpenBean) {
                        ToastUtil.makeLongText(this@VipTopupActivity,"开通成功")
                        finish()
                    }

                }))
    }


    private fun getPayInfo(id:Int) {

        if(type==0){
            ToastUtil.makeLongText(this,"请选择支付方式")
            return
        }
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()

        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")
        json.put("vipTypeId",id)

        req = PayReq()

        OkHttpUtils.postString().url(FristServer.PayURL + "vipFillByWechatPay")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<WxPAyBean> {
                    override fun onError(e: String?) {


                    }

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(resone: WxPAyBean) {
                        val result = resone.result

                        iwxapi!!.sendReq(req)
                        partnerid = result.prepayid
                        req!!.appId = result.appid
                        req!!.partnerId = result.partnerid
                        req!!.prepayId = result.prepayid
                        req!!.packageValue = result.packageX
                        req!!.nonceStr = result.noncestr
                        req!!.timeStamp = result.timestamp.toString()
                        req!!.sign = result.sign
                        iwxapi!!.sendReq(req)
                        SharedPreferenceUtils.setprepayid("vip")
                    }

                }))
    }
    override fun createLayoutID(): Int {
        return R.layout.activity_vip_topup
    }

    fun Query() {

        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()

        json.put("prepayid", partnerid)


        req = PayReq()

        OkHttpUtils.postString().url(FristServer.PayURL + "orderQuery")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<WxPAyBean> {
                    override fun onError(e: String?) {


                    }

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(resone: WxPAyBean) {
                        finish()
                        ToastUtil.makeLongText(this@VipTopupActivity,"充值成功")
                    }

                }))
    }

    companion object {
        lateinit var instance: VipTopupActivity
    }
}
