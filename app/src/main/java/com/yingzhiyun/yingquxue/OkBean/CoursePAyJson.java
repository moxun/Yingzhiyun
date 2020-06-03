package com.yingzhiyun.yingquxue.OkBean;

public class CoursePAyJson {


    /**
     * app_user_id : 35
     * token : Zjg1ZGM4NzI2M2IzNGI4NDhiMmQwMWZjZTU1NDQ5NTc=
     * courseId : 8
     * device : Android
     */

    private String app_user_id;
    private String token;
    private int courseId;
    private String device;

    public CoursePAyJson(String app_user_id, String token, int courseId, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.courseId = courseId;
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
