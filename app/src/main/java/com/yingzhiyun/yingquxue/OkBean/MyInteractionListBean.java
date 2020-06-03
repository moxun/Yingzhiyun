package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class MyInteractionListBean {


    /**
     * status : 200
     * hint : 查询成功
     * result : [{"addTime":"2019-08-19 19:00:00","contentString":"1+1等于3？","id":14}]
     */

    private int status;
    private String hint;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * addTime : 2019-08-19 19:00:00
         * contentString : 1+1等于3？
         * id : 14
         */

        private String addTime;
        private String contentString;
        private int id;

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getContentString() {
            return contentString;
        }

        public void setContentString(String contentString) {
            this.contentString = contentString;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
