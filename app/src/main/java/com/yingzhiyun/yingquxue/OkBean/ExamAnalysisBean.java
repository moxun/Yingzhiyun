package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class ExamAnalysisBean {

    /**
     * status : 200
     * hint : null
     * result : {"imgWidth":750,"examUrl":"http://192.168.0.120/yzyFiles/icons/204bb320-6c2e-43fb-ab1f-e51ae7b956a31583480895883.png","examDetail":[{"imgWidth":750,"th":1,"imgHeight":500,"analysis":"http://192.168.0.120/yzyFiles/icons/1f633b25-d741-4882-a5b3-f4e8a9ea07fc1584260586225.jpg"},{"imgWidth":750,"th":2,"imgHeight":500,"analysis":"http://192.168.0.120/yzyFiles/icons/1f633b25-d741-4882-a5b3-f4e8a9ea07fc1584260586225.jpg"}],"imgHeight":958}
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
         * imgWidth : 750
         * examUrl : http://192.168.0.120/yzyFiles/icons/204bb320-6c2e-43fb-ab1f-e51ae7b956a31583480895883.png
         * examDetail : [{"imgWidth":750,"th":1,"imgHeight":500,"analysis":"http://192.168.0.120/yzyFiles/icons/1f633b25-d741-4882-a5b3-f4e8a9ea07fc1584260586225.jpg"},{"imgWidth":750,"th":2,"imgHeight":500,"analysis":"http://192.168.0.120/yzyFiles/icons/1f633b25-d741-4882-a5b3-f4e8a9ea07fc1584260586225.jpg"}]
         * imgHeight : 958
         */

        private int imgWidth;
        private String examUrl;
        private int imgHeight;
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

        public List<ExamDetailBean> getExamDetail() {
            return examDetail;
        }

        public void setExamDetail(List<ExamDetailBean> examDetail) {
            this.examDetail = examDetail;
        }

        public static class ExamDetailBean {
            /**
             * imgWidth : 750
             * th : 1
             * imgHeight : 500
             * analysis : http://192.168.0.120/yzyFiles/icons/1f633b25-d741-4882-a5b3-f4e8a9ea07fc1584260586225.jpg
             */

            private int imgWidth;
            private int th;
            private int imgHeight;
            private String analysis;

            public int getImgWidth() {
                return imgWidth;
            }

            public void setImgWidth(int imgWidth) {
                this.imgWidth = imgWidth;
            }

            public int getTh() {
                return th;
            }

            public void setTh(int th) {
                this.th = th;
            }

            public int getImgHeight() {
                return imgHeight;
            }

            public void setImgHeight(int imgHeight) {
                this.imgHeight = imgHeight;
            }

            public String getAnalysis() {
                return analysis;
            }

            public void setAnalysis(String analysis) {
                this.analysis = analysis;
            }
        }
    }
}
