package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class AliveListBean {

    /**
     * status : 200
     * hint : null
     * result : [{"effective":"长期有效","teacherName":"R楠","teacherHead":"http://192.168.0.120/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"语文","price":0,"countSignUp":100,"id":45,"signUpNumber":"0","time":157066,"title":"直播课程测试2"},{"effective":"2020年4月15日 10:00-12:00","teacherName":"R楠","teacherHead":"http://192.168.0.120/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"语文","price":593,"countSignUp":50,"id":44,"signUpNumber":"0","time":157066,"title":"直播课程测试1"}]
     */

    private int status;
    private String hint;
    private List<ZiyuanBean.ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public List<ZiyuanBean.ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ZiyuanBean.ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * effective : 长期有效
         * teacherName : R楠
         * teacherHead : http://192.168.0.120/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG
         * subject : 语文
         * price : 0
         * countSignUp : 100
         * id : 45
         * signUpNumber : 0
         * time : 157066
         * title : 直播课程测试2
         */
        private String signUpNumber;
        private String effective;
        private String teacherName;
        private String teacherHead;
        private String subject;
        private double price;
        private int countSignUp;


        private int time;
        private String title;
        private int id;
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

        public int getCountSignUp() {
            return countSignUp;
        }

        public void setCountSignUp(int countSignUp) {
            this.countSignUp = countSignUp;
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

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
