package com.yingzhiyun.yingquxue.Fragment.zhibo


import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.inputmethod.EditorInfo.IME_ACTION_SEND
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.jpush.im.android.api.ChatRoomManager
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.callback.RequestCallback
import cn.jpush.im.android.api.content.TextContent
import cn.jpush.im.android.api.enums.ContentType
import cn.jpush.im.android.api.event.ChatRoomMessageEvent
import cn.jpush.im.android.api.event.MessageEvent
import cn.jpush.im.android.api.event.OfflineMessageEvent
import cn.jpush.im.android.api.model.Conversation
import cn.jpush.im.android.api.model.UserInfo
import cn.jpush.im.api.BasicCallback
import com.chad.library.adapter.base.BaseQuickAdapter
import com.tencent.imsdk.TIMConversation
import com.tencent.imsdk.TIMMessage
import com.tencent.imsdk.TIMTextElem
import com.tencent.imsdk.TIMValueCallBack
import com.yingzhiyun.yingquxue.OkBean.MsgBean
import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.adapter.PinglunAdapter
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment
import com.yingzhiyun.yingquxue.units.KeyboardUtil
import com.yingzhiyun.yingquxue.units.ToastUtil
import kotlinx.android.synthetic.main.fragment_danmu.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DanmuFragment : SimpleFragment(), KeyboardUtil.OnSoftKeyboardChangeListener {
    private var handlerPosition = 0

    var conversation: TIMConversation? = null
    private var mOnGlobalLayoutListener: OnGlobalLayoutListener? = null
    var pinglunAdapter: PinglunAdapter? = null
    var list: ArrayList<MsgBean>? = null
    var groupConversation: Conversation? = null;
    var myInfo: UserInfo? = null
    var string:String=""
    var linearLayoutManager:LinearLayoutManager?=null
    override fun initData() {
        myInfo = JMessageClient.getMyInfo()
         string = arguments!!.getString("roomId")
        mOnGlobalLayoutListener = KeyboardUtil.observeSoftKeyboard(mActivity, this);
        list = ArrayList<MsgBean>()
        groupConversation = JMessageClient.getGroupConversation(43630364)
        JMessageClient.registerEventReceiver(this)
         linearLayoutManager = LinearLayoutManager(mActivity)
        recy_pinglun.layoutManager = linearLayoutManager
        pinglunAdapter = PinglunAdapter(list)

        recy_pinglun.adapter = pinglunAdapter
// 进入聊天室
        ChatRoomManager.enterChatRoom(string.toLong(), object : RequestCallback<Conversation>() {
            override fun gotResult(p0: Int, p1: String?, p2: Conversation?) {

            Log.d("------------",p1)        }


        });



        btn_send.setOnClickListener {

            sendJMessage()
            hideKeyboard(ed_pl)
        }
//滑动后复位
        //滑动后复位
        recy_pinglun.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (mShouldScroll) {
                    mShouldScroll = false
                    smoothMoveToPosition(recyclerView, mToPosition)
                }
            }
        })
        ed_pl.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == IME_ACTION_SEND) {
                    sendJMessage()
                }
                hideKeyboard(ed_pl)
                return false
            }

        })

//设置消息监听器，收到新消息时，通过此监听器回调
        //设置消息监听器，收到新消息时，通过此监听器回调
