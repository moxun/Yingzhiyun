package com.yingzhiyun.yingquxue.OkBean;

public class CourseinfoJson {


    public CourseinfoJson(int app_user_id, int courseId, String token, String version, String device) {
        this.app_user_id = app_user_id;
        this.courseId = courseId;
        this.token = token;
        this.version = version;
        this.device = device;
    }

    /**
     * appUserId : 1
     * courseId : 1
     */

    private int app_user_id;
    private int courseId;
    private String token;
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



    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
