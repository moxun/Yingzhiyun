package com.yingzhiyun.yingquxue.OkBean.localbean;

public class FollowTeacherJson {

    /**
     * app_user_id : 1
     * teacherId : 1
     * token : ZTdiMjUwYzRiYTkzNGJhNDkzNzUzNGZkZTZmOGFiYzQ=
     */

    private int app_user_id;
    private int teacherId;
    private String token;

    public FollowTeacherJson(int app_user_id, int teacherId, String token) {
        this.app_user_id = app_user_id;
        this.teacherId = teacherId;
        this.token = token;
    }

    public int getApp_user_id() {
        return app_user_id;
    }

    public void setApp_user_id(int app_user_id) {
        this.app_user_id = app_user_id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
