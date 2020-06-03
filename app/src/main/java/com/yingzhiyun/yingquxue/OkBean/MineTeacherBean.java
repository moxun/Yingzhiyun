package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class MineTeacherBean {


    /**
     * status : 200
     * hint : null
     * result : [{"teacherName":"R楠","teacherHead":"http://192.168.0.120/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","teacherLabel":"个性签名还是没有弄诶","id":1}]
     */

    private int status;
    private Object hint;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * teacherName : R楠
         * teacherHead : http://192.168.0.120/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG
         * teacherLabel : 个性签名还是没有弄诶
         * id : 1
         */

        private String teacherName;
        private String teacherHead;
        private String teacherLabel;
        private int id;

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

        public String getTeacherLabel() {
            return teacherLabel;
        }

        public void setTeacherLabel(String teacherLabel) {
            this.teacherLabel = teacherLabel;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
