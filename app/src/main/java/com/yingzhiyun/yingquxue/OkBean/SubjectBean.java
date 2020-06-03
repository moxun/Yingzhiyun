package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class SubjectBean {

    /**
     * status : 200
     * hint : null
     * result : [{"name":"语文","subjectId":2},{"name":"数学","subjectId":3},{"name":"英语","subjectId":4},{"name":"物理","subjectId":15},{"name":"化学","subjectId":16},{"name":"生物","subjectId":17},{"name":"政治","subjectId":18},{"name":"历史","subjectId":19},{"name":"地理","subjectId":20},{"name":"科学","subjectId":21},{"name":"历史与社会","subjectId":22},{"name":"信息","subjectId":24}]
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
         * name : 语文
         * subjectId : 2
         */

        private String name;
        private int subjectId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(int subjectId) {
            this.subjectId = subjectId;
        }
    }
}
