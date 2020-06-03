package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class getTPLoginBinPhoneJson {

    public getTPLoginBinPhoneJson(String openid, String phoneNum, String code, String version, String device) {
        this.openid = openid;
        this.phoneNum = phoneNum;
        this.code = code;
        this.version = version;
        this.device = device;
    }

    /**
     * openid : openid
     * phoneNum : 12345678910
     * code : 123456
     */

    private String openid;
    private String phoneNum;
    private String code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
