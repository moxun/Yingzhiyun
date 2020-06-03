package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class TeacherinfoBean {

    /**
     * status : 200
     * hint : null
     * result : {"isFollow":true,"headImg":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","name":"R楠","courseList":[{"effective":"2019.9.25-2020.1.1","teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"数学","price":"免费","id":1,"signUpNumber":"1","title":"数学高三如何学好数学"},{"effective":"2019-9-26~2019-9-27","teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"信息","price":"免费","id":2,"signUpNumber":"0","title":"信息初三网页的制作"},{"effective":"2019-9-26~2019-9-27","teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"信息","price":"免费","id":3,"signUpNumber":"0","title":"信息初一网页的制作"},{"effective":"2019-9-26~2019-9-27","teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"数学","price":"免费","id":4,"signUpNumber":"0","title":"数学初三中考最后冲刺"},{"effective":"2019-9-26~2019-9-27","teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"文科综合","price":"免费","id":5,"signUpNumber":"0","title":"文综高三起名字好难"}],"briefing":[{"contentType":"title","content":"过往经历"},{"contentType":"text","content":"不堪回首"}]}
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
         * isFollow : true
         * headImg : http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG
         * name : R楠
         * courseList : [{"effective":"2019.9.25-2020.1.1","teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"数学","price":"免费","id":1,"signUpNumber":"1","title":"数学高三如何学好数学"},{"effective":"2019-9-26~2019-9-27","teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"信息","price":"免费","id":2,"signUpNumber":"0","title":"信息初三网页的制作"},{"effective":"2019-9-26~2019-9-27","teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"信息","price":"免费","id":3,"signUpNumber":"0","title":"信息初一网页的制作"},{"effective":"2019-9-26~2019-9-27","teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"数学","price":"免费","id":4,"signUpNumber":"0","title":"数学初三中考最后冲刺"},{"effective":"2019-9-26~2019-9-27","teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"文科综合","price":"免费","id":5,"signUpNumber":"0","title":"文综高三起名字好难"}]
         * briefing : [{"contentType":"title","content":"过往经历"},{"contentType":"text","content":"不堪回首"}]
         */

        private boolean isFollow;
        private String headImg;
        private String name;
        private List<CourseBean.ResultBean> courseList;
        private List<BriefingBean> briefing;
        private List<BriefingBean> evaluateList;
        private double avgScore;

        public boolean isFollow() {
            return isFollow;
        }

        public void setFollow(boolean follow) {
            isFollow = follow;
        }

        public List<BriefingBean> getEvaluateList() {
            return evaluateList;
        }

        public void setEvaluateList(List<BriefingBean> evaluateList) {
            this.evaluateList = evaluateList;
        }

        public double getAvgScore() {
            return avgScore;
        }

        public void setAvgScore(double avgScore) {
            this.avgScore = avgScore;
        }

        /**
         * appUserHead : http://192.168.0.120/yzyFiles/userHead/bdd1f4e2-3d89-457c-a51b-d67def17f122-1574747939751.png
         * answer : 你行你来！
         * appUserName : 九八
         * time : 2020-04-15 09:59
         * content : 啥 啥 啥  讲的这是啥（sha 统一四声）
         */



        public boolean isIsFollow() {
            return isFollow;
        }

        public void setIsFollow(boolean isFollow) {
            this.isFollow = isFollow;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CourseBean.ResultBean> getCourseList() {
            return courseList;
        }

        public void setCourseList(List<CourseBean.ResultBean> courseList) {
            this.courseList = courseList;
        }

        public List<BriefingBean> getBriefing() {
            return briefing;
        }

        public void setBriefing(List<BriefingBean> briefing) {
            this.briefing = briefing;
        }


        public static class CourseListBean {
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
            private String price;
            private int id;
            private String signUpNumber;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
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

        public static class BriefingBean {
            /**
             * contentType : title
             * content : 过往经历
             */
            private String appUserHead;
            private String answer;
            private String appUserName;
            private String time;
            private String content;

            public String getSchoolName() {
                return schoolName;
            }

            public void setSchoolName(String schoolName) {
                this.schoolName = schoolName;
            }

            private String contentType;
            private String schoolName;
            public String getAppUserHead() {
                return appUserHead;
            }

            public void setAppUserHead(String appUserHead) {
                this.appUserHead = appUserHead;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public String getAppUserName() {
                return appUserName;
            }

            public void setAppUserName(String appUserName) {
                this.appUserName = appUserName;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContentType() {
                return contentType;
            }

            public void setContentType(String contentType) {
                this.contentType = contentType;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

    }
}
