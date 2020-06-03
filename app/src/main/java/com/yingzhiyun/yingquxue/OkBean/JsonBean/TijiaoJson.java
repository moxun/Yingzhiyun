package com.yingzhiyun.yingquxue.OkBean.JsonBean;

import java.util.List;

public class TijiaoJson{


    public TijiaoJson(ExamCardBean examCard, int app_user_id, int time, String token, String device, String version, int oenId) {
        this.examCard = examCard;
        this.app_user_id = app_user_id;
        this.time = time;
        this.token = token;
        this.device = device;
        this.version = version;
        this.oenId = oenId;
    }

    /**
     * examCard : {"examDetail":[{"th":1,"keys":["A","B","C","D"],"userKey":["A"],"isMultiple":false,"id":1},{"th":2,"keys":["A","B","C","D","E"],"userKey":["C"],"isMultiple":true,"id":2}]}
     * app_user_id : 1
     * time : 600
     * token : NjU5ZTRkMTljYzY5NDNhM2FiOGMwNzFmZjdkNzUxN2U=
     * device : iOS
     * version : 1.0.10
     */

    private ExamCardBean examCard;
    private int app_user_id;
    private int time;
    private String token;
    private String device;
    private String version;
    private int oenId;
    public ExamCardBean getExamCard() {
        return examCard;
    }

    public void setExamCard(ExamCardBean examCard) {
        this.examCard = examCard;
    }

    public int getApp_user_id() {
        return app_user_id;
    }

    public void setApp_user_id(int app_user_id) {
        this.app_user_id = app_user_id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class ExamCardBean {
        private List<ExamDetailBean> examDetail;

        public ExamCardBean(List<ExamDetailBean> examDetail) {
            this.examDetail = examDetail;
        }

        public List<ExamDetailBean> getExamDetail() {
            return examDetail;
        }

        public void setExamDetail(List<ExamDetailBean> examDetail) {
            this.examDetail = examDetail;
        }

        public static class ExamDetailBean {
            /**
             * th : 1
             * keys : ["A","B","C","D"]
             * userKey : ["A"]
             * isMultiple : false
             * id : 1
             */

            private int th;
            private boolean isMultiple;
            private String  id;
            private List<String> keys;
            private List<String> userKey;

            public ExamDetailBean(int th, boolean isMultiple, String id, List<String> keys, List<String> userKey) {
                this.th = th;
                this.isMultiple = isMultiple;
                this.id = id;
                this.keys = keys;
                this.userKey = userKey;
            }

            public int getTh() {
                return th;
            }

            public void setTh(int th) {
                this.th = th;
            }

            public boolean isIsMultiple() {
                return isMultiple;
            }

            public void setIsMultiple(boolean isMultiple) {
                this.isMultiple = isMultiple;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public List<String> getKeys() {
                return keys;
            }

            public void setKeys(List<String> keys) {
                this.keys = keys;
            }

            public List<String> getUserKey() {
                return userKey;
            }

            public void setUserKey(List<String> userKey) {
                this.userKey = userKey;
            }
        }
    }
}
