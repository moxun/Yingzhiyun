package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class MyCollectJson {

    /**
     * app_user_id : 10
     * token : Yjc2NmI2ZmJmZWEzNDMzNDlmNGRkYzZhMzJmMGZiOTI=
     * type : video
     */

    private int app_user_id;
    private String token;
    private String type;
    private String version;
    private String device;

    public MyCollectJson(int app_user_id, String token, String type, String version, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.type = type;
        this.version = version;
        this.device = device;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
