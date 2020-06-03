package com.yingzhiyun.yingquxue.activity.zhibo

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.media.AudioManager
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.jpush.im.android.api.ChatRoomManager
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.callback.RequestCallback
import cn.jpush.im.android.api.content.TextContent
import cn.jpush.im.android.api.event.ChatRoomMessageEvent
import cn.jpush.im.android.api.model.ChatRoomInfo
import cn.jpush.im.android.api.model.Conversation
import cn.jpush.im.android.api.model.UserInfo
import cn.jpush.im.api.BasicCallback
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.tencent.liteav.basic.log.TXCLog
import com.tencent.liteav.demo.play.SuperPlayerConst
import com.tencent.liteav.demo.play.SuperPlayerGlobalConfig
import com.tencent.liteav.demo.play.SuperPlayerView
import com.tencent.liteav.demo.play.SuperPlayerView.TAG
import com.tencent.liteav.demo.play.utils.TCUrlUtil
import com.tencent.liteav.demo.play.utils.TCVideoGestureUtil
import com.tencent.rtmp.ITXLivePlayListener
import com.tencent.rtmp.TXLiveConstants
import com.tencent.rtmp.TXLivePlayConfig
import com.tencent.rtmp.TXLivePlayer
import com.yingzhiyun.yingquxue.Fragment.zhibo.AliveinfoFragment
import com.yingzhiyun.yingquxue.Fragment.zhibo.DanmuFragment
import com.yingzhiyun.yingquxue.Fragment.zhibo.JiaoshuFragment
import com.yingzhiyun.yingquxue.MyApp.MyApp
import com.yingzhiyun.yingquxue.OkBean.AliveBeaginBean
import com.yingzhiyun.yingquxue.OkBean.CollectionTiBean
import com.yingzhiyun.yingquxue.OkBean.MsgBean
import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity
import com.yingzhiyun.yingquxue.adapter.PinglunAdapter
import com.yingzhiyun.yingquxue.base.adapter.NewsListadapter
import com.yingzhiyun.yingquxue.httpUnits.FristServer
import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils
import com.yingzhiyun.yingquxue.okhttp.callback.ResponseCallBack
import com.yingzhiyun.yingquxue.okhttp.callback.ResultModelCallback
import com.yingzhiyun.yingquxue.units.*
import kotlinx.android.synthetic.main.activity_alive_play.*
import kotlinx.android.synthetic.main.fragment_danmu.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList


class AlivePlayActivity : AppCompatActivity(), ITXLivePlayListener, GestureDetectorController.IGestureListener, View.OnTouchListener, MyShare.ShareResultCallback {


