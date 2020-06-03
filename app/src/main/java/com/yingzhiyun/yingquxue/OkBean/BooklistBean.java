package com.yingzhiyun.yingquxue.OkBean;

import java.io.Serializable;
import java.util.List;

public class BooklistBean implements Serializable {


    /**
     * status : 200
     * hint : 查询成功
     * result : [{"id":1,"title":"高一上"},{"id":4,"title":"高一下"}]
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
         * id : 1
         * title : 高一上
         */

        private int id;
        private String title;
        private String img;
        private boolean isChoose;

        public boolean isChoose() {
            return isChoose;
        }

        public void setChoose(boolean choose) {
            isChoose = choose;
        }

        public int getId() {
            return id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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
