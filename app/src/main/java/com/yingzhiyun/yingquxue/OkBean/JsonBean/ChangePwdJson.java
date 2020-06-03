package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class ChangePwdJson {

    /**
     * app_user_id : 1
     * token : MzZjZDRiM2FkNmU1NDc4Zjg5NzM1MzdhNjVhMTdkMjI=
     * oldPwd : concat
     * newPwd : content
     */

    private int app_user_id;

    public ChangePwdJson(int app_user_id, String token, String oldPwd, String newPwd, String version, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.oldPwd = oldPwd;
        this.newPwd = newPwd;
        this.version = version;
        this.device = device;
    }

    private String token;
    private String oldPwd;
    private String newPwd;
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

    public int getApp_user_id() {
        return app_user_id;
    }

    public void setApp_user_id(int app_user_id) {
        this.app_user_id = app_user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
