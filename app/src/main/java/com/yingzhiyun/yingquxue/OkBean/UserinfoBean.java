package com.yingzhiyun.yingquxue.OkBean;

public class UserinfoBean {


    /**
     * status : 200
     * hint : null
     * result : {"school_id":0,"school":"","head_path":"http://www.yingzhiyunwenhua.cn/yzyFiles/userHead/73ed4377-c523-494e-96e8-da1ef84e3302-1567049402795.jpg","sex":1,"grade":8,"nickname":"甲","enrollment_year":"2017","student_id":"666666","clazz":"火箭"}
     */

    private int status;
    private Object hint;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getHint() {
        return hint;
    }

    public void setHint(Object hint) {
        this.hint = hint;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * school_id : 0
         * school :
         * head_path : http://www.yingzhiyunwenhua.cn/yzyFiles/userHead/73ed4377-c523-494e-96e8-da1ef84e3302-1567049402795.jpg
         * sex : 1
         * grade : 8
         * nickname : 甲
         * enrollment_year : 2017
         * student_id : 666666
         * clazz : 火箭
         */

        private String school_id;
        private String school;
        private String head_path;
        private String sex;
        private String grade;
        private String nickname;
        private String enrollment_year;
        private String student_id;
        private String clazz;

        public String getSchool_id() {
            return school_id;
        }

        public void setSchool_id(String school_id) {
            this.school_id = school_id;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getHead_path() {
            return head_path;
        }

        public void setHead_path(String head_path) {
            this.head_path = head_path;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getEnrollment_year() {
            return enrollment_year;
        }

        public void setEnrollment_year(String enrollment_year) {
            this.enrollment_year = enrollment_year;
        }

        public String getStudent_id() {
            return student_id;
        }

        public void setStudent_id(String student_id) {
            this.student_id = student_id;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }
    }
}
