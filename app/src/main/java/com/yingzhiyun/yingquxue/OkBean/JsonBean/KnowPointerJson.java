package com.yingzhiyun.yingquxue.OkBean.JsonBean;

import java.util.List;

public class KnowPointerJson
{

    /**
     * app_user_id : 1
     * token : token
     * book_id_list : [6]
     */

    private String app_user_id;
    private String token;
    private List<Integer> book_id_list;

    private String version;
    private String device;

    public KnowPointerJson(String app_user_id, String token, List<Integer> book_id_list, String version, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.book_id_list = book_id_list;
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

    public List<Integer> getBook_id_list() {
        return book_id_list;
    }

    public void setBook_id_list(List<Integer> book_id_list) {
        this.book_id_list = book_id_list;
    }
}
