package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class TPLoginMatchesPhoneNumJson {

    /**
     * openid : openid
     * phoneNum : 12345678910
     */

    private String openid;
    private String phoneNum;
    private String version;
    private String device;

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

    public TPLoginMatchesPhoneNumJson(String openid, String phoneNum, String version, String device) {
        this.openid = openid;
        this.phoneNum = phoneNum;
        this.version = version;
        this.device = device;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
