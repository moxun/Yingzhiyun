package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class PlayVideoJson {

    /**
     * app_user_id : 1
     * token : YjdiYjgyNzRkMTNjNGQ4YzllMjI4NmExMGY0NTJlYmE=
     * id : 251
     */

    private int app_user_id;
    private String token;
    private int id;

    public PlayVideoJson(int app_user_id, String token, int id) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
