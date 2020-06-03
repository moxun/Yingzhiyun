package com.yingzhiyun.yingquxue.OkBean.JsonBean;

import java.util.List;

public class BankCombinationJson {



    /**
     * app_user_id : 10
     * token : MzcyOGI0ODE4ZTdkNDE0NjhmMmU2ZWFlZmZhODJmMzM=
     * subject_id : 6
     * detail : [{"question_type_id":"1","num":"6"},{"question_type_id":"2","num":"2"}]
     * knowledgePoints : [4]
     */

    private String app_user_id;
    private String token;
    private String grade;


    public BankCombinationJson(String app_user_id, String token, String grade, String subject_id, List<DetailBean> detail, List<Integer> knowledgePoints, String version, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.grade = grade;
        this.subject_id = subject_id;
        this.detail = detail;
        this.knowledgePoints = knowledgePoints;
        this.version = version;
        this.device = device;
    }

    private String subject_id;
    private List<DetailBean> detail;
    private List<Integer> knowledgePoints;
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

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public List<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailBean> detail) {
        this.detail = detail;
    }

    public List<Integer> getKnowledgePoints() {
        return knowledgePoints;
    }

    public void setKnowledgePoints(List<Integer> knowledgePoints) {
        this.knowledgePoints = knowledgePoints;
    }

    public static class DetailBean {
        public DetailBean(String question_type_id, String num) {
            this.question_type_id = question_type_id;
            this.num = num;
        }

        /**
         * question_type_id : 1
         * num : 6
         */

        private String question_type_id;
        private String num;

        public String getQuestion_type_id() {
            return question_type_id;
        }

        public void setQuestion_type_id(String question_type_id) {
            this.question_type_id = question_type_id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
