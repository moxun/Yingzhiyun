package com.yingzhiyun.yingquxue.OkBean;

public class CollectBean   {


    /**
     * status : 200
     * hint : 收藏成功
     * result : {"collection_status":"1"}
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
         * collection_status : 1
         */

        private String collection_status;

        public String getCollection_status() {
            return collection_status;
        }

        public void setCollection_status(String collection_status) {
            this.collection_status = collection_status;
        }
    }
}
