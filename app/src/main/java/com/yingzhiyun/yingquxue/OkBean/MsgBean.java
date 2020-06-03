package com.yingzhiyun.yingquxue.OkBean;

public class MsgBean {
    private String sender;
    private String msg;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MsgBean(String sender, String msg) {
        this.sender = sender;
        this.msg = msg;
    }
}
