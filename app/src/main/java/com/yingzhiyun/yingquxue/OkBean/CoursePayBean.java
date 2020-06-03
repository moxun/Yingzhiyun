package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class CoursePayBean {


    /**
     * status : 200
     * hint : null
     * result : {"msg":"当前余额0.0趣学币，需充值49.0趣学币","effective":"长期有效","payTypeList":[{"id":1,"title":"余额","icon":"https://s2.ax1x.com/2019/10/24/KUFIhV.png"},{"id":2,"title":"支付宝","icon":"https://s2.ax1x.com/2019/10/24/KUiNaq.png"},{"id":3,"title":"微信","icon":"https://s2.ax1x.com/2019/10/24/KUihRO.png"}],"isEnough":false,"subject":"数学","price":49,"title":"《直击2020高考--函数核心考点全覆盖》"}
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

    public String  getHint() {
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

    public static class ResultBean {
        /**
         * msg : 当前余额0.0趣学币，需充值49.0趣学币
         * effective : 长期有效
         * payTypeList : [{"id":1,"title":"余额","icon":"https://s2.ax1x.com/2019/10/24/KUFIhV.png"},{"id":2,"title":"支付宝","icon":"https://s2.ax1x.com/2019/10/24/KUiNaq.png"},{"id":3,"title":"微信","icon":"https://s2.ax1x.com/2019/10/24/KUihRO.png"}]
         * isEnough : false
         * subject : 数学
         * price : 49
         * title : 《直击2020高考--函数核心考点全覆盖》
         */

        private String msg;
        private String effective;
        private boolean isEnough;
        private String subject;
        private double price;
        private String title;
        private List<PayTypeListBean> payTypeList;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getEffective() {
            return effective;
        }

        public void setEffective(String effective) {
            this.effective = effective;
        }

        public boolean isIsEnough() {
            return isEnough;
        }

        public void setIsEnough(boolean isEnough) {
            this.isEnough = isEnough;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<PayTypeListBean> getPayTypeList() {
            return payTypeList;
        }

        public void setPayTypeList(List<PayTypeListBean> payTypeList) {
            this.payTypeList = payTypeList;
        }

        public static class PayTypeListBean {
            /**
             * id : 1
             * title : 余额
             * icon : https://s2.ax1x.com/2019/10/24/KUFIhV.png
             */

            private int id;
            private String title;
            private String icon;
            private boolean visible;

            public boolean isVisible() {
                return visible;
            }

            public void setVisible(boolean visible) {
                this.visible = visible;
            }

            public PayTypeListBean(int id, String title, String icon) {
                this.id = id;
                this.title = title;
                this.icon = icon;
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

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
