package com.yingzhiyun.yingquxue.OkBean;

public class UpdatePassBean {

    /**
     * status : 200
     * hint : 更改密码成功，请前往登录
     * result : null
     */

    private int status;
    private String hint;
    private Object result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
