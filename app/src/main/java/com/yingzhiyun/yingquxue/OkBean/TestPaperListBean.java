package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class TestPaperListBean {


    /**
     * status : 200
     * hint : null
     * result : [{"id":7,"title":"文科数学高二第四次周考"}]
     */

    private int status;
    private Object hint;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 7
         * title : 文科数学高二第四次周考
         */

        private int id;
        private String title;
        private String scope;
        public int getId() {
            return id;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
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
