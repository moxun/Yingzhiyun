package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class CollectionTiJson {
    private String version;
    private String device;

    public CollectionTiJson(String version, String device, String app_user_id, String token, String subject_id, String item_bank_id) {
        this.version = version;
        this.device = device;
        this.app_user_id = app_user_id;
        this.token = token;
        this.subject_id = subject_id;
        this.item_bank_id = item_bank_id;
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

    /**
     * app_user_id : 8
     * token : ODQ5OWJkY2I4Y2U1NDFlOWFmNDc4NzhkNGRhYjcwNDU=
     * subject_id : 7
     * item_bank_id : 38
     */

    private String app_user_id;
    private String token;
    private String subject_id;
    private String item_bank_id;

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

    public String getItem_bank_id() {
        return item_bank_id;
    }

    public void setItem_bank_id(String item_bank_id) {
        this.item_bank_id = item_bank_id;
    }
}
