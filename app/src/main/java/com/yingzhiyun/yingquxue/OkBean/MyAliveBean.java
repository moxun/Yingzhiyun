package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class MyAliveBean {


    /**
     * status : 200
     * hint : null
     * result : [{"detail":[{"effective":"2020年4月15日 10:00-12:00","teacherName":"R楠","teacherHead":"http://192.168.0.120/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"语文","price":1,"id":44,"signUpNumber":3,"title":"直播课程测试1"}],"title":"未开始的课程"},{"detail":[],"title":"已开始的课程"},{"detail":[],"title":"已结束的课程"},{"detail":[],"title":"退费课程"}]
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
         * detail : [{"effective":"2020年4月15日 10:00-12:00","teacherName":"R楠","teacherHead":"http://192.168.0.120/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"语文","price":1,"id":44,"signUpNumber":3,"title":"直播课程测试1"}]
         * title : 未开始的课程
         */

        private String title;
        private List<CourseBean.ResultBean> detail;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<CourseBean.ResultBean> getDetail() {
            return detail;
        }

        public void setDetail(List<CourseBean.ResultBean> detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * effective : 2020年4月15日 10:00-12:00
             * teacherName : R楠
             * teacherHead : http://192.168.0.120/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG
             * subject : 语文
             * price : 1
             * id : 44
             * signUpNumber : 3
             * title : 直播课程测试1
             */

            private String effective;
            private String teacherName;
            private String teacherHead;
            private String subject;
            private int price;
            private int id;
            private int signUpNumber;
            private String title;

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

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSignUpNumber() {
                return signUpNumber;
            }

            public void setSignUpNumber(int signUpNumber) {
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
}
