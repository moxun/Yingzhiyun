package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class VipCenterBean {


    /**
     * status : 200
     * hint : null
     * result : {"vipCourseList":[{"coverImg":"http://192.168.0.120/yzyFiles/icons/9b282e50-1bab-4f67-bede-4495aeb3637b1589523452606.jpg","id":43,"title":"秒杀2020高考化学（含3节试听课）"},{"coverImg":"http://192.168.0.120/yzyFiles/icons/ecd51f7f-2e1a-4e94-86d3-46a9088839201589523586232.jpg","id":32,"title":"精准提分\u20142020高考物理万能解题模型（三）曲线运动"},{"coverImg":"http://192.168.0.120/yzyFiles/icons/5ae52669-1e5d-486c-bb4a-9aeb6f16130d1589523491215.jpg","id":33,"title":"直击2020高考.化学（三）\u2014\u2014揭秘元素推断问题"},{"coverImg":"http://192.168.0.120/yzyFiles/icons/cbeb6a79-b35a-4574-aea3-da0325e32d081589523552544.jpg","id":26,"title":"精准提分\u20142020高考物理万能解题模型（二）牛顿第二定律"},{"coverImg":"http://192.168.0.120/yzyFiles/icons/6a9c2dcc-0033-4c9e-8455-4cd5439533131589523258261.jpg","id":15,"title":"2020高考生物\u2014重难点分析专题集锦（上）"}],"vipTestpapaerList":[],"head_path":"http://192.168.0.120/yzyFiles/userHead/d19802bc-d7da-4b50-8bb7-d98037ae99b7-1580887870529.jpeg","vipCoursewareList":[{"id":11008,"title":"2020年高中毕业班第二次质量检测理科综合答案"},{"id":11004,"title":"2020年高中毕业班第二次质量检测文科综合答案"},{"id":11000,"title":"2020年高中毕业班第二次质量检测语文答案"},{"id":10996,"title":"2020年高中毕业班第二次质量检测理科数学答案"},{"id":10992,"title":"2020年高中毕业班第二次质量检测文科数学答案"},{"id":10988,"title":"2020年高中毕业班第二次质量检测英语答案"},{"id":10984,"title":"2020年高中毕业班第二次质量检测语文试题"},{"id":10980,"title":"2020年高中毕业班第二次质量检测英语试题"},{"id":10976,"title":"2020年高中毕业班第二次质量检测文科综合试题"},{"id":10972,"title":"2020年高中毕业班第二次质量检测文科数学试题"},{"id":10968,"title":"2020年高中毕业班第二次质量检测理科综合试题"},{"id":10964,"title":"2020年高中毕业班第二次质量检测理科数学试题"}],"nickname":"达达","vipExpireTime":"2021-07-13","vip":true}
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
         * vipCourseList : [{"coverImg":"http://192.168.0.120/yzyFiles/icons/9b282e50-1bab-4f67-bede-4495aeb3637b1589523452606.jpg","id":43,"title":"秒杀2020高考化学（含3节试听课）"},{"coverImg":"http://192.168.0.120/yzyFiles/icons/ecd51f7f-2e1a-4e94-86d3-46a9088839201589523586232.jpg","id":32,"title":"精准提分\u20142020高考物理万能解题模型（三）曲线运动"},{"coverImg":"http://192.168.0.120/yzyFiles/icons/5ae52669-1e5d-486c-bb4a-9aeb6f16130d1589523491215.jpg","id":33,"title":"直击2020高考.化学（三）\u2014\u2014揭秘元素推断问题"},{"coverImg":"http://192.168.0.120/yzyFiles/icons/cbeb6a79-b35a-4574-aea3-da0325e32d081589523552544.jpg","id":26,"title":"精准提分\u20142020高考物理万能解题模型（二）牛顿第二定律"},{"coverImg":"http://192.168.0.120/yzyFiles/icons/6a9c2dcc-0033-4c9e-8455-4cd5439533131589523258261.jpg","id":15,"title":"2020高考生物\u2014重难点分析专题集锦（上）"}]
         * vipTestpapaerList : []
         * head_path : http://192.168.0.120/yzyFiles/userHead/d19802bc-d7da-4b50-8bb7-d98037ae99b7-1580887870529.jpeg
         * vipCoursewareList : [{"id":11008,"title":"2020年高中毕业班第二次质量检测理科综合答案"},{"id":11004,"title":"2020年高中毕业班第二次质量检测文科综合答案"},{"id":11000,"title":"2020年高中毕业班第二次质量检测语文答案"},{"id":10996,"title":"2020年高中毕业班第二次质量检测理科数学答案"},{"id":10992,"title":"2020年高中毕业班第二次质量检测文科数学答案"},{"id":10988,"title":"2020年高中毕业班第二次质量检测英语答案"},{"id":10984,"title":"2020年高中毕业班第二次质量检测语文试题"},{"id":10980,"title":"2020年高中毕业班第二次质量检测英语试题"},{"id":10976,"title":"2020年高中毕业班第二次质量检测文科综合试题"},{"id":10972,"title":"2020年高中毕业班第二次质量检测文科数学试题"},{"id":10968,"title":"2020年高中毕业班第二次质量检测理科综合试题"},{"id":10964,"title":"2020年高中毕业班第二次质量检测理科数学试题"}]
         * nickname : 达达
         * vipExpireTime : 2021-07-13
         * vip : true
         */

        private String head_path;
        private String nickname;
        private String vipExpireTime;
        private boolean vip;
        private List<VipCourseListBean> vipCourseList;
        private List<VipCourseListBean> vipTestpapaerList;
        private List<VipCourseListBean> vipCoursewareList;

        public String getHead_path() {
            return head_path;
        }

        public void setHead_path(String head_path) {
            this.head_path = head_path;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getVipExpireTime() {
            return vipExpireTime;
        }

        public void setVipExpireTime(String vipExpireTime) {
            this.vipExpireTime = vipExpireTime;
        }

        public boolean isVip() {
            return vip;
        }

        public void setVip(boolean vip) {
            this.vip = vip;
        }

        public List<VipCourseListBean> getVipCourseList() {
            return vipCourseList;
        }

        public void setVipCourseList(List<VipCourseListBean> vipCourseList) {
            this.vipCourseList = vipCourseList;
        }

        public List<VipCourseListBean> getVipTestpapaerList() {
            return vipTestpapaerList;
        }

        public void setVipTestpapaerList(List<VipCourseListBean> vipTestpapaerList) {
            this.vipTestpapaerList = vipTestpapaerList;
        }

        public List<VipCourseListBean> getVipCoursewareList() {
            return vipCoursewareList;
        }

        public void setVipCoursewareList(List<VipCourseListBean> vipCoursewareList) {
            this.vipCoursewareList = vipCoursewareList;
        }

        public static class VipCourseListBean {
            public String getFile_path() {
                return file_path;
            }

            public void setFile_path(String file_path) {
                this.file_path = file_path;
            }
            private String scope;

            public String getScope() {
                return scope;
            }

            public void setScope(String scope) {
                this.scope = scope;
            }
            /**
             * coverImg : http://192.168.0.120/yzyFiles/icons/9b282e50-1bab-4f67-bede-4495aeb3637b1589523452606.jpg
             * id : 43
             * title : 秒杀2020高考化学（含3节试听课）
             */

            private String coverImg;
            private int id;
            private String title;
            private String file_path;
            public String getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(String coverImg) {
                this.coverImg = coverImg;
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

        public static class VipCoursewareListBean {
            /**
             * id : 11008
             * title : 2020年高中毕业班第二次质量检测理科综合答案
             */

            private int id;
            private String title;
            private String scope;

            public String getScope() {
                return scope;
            }

            public void setScope(String scope) {
                this.scope = scope;
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
}
