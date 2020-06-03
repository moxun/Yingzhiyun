package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class CourseinfoBean {


    /**
     * status : 200
     * hint : null
     * result : {"isSignUp":false,"effective":"2019.9.25-2020.1.1","outline":[{"effective":"2019.9.25-2020.1.1","title":"1.四则运算"},{"effective":"2019.9.25-2020.1.1","title":"2.乘方"},{"effective":"2019.9.25-2020.1.1","title":"3.完结-总结"}],"teacherId":1,"teacherName":"R楠","teacherHead":"http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"数学","price":"免费","courseBriefing":[{"contentType":"title","content":"过往经历"},{"contentType":"text","content":"不堪回首"}],"teacherLabel":"眼睛是心灵的窗户","signUpNumber":"1","title":"数学高三如何学好数学"}
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
         * isSignUp : false
         * effective : 2019.9.25-2020.1.1
         * outline : [{"effective":"2019.9.25-2020.1.1","title":"1.四则运算"},{"effective":"2019.9.25-2020.1.1","title":"2.乘方"},{"effective":"2019.9.25-2020.1.1","title":"3.完结-总结"}]
         * teacherId : 1
         * teacherName : R楠
         * teacherHead : http://192.168.1.2/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG
         * subject : 数学
         * price : 免费
         * courseBriefing : [{"contentType":"title","content":"过往经历"},{"contentType":"text","content":"不堪回首"}]
         * teacherLabel : 眼睛是心灵的窗户
         * signUpNumber : 1
         * title : 数学高三如何学好数学
         */
        private String score;

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        private String courseImg;
        private boolean isSignUp;
        private String effective;
        private String courseType;
        private String playbackLink;
        public String getCourseType() {
            return courseType;
        }
        public boolean vip;

        public boolean isVip() {
            return vip;
        }

        public void setVip(boolean vip) {
            this.vip = vip;
        }

        public void setCourseType(String courseType) {
            this.courseType = courseType;
        }

        public int getCountSignUp() {
            return countSignUp;
        }

        public void setCountSignUp(int countSignUp) {
            this.countSignUp = countSignUp;
        }

        public String getPlaybackLink() {
            return playbackLink;
        }

        public void setPlaybackLink(String playbackLink) {
            this.playbackLink = playbackLink;
        }

        private int countSignUp;
        private int teacherId;
        private String teacherName;
        private String teacherHead;
        private String subject;
        private double price;
        private String teacherLabel;
        private String signUpNumber;
        private String title;
        private List<OutlineBean> outline;
        private List<CourseBriefingBean> courseBriefing;




        public String getCourseImg() {
            return courseImg;
        }

        public void setCourseImg(String courseImg) {
            this.courseImg = courseImg;
        }

        public boolean isSignUp() {
            return isSignUp;
        }

        public void setSignUp(boolean signUp) {
            isSignUp = signUp;
        }

        public boolean isIsSignUp() {
            return isSignUp;
        }

        public void setIsSignUp(boolean isSignUp) {
            this.isSignUp = isSignUp;
        }

        public String getEffective() {
            return effective;
        }

        public void setEffective(String effective) {
            this.effective = effective;
        }

        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
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

        public String getTeacherLabel() {
            return teacherLabel;
        }

        public void setTeacherLabel(String teacherLabel) {
            this.teacherLabel = teacherLabel;
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

        public List<OutlineBean> getOutline() {
            return outline;
        }

        public void setOutline(List<OutlineBean> outline) {
            this.outline = outline;
        }

        public List<CourseBriefingBean> getCourseBriefing() {
            return courseBriefing;
        }

        public void setCourseBriefing(List<CourseBriefingBean> courseBriefing) {
            this.courseBriefing = courseBriefing;
        }

        public static class OutlineBean {
            /**
             * effective : 2019.9.25-2020.1.1
             * title : 1.四则运算
             */

            private String effective;
            private String title;
            private String isFreeTask;
            private String liveStatus;
            private int id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIsFreeTask() {
                return isFreeTask;
            }

            public void setIsFreeTask(String isFreeTask) {
                this.isFreeTask = isFreeTask;
            }

            public String getLiveStatus() {
                return liveStatus;
            }

            public void setLiveStatus(String liveStatus) {
                this.liveStatus = liveStatus;
            }

            public String getEffective() {
                return effective;
            }

            public void setEffective(String effective) {
                this.effective = effective;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class CourseBriefingBean {
            /**
             * contentType : title
             * content : 过往经历
             */

            private String contentType;
            private String content;
            private int width;
            private int height;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
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
