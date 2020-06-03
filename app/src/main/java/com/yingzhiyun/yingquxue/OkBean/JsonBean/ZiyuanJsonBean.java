package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class ZiyuanJsonBean {

    /**
     * pageNum : 1
     * indexListTypeId : 32
     * app_user_id : 1
     * level : 0
     * subject : 0
     * token : token
     */

    private int pageNum;

    public ZiyuanJsonBean(int pageNum, int indexListTypeId, int app_user_id, int level, int subject, String token, String keyWord, String version, String device) {
        this.pageNum = pageNum;
        this.indexListTypeId = indexListTypeId;
        this.app_user_id = app_user_id;
        this.level = level;
        this.subject = subject;
        this.token = token;
        this.keyWord = keyWord;
        this.version = version;
        this.device = device;
    }

    public ZiyuanJsonBean(int pageNum, int indexListTypeId, int app_user_id, int level, int subject, String token, String version, String device) {
        this.pageNum = pageNum;
        this.indexListTypeId = indexListTypeId;
        this.app_user_id = app_user_id;
        this.level = level;
        this.subject = subject;
        this.token = token;
        this.version = version;
        this.device = device;
    }

    private int indexListTypeId;
    private int app_user_id;
    private int level;
    private int subject;
    private String token;
    private String keyWord;
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
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
