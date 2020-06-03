package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class YueChongzhiJson
{

    public YueChongzhiJson(String app_user_id, String token, int number) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.number = number;
    }

    /**
     * app_user_id : 1
     * token : YjQ3MDI1NTkwOTU1NGYzYTlkNDhlMzljNTFhMzMxZDM=
     * number : 6
     */

    private String app_user_id;
    private String token;
    private int number;

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
