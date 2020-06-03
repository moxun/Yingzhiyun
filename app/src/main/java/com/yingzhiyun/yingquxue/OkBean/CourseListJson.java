package com.yingzhiyun.yingquxue.OkBean;

public class CourseListJson {

    /**
     * subjectId : 6
     * price : 63.14
     * grade : 12
     * title : 标题
     * appUserId : 1
     */

    private int subjectId;

    private String grade;
    private String title;
    private int app_user_id;
    private int page;
    private String version;
    private String device;
    private int vip;


    public int getApp_user_id() {
        return app_user_id;
    }

    public void setApp_user_id(int app_user_id) {
        this.app_user_id = app_user_id;
    }

    public int isVip() {

        return vip;
    }

    public void setVip(int vip) {
        vip = vip;
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
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setDevice(String device) {
        this.device = device;
    }
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }





    public CourseListJson(int subjectId, String grade, int app_user_id, int page, String version, String device, String token,int vip) {
        this.subjectId = subjectId;
        this.grade = grade;
        this.app_user_id = app_user_id;
        this.page = page;
        this.version = version;
        this.device = device;
        this.token = token;
        this.vip=vip;
    }




    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAppUserId() {
        return app_user_id;
    }

    public void setAppUserId(int appUserId) {
        this.app_user_id = appUserId;
    }
}
