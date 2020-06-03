package com.yingzhiyun.yingquxue.OkBean;

import java.io.Serializable;
import java.util.List;

public class InteractionsListBean implements Serializable {


    /**
     * status : 200
     * hint : null
     * result : [{"userHeadImgPath":null,"addTime":"2019-08-17 02:05:35","contentString":"啊啊啊啊","userNickname":"17835074370","id":12},{"userHeadImgPath":null,"addTime":"2019-08-17 02:05:34","contentString":"啊啊啊啊","userNickname":"17835074370","id":11},{"userHeadImgPath":null,"addTime":"2019-08-14 19:23:59","contentString":"8*9为什么等于2有没有人知道","userNickname":"15565183652","id":2},{"userHeadImgPath":null,"addTime":"2019-08-14 18:34:42","contentString":"1+1为什么等于2有没有人知道","userNickname":"15565183652","id":1}]
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

    public static class ResultBean implements Serializable{
        /**
         * userHeadImgPath : null
         * addTime : 2019-08-17 02:05:35
         * contentString : 啊啊啊啊
         * userNickname : 17835074370
         * id : 12
         */

        private Object userHeadImgPath;
        private String addTime;
        private String contentString;
        private String userNickname;
        private int id;

        public Object getUserHeadImgPath() {
            return userHeadImgPath;
        }

        public void setUserHeadImgPath(Object userHeadImgPath) {
            this.userHeadImgPath = userHeadImgPath;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getContentString() {
            return contentString;
        }

        public void setContentString(String contentString) {
            this.contentString = contentString;
        }

        public String getUserNickname() {
            return userNickname;
        }

        public void setUserNickname(String userNickname) {
            this.userNickname = userNickname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
