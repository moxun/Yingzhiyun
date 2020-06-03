package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class FolderListOptionsBean {

    /**
     * status : 200
     * hint : null
     * result : [{"id":0,"title":"全部年级"},{"id":10,"title":"高一"},{"id":11,"title":"高二"},{"id":12,"title":"高三"}]
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

    public static class ResultBean {
        public ResultBean(int id, String title) {
            this.id = id;
            this.title = title;
        }

        /**
         * id : 0
         * title : 全部年级
         */

        private int id;
        private String title;

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
    }
}
