package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class ChapterListJson {


    public ChapterListJson(String app_user_id, String token, String book_id, String version, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.book_id = book_id;
        this.version = version;
        this.device = device;
    }

    /**
     * app_user_id : 1
     * token : token
     * book_id : 1
     */

    private String app_user_id;
    private String token;
    private String book_id;
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
    public String getApp_user_id() {
        return app_user_id;
    }

    public void setApp_user_id(String app_user_id) {
        this.app_user_id = app_user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }
}
