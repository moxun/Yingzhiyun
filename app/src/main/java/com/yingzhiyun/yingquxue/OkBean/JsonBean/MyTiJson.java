package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class MyTiJson {

    public MyTiJson(int app_user_id, String token, int subject_id, int pageNum, String version, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.subject_id = subject_id;
        this.pageNum = pageNum;
        this.version = version;
        this.device = device;
    }

    /**
     * app_user_id : 10
     * token : YmI5OTIzMWY0MTdhNDIwMGE0MTY5ZDZlZjVkYjdlYjc=
     * subject_id : 6
     * pageNum : 1
     */

    private int app_user_id;
    private String token;
    private int subject_id;
    private int pageNum;
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

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
