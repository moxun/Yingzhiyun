package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class HudongIfoBean {


    /**
     * status : 200
     * hint : 查询成功
     * result : {"userHeadImgPath":null,"question":{"addTime":"2019-08-14 18:34:42","contentString":"1+1为什么等于2有没有人知道","imgList":[]},"answer":{"addTime":"2019-08-17 02:01:11","contentString":"盘古开天地以来他就等于二，今天就是天王老子来了，他1+1也是2","imgList":[]},"readVolume":14,"userNickname":"15565183652"}
     */

    private int status;
    private String hint;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
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
         * userHeadImgPath : null
         * question : {"addTime":"2019-08-14 18:34:42","contentString":"1+1为什么等于2有没有人知道","imgList":[]}
         * answer : {"addTime":"2019-08-17 02:01:11","contentString":"盘古开天地以来他就等于二，今天就是天王老子来了，他1+1也是2","imgList":[]}
         * readVolume : 14
         * userNickname : 15565183652
         */

        private Object userHeadImgPath;
        private QuestionBean question;
        private AnswerBean answer;
        private int readVolume;
        private String userNickname;

        public Object getUserHeadImgPath() {
            return userHeadImgPath;
        }

        public void setUserHeadImgPath(Object userHeadImgPath) {
            this.userHeadImgPath = userHeadImgPath;
        }

        public QuestionBean getQuestion() {
            return question;
        }

        public void setQuestion(QuestionBean question) {
            this.question = question;
        }

        public AnswerBean getAnswer() {
            return answer;
        }

        public void setAnswer(AnswerBean answer) {
            this.answer = answer;
        }

        public int getReadVolume() {
            return readVolume;
        }

        public void setReadVolume(int readVolume) {
            this.readVolume = readVolume;
        }

        public String getUserNickname() {
            return userNickname;
        }

        public void setUserNickname(String userNickname) {
            this.userNickname = userNickname;
        }

        public static class QuestionBean {
            /**
             * addTime : 2019-08-14 18:34:42
             * contentString : 1+1为什么等于2有没有人知道
             * imgList : []
             */

            private String addTime;
            private String contentString;
            private List<String> imgList;

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getContentString() {
                return contentString;
            }

            public void setContentString(String contentString) {
                this.contentString = contentString;
            }

            public List<String> getImgList() {
                return imgList;
            }

            public void setImgList(List<String> imgList) {
                this.imgList = imgList;
            }
        }

        public static class AnswerBean {
            /**
             * addTime : 2019-08-17 02:01:11
             * contentString : 盘古开天地以来他就等于二，今天就是天王老子来了，他1+1也是2
             * imgList : []
             */

            private String addTime;
            private String contentString;
            private List<?> imgList;

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getContentString() {
                return contentString;
            }

            public void setContentString(String contentString) {
                this.contentString = contentString;
            }

            public List<?> getImgList() {
                return imgList;
            }

            public void setImgList(List<?> imgList) {
                this.imgList = imgList;
            }
        }
    }
}
