package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class MineCourseBean {


    /**
     * status : 200
     * hint : null
     * result : {"notBegun":[],"begun":[{"effective":"2019.9.25-2020.1.1","teacherName":"R楠","teacherHead":"http://192.168.0.120/yzyFiles//userHead/0de88590-df13-4a2f-b884-0b5224a91ac5-1567062269600.JPG","subject":"数学","price":"免费","id":1,"signUpNumber":4,"title":"数学高三如何学好数学"}]}
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
        private List<CourseBean.ResultBean> notBegun;
        private List<CourseBean.ResultBean> begun;

        public List<CourseBean.ResultBean> getNotBegun() {
            return notBegun;
        }

        public void setNotBegun(List<CourseBean.ResultBean> notBegun) {
            this.notBegun = notBegun;
        }

        public List<CourseBean.ResultBean> getBegun() {
            return begun;
        }

        public void setBegun(List<CourseBean.ResultBean> begun) {
            this.begun = begun;
        }


    }
}
