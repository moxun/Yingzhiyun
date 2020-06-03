package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class CoedeJsonBean {
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

    public CoedeJsonBean(String phoneNum, String version, String device) {
        this.phoneNum = phoneNum;
        this.version = version;
        this.device = device;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
