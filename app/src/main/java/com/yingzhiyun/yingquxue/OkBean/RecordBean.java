package com.yingzhiyun.yingquxue.OkBean;

import java.io.Serializable;
import java.util.List;

public class RecordBean implements Serializable {


    /**
     * status : 200
     * hint : null
     * result : [{"transactionType":2,"totalFree":0.01,"outTradeNo":"201911121731468f489a","id":36,"time":"2019-11-12 17:32","type":2,"body":"应趣学--趣学币充值"},{"transactionType":2,"totalFree":0.01,"outTradeNo":"201911121733a85bf3f1","id":37,"time":"2019-11-12 17:33","type":2,"body":"应趣学--趣学币充值"},{"transactionType":2,"totalFree":0.01,"outTradeNo":"20191113101634b7578c","id":41,"time":"2019-11-13 10:17","type":2,"body":"应趣学--趣学币充值"},{"transactionType":1,"totalFree":0.01,"outTradeNo":"20191115156edc39727","id":87,"time":"2019-11-15 15:06","type":1,"body":"课程购买-精准提分\u2014\u2014物理万能解题模型（一）"},{"transactionType":2,"totalFree":0.01,"outTradeNo":"201911151510f27f0bc0","id":89,"time":"2019-11-15 15:10","type":1,"body":"应趣学--趣学币充值"},{"transactionType":1,"totalFree":0.01,"outTradeNo":"201911151511a061feac","id":90,"time":"2019-11-15 15:11","type":1,"body":"应趣学--《王者归来-2020高考英语书面表达高分秘籍》"}]
     */

    private int status;
    private String hint;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean  implements Serializable {
        /**
         * transactionType : 2
         * totalFree : 0.01
         * outTradeNo : 201911121731468f489a
         * id : 36
         * time : 2019-11-12 17:32
         * type : 2
         * body : 应趣学--趣学币充值
         */

        private int transactionType;
        private double totalFree;
        private String outTradeNo;
        private int id;
        private String time;
        private int type;
        private String body;
        private String refundStatus;

        public String getRefundStatus() {
            return refundStatus;
        }

        public void setRefundStatus(String refundStatus) {
            this.refundStatus = refundStatus;
        }

        public int getTransactionType() {
            return transactionType;
        }

        public void setTransactionType(int transactionType) {
            this.transactionType = transactionType;
        }

        public double getTotalFree() {
            return totalFree;
        }

        public void setTotalFree(double totalFree) {
            this.totalFree = totalFree;
        }

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
