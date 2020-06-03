package com.yingzhiyun.yingquxue.OkBean.JsonBean;

import java.util.List;

public class ShaixuanJson {
    private List<String> items;
    private int weight;
    public List<String> getItems() {
        return items;
    }

    public ShaixuanJson(List<String> items, int weight) {
        this.items = items;
        this.weight = weight;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

}