    var mCurrentPlayState: Int = 0;
    var mCurrentPlayType: Int = 0;
    var mLivePlayer: TXLivePlayer? = null
    var isVis: Boolean = true
    var extras: Bundle? = null
    var isfollow = true
    var teacherId = 0
    var showDialogLeft:Dialog?=null
    var mReList: RecyclerView? = null
    var popWiw: BaseSelectPopupWindow? = null
    var texted: EditText? = null
    var idLock=false
    var myInfo: UserInfo? = null
    private var mBool = false
    private var mGestureDetector: GestureDetector? = null
    private var mCurrentVolume = 0
    private var mMaxVolume = 10
    private var mAudioManager: AudioManager? = null
    private var mGestureController: GestureDetectorController? = null
    var mVideoGestureUtil: TCVideoGestureUtil? = null
    var fragments:ArrayList<Fragment> = ArrayList<Fragment>()
    var newsListadapter:NewsListadapter?=null
    var pinglunAdapter: PinglunAdapter? = null
    var linearLayoutManager: LinearLayoutManager?=null
    var list: ArrayList<MsgBean>? = ArrayList()
    var roomId =""
    var ss:ChatRoomInfo?=null
    private var timer: Timer? = null
    private var task: TimerTask? = null
    var mutableSetOf:MutableSet<Long>?=null
    @SuppressLint("HandlerLeak")
    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 0x1649) {
                ChatRoomManager.getChatRoomInfos(mutableSetOf,object : RequestCallback<List<ChatRoomInfo>>(){
                    override fun gotResult(p0: Int, p1: String?, p2: List<ChatRoomInfo>?) {
                        ss = p2?.get(0)
                        iv_pause.setText(ss?.totalMemberCount.toString())

                    }

                });
            } else if (msg.what == 0x1305) {
            }
        }
    }
    fun initData() {

        extras = intent.extras

        //判断当前屏幕方向
        if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) { //切换竖屏

            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        }

        JMessageClient.registerEventReceiver(this)
        myInfo = JMessageClient.getMyInfo()
        var int = intent.extras.getInt("id");
        getData(int)
        popBiZhong()

        initLivePlayer(this)
        show()
        toggleView(small_iv_background, true)
        toggleView(pb_live, true)
        re_alive.setOnClickListener {
            if(idLock){

                hide()
            }else{
                if (isVis) {
                    hide()
                } else {
                    show()
                }
            }

        }
        var strings = listOf<String>( "聊天","课程详情")



        navigation_tab_layout.setInlineLabel(true)
        navigation_tab_layout.setupWithViewPager(viewpager)
         newsListadapter = NewsListadapter(supportFragmentManager, strings, fragments)
        viewpager.adapter = newsListadapter
        viewpager.addOnPageChangeListener(TabLayoutOnPageChangeListener(navigation_tab_layout))
        navigation_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewpager.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        follow.setOnClickListener {
            followteacher(teacherId)
        }
        iv_fullscreen.setOnClickListener {

            //判断当前屏幕方向
            if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) { //切换竖屏
                this@AlivePlayActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

            } else {
                this@AlivePlayActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

            }
        }
        liaotian.setOnClickListener {
            showDialogLeft?.show()
        }
        mGestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {

                return true
            }

            override fun onSingleTapConfirmed(e: MotionEvent): Boolean {

                return true
            }

            override fun onScroll(downEvent: MotionEvent, moveEvent: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
                if (downEvent == null || moveEvent == null) {
                    return false
                }
                Log.d("----------", "----------------")
                if (mVideoGestureUtil != null && gesture_progress != null) {
                    mVideoGestureUtil?.check(gesture_progress.getHeight(), downEvent, moveEvent, distanceX, distanceY)
                }
                return true
            }

            override fun onDown(e: MotionEvent): Boolean {
                if (mVideoGestureUtil != null) {
                    mVideoGestureUtil!!.reset(video_view.getWidth(), 0)
                }
                return true
            }
        })
        mGestureDetector!!.setIsLongpressEnabled(false)
        mVideoGestureUtil = TCVideoGestureUtil(this)
        mVideoGestureUtil?.setVideoGestureListener(
                object : TCVideoGestureUtil.VideoGestureListener {
                    override fun onBrightnessGesture(newBrightness: Float) {
                        if (gesture_progress != null) {
                            gesture_progress.setProgress((newBrightness * 100).toInt())
                            gesture_progress.setImageResource(R.drawable.ic_light_max)
                            gesture_progress.show()
                        }
                    }

                    override fun onVolumeGesture(volumeProgress: Float) {
                        if (gesture_progress != null) {
                            gesture_progress.setImageResource(R.drawable.ic_volume_max)
                            gesture_progress.setProgress(volumeProgress.toInt())
                            gesture_progress.show()
                        }
                    }

                    override fun onSeekGesture(progress: Int) {

                    }
                })
        video_view.setOnTouchListener(this);
        video_view.setFocusable(true);
        video_view.setClickable(true);
        video_view.setLongClickable(true);



    }

    @SuppressLint("WrongConstant")
    private fun popBiZhong() {
        var inflate = LayoutInflater.from(this).inflate(R.layout.pop_bizhong, null)

         showDialogLeft = DialogUtil.showDialogLeft(this, inflate, 300)
        mReList = inflate.findViewById<RecyclerView>(R.id.reList)
        texted = inflate.findViewById<EditText>(R.id.ed_pl)
        linearLayoutManager= LinearLayoutManager(this)
        pinglunAdapter = PinglunAdapter(list)
        mReList?.let {
            recyclerView -> recyclerView.layoutManager=linearLayoutManager
            recyclerView.adapter=pinglunAdapter
        }


    inflate.setOnClickListener {
        showDialogLeft?.dismiss()
    }
    showDialogLeft!!.setCanceledOnTouchOutside(true)
        texted!!.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if(texted!!.text.toString().equals("")){
                        ToastUtil.makeLongText(this@AlivePlayActivity,"请输入聊天内容")

                    }else{
                        sendJMessage(texted!!.text.toString())
                    }

                }
                texted!!.setText("")

                hideKeyboard(ed_pl)
                return false
            }

        })

    }

    /**
     * 播放时移直播url
     */
    private fun playTimeShiftLiveURL(url: String, playType: Int) {
        var liveURL = url
        var bizid = liveURL.substring(liveURL.indexOf("//") + 2, liveURL.indexOf("."))
        var domian = SuperPlayerGlobalConfig.getInstance().playShiftDomain
        var streamid = liveURL.substring(liveURL.lastIndexOf("/") + 1, liveURL.lastIndexOf("."))
        var appid = playType
        TXCLog.i(SuperPlayerView.TAG, "bizid:$bizid,streamid:$streamid,appid:$appid")
        playLiveURL(liveURL, TXLivePlayer.PLAY_TYPE_LIVE_FLV)
        try {
            var bizidNum = Integer.valueOf(bizid)
            mLivePlayer!!.prepareLiveSeek(domian, bizidNum)
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            TXCLog.e(SuperPlayerView.TAG, "playTimeShiftLiveURL: bizidNum 错误 = %s $bizid")
        }
    }

    /**
     * 更新播放类型
     *
     * @param playType
     */
    private fun updatePlayType(playType: Int) {
        mCurrentPlayType = playType

    }

    fun playvideo(videoURL: String, appid: Int) {
        if (TextUtils.isEmpty(videoURL)) {
            Toast.makeText(this, "播放视频失败，播放连接为空", Toast.LENGTH_SHORT).show()
            return
        }
        if (TCUrlUtil.isRTMPPlay(videoURL)) { // 直播播放器：普通RTMP流播放

            mLivePlayer!!.setPlayerView(video_view)
            playLiveURL(videoURL, TXLivePlayer.PLAY_TYPE_LIVE_RTMP)
        } else if (TCUrlUtil.isFLVPlay(videoURL)) { // 直播播放器：直播FLV流播放

            mLivePlayer!!.setPlayerView(video_view)
            playTimeShiftLiveURL(videoURL, appid)

        }
        var isLivePlay = TCUrlUtil.isRTMPPlay(videoURL) || TCUrlUtil.isFLVPlay(videoURL)
        updatePlayType(if (isLivePlay) SuperPlayerConst.PLAYTYPE_LIVE else SuperPlayerConst.PLAYTYPE_VOD)
    }

    /**
     * 播放直播URL
     */
    private fun playLiveURL(url: String, playType: Int) {

        if (mLivePlayer != null) {
            mLivePlayer!!.setPlayListener(this)
            var result = mLivePlayer!!.startPlay(url, playType) // result返回值：0 success;  -1 empty url; -2 invarid url; -3 invarid playType;
            if (result != 0) {
                TXCLog.e(SuperPlayerView.TAG, "playLiveURL videoURL:$url,result:$result")
            } else {

            }
        }
    }

    /**
     * 初始化直播播放器
     *
     * @param context
     */
    private fun initLivePlayer(context: Context) {
        if (mLivePlayer != null) return
        mLivePlayer = TXLivePlayer(context)
        var config = SuperPlayerGlobalConfig.getInstance()
        var mLivePlayConfig = TXLivePlayConfig()
        mLivePlayer!!.setConfig(mLivePlayConfig)
        mLivePlayer!!.setRenderMode(config.renderMode)
        mLivePlayer!!.setRenderRotation(TXLiveConstants.RENDER_ROTATION_PORTRAIT)
        mLivePlayer!!.setPlayListener(this)
        mLivePlayer!!.enableHardwareDecode(config.enableHWAcceleration)
    }

    private fun getData(id: Int) {
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()
        Log.d("-------------",""+id);
        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")
        json.put("courseId", id)
        Log.d("-----", json.toString())
        OkHttpUtils.postString().url(FristServer.URL + "liveCourseBegun")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<AliveBeaginBean> {
                    override fun onError(e: String?) {
                        if (e == "您的账号在别处登录，请重新登录") {
                            finish()
                            startActivity(PwdLoginActivity::class.java)
                        }
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(response: AliveBeaginBean) {
                        var result = response.result;
                        Glide.with(this@AlivePlayActivity).load(result.courseImg).into(small_iv_background)
                        tv_title.text = result.title
                        playvideo(response.result.playerUrl,
                                MyApp.tenentappid)
                        Log.d("---------------","-"+result.getIsFollow())
                        if (result.getIsFollow()) {
                            isfollow = true
                            follow.background = getDrawable(R.drawable.graytuo)
                            follow.text = "已关注"
                        } else {
                            isfollow = false
                            follow.background = getDrawable(R.drawable.maincolor_13)
                            follow.text = "关注"
                        }
                        roomId = response.result.roomId
                        teacherId = response.result.teacherId;
                        var danmuFragment = DanmuFragment();
                        var bundle=Bundle()
                        bundle.putString("roomId",response.result.roomId)
                        danmuFragment.arguments=bundle
                        var aliveinfoFragment = AliveinfoFragment();
                        aliveinfoFragment.arguments = extras
                        var jiaoshuFragment = JiaoshuFragment()
                        fragments.add(danmuFragment)
                        fragments.add(aliveinfoFragment)
                        newsListadapter!!.notifyDataSetChanged()
                         mutableSetOf = mutableSetOf<Long>()
                        mutableSetOf?.add(roomId.toLong())
                        ChatRoomManager.getChatRoomInfos(mutableSetOf,object : RequestCallback<List<ChatRoomInfo>>(){
                            override fun gotResult(p0: Int, p1: String?, p2: List<ChatRoomInfo>?) {
                                ss = p2?.get(0)
                                iv_pause.setText(ss?.totalMemberCount.toString())

                            }

                        });
                        ChatRoomManager.enterChatRoom(roomId.toLong(), object : RequestCallback<Conversation>() {
                            override fun gotResult(p0: Int, p1: String?, p2: Conversation?) {

                                   }


                        });
                        timer = Timer()
                        task = object : TimerTask() {
                            override fun run() {
                                val message = Message()
                                message.what = 0x1649
                                handler.sendMessage(message)
                            }
                        }
                        timer!!.schedule(task, 2000, 100000)
                    }

                }))
    }

    private fun followteacher(id: Int) {
        var mediaType = "application/json".toMediaTypeOrNull()
        var json = JSONObject()

        json.put("app_user_id", SharedPreferenceUtils.getUserid())
        json.put("token", SharedPreferenceUtils.getToken())
        json.put("version", MyApp.version)
        json.put("device", "Android")
        json.put("teacherId", id)
        Log.d("-----", json.toString())
        OkHttpUtils.postString().url(FristServer.URL + "followTeacher")
                .mediaType(mediaType)
                .content(json.toString())
                .build().execute(ResultModelCallback<Any?>(this, object : ResponseCallBack<CollectionTiBean> {
                    override fun onError(e: String?) {
                        if (e == "您的账号在别处登录，请重新登录") {
                            finish()
                            startActivity(PwdLoginActivity::class.java)
                        }
                    }

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(response: CollectionTiBean) {
                        ToastUtil.makeLongText(this@AlivePlayActivity, response.hint)
                        if (response.result == 0) {
                            follow.background = getDrawable(R.drawable.maincolor_13)
                            follow.text = "关注"
                        } else {
                            follow.background = getDrawable(R.drawable.graytuo)
                            follow.text = "已关注"
                        }
                    }

                }))
    }

    fun show() {
        isVis = true
        iv_lock.visibility=View.VISIBLE
        layout_top.visibility = View.VISIBLE
        layout_bottom.visibility = View.VISIBLE


    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // land do nothing is ok
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // port do nothing is ok
        }
    }

    fun hide() {
        isVis = false
        layout_top.visibility = View.GONE
        layout_bottom.visibility = View.GONE
        iv_lock.visibility=View.GONE

    }



    /**
     * 设置控件的可见性
     *
     * @param view      目标控件
     * @param isVisible 显示：true 隐藏：false
     */
    private fun toggleView(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
    }


    /**
     * 更新播放类型
     *
     * @param playType
     */
    private fun updatePlayState(playType: Int) {
        mCurrentPlayState = playType

    }

    override fun onPlayEvent(event: Int, param: Bundle?) {
        if (event != TXLiveConstants.PLAY_EVT_PLAY_PROGRESS) {
            var playEventLog = "TXLivePlayer onPlayEvent event: " + event + ", " + param!!.getString(TXLiveConstants.EVT_DESCRIPTION)
            TXCLog.d(TAG, playEventLog)
        }
        when (event) {
            TXLiveConstants.PLAY_EVT_VOD_PLAY_PREPARED -> {
                updatePlayState(SuperPlayerConst.PLAYSTATE_PLAYING)
            }
            TXLiveConstants.PLAY_EVT_PLAY_BEGIN -> {
                updatePlayState(SuperPlayerConst.PLAYSTATE_PLAYING)
                hide()
                toggleView(pb_live, false)
                video_view.visibility = View.VISIBLE
                toggleView(small_iv_background, false)
            }
            TXLiveConstants.PLAY_ERR_NET_DISCONNECT, TXLiveConstants.PLAY_EVT_PLAY_END -> if (mCurrentPlayType == SuperPlayerConst.PLAYTYPE_LIVE_SHIFT) { // 直播时移失败，返回直播
                finish()
                Toast.makeText(this, "直播已经结束了哦~", Toast.LENGTH_SHORT).show()

            } else {
                stopPlay()
                updatePlayState(SuperPlayerConst.PLAYSTATE_END)
                if (event == TXLiveConstants.PLAY_ERR_NET_DISCONNECT) {
                    Toast.makeText(this, "直播已经结束了哦~", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, param!!.getString(TXLiveConstants.EVT_DESCRIPTION), Toast.LENGTH_SHORT).show()
                }
                finish()
            }

            TXLiveConstants.PLAY_EVT_PLAY_LOADING, TXLiveConstants.PLAY_WARNING_RECONNECT -> {
                updatePlayState(SuperPlayerConst.PLAYSTATE_LOADING)

            }

            TXLiveConstants.PLAY_EVT_STREAM_SWITCH_SUCC -> Toast.makeText(this, "清晰度切换成功", Toast.LENGTH_SHORT).show()
            TXLiveConstants.PLAY_ERR_STREAM_SWITCH_FAIL -> Toast.makeText(this, "清晰度切换失败", Toast.LENGTH_SHORT).show()
            TXLiveConstants.PLAY_EVT_PLAY_PROGRESS -> {

            }
            else -> {
            }
        }
    }

    override fun onNetStatus(p0: Bundle?) {

    }

    /**
     * 停止播放
     */
    private fun stopPlay() {

        if (mLivePlayer != null) {
            mLivePlayer!!.setPlayListener(null)
            mLivePlayer!!.stopPlay(false)

        }

        mCurrentPlayState = SuperPlayerConst.PLAYSTATE_PAUSE
        TXCLog.e(TAG, "stopPlay mCurrentPlayState:$mCurrentPlayState")

    }

    override fun onDestroy() {
        super.onDestroy()
        stopPlay()
        JMessageClient.unRegisterEventReceiver(this)
        // 离开聊天室
        // 离开聊天室
        ChatRoomManager.leaveChatRoom(roomId.toLong(), object : BasicCallback() {
            override fun gotResult(responseCode: Int, responseMessage: String) {
                Log.d("---------",responseMessage)
            }
        })
        if (handler != null) {
            handler.removeCallbacksAndMessages(null)
        }
        if (timer != null) {
            timer!!.cancel()
            timer = null
            task = null
        }
    }

    fun showKeyboard(view: View) {
        var imm = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null) {
            view.requestFocus();
            imm.showSoftInput(view, 0);
        }
    }

    fun hideKeyboard(view: View) {
        var imm = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private fun showPop() {
        if (popWiw == null) {
            popWiw = BaseSelectPopupWindow(this, R.layout.edit_data)
            // popWiw.setOpenKeyboard(true);
            popWiw!!.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED)
            popWiw!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            popWiw!!.setShowTitle(false)
        }
        popWiw!!.setFocusable(true)
        var im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        im.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
        var send = popWiw!!.getContentView().findViewById(
                R.id.btn_send) as Button
        var edt = popWiw!!.getContentView().findViewById(
                R.id.edt_content) as EditText
        edt.hint = "来点互动吧"
        edt.inputType = EditorInfo.TYPE_CLASS_TEXT
        edt.imeOptions = EditorInfo.IME_ACTION_SEND
        edt.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                       count: Int) {
                if (TextUtils.isEmpty(edt.text)) {
                    send.isEnabled = false
                } else {
                    send.isEnabled = true
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {
            }

            override fun afterTextChanged(s: Editable) {}
        })
        edt.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            //输入法软键盘的控制
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || event != null && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                if (!TextUtils.isEmpty(edt.text.toString().trim { it <= ' ' })) { // /提交内容
                    var content = edt.text.toString().trim { it <= ' ' }
                    sendJMessage(content)
                    hideKeyboard(edt)
                    popWiw!!.dismiss()
                }
                return@OnEditorActionListener true
            }
            false
        })
        send.setOnClickListener {
            if (!TextUtils.isEmpty(edt.text.toString().trim { it <= ' ' })) { // /提交内容
                var content = edt.text.toString().trim { it <= ' ' }

                sendJMessage(content)
                hideKeyboard(edt)
                popWiw!!.dismiss()
            }
        }
        popWiw!!.setOnDismissListener(object : PopupWindow.OnDismissListener {
            override fun onDismiss() {

                hideKeyboard(edt)
            }

        })
        showKeyboard(edt)
        popWiw!!.showAtLocation(layout_top, Gravity.BOTTOM
                or Gravity.CENTER_HORIZONTAL, 0, 0)
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    fun startActivity(clz: Class<*>?) {
        startActivity(Intent(this, clz))
    }



    fun chenjin(color: Int) {
               StatusBarUtil.setColor(this, resources.getColor(color), 0)

    }

    override fun onScrollVerticalLeft(y1: Float, y2: Float) {
        val FLING_MIN_DISTANCE = 0.5;
        val FLING_MIN_VELOCITY = 0.5;
        if (y2 > FLING_MIN_DISTANCE && Math.abs(y2) > FLING_MIN_VELOCITY) {
            setBrightness(10F);
        }
        if (y2 < FLING_MIN_DISTANCE && Math.abs(y2) > FLING_MIN_VELOCITY) {
            setBrightness((-10).toFloat());
        }
    }

    override fun onScrollHorizontal(x1: Float, x2: Float) {

    }

    override fun onScrollStart(type: GestureDetectorController.ScrollType?) {

    }

    override fun onScrollVerticalRight(y1: Float, y2: Float) {

        val height = resources.displayMetrics.heightPixels
        val offset = (mMaxVolume * y1) as Int / height
        if (Math.abs(offset) > 0) {
            mCurrentVolume += offset //得到变化后的声音
            mCurrentVolume = Math.max(0, Math.min(mMaxVolume, mCurrentVolume))
            // 更新系统声音
            mAudioManager?.setStreamVolume(AudioManager.STREAM_MUSIC, mCurrentVolume / 10, 0)

        }
    }


    fun setBrightness(brightness: Float) {
        val lp = getWindow().attributes
        lp.screenBrightness = lp.screenBrightness + brightness / 255.0f
        Log.e("chimu", "setBrightness: 亮度" + lp.screenBrightness)
        if (lp.screenBrightness > 1) {
            lp.screenBrightness = 1f
            Log.e("chimu", "setBrightness: 调节亮度1")
        } else if (lp.screenBrightness < 0.1) {
            lp.screenBrightness = 0.1.toFloat()
            Log.e("chimu", "setBrightness: 调节亮度2")
        }
        getWindow().attributes = lp
        val sb = lp.screenBrightness

    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (mGestureDetector == null) {
            return false
        }
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> if (isVis) {
                hide()
            } else {
                show()
            }
        }
        return mGestureDetector!!.onTouchEvent(event)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alive_play)
        chenjin(R.color.black)
        initData()
        iv_back.setOnClickListener {
            //判断当前屏幕方向
            if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) { //切换竖屏

                this@AlivePlayActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            } else {
                finish()

            }
        }
        iv_lock.setOnClickListener {
            if(idLock){
                iv_lock.setImageResource(R.drawable.ic_player_unlock)
                show()
                idLock=false
            }else{
                iv_lock.setImageResource(R.drawable.ic_player_lock)
                hide()
                idLock=true
            }
        }
        share.setOnClickListener {
            DialogUtil.showShareMusicDialog(this,"应有趣,应趣学","初高中学习的必备软件","","http://192.168.0.120/share",this)
        }
    }
    fun sendJMessage(text:String) {
        // 发送聊天室消息
        var conv = JMessageClient.getChatRoomConversation(roomId.toLong());
        if (null == conv) {
            conv = Conversation.createChatRoomConversation(roomId.toLong());
        }



        val msg = conv.createSendTextMessage(text);//实际聊天室可以支持所有类型的消息发送，demo为了简便，仅仅实现了文本类型的消息发送，其他的消息类型参考消息发送相关文档
        msg.setOnSendCompleteCallback(object : BasicCallback() {
            override fun gotResult(p0: Int, p1: String?) {

                if (0 == p0) {

                    list?.add(MsgBean(myInfo!!.nickname, text))
                    MoveToPosition(linearLayoutManager!!,mReList!!,list!!.size-1)
                    ed_pl.setText("")
                    pinglunAdapter!!.notifyItemRangeChanged(0, list!!.size)
                } else {
                    Log.d("==========",p1)
                    ToastUtil.makeLongText(this@AlivePlayActivity, "消息發送失敗")
                }
            }

        });
        JMessageClient.sendMessage(msg);//使用默认控制参数发送消息
    }
    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    fun MoveToPosition(manager:LinearLayoutManager , mRecyclerView:RecyclerView ,n: Int ) {


        var firstItem = manager.findFirstVisibleItemPosition();
        var lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            var top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }
    }
    override fun shareResultCallBack(result: Int) {

    }
    // 接收聊天室消息
    fun onEventMainThread(event: ChatRoomMessageEvent) {
        Log.d("--------------", "ChatRoomMessageEvent received ."+ event.getMessages().size);
        var msgs = event.getMessages();
        for (msg in msgs) {
            val content = msg.content as TextContent
            list?.add(MsgBean(msg.fromUser.nickname, content.text))
            pinglunAdapter!!.notifyItemRangeChanged(0, list!!.size)
        }
    }
}

