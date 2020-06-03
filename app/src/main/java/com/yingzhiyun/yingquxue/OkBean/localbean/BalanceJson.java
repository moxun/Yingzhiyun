package com.yingzhiyun.yingquxue.OkBean.localbean;

public class BalanceJson {

    /**
     * app_user_id : 35
     * token : NmUxNGQ2OGJlMDZkNDViYmJkZjA5OTI2YTJiOTNkYWE=
     * device : Android
     */

    private int app_user_id;
    private String token;
    private String device;

    public int getApp_user_id() {
        return app_user_id;
    }

    public BalanceJson(int app_user_id, String token, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.device = device;
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

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
