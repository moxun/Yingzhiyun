package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class KnowledgeJson {

    /**
     * app_user_id : 10
     * token : MzcyOGI0ODE4ZTdkNDE0NjhmMmU2ZWFlZmZhODJmMzM=
     * subject_id : 6
     */

    private String app_user_id;
    private String token;
    private String subject_id;
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

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public KnowledgeJson(String app_user_id, String token, String subject_id, String version, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.subject_id = subject_id;
        this.version = version;
        this.device = device;
    }
}
