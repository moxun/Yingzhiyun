package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class WrongListJson {

    /**
     * app_user_id : 10
     * token : M2MyNDZkZjAyNDQzNGZjNWI5MGQ2MTg1ODAwNGUxYzE=
     * subject_id : 7
     */

    private int app_user_id;
    private String token;
    private int subject_id;
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

    public WrongListJson(int app_user_id, String token, int subject_id, String version, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.subject_id = subject_id;
        this.version = version;
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

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }
}
