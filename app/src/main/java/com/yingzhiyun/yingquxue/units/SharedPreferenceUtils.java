package com.yingzhiyun.yingquxue.units;

import android.content.Context;
import android.content.SharedPreferences;

import com.yingzhiyun.yingquxue.MyApp.MyApp;

import java.util.HashSet;
import java.util.Set;


public class SharedPreferenceUtils {
    public static SharedPreferences getAppSp() {
        return MyApp.getMyApp().getSharedPreferences("Yingzhiyun", Context.MODE_PRIVATE);
    }

    public static String getToken() {
        return getAppSp().getString("token", "");
    }

    public static void setToken(String token) {
        getAppSp().edit().putString("token", token).apply();
    }
    public static String getFilepath() {
        return getAppSp().getString("filepath", "");
    }

    public static void setFilepath(String token) {
        getAppSp().edit().putString("filepath", token).apply();
    }
    public static boolean getisLogin() {
        return getAppSp().getBoolean("is", false);
    }

    public static void setisLogin(boolean islogin) {
        getAppSp().edit().putBoolean("is", islogin).apply();
    }
    public static String getteachetid() {
        return getAppSp().getString("teacherid", "");
    }

    public static void setteacherid(String teacherid) {
        getAppSp().edit().putString("teacherid", teacherid).apply();
    }
    public static int getsubject_id() {
        return getAppSp().getInt("subject_id", 0);
    }

    public static void setsubject_id(int type) {
        getAppSp().edit().putInt("subject_id", type).apply();
    }
    public static int getgroup_id() {
        return getAppSp().getInt("group", 0);
    }

    public static void setgroup_id(int type) {
        getAppSp().edit().putInt("group", type).apply();
    }
    public static int getusercont() {
        return getAppSp().getInt("cont", 0);
    }

    public static void setusercont(int type) {
        getAppSp().edit().putInt("cont", type).apply();
    }

    public static void setusername(String name) {
        getAppSp().edit().putString("username", name).apply();
    }
    public static String getusername() {
        return getAppSp().getString("username","");
    }

    public static void setuserhead(String psd) {
        getAppSp().edit().putString("userhead", psd).apply();
    }
    public static String getuserhead() {
        return getAppSp().getString("userhead","");
    }


    public static void setUserid(int userid) {
        {
            getAppSp().edit().putInt("userid", userid).apply();
        }
    }

    public static int getUserid() {
        {
          return   getAppSp().getInt("userid",0 );
        }
    }

    public static void setSchool(String psd) {
        getAppSp().edit().putString("school", psd).apply();
    }
    public static String getSchool() {
        return getAppSp().getString("school","暂无");
    }

    public static void setClazz(String psd) {
        getAppSp().edit().putString("class", psd).apply();
    }
    public static String getClazz() {
        return getAppSp().getString("class","暂无");
    }

    public static void setHuancun(String psd) {
        getAppSp().edit().putString("Huancun", psd).apply();
    }
    public static String getHuancun() {
        return getAppSp().getString("Huancun","");
    }

    public static void setprepayid(String psd) {
        getAppSp().edit().putString("prepayid", psd).apply();
    }
    public static String getprepayid() {
        return getAppSp().getString("prepayid","");
    }


    public static void setCookie(HashSet<String> psd) {
        getAppSp().edit().putStringSet("cokkie", psd).apply();
    }
    public static HashSet<String> getCookie() {
        return (HashSet<String>) getAppSp().getStringSet("cokkie",new HashSet<String>());
    }
}
