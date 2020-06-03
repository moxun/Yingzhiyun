package com.yingzhiyun.yingquxue.httpUnits;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class PersistenceCookieJar implements CookieJar {

    List<Cookie> cache = new ArrayList<>();



    //Http发送请求前回调，Request中设置Cookie
    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        //过期的Cookie
        List<Cookie> invalidCookies = new ArrayList<>();
        //有效的Cookie
        List<Cookie> validCookies = new ArrayList<>();

        for (Cookie cookie : cache) {

            if (cookie.expiresAt() < System.currentTimeMillis()) {
                //判断是否过期

                invalidCookies.add(cookie);
            } else if (cookie.matches(url)) {
                //匹配Cookie对应url

                validCookies.add(cookie);
            }
        }

        //缓存中移除过期的Cookie
        cache.removeAll(invalidCookies);

        //返回List<Cookie>让Request进行设置
        return validCookies;
    }

    @Override
    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
        //内存中缓存Cookie

        cache.addAll(list);
    }
}

