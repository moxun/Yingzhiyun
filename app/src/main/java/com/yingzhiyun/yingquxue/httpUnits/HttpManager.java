package com.yingzhiyun.yingquxue.httpUnits;




import android.util.Log;

import com.yingzhiyun.yingquxue.MyApp.MyApp;

import com.yingzhiyun.yingquxue.okhttp.cookie.CookieJarImpl;
import com.yingzhiyun.yingquxue.okhttp.cookie.store.PersistentCookieStore;
import com.yingzhiyun.yingquxue.units.SharedPreferenceUtils;
import com.yingzhiyun.yingquxue.units.SystemUtil;
import com.google.gson.Gson;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



/**
 * 项目名：MyProject
 * 包名：  com.liangxq.mydemo.http
 * 文件名：HttpManager
 * 创建者：liangxq
 * 创建时间：2018/12/21  8:50
 * 描述：TODO
 */
public class HttpManager {

    private static HttpManager httpManager;

    private HttpManager() {

    }

    public static HttpManager getInstance() {
        if (httpManager == null) {
            synchronized (HttpManager.class) {
                if (httpManager == null) {
                    httpManager = new HttpManager();
                }
            }
        }
        return httpManager;
    }



    public Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkhttpClient())
                .build();
    }
    public Retrofit getNoRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)

                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkhttpClient())

                .build();
    }
    public OkHttpClient getOkhttpClient() {


        //缓存文件定义：缓存到当前项目的包路径下
        Cache cache = new Cache(new File(MyApp.getMyApp().getCacheDir(), "Cache"), 1024 * 1024 * 10);
        //网络请求的Log日志输出

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        //缓存拦截器
        MyCacheinterceptor myCacheinterceptor = new MyCacheinterceptor();
        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetworkInterceptor())
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(myCacheinterceptor)
                .addInterceptor(new AddCookiesInterceptor())
                .addInterceptor(new ReceivedCookiesInterceptor())
                .addNetworkInterceptor(myCacheinterceptor)
                .cookieJar(new PersistenceCookieJar())
                //启用错误重连
                .retryOnConnectionFailure(true)
                .build();
    }





    //缓存拦截器
    private class MyCacheinterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //这里就是说判读我们的网络条件，要是有网络的话我么就直接获取网络上面的数据，要是没有网络的话我么就去缓存里面取数据
            if (!SystemUtil.isNetworkConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();

            }
            Response originalResponse = chain.proceed(request);
            if (SystemUtil.isNetworkConnected()) {
                int maxAge = 0;
                return originalResponse.newBuilder()
                        // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 7;
                return originalResponse.newBuilder()
                        // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Pragma")
                        //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }

        }
    }
    public class NetworkInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            int maxAge = 60;  //有效期

            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        }
    }
    /**
     *
     * @param baseUrl
     * @param tSClass
     * @param <S>
     * @return
     */


    public <S> S getServer(String baseUrl, Class<S> tSClass) {
        return getRetrofit(baseUrl).create(tSClass);
    }

    public <S> S getNoServer(String baseUrl, Class<S> tSClass) {
        return getNoRetrofit(baseUrl).create(tSClass);
    }
    /**
     * This interceptor put all the Cookies in Preferences in the Request.
     * Your implementation on how to get the Preferences MAY VARY.
     * <p>
     * Created by tsuharesu on 4/1/15.
     */
    public class AddCookiesInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            HashSet<String> preferences = SharedPreferenceUtils.getCookie();
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
                Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            }

            return chain.proceed(builder.build());
        }
    }


    /**
     * This Interceptor add all received Cookies to the app DefaultPreferences.
     * Your implementation on how to save the Cookies on the Preferences MAY VARY.
     * <p>
     * Created by tsuharesu on 4/1/15.
     */
    public class ReceivedCookiesInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());

            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                HashSet<String> cookies = new HashSet<>();

                for (String header : originalResponse.headers("Set-Cookie")) {
                    cookies.add(header);
                }

                SharedPreferenceUtils.setCookie(cookies);
            }

            return originalResponse;
        }
    }
}
