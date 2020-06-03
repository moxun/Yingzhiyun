package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class SubjectAdapterBean {
    private String title;
    private List<SubjectInfoBean.ResultBean.DetailBean>  list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectInfoBean.ResultBean.DetailBean> getList() {
        return list;
    }

    public void setList(List<SubjectInfoBean.ResultBean.DetailBean> list) {
        this.list = list;
    }

    public SubjectAdapterBean(String title, List<SubjectInfoBean.ResultBean.DetailBean> list) {
        this.title = title;
        this.list = list;
    }
}
