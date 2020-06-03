package com.yingzhiyun.yingquxue.units

import android.app.Activity
import java.util.concurrent.CopyOnWriteArrayList

object ActivityCollector {
    private val activites= CopyOnWriteArrayList<Activity>()

    fun addActivity(activity:Activity){
        activites.add(activity)
    }

    fun removeActivity(activity:Activity){
        activites.remove(activity)
    }

    fun finishActivity(){
        for (activity in activites){
            if(!activity.isFinishing){
                activity.finish()
            }
            activites.clear()
        }
    }
}