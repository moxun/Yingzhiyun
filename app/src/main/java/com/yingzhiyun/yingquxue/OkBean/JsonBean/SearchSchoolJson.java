package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class SearchSchoolJson {

    /**
     * app_user_id : 10
     * token : MjlkNmFmMDIyZjM0NDliZWI0MTRlZmFkOTk2ODExN2I=
     * key_word : 第一
     */

    private int app_user_id;
    private String token;
    private String key_word;
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

    public SearchSchoolJson(int app_user_id, String token, String key_word, String version, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.key_word = key_word;
        this.version = version;
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

    public String getKey_word() {
        return key_word;
    }

    public void setKey_word(String key_word) {
        this.key_word = key_word;
    }
}
