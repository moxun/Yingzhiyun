package com.yingzhiyun.yingquxue.OkBean;

public class CodeBean {

    /**
     * status : 200
     * hint : 手机号验证合法
     * result : {"code":"432757"}
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

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * code : 432757
         */

        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
