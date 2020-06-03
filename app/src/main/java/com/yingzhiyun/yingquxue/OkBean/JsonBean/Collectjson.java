package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class Collectjson {

    /**
     * id : 2
     * type : video
     * app_user_id : 10
     * token : Yjc2NmI2ZmJmZWEzNDMzNDlmNGRkYzZhMzJmMGZiOTI=
     */

    private int id;
    private String type;
    private int app_user_id;
    private String token;

    private String version;
    private String device;

    public Collectjson(int id, String type, int app_user_id, String token, String version, String device) {
        this.id = id;
        this.type = type;
        this.app_user_id = app_user_id;
        this.token = token;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
