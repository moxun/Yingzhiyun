package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class VersionJson {

    /**
     * version : 1.0.0
     * device : iOS
     */

    private String version;
    private String device;

    public VersionJson(String version, String device) {
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
}
