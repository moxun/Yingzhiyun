package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class PwdJson {

    /**
     * phoneNum : 17611559382
     * password : 123456
     */

    private String phoneNum;
    private String password;
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

    public PwdJson(String phoneNum, String password, String jpushRegisterId, String version, String device) {
        this.phoneNum = phoneNum;
        this.password = password;
        this.jpushRegisterId = jpushRegisterId;
        this.version = version;
        this.device = device;
    }
}