//        TIMManager.getInstance().addMessageListener {
//            if (it != null) {
//                for (msg in it){
//                    val sender = msg.sender;
//                    Log.d("------------",""+msg.elementCount)
//                    if(msg.elementCount==0){
//
//                    }else{
//                        for (i in 0..msg.elementCount){
//                            var elem=  msg.getElement(i)
//                            val type = elem.type
//                            if (type == TIMElemType.Text) {
//                                var textmlem=elem as TIMTextElem
//                                val text = textmlem.text
//                                list!!.add(MsgBean(sender,text))
//                                pinglunAdapter!!.notifyItemRangeChanged(0, list!!.size)
//                            }
//                        }
//                    }
//
//
//                }
//            }
//
//            true //返回true将终止回调链，不再调用下一个新消息监听器
//        }
    }

    //腾讯IM发送消息
    private fun sendmessage() {


        var msg = TIMMessage();


        var elem = TIMTextElem();
        elem.setText(ed_pl.text.toString());


        //发送消息
        conversation!!.sendMessage(msg, object : TIMValueCallBack<TIMMessage> {
            override fun onSuccess(p0: TIMMessage?) {
                list!!.add(MsgBean(p0!!.sender, elem.text))
                Log.d("---------------", "send ");
                smoothMoveToPosition(recy_pinglun, handlerPosition)
                pinglunAdapter!!.notifyItemRangeChanged(0, list!!.size)
                ed_pl.setText("")
            }

            override fun onError(p0: Int, p1: String?) {
                Log.d("---------------", "send failed. code: " + p0 + " errmsg: " + p1);
            }//发送消息回调

        });
        handlerPosition++;

    }

    override fun onDestroy() {
        super.onDestroy()
        KeyboardUtil.removeSoftKeyboardObserver(mActivity, mOnGlobalLayoutListener)
        JMessageClient.unRegisterEventReceiver(this)
        // 离开聊天室
        // 离开聊天室
        ChatRoomManager.leaveChatRoom(string.toLong(), object : BasicCallback() {
            override fun gotResult(responseCode: Int, responseMessage: String) {
                Log.d("---------",responseMessage)
            }
        })
    }

    fun hideKeyboard(view: View) {
        var imm = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    override fun createLayoutId(): Int {
        return R.layout.fragment_danmu
    }

    /**
     * 目标项是否在最后一个可见项之后
     */
    private var mShouldScroll = false
    /**
     * 记录目标项位置
     */
    private var mToPosition = 0

    /**
     * 滑动到指定位置
     *
     * @param mRecyclerView
     * @param position
     */
    private fun smoothMoveToPosition(mRecyclerView: RecyclerView, position: Int) { // 第一个可见位置
        val firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0))
        // 最后一个可见位置
        val lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.childCount - 1))
        println("smoothMoveToPosition--$lastItem")
        if (position < firstItem) {
            println("smoothMoveToPosition-11-$lastItem")
            // 如果跳转位置在第一个可见位置之前，就smoothScrollToPosition可以直接跳转
            mRecyclerView.smoothScrollToPosition(position)
        } else if (position <= lastItem) { // 跳转位置在第一个可见项之后，最后一个可见项之前
// smoothScrollToPosition根本不会动，此时调用smoothScrollBy来滑动到指定位置
            val movePosition = position - firstItem
            if (movePosition >= 0 && movePosition < mRecyclerView.childCount) {
                val top = mRecyclerView.getChildAt(movePosition).top
                mRecyclerView.smoothScrollBy(0, top)
            }
        } else { // 如果要跳转的位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
// 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
            println("smoothMoveToPosition-33-$position")
            mRecyclerView.smoothScrollToPosition(position)
            mToPosition = position
            mShouldScroll = true
        }
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


    override fun onSoftKeyBoardChange(softKeyboardHeight: Int, visible: Boolean) {

        var isKeyboardUtil = visible
//        if (isKeyboardUtil) {
//            btn_send.visibility = View.VISIBLE
//        } else {
//            btn_send.visibility = View.GONE
//        }

    }

    fun sendJMessage() {
        // 发送聊天室消息
        var conv = JMessageClient.getChatRoomConversation(string.toLong());
        if (null == conv) {
            conv = Conversation.createChatRoomConversation(string.toLong());
        }

        var text = ed_pl.text.toString();
        if(text.equals("")){
            ToastUtil.makeLongText(mActivity,"请输入聊天内容")
            return
        }
        val msg = conv.createSendTextMessage(text);//实际聊天室可以支持所有类型的消息发送，demo为了简便，仅仅实现了文本类型的消息发送，其他的消息类型参考消息发送相关文档
        msg.setOnSendCompleteCallback(object : BasicCallback() {
            override fun gotResult(p0: Int, p1: String?) {

                if (0 == p0) {

                    list?.add(MsgBean(myInfo!!.nickname, text))
                   MoveToPosition(linearLayoutManager!!,recy_pinglun,list!!.size-1)
                    ed_pl.setText("")
                    pinglunAdapter!!.notifyItemRangeChanged(0, list!!.size)
                } else {
                    Log.d("==========",p1)
                    ToastUtil.makeLongText(mActivity, "消息發送失敗")
                }
            }

        });
        JMessageClient.sendMessage(msg);//使用默认控制参数发送消息
    }

    // 接收聊天室消息
    fun onEventMainThread(event: ChatRoomMessageEvent) {
        Log.d("tag", "ChatRoomMessageEvent received .");
        var msgs = event.getMessages();
        for (msg in msgs) {
            val content = msg.content as TextContent
            list?.add(MsgBean(msg.fromUser.nickname, content.text))
            pinglunAdapter!!.notifyItemRangeChanged(0, list!!.size)
        }
    }

    /**
     * 接收离线消息。
     * 类似MessageEvent事件的接收，上层在需要的地方增加OfflineMessageEvent事件的接收
     * 即可实现离线消息的接收。
     **/
    fun onEvent(event: OfflineMessageEvent) {
        val conversation1 = event.conversation
        val allMessage = conversation1.allMessage

    }

    /**
     * #################    处理消息事件    #################
     */
    fun onEvent(event: MessageEvent) {
        val msg = event.message
        val content = msg.content
        when (msg.contentType) {
            ContentType.text -> {
                val textContent = content as TextContent
                val str = textContent.text
                val fromUser = msg.fromUser.userName

                mActivity.runOnUiThread(Runnable {
                    list?.add(MsgBean(fromUser, str))
                    pinglunAdapter?.notifyDataSetChanged()
                })
            }
        }
    }
}
