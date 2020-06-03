package com.yingzhiyun.yingquxue.OkBean;

public class AliveBeaginBean {


    /**
     * status : 200
     * hint : null
     * result : {"isFollow":true,"effective":"2020年4月15日 10:00-12:00","teacherId":1,"teacherName":"R楠","playerUrl":"null","teacherHead":"http://192.168.0.120/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"语文","courseImg":"http://192.168.0.120/yzyFiles/icons/3d058f74-90cb-4768-ae93-e6bc308580b31578714814099.png","teacherLabel":null,"title":"直播课程测试1"}
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
         * effective : 2020年4月15日 10:00-12:00
         * teacherId : 1
         * teacherName : R楠
         * playerUrl : null
         * teacherHead : http://192.168.0.120/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG
         * subject : 语文
         * courseImg : http://192.168.0.120/yzyFiles/icons/3d058f74-90cb-4768-ae93-e6bc308580b31578714814099.png
         * teacherLabel : null
         * title : 直播课程测试1
         */

        private boolean isFollow;
        private String effective;
        private int teacherId;
        private String teacherName;
        private String playerUrl;
        private String teacherHead;
        private String subject;
        private String courseImg;
        private Object teacherLabel;
        private String title;
        private String roomId;

        public boolean isFollow() {
            return isFollow;
        }

        public void setFollow(boolean follow) {
            isFollow = follow;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public boolean getIsFollow() {
            return isFollow;
        }

        public void setIsFollow(boolean isFollow) {
            this.isFollow = isFollow;
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

        public String getPlayerUrl() {
            return playerUrl;
        }

        public void setPlayerUrl(String playerUrl) {
            this.playerUrl = playerUrl;
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

        public String getCourseImg() {
            return courseImg;
        }

        public void setCourseImg(String courseImg) {
            this.courseImg = courseImg;
        }

        public Object getTeacherLabel() {
            return teacherLabel;
        }

        public void setTeacherLabel(Object teacherLabel) {
            this.teacherLabel = teacherLabel;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
