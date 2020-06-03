package com.yingzhiyun.yingquxue.activity.homepagr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yingzhiyun.yingquxue.R
import com.yingzhiyun.yingquxue.base.activity.SimpleActivity

class CollegeActivity : SimpleActivity() {
    override fun choseeClor(): Int {
        return  R.color.white
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_college)
    }

    override fun initData() {

    }

    override fun createLayoutID(): Int {
        return R.layout.activity_college
    }
}
