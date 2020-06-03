package com.yingzhiyun.yingquxue.OkBean;

public class BetDetailBean {


    /**
     * status : 200
     * hint : null
     * result : {"img":"http://192.168.0.120/yzyFiles/icons/c1556aa3-9f1b-4bc2-a8d8-b9f50a8ff51d1585379454219.png","imgWidth":750,"imgHeight":3840,"id":1,"title":"把舅舅灌醉从他手里套的2020年的高考题(文科)","isSign":false}
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
         * img : http://192.168.0.120/yzyFiles/icons/c1556aa3-9f1b-4bc2-a8d8-b9f50a8ff51d1585379454219.png
         * imgWidth : 750
         * imgHeight : 3840
         * id : 1
         * title : 把舅舅灌醉从他手里套的2020年的高考题(文科)
         * isSign : false
         */

        private String img;
        private int imgWidth;
        private int imgHeight;
        private int id;
        private String title;
        private boolean isSign;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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

        public boolean isIsSign() {
            return isSign;
        }

        public void setIsSign(boolean isSign) {
            this.isSign = isSign;
        }
    }
}
