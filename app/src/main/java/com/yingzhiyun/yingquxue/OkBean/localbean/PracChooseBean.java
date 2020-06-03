package com.yingzhiyun.yingquxue.OkBean.localbean;

public class PracChooseBean {
    private String keys;
    private boolean isChoose;

    public PracChooseBean(String keys, boolean isChoose) {
        this.keys = keys;
        this.isChoose = isChoose;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }
}
