package com.yingzhiyun.yingquxue.OkBean.JsonBean;

import java.util.List;

public class ZuJuanWrong {

    /**
     * app_user_id : 1
     * token : token
     * test_paper_id : 6
     * subject_id : 6
     * total_time : total_time
     * detail : [{"item_bank_id":"30","item_th":"0","test_paper_th":"2","key":"A,B"},{"item_bank_id":"31","item_th":"4","test_paper_th":"3","key":"C"}]
     */

    private String app_user_id;
    private String token;
    private String test_paper_id;
    private String subject_id;
    private String total_time;
    private List<DetailBean> detail;
    private String version;
    private String device;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public ZuJuanWrong(String app_user_id, String token, String test_paper_id, String subject_id, String total_time, List<DetailBean> detail, String version, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.test_paper_id = test_paper_id;
        this.subject_id = subject_id;
        this.total_time = total_time;
        this.detail = detail;
        this.version = version;
        this.device = device;
    }

    public String getApp_user_id() {
        return app_user_id;
    }

    public void setApp_user_id(String app_user_id) {
        this.app_user_id = app_user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTest_paper_id() {
        return test_paper_id;
    }

    public void setTest_paper_id(String test_paper_id) {
        this.test_paper_id = test_paper_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getTotal_time() {
        return total_time;
    }

    public void setTotal_time(String total_time) {
        this.total_time = total_time;
    }

    public List<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailBean> detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        /**
         * item_bank_id : 30
         * item_th : 0
         * test_paper_th : 2
         * key : A,B
         */

        private String item_bank_id;
        private String item_th;
        private String test_paper_th;
        private String key;

        public String getItem_bank_id() {
            return item_bank_id;
        }

        public void setItem_bank_id(String item_bank_id) {
            this.item_bank_id = item_bank_id;
        }

        public String getItem_th() {
            return item_th;
        }

        public void setItem_th(String item_th) {
            this.item_th = item_th;
        }

        public String getTest_paper_th() {
            return test_paper_th;
        }

        public void setTest_paper_th(String test_paper_th) {
            this.test_paper_th = test_paper_th;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
