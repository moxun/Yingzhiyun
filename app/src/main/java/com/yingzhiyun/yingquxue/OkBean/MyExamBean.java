package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class MyExamBean  {


    /**
     * status : 200
     * hint : null
     * result : [{"updateTime":"2020-03-11","id":1,"title":"高一数学第一次测试"}]
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
         * updateTime : 2020-03-11
         * id : 1
         * title : 高一数学第一次测试
         */

        private String updateTime;
        private int id;
        private String title;
        private boolean hasAchievements;
        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
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

        public boolean isHasAchievements() {
            return hasAchievements;
        }

        public void setHasAchievements(boolean hasAchievements) {
            this.hasAchievements = hasAchievements;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
