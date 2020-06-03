package com.yingzhiyun.yingquxue.OkBean.JsonBean;

import cn.jpush.android.api.JPushInterface;

public class CodeLojinJson {
    private String phoneNum;

    private String code;

    private String jpushRegisterId;

    private String version;
    private String device;

    public CodeLojinJson(String phoneNum, String code, String jpushRegisterId, String version, String device) {
        this.phoneNum = phoneNum;
        this.code = code;
        this.jpushRegisterId = jpushRegisterId;
        this.version = version;
        this.device = device;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
