package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class UpdatepassJson {
    private String phoneNum;
    private String newPassword;
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

    public UpdatepassJson(String phoneNum, String newPassword, String code, String version, String device) {
        this.phoneNum = phoneNum;
        this.newPassword = newPassword;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
