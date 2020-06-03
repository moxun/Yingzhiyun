package com.yingzhiyun.yingquxue.OkBean;

public class WxPayJson {


    public WxPayJson(String app_user_id, String token, int courseId) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.courseId = courseId;
    }
    public WxPayJson(int betid,String app_user_id, String token) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.betId = betid;
    }
    /**
     * app_user_id : 1
     * token : YjQ3MDI1NTkwOTU1NGYzYTlkNDhlMzljNTFhMzMxZDM=
     * courseId : 8
     */

    private String app_user_id;
    private String token;
    private int courseId;
    private int betId;
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
}
