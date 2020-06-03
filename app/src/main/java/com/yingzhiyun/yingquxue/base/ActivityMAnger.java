package com.yingzhiyun.yingquxue.base;

import android.app.Activity;


import com.yingzhiyun.yingquxue.OkBean.daobean.HistoryHelper;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ActivityMAnger {
    private static ActivityMAnger sLoginHelper;
    private CopyOnWriteArrayList activites=new CopyOnWriteArrayList<Activity>();

    public static ActivityMAnger getInstance() {
        if (sLoginHelper == null) {
            synchronized (HistoryHelper.class) {
                if (sLoginHelper == null) {
                    sLoginHelper = new ActivityMAnger();
                }
            }
        }

        return sLoginHelper;
    }
    public void AddActivity(Activity activity){
        activites.add(activity);
    }

    public void RemoveActivity(Activity activity){
        activites.remove(activity);
    }

    public void finishAllActivity(){
        for (int i = 0; i < activites.size(); i++) {
            Activity o = (Activity) activites.get(i);
            if(!((Activity) activites.get(i)).isFinishing()){
                o.finish();
            }
        }
        activites.clear();
    }
}
