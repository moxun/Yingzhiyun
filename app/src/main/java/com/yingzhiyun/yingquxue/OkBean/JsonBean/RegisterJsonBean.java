package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class RegisterJsonBean {

    private String phoneNum;
    private String password;
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

    public RegisterJsonBean(String phoneNum, String password, String code, String version, String device) {
        this.phoneNum = phoneNum;
        this.password = password;
        this.code = code;
        this.version = version;
        this.device = device;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
