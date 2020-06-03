package com.yingzhiyun.yingquxue.OkBean.JsonBean;

import java.util.List;

public class TeachingShaiJson {

    private List<String> items;

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public TeachingShaiJson(List<String> items) {
        this.items = items;
    }
}
