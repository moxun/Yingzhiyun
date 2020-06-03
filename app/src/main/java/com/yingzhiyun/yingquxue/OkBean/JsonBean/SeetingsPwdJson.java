package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class SeetingsPwdJson {

    /**
     * openid : openid
     * pwd : pwd
     */

    private String openid;
    private String pwd;
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

    public SeetingsPwdJson(String openid, String pwd, String version, String device) {
        this.openid = openid;
        this.pwd = pwd;
        this.version = version;
        this.device = device;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
