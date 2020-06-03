package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class DeleteWrongJson {

    /**
     * app_user_id : 10
     * token : YmI5OTIzMWY0MTdhNDIwMGE0MTY5ZDZlZjVkYjdlYjc=
     * wrong_id : 215
     */

    private int app_user_id;

    public DeleteWrongJson(int app_user_id, String version, String device, String token, int wrong_id) {
        this.app_user_id = app_user_id;
        this.version = version;
        this.device = device;
        this.token = token;
        this.wrong_id = wrong_id;
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

    private String token;
    private int wrong_id;

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

    public int getWrong_id() {
        return wrong_id;
    }

    public void setWrong_id(int wrong_id) {
        this.wrong_id = wrong_id;
    }
}
