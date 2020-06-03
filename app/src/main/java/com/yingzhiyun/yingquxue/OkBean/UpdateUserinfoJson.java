package com.yingzhiyun.yingquxue.OkBean;

public class UpdateUserinfoJson {


    /**
     * app_user_id : 1
     * token : NzY1ZGI1ODllODI2NDczMGJmZGIxNmVjY2Y2NjAxMzc=
     * nickname : 张3
     * sex : 1
     * school_id : 清华附中
     * grade : 10
     * clazz : 清华直升班
     * enrollment_year : 2019
     * student_id : 6666666666666666
     */

    private int app_user_id;
    private String token;
    private String nickname;
    private String sex;
    private String school_id;
    private String grade;
    private String clazz;
    private String enrollment_year;
    private String student_id;
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
    public int getApp_user_id() {
        return app_user_id;
    }

    public UpdateUserinfoJson(int app_user_id, String token, String nickname, String sex, String school_id, String grade, String clazz, String enrollment_year, String student_id, String version, String device) {
        this.app_user_id = app_user_id;
        this.token = token;
        this.nickname = nickname;
        this.sex = sex;
        this.school_id = school_id;
        this.grade = grade;
        this.clazz = clazz;
        this.enrollment_year = enrollment_year;
        this.student_id = student_id;
        this.version = version;
        this.device = device;
    }

    public void setApp_user_id(int app_user_id) {
        this.app_user_id = app_user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
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
}
