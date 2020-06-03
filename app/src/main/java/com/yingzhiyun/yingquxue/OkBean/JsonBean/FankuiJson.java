package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class FankuiJson
{

    /**
     * app_user_id : 1
     * token : MzZjZDRiM2FkNmU1NDc4Zjg5NzM1MzdhNjVhMTdkMjI=
     * concat : concat
     * content : content
     */

    private int app_user_id;
    private String token;

    private String version;
    private String device;

    public FankuiJson(int app_user_id, String token, String version, String device, String concat, String content) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.version = version;
        this.device = device;
        this.concat = concat;
        this.content = content;
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

    private String concat;
    private String content;

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

    public String getConcat() {
        return concat;
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
