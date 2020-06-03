package com.yingzhiyun.yingquxue.OkBean.localbean;

public class UserinAdapterBean {
    private String title;
    private String name;

    public UserinAdapterBean(String title, String name) {

        this.title = title;
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
