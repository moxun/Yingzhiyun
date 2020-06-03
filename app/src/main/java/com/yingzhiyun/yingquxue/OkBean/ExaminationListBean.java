package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class ExaminationListBean {


    /**
     * status : null
     * hint : null
     * result : [{"timeRanges":"考试时间:2020-03-12 12:00-14:00","id":1,"title":"高一数学第一次测试"}]
     */

    private int status;
    private String hint;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * timeRanges : 考试时间:2020-03-12 12:00-14:00
         * id : 1
         * title : 高一数学第一次测试
         */

        private String timeRanges;
        private int id;
        private String title;
        private boolean isSign;
        private boolean isStart;
        private boolean isEnd;
        public boolean isSign() {
            return isSign;
        }

        public void setSign(boolean sign) {
            isSign = sign;
        }

        public String getTimeRanges() {
            return timeRanges;
        }

        public void setTimeRanges(String timeRanges) {
            this.timeRanges = timeRanges;
        }

        public int getId() {
            return id;
        }

        public boolean isStart() {
            return isStart;
        }

        public void setStart(boolean start) {
            isStart = start;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
