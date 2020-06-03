package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class CoursewareJson {
    public CoursewareJson(String version, String device, int teachingMaterialId, int type, int pageNum, int app_user_id, String token) {
        this.version = version;
        this.device = device;
        this.vip = teachingMaterialId;
        this.type = type;
        this.pageNum = pageNum;
        this.app_user_id = app_user_id;
        this.token = token;
    }

    public CoursewareJson(String version, String device, int teachingMaterialId, int type, int pageNum, int app_user_id, String token, String keyWord) {
        this.version = version;
        this.device = device;
        this.vip = teachingMaterialId;
        this.type = type;
        this.pageNum = pageNum;
        this.app_user_id = app_user_id;
        this.token = token;
        this.keyWord = keyWord;
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
    /**
     * teachingMaterialId : 66
     * type : 0
     * pageNum : 1
     * app_user_id : 1
     * token : token
     */

    private int vip;
    private int type;
    private int pageNum;
    private int app_user_id;
    private String token;
    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getTeachingMaterialId() {
        return vip;
    }

    public void setTeachingMaterialId(int teachingMaterialId) {
        this.vip = teachingMaterialId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
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
}
