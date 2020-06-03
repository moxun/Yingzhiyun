package com.yingzhiyun.yingquxue.OkBean;

import com.google.gson.annotations.SerializedName;
import com.yingzhiyun.yingquxue.adapter.P;

public class WxPAyBean {


    /**
     * status : 200
     * hint : null
     * result : {"package":"Sign=WXPay","appid":"wx07c1fa2b28ba0dfa","sign":"c2219cf224a4ffc3ea7d547a81973c52","partnerid":"1562296701","prepayid":"wx12103745253817f564c22ba61433098300","noncestr":"3de010932c7e414188eb35d39b8068b6","timestamp":1573526264}
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
         * package : Sign=WXPay
         * appid : wx07c1fa2b28ba0dfa
         * sign : c2219cf224a4ffc3ea7d547a81973c52
         * partnerid : 1562296701
         * prepayid : wx12103745253817f564c22ba61433098300
         * noncestr : 3de010932c7e414188eb35d39b8068b6
         * timestamp : 1573526264
         */

        @SerializedName("package")
        private String packageX;
        private String appid;
        private String sign;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private int timestamp;
        private int courseId;

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }
    }
}

