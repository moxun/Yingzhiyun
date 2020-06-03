package com.yingzhiyun.yingquxue.OkBean;

public class TeacherinfoJson {


    public TeacherinfoJson(int app_user_id, int teacherId, String version, String device, String token) {
        this.app_user_id = app_user_id;
        this.teacherId = teacherId;
        this.version = version;
        this.device = device;
        this.token = token;
    }

    /**
     * appUserId : 1
     * teacherId : 1
     */

    private int app_user_id;
    private int teacherId;
    private String version;
    private String device;
    private String token;

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
    public int getAppUserId() {
        return app_user_id;
    }

    public void setAppUserId(int appUserId) {
        this.app_user_id = appUserId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
