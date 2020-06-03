package com.yingzhiyun.yingquxue.OkBean;

public class BashiinfoJson {


    /**
     * indexListTypeId : 40
     * app_user_id : 1
     * token : YTgyZDRhMzVhNWVhNGY3Nzk1ZDRlYTg4YjkwMDc3YzY=
     * device : iOS
     * version : 1.0.3
     */

    private int indexListTypeId;
    private int app_user_id;
    private String token;
    private String device;
    private String version;

    public int getIndexListTypeId() {
        return indexListTypeId;
    }

    public void setIndexListTypeId(int indexListTypeId) {
        this.indexListTypeId = indexListTypeId;
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

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public BashiinfoJson(int indexListTypeId, int app_user_id, String token, String device, String version) {
        this.indexListTypeId = indexListTypeId;
        this.app_user_id = app_user_id;
        this.token = token;
        this.device = device;
        this.version = version;
    }
}
