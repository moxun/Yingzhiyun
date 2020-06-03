package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class HodongInfoJson {




    public HodongInfoJson(int app_user_id, String token, int id, String version, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.id = id;
        this.version = version;
        this.device = device;
    }

    /**
     * app_user_id : 1
     * token : token
     * id : 4
     */

    private int app_user_id;
    private String token;
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
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
}
