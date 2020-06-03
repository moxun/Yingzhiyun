package com.yingzhiyun.yingquxue.OkBean.JsonBean;

public class SelectJsonBean {

    private int indexListTypeId;
    private int schoolTypeId;
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

    public SelectJsonBean(int indexListTypeId, int schoolTypeId, String version, String device) {
        this.indexListTypeId = indexListTypeId;
        this.schoolTypeId = schoolTypeId;
        this.version = version;
        this.device = device;
    }

    public int getIndexListTypeId() {
        return indexListTypeId;
    }

    public void setIndexListTypeId(int indexListTypeId) {
        this.indexListTypeId = indexListTypeId;
    }

    public int getSchoolTypeId() {
        return schoolTypeId;
    }

    public void setSchoolTypeId(int schoolTypeId) {
        this.schoolTypeId = schoolTypeId;
    }
}
