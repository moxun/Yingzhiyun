package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class TestpagperinfoJson {

    public TestpagperinfoJson(int app_user_id, String token, String test_paper_id, String version, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.test_paper_id = test_paper_id;
        this.version = version;
        this.device = device;
    }

    /**
     * app_user_id : 1
     * token : token
     * test_paper_id : 2
     */

    private int app_user_id;
    private String token;
    private String test_paper_id;
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

    public String getTest_paper_id() {
        return test_paper_id;
    }

    public void setTest_paper_id(String test_paper_id) {
        this.test_paper_id = test_paper_id;
    }
}
