package com.yingzhiyun.yingquxue.OkBean;

public class SdVideoBean {
    private String title;
    private String sdpath;

    public SdVideoBean(String title, String sdpath) {
        this.title = title;
        this.sdpath = sdpath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSdpath() {
        return sdpath;
    }

    public void setSdpath(String sdpath) {
        this.sdpath = sdpath;
    }
}
