package com.yingzhiyun.yingquxue.OkBean;

import java.io.Serializable;

public class AdvertisingBean implements Serializable {


    /**
     * status : 200
     * hint : 查询成功
     * result : {"id":19,"type":"folderList","url":"http://192.168.0.120/yzyFiles/icons/22fcad99-5b20-491d-83ff-3455137c4b171580720729257.png"}
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

    public static class ResultBean implements Serializable{
        /**
         * id : 19
         * type : folderList
         * url : http://192.168.0.120/yzyFiles/icons/22fcad99-5b20-491d-83ff-3455137c4b171580720729257.png
         */

        private int id;
        private String type;
        private String url;
        private String day;
        private String title;
        private String gradeType;
        public boolean isCollection() {
            return isCollection;
        }

        public void setCollection(boolean collection) {
            isCollection = collection;
        }

        private  boolean isCollection;
        public String getDay() {
            return day;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGradeType() {
            return gradeType;
        }

        public void setGradeType(String gradeType) {
            this.gradeType = gradeType;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
