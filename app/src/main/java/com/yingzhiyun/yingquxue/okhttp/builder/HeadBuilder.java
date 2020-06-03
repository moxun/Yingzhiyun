package com.yingzhiyun.yingquxue.okhttp.builder;


import com.yingzhiyun.yingquxue.okhttp.OkHttpUtils;
import com.yingzhiyun.yingquxue.okhttp.request.OtherRequest;
import com.yingzhiyun.yingquxue.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
