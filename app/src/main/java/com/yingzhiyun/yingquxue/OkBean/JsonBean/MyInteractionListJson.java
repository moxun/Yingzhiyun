package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class MyInteractionListJson {

    public MyInteractionListJson(String app_user_id, String token, String answer_status, String version, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.answer_status = answer_status;
        this.version = version;
        this.device = device;
    }

    /**
     * app_user_id : 10
     * token : YmI5OTIzMWY0MTdhNDIwMGE0MTY5ZDZlZjVkYjdlYjc=
     * answer_status : 2
     */

    private String app_user_id;
    private String token;
    private String answer_status;
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

    public String getAnswer_status() {
        return answer_status;
    }

    public void setAnswer_status(String answer_status) {
        this.answer_status = answer_status;
    }
}
