package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class TPLoginJson {

    /**
     * openid : openid
     */

    private String openid;

    private String jpushRegisterId;
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

    public TPLoginJson(String openid, String jpushRegisterId, String version, String device) {
        this.openid = openid;
        this.jpushRegisterId = jpushRegisterId;
        this.version = version;
        this.device = device;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
