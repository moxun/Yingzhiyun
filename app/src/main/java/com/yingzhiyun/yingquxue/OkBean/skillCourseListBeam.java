package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class skillCourseListBeam {

    /**
     * status : 200
     * hint : null
     * result : [{"img":"http://192.168.1.25/yzyFiles/icons/dad6da1d-74c4-4417-93ca-c60c031778a3-1561010069748.png","price":"免费","id":1,"title":"数学高三如何学好数学","enrolmentNum":"4"},{"img":"http://192.168.1.25/yzyFiles/icons/dad6da1d-74c4-4417-93ca-c60c031778a3-1561010069748.png","price":"免费","id":2,"title":"信息初三网页的制作","enrolmentNum":"0"}]
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
         * img : http://192.168.1.25/yzyFiles/icons/dad6da1d-74c4-4417-93ca-c60c031778a3-1561010069748.png
         * price : 免费
         * id : 1
         * title : 数学高三如何学好数学
         * enrolmentNum : 4
         */

        private String img;
        private String price;
        private int id;
        private String title;
        private String enrolmentNum;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public String getEnrolmentNum() {
            return enrolmentNum;
        }

        public void setEnrolmentNum(String enrolmentNum) {
            this.enrolmentNum = enrolmentNum;
        }
    }
}
