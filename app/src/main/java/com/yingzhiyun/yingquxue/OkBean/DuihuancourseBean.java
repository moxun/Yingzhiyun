package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class DuihuancourseBean {


    /**
     * status : 200
     * hint : null
     * result : [{"effective":"长期有效","teacherName":"杨敏敏","teacherHead":"http://res.minxuejiaoyu.cn//res//shaanxi//SSEP/uploadfiles/image/2019954248334780.jpg","subject":"语文","id":37,"couponId":401,"title":"秒杀2020高考\u2014\u2014语文夺分宝典"},{"effective":"长期有效","teacherName":"邓虎","teacherHead":"http://res.minxuejiaoyu.cn//res//shaanxi//SSEP/uploadfiles/image/2019277660729420.png","subject":"英语","id":19,"couponId":401,"title":"2020高考英语书面表达高分秘籍"}]
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
         * effective : 长期有效
         * teacherName : 杨敏敏
         * teacherHead : http://res.minxuejiaoyu.cn//res//shaanxi//SSEP/uploadfiles/image/2019954248334780.jpg
         * subject : 语文
         * id : 37
         * couponId : 401
         * title : 秒杀2020高考——语文夺分宝典
         */

        private String effective;
        private String teacherName;
        private String teacherHead;
        private String subject;
        private int id;
        private int couponId;
        private String title;

        public String getEffective() {
            return effective;
        }

        public void setEffective(String effective) {
            this.effective = effective;
        }

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

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
