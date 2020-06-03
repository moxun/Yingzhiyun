package com.yingzhiyun.yingquxue.Fragment.zhibo


import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tencent.bugly.Bugly.applicationContext

import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.base.fragment.SimpleFragment
import com.yingzhiyun.yingquxue.units.UrlImageUnnits
import kotlinx.android.synthetic.main.activity_alive_status.*
import kotlinx.android.synthetic.main.fragment_aliveinfo.*
import kotlinx.android.synthetic.main.fragment_aliveinfo.recy_info



/**
 * A simple [Fragment] subclass.
 *
 */
class AliveinfoFragment : SimpleFragment() {
    override fun initData() {
        val string = arguments?.getString("img");
        recy_info.text=Html.fromHtml(string, UrlImageUnnits(recy_info, mActivity.application), null)
    }



    override fun createLayoutId(): Int {
        return R.layout.fragment_aliveinfo
    }


}
