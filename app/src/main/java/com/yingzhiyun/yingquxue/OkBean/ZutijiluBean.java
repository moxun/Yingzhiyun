package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class ZutijiluBean {

    /**
     * status : 200
     * hint : 查询成功
     * result : {"totalRight":0,"totalTime":0,"detail":[{"totalRight":0,"totalSize":1,"subject":"数学","schoolType":"高中","id":80,"time":"2019-08-24 01:11:41","title":"remember set alarm"}],"frequency":1}
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
         * totalRight : 0
         * totalTime : 0
         * detail : [{"totalRight":0,"totalSize":1,"subject":"数学","schoolType":"高中","id":80,"time":"2019-08-24 01:11:41","title":"remember set alarm"}]
         * frequency : 1
         */

        private int totalRight;
        private int totalTime;
        private int frequency;
        private List<DetailBean> detail;

        public int getTotalRight() {
            return totalRight;
        }

        public void setTotalRight(int totalRight) {
            this.totalRight = totalRight;
        }

        public int getTotalTime() {
            return totalTime;
        }

        public void setTotalTime(int totalTime) {
            this.totalTime = totalTime;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        public List<DetailBean> getDetail() {
            return detail;
        }

        public void setDetail(List<DetailBean> detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * totalRight : 0
             * totalSize : 1
             * subject : 数学
             * schoolType : 高中
             * id : 80
             * time : 2019-08-24 01:11:41
             * title : remember set alarm
             */

            private int totalRight;
            private int totalSize;
            private String subject;
            private String schoolType;
            private int id;
            private String time;
            private String title;

            public int getTotalRight() {
                return totalRight;
            }

            public void setTotalRight(int totalRight) {
                this.totalRight = totalRight;
            }

            public int getTotalSize() {
                return totalSize;
            }

            public void setTotalSize(int totalSize) {
                this.totalSize = totalSize;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public String getSchoolType() {
                return schoolType;
            }

            public void setSchoolType(String schoolType) {
                this.schoolType = schoolType;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
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
}
