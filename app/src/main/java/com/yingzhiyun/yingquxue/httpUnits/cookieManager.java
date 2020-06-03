package com.yingzhiyun.yingquxue.httpUnits;

import android.content.Context;

import com.yingzhiyun.yingquxue.units.PersistentCookieStore;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class cookieManager implements CookieJar {
    Context context;
    private PersistentCookieStore cookieStore ;
    public cookieManager(Context context) {
        // TODO Auto-generated constructor stub
        this.context=context;
        cookieStore= new PersistentCookieStore(context);
    }
    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        // TODO Auto-generated method stub
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

}