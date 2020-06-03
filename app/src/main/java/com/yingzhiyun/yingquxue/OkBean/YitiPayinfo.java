package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class YitiPayinfo  {

    /**
     * status : 200
     * hint : null
     * result : {"msg":"当前余额5393.20趣学币可以完成支付","payTypeList":[{"id":1,"title":"余额","icon":"https://s2.ax1x.com/2019/10/24/KUFIhV.png","visible":true},{"id":3,"title":"微信","icon":"https://s2.ax1x.com/2019/10/24/KUihRO.png","visible":false}],"isEnough":true,"price":59,"title":"把舅舅灌醉从他手里套的2020年的高考题(文科)"}
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

    public static class ResultBean {
        /**
         * msg : 当前余额5393.20趣学币可以完成支付
         * payTypeList : [{"id":1,"title":"余额","icon":"https://s2.ax1x.com/2019/10/24/KUFIhV.png","visible":true},{"id":3,"title":"微信","icon":"https://s2.ax1x.com/2019/10/24/KUihRO.png","visible":false}]
         * isEnough : true
         * price : 59
         * title : 把舅舅灌醉从他手里套的2020年的高考题(文科)
         */

        private String msg;
        private boolean isEnough;
        private double price;
        private String title;
        private List<CoursePayBean.ResultBean.PayTypeListBean> payTypeList;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public boolean isIsEnough() {
            return isEnough;
        }

        public void setIsEnough(boolean isEnough) {
            this.isEnough = isEnough;
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

        public List<CoursePayBean.ResultBean.PayTypeListBean> getPayTypeList() {
            return payTypeList;
        }

        public void setPayTypeList(List<CoursePayBean.ResultBean.PayTypeListBean> payTypeList) {
            this.payTypeList = payTypeList;
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
    }
}
