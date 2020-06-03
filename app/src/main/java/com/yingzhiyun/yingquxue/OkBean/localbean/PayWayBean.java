package com.yingzhiyun.yingquxue.OkBean.localbean;

public class PayWayBean {
   private int payimage;
   private String payString;

    public PayWayBean(int payimage, String payString) {
        this.payimage = payimage;
        this.payString = payString;
    }

    public int getPayimage() {
        return payimage;
    }

    public void setPayimage(int payimage) {
        this.payimage = payimage;
    }

    public String getPayString() {
        return payString;
    }

    public void setPayString(String payString) {
        this.payString = payString;
    }
}
