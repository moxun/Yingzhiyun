package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class RightLeft {


    /**
     * status : 200
     * hint : null
     * result : {"detail":[{"effective":"长期有效","teacherName":"","img_url":"http://192.168.0.120/yzyFiles/icons/3d058f74-90cb-4768-ae93-e6bc308580b31578714814099.png","teacherHead":"http://res.minxuejiaoyu.cn//res//shaanxi//SSEP/uploadfiles/image/2019692228566906.png","subject":"化学","price":59,"id":43,"signUpNumber":"0","mini_img_url":null,"title":"秒杀2020高考化学（含3节试听课）","type":"course"},{"effective":"长期有效","teacherName":"","img_url":"http://192.168.0.120/yzyFiles/icons/2b1eaa2a-e064-4392-8104-4bb579219dbf1578714736803.png","teacherHead":"http://res.minxuejiaoyu.cn//res//shaanxi//SSEP/uploadfiles/image/2019692228566906.png","subject":"化学","price":39,"id":39,"signUpNumber":"0","mini_img_url":null,"title":"直击2020高考.化学（五）\u2014\u2014征服速率与平衡问题","type":"course"},{"effective":"长期有效","teacherName":"","img_url":"http://192.168.0.120/yzyFiles/icons/717a7f8b-4483-4973-bd53-a2f5e96f29221578714558601.png","teacherHead":"http://res.minxuejiaoyu.cn//res//shaanxi//SSEP/uploadfiles/image/2019692228566906.png","subject":"数学","price":59,"id":28,"signUpNumber":"0","mini_img_url":null,"title":"2020高考数学终极预测题（含1节试听课）","type":"course"},{"effective":"长期有效","teacherName":"朱瑞峰","img_url":"http://192.168.0.120/yzyFiles/icons/e9c27242-5223-4ad7-8d28-003c4add48931578714635085.png","teacherHead":"http://res.minxuejiaoyu.cn//res//shaanxi//SSEP/uploadfiles/image/2019649531333451.jpg","subject":"物理","price":69,"id":32,"signUpNumber":"0","mini_img_url":null,"title":"精准提分\u20142020高考物理万能解题模型（三）曲线运动","type":"course"},{"effective":"长期有效","teacherName":"","img_url":"http://192.168.0.120/yzyFiles/icons/ac9769c9-61e9-4afc-bf76-075e5f9011ed1578714892427.png","teacherHead":"http://res.minxuejiaoyu.cn//res//shaanxi//SSEP/uploadfiles/image/2019692228566906.png","subject":"理科综合","price":59,"id":49,"signUpNumber":"0","mini_img_url":null,"title":"2019高考稳派教育押题密卷第三卷·理科","type":"course"}],"title":"精品推荐"}
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
         * detail : [{"effective":"长期有效","teacherName":"","img_url":"http://192.168.0.120/yzyFiles/icons/3d058f74-90cb-4768-ae93-e6bc308580b31578714814099.png","teacherHead":"http://res.minxuejiaoyu.cn//res//shaanxi//SSEP/uploadfiles/image/2019692228566906.png","subject":"化学","price":59,"id":43,"signUpNumber":"0","mini_img_url":null,"title":"秒杀2020高考化学（含3节试听课）","type":"course"},{"effective":"长期有效","teacherName":"","img_url":"http://192.168.0.120/yzyFiles/icons/2b1eaa2a-e064-4392-8104-4bb579219dbf1578714736803.png","teacherHead":"http://res.minxuejiaoyu.cn//res//shaanxi//SSEP/uploadfiles/image/2019692228566906.png","subject":"化学","price":39,"id":39,"signUpNumber":"0","mini_img_url":null,"title":"直击2020高考.化学（五）\u2014\u2014征服速率与平衡问题","type":"course"},{"effective":"长期有效","teacherName":"","img_url":"http://192.168.0.120/yzyFiles/icons/717a7f8b-4483-4973-bd53-a2f5e96f29221578714558601.png","teacherHead":"http://res.minxuejiaoyu.cn//res//shaanxi//SSEP/uploadfiles/image/2019692228566906.png","subject":"数学","price":59,"id":28,"signUpNumber":"0","mini_img_url":null,"title":"2020高考数学终极预测题（含1节试听课）","type":"course"},{"effective":"长期有效","teacherName":"朱瑞峰","img_url":"http://192.168.0.120/yzyFiles/icons/e9c27242-5223-4ad7-8d28-003c4add48931578714635085.png","teacherHead":"http://res.minxuejiaoyu.cn//res//shaanxi//SSEP/uploadfiles/image/2019649531333451.jpg","subject":"物理","price":69,"id":32,"signUpNumber":"0","mini_img_url":null,"title":"精准提分\u20142020高考物理万能解题模型（三）曲线运动","type":"course"},{"effective":"长期有效","teacherName":"","img_url":"http://192.168.0.120/yzyFiles/icons/ac9769c9-61e9-4afc-bf76-075e5f9011ed1578714892427.png","teacherHead":"http://res.minxuejiaoyu.cn//res//shaanxi//SSEP/uploadfiles/image/2019692228566906.png","subject":"理科综合","price":59,"id":49,"signUpNumber":"0","mini_img_url":null,"title":"2019高考稳派教育押题密卷第三卷·理科","type":"course"}]
         * title : 精品推荐
         */

        private String title;
        private List<HomePagerBean.ResultBean.RightBean.DetailBeanX> detail;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<HomePagerBean.ResultBean.RightBean.DetailBeanX> getDetail() {
            return detail;
        }

        public void setDetail(List<HomePagerBean.ResultBean.RightBean.DetailBeanX> detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * effective : 长期有效
             * teacherName :
             * img_url : http://192.168.0.120/yzyFiles/icons/3d058f74-90cb-4768-ae93-e6bc308580b31578714814099.png
             * teacherHead : http://res.minxuejiaoyu.cn//res//shaanxi//SSEP/uploadfiles/image/2019692228566906.png
             * subject : 化学
             * price : 59
             * id : 43
             * signUpNumber : 0
             * mini_img_url : null
             * title : 秒杀2020高考化学（含3节试听课）
             * type : course
             */

            private String effective;
            private String teacherName;
            private String img_url;
            private String teacherHead;
            private String subject;
            private int price;
            private int id;
            private String signUpNumber;
            private Object mini_img_url;
            private String title;
            private String type;

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

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
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

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getSignUpNumber() {
                return signUpNumber;
            }

            public void setSignUpNumber(String signUpNumber) {
                this.signUpNumber = signUpNumber;
            }

            public Object getMini_img_url() {
                return mini_img_url;
            }

            public void setMini_img_url(Object mini_img_url) {
                this.mini_img_url = mini_img_url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
