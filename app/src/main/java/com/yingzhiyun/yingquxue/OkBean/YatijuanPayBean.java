package com.yingzhiyun.yingquxue.OkBean;

public class YatijuanPayBean {

    /**
     * status : 517
     * hint : 重复购买
     * result : {"betId":{"id":1,"title":"把舅舅灌醉从他手里套的2020年的高考题(文科)","advanceTime":"2020-03-27T22:04:26.000+0000","formallyTime":"2020-04-27T22:04:31.000+0000","status":1,"advancePrice":59,"formallyPrice":99,"imgUrl":"icons/3a91c620-9394-4d23-ae67-04307ad71dca1585383346335.png","imgWidth":750,"imgHeight":3840,"addTime":"2020-03-28T06:06:12.000+0000","updateId":666,"coverImg":null,"documentNum":4}}
     */

    private int status;
    private String hint;
    private WxPAyBean.ResultBean result;

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

    public WxPAyBean.ResultBean getResult() {
        return result;
    }

    public void setResult(WxPAyBean.ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * betId : {"id":1,"title":"把舅舅灌醉从他手里套的2020年的高考题(文科)","advanceTime":"2020-03-27T22:04:26.000+0000","formallyTime":"2020-04-27T22:04:31.000+0000","status":1,"advancePrice":59,"formallyPrice":99,"imgUrl":"icons/3a91c620-9394-4d23-ae67-04307ad71dca1585383346335.png","imgWidth":750,"imgHeight":3840,"addTime":"2020-03-28T06:06:12.000+0000","updateId":666,"coverImg":null,"documentNum":4}
         */

        private BetIdBean betId;

        public BetIdBean getBetId() {
            return betId;
        }

        public void setBetId(BetIdBean betId) {
            this.betId = betId;
        }

        public static class BetIdBean {
            /**
             * id : 1
             * title : 把舅舅灌醉从他手里套的2020年的高考题(文科)
             * advanceTime : 2020-03-27T22:04:26.000+0000
             * formallyTime : 2020-04-27T22:04:31.000+0000
             * status : 1
             * advancePrice : 59
             * formallyPrice : 99
             * imgUrl : icons/3a91c620-9394-4d23-ae67-04307ad71dca1585383346335.png
             * imgWidth : 750
             * imgHeight : 3840
             * addTime : 2020-03-28T06:06:12.000+0000
             * updateId : 666
             * coverImg : null
             * documentNum : 4
             */

            private int id;
            private String title;
            private String advanceTime;
            private String formallyTime;
            private int status;
            private int advancePrice;
            private int formallyPrice;
            private String imgUrl;
            private int imgWidth;
            private int imgHeight;
            private String addTime;
            private int updateId;
            private Object coverImg;
            private int documentNum;

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

            public String getAdvanceTime() {
                return advanceTime;
            }

            public void setAdvanceTime(String advanceTime) {
                this.advanceTime = advanceTime;
            }

            public String getFormallyTime() {
                return formallyTime;
            }

            public void setFormallyTime(String formallyTime) {
                this.formallyTime = formallyTime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getAdvancePrice() {
                return advancePrice;
            }

            public void setAdvancePrice(int advancePrice) {
                this.advancePrice = advancePrice;
            }

            public int getFormallyPrice() {
                return formallyPrice;
            }

            public void setFormallyPrice(int formallyPrice) {
                this.formallyPrice = formallyPrice;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public int getImgWidth() {
                return imgWidth;
            }

            public void setImgWidth(int imgWidth) {
                this.imgWidth = imgWidth;
            }

            public int getImgHeight() {
                return imgHeight;
            }

            public void setImgHeight(int imgHeight) {
                this.imgHeight = imgHeight;
            }

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public int getUpdateId() {
                return updateId;
            }

            public void setUpdateId(int updateId) {
                this.updateId = updateId;
            }

            public Object getCoverImg() {
                return coverImg;
            }

            public void setCoverImg(Object coverImg) {
                this.coverImg = coverImg;
            }

            public int getDocumentNum() {
                return documentNum;
            }

            public void setDocumentNum(int documentNum) {
                this.documentNum = documentNum;
            }
        }
    }
}
