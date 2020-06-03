package com.yingzhiyun.yingquxue.OkBean;

public class Searchcontentjson {

    /**
     * app_user_id : 1
     * token : token
     * pageNum : 2
     * keyWord : 英语
     */

    private int app_user_id;
    private String token;
    private int pageNum;
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

    public Searchcontentjson(int app_user_id, String token, int pageNum, String version, String device, String keyWord) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.pageNum = pageNum;
        this.version = version;
        this.device = device;
        this.keyWord = keyWord;
    }

    private String keyWord;

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

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
