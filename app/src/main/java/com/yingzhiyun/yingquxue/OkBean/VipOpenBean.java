package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class VipOpenBean {


    /**
     * status : 200
     * hint : null
     * result : {"payTypeList":[{"id":1,"title":"余额","icon":"https://s2.ax1x.com/2019/10/24/KUFIhV.png","visible":true},{"id":3,"title":"微信","icon":"https://s2.ax1x.com/2019/10/24/KUihRO.png","visible":false}],"vipTypeList":[{"price":20,"title":"1个月","info":"20/月"},{"price":198,"discount":99.99,"title":"12个月","info":"16.5/月,测试折扣功能"},{"price":56,"title":"3个月","info":"18.7/月"}],"balance":4937.2}
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
         * payTypeList : [{"id":1,"title":"余额","icon":"https://s2.ax1x.com/2019/10/24/KUFIhV.png","visible":true},{"id":3,"title":"微信","icon":"https://s2.ax1x.com/2019/10/24/KUihRO.png","visible":false}]
         * vipTypeList : [{"price":20,"title":"1个月","info":"20/月"},{"price":198,"discount":99.99,"title":"12个月","info":"16.5/月,测试折扣功能"},{"price":56,"title":"3个月","info":"18.7/月"}]
         * balance : 4937.2
         */

        private double balance;
        private List<CoursePayBean.ResultBean.PayTypeListBean> payTypeList;
        private List<VipTypeListBean> vipTypeList;
        private String head_img;
        private String nickname;

        public String getHead_img() {
            return head_img;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public List<CoursePayBean.ResultBean.PayTypeListBean> getPayTypeList() {
            return payTypeList;
        }

        public void setPayTypeList(List<CoursePayBean.ResultBean.PayTypeListBean> payTypeList) {
            this.payTypeList = payTypeList;
        }

        public List<VipTypeListBean> getVipTypeList() {
            return vipTypeList;
        }

        public void setVipTypeList(List<VipTypeListBean> vipTypeList) {
            this.vipTypeList = vipTypeList;
        }

        public static class PayTypeListBean {
            /**
             * id : 1
             * title : 余额
             * icon : https://s2.ax1x.com/2019/10/24/KUFIhV.png
             * visible : true
             */

            private int id;
            private String title;
            private String icon;
            private boolean visible;

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

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public boolean isVisible() {
                return visible;
            }

            public void setVisible(boolean visible) {
                this.visible = visible;
            }
        }

        public static class VipTypeListBean {
            /**
             * price : 20
             * title : 1个月
             * info : 20/月
             * discount : 99.99
             */

            private String price;
            private String title;
            private String info;
            private String discount;
            private int id;
            private boolean subscribe;
            private String moreInfo;

            public String getMoreInfo() {
                return moreInfo;
            }

            public void setMoreInfo(String moreInfo) {
                this.moreInfo = moreInfo;
            }

            public boolean isSubscribe() {
                return subscribe;
            }

            public void setSubscribe(boolean subscribe) {
                this.subscribe = subscribe;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }
        }
    }
}
