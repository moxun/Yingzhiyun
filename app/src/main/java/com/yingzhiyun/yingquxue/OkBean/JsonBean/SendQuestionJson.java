package com.yingzhiyun.yingquxue.OkBean.JsonBean;

import java.io.File;
import java.util.List;

import okhttp3.RequestBody;

public class SendQuestionJson {


    public SendQuestionJson(String app_user_id, String token, String content, List<RequestBody> img, String version, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.content = content;
        this.img = img;
        this.version = version;
        this.device = device;
    }

    /**
     * app_user_id : 1
     * token : token
     * content : 巴拉巴拉巴拉吧
     * img : []
     */

    private String app_user_id;
    private String token;
    private String content;
    private List<RequestBody> img;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<RequestBody> getImg() {
        return img;
    }

    public void setImg(List<RequestBody> img) {
        this.img = img;
    }
}
