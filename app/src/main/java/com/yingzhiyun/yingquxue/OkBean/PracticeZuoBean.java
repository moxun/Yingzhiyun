package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class PracticeZuoBean {


    /**
     * status : 200
     * hint : null
     * result : {"imgWidth":750,"examUrl":"http://192.168.0.120/yzyFiles/icons/bb1cd8b3-40c0-42ca-9780-c025f3ee8224-1584513966717.jpg","examDetail":[{"th":1,"keys":["A","B","C","D"],"isMultiple":false,"id":17},{"th":2,"keys":["A","B","C","D"],"isMultiple":false,"id":18},{"th":3,"keys":["A","B","C","D"],"isMultiple":false,"id":20},{"th":4,"keys":["A","B","C","D"],"isMultiple":false,"id":23},{"th":5,"keys":["A","B","C","D"],"isMultiple":false,"id":27},{"th":6,"keys":["A","B","C","D"],"isMultiple":false,"id":29},{"th":7,"keys":["A","B","C","D"],"isMultiple":false,"id":30}],"imgHeight":23640,"time":165763}
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
         * imgWidth : 750
         * examUrl : http://192.168.0.120/yzyFiles/icons/bb1cd8b3-40c0-42ca-9780-c025f3ee8224-1584513966717.jpg
         * examDetail : [{"th":1,"keys":["A","B","C","D"],"isMultiple":false,"id":17},{"th":2,"keys":["A","B","C","D"],"isMultiple":false,"id":18},{"th":3,"keys":["A","B","C","D"],"isMultiple":false,"id":20},{"th":4,"keys":["A","B","C","D"],"isMultiple":false,"id":23},{"th":5,"keys":["A","B","C","D"],"isMultiple":false,"id":27},{"th":6,"keys":["A","B","C","D"],"isMultiple":false,"id":29},{"th":7,"keys":["A","B","C","D"],"isMultiple":false,"id":30}]
         * imgHeight : 23640
         * time : 165763
         */

        private int imgWidth;
        private String examUrl;
        private int imgHeight;
        private int time;
        private List<ExamDetailBean> examDetail;

        public int getImgWidth() {
            return imgWidth;
        }

        public void setImgWidth(int imgWidth) {
            this.imgWidth = imgWidth;
        }

        public String getExamUrl() {
            return examUrl;
        }

        public void setExamUrl(String examUrl) {
            this.examUrl = examUrl;
        }

        public int getImgHeight() {
            return imgHeight;
        }

        public void setImgHeight(int imgHeight) {
            this.imgHeight = imgHeight;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public List<ExamDetailBean> getExamDetail() {
            return examDetail;
        }

        public void setExamDetail(List<ExamDetailBean> examDetail) {
            this.examDetail = examDetail;
        }

        public static class ExamDetailBean {
            /**
             * th : 1
             * keys : ["A","B","C","D"]
             * isMultiple : false
             * id : 17
             */

            private int th;
            private boolean isMultiple;
            private int id;
            private List<String> keys;

            public int getTh() {
                return th;
            }

            public void setTh(int th) {
                this.th = th;
            }

            public boolean isMultiple() {
                return isMultiple;
            }

            public void setMultiple(boolean multiple) {
                isMultiple = multiple;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<String> getKeys() {
                return keys;
            }

            public void setKeys(List<String> keys) {
                this.keys = keys;
            }
        }
    }
}
