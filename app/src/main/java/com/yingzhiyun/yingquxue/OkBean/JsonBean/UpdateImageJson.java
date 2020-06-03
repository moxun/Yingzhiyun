package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class UpdateImageJson {

    /**
     * app_user_id : 1
     * token : NzY1ZGI1ODllODI2NDczMGJmZGIxNmVjY2Y2NjAxMzc=
     * img : {}
     */

    private String app_user_id;
    private String token;
    private ImgBean img;
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

    public ImgBean getImg() {
        return img;
    }

    public void setImg(ImgBean img) {
        this.img = img;
    }

    public static class ImgBean {
    }
}
