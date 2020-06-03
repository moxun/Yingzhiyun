package com.yingzhiyun.yingquxue.okhttp.callback;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.yingzhiyun.yingquxue.MyApp.MyApp;
import com.yingzhiyun.yingquxue.activity.login.PwdLoginActivity;
import com.yingzhiyun.yingquxue.activity.pay.RechargeActivity;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by LG on 2017/3/26.
 */

public class ResultModelCallback<T> extends StringCallback {

    private ResponseCallBack callBack;
    private Context mContext;
    private String strCode;
    private boolean isLoadRefresh = true;

    public ResultModelCallback(Context context, ResponseCallBack callBack) {
        this.mContext = context;
        this.callBack = callBack;
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        e.printStackTrace();
        call.cancel();
        callBack.onError("");
        /*if(id==404){
         EventBus.getDefault().
        }else{*/
        //callBack.onError(e.getMessage());
        //}


    }

    @Override
    public void onResponse(String response, int id) {
        try {
            if (response != null) {
                Log.d("c", "onResponse: "+response);
                JSONObject dataJson = new JSONObject(response);
                String code = null;
                String message = null;
                //Log.e("测试数据",response);
                if (dataJson.has("status")) {
                    code = dataJson.getString("status");
                }

                if (dataJson.has("hint")) {
                    message = dataJson.getString("hint");
                }
                //读取服务器的状态码，由于接口返回实体类不一样，只能伪装成code成功的样子。
                if (dataJson.has("ret")) {
                    code = dataJson.getString("ret");
                }

                if (code == null) {
                    Type finalNeedType;
                    //获取到泛型的对象类型
                    final Type[] types = callBack.getClass().getGenericInterfaces();
                    if (MethodHandler(types) == null || MethodHandler(types).size() == 0) {
                        finalNeedType = null;
                    }

                    finalNeedType = MethodHandler(types).get(0);

                    if (new Gson().fromJson(response, finalNeedType) == null) {
                        throw new NullPointerException();
                    }

                    T modelT = new Gson().fromJson(response, finalNeedType);
                    callBack.onResponse(modelT);

                } else {
                    if (code.equals("200")) {
                        Type finalNeedType;
                        //获取到泛型的对象类型
                        final Type[] types = callBack.getClass().getGenericInterfaces();
                        if (MethodHandler(types) == null || MethodHandler(types).size() == 0) {
                            finalNeedType = null;
                        }

                        finalNeedType = MethodHandler(types).get(0);

                        if (new Gson().fromJson(response, finalNeedType) == null) {
                            throw new NullPointerException();
                        }

                        T modelT = new Gson().fromJson(response, finalNeedType);

                        callBack.onResponse(modelT);

                    }/*else if(code.equals("401")){
                     *//*if(isLoadRefresh&&(boolean)SharedPreferencesUtils.getInstance().getData("islogin", true)){
                       requestUserLogin();
                       isLoadRefresh=false;
                   }*//*
               }*/ else {
                        if (message != null && message != "") {
                            getErrorCode(code);
                            return;
                        }
//                        callBack.onError(message);
                    }
                }

            } else {


                //callBack.onError("");
            }

        } catch (Exception e) {
            e.printStackTrace();
            callBack.onError("网络异常,请稍后重试！");
        }
    }

    public void getErrorCode(String strCode) {
        switch (strCode) {
            case "517":
                callBack.onError("请勿重复报名");
                break;
            case "518":
                callBack.onError("您没有报名该课程哦~");
                break;
            case "519":
                callBack.onError("课程还未开始");
                break;
            case "520":
                callBack.onError("充值失败请稍后重试");
                break;
            case "521":
                mContext.startActivity(new Intent(mContext, RechargeActivity.class));
                callBack.onError("钱包余额不足");
                break;
            case "522":
                callBack.onError("交易失败，请稍后重试");
                break;
            case "523":
                callBack.onError("微信接口出错，请稍后重试");
                break;
            case "524":
                callBack.onError("微信支付失败请稍后重试");
                break;
            case "526":
                callBack.onError("报名已截止");
                break;
            case "529":
                callBack.onError("押题卷尚未失败");
                break;
            case "511":
                mContext.startActivity(new Intent(mContext, PwdLoginActivity.class));
                callBack.onError("您的账号在别处登录，请重新登录");
                break;
            case "532":
                callBack.onError("兑换券失效或者已使用");
                break;
            case "1012":
                callBack.onError("不是商家,不能发布信息");
                break;
            case "1013":
                callBack.onError("C2C账户被冻结,无法交易");
                break;
            case "1014":
                callBack.onError("请求参数不正确");
                break;
            case "1015":
                callBack.onError("请求信息不正确");
                break;
            case "1019":
                callBack.onError("剩余数量不足");
                break;
            case "1021":
                callBack.onError("已存在申诉订单,不能重复提交");
                break;
            case "1023":
                callBack.onError("没有符合的收款方式，请绑定更多的收款方式");
                break;
            default:
                callBack.onError("失败");
                break;

        }
    }



    /**
     * 得到泛型类型
     * MethodHandler
     */
    private static List<Type> MethodHandler(Type[] types) {

        List<Type> needtypes = new ArrayList<>();
        Type needParentType = null;
        for (Type paramType : types) {
            System.out.println("  " + paramType);

            if (paramType instanceof ParameterizedType) {
                Type[] parentypes = ((ParameterizedType) paramType).getActualTypeArguments();
                for (Type childtype : parentypes) {
                    needtypes.add(childtype);
                    if (childtype instanceof ParameterizedType) {
                        Type[] childtypes = ((ParameterizedType) childtype).getActualTypeArguments();
                        for (Type type : childtypes) {
                            needtypes.add(type);
                        }
                    }
                }
            }
        }
        return needtypes;
    }

}
