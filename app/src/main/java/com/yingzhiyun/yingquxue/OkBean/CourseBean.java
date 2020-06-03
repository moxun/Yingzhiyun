package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class CourseBean {


    /**
     * status : 200
     * hint : null
     * result : [{"effective":"2019.9.25-2020.1.1","teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"数学","price":"免费","id":1,"signUpNumber":"1","title":"数学高三如何学好数学"},{"effective":"2019-9-26~2019-9-27","teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"信息","price":"免费","id":2,"signUpNumber":"0","title":"信息初三网页的制作"},{"effective":"2019-9-26~2019-9-27","teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"信息","price":"免费","id":3,"signUpNumber":"0","title":"信息初一网页的制作"},{"effective":"2019-9-26~2019-9-27","teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"数学","price":"免费","id":4,"signUpNumber":"0","title":"数学初三中考最后冲刺"},{"effective":"2019-9-26~2019-9-27","teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"文科综合","price":"免费","id":5,"signUpNumber":"0","title":"文综高三起名字好难"}]
     */

    private int status;
    private Object hint;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * effective : 2019.9.25-2020.1.1
         * teacherName : R楠
         * teacherHead : http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG
         * subject : 数学
         * price : 免费
         * id : 1
         * signUpNumber : 1
         * title : 数学高三如何学好数学
         */

        private String effective;
        private String teacherName;
        private String teacherHead;
        private String subject;
        private double price;
        private int id;
        private String courseImg;
        private String signUpNumber;
        private String title;
        private boolean vip;

        public boolean isVip() {
            return vip;
        }

        public void setVip(boolean vip) {
            this.vip = vip;
        }

        public String getCourseImg() {
            return courseImg;
        }

        public void setCourseImg(String courseImg) {
            this.courseImg = courseImg;
        }

        public String getEffective() {
            return effective;
        }

        public void setEffective(String effective) {
            this.effective = effective;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getTeacherHead() {
            return teacherHead;
        }

        public void setTeacherHead(String teacherHead) {
            this.teacherHead = teacherHead;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSignUpNumber() {
            return signUpNumber;
        }

        public void setSignUpNumber(String signUpNumber) {
            this.signUpNumber = signUpNumber;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
