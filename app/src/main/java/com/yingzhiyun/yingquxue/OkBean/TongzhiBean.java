package com.yingzhiyun.yingquxue.OkBean;

public class TongzhiBean {


    /**
     * result : {"actionType":"courseInfo","id":1,"title":"课程标题"}
     * status : 200
     */

    private ResultBean result;
    private int status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * actionType : courseInfo
         * id : 1
         * title : 课程标题
         */

        private String actionType;
        private int id;
        private String title;

        public String getActionType() {
            return actionType;
        }

        public void setActionType(String actionType) {
            this.actionType = actionType;
        }

        public int getId() {
            return id;
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
