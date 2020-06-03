package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class SelectedOptionsBean {

    /**
     * status : 200
     * hint : 查询成功
     * result : [[{"id":"0","title":"全部年级"},{"id":"7","title":"七年级"},{"id":"8","title":"八年级"},{"id":"9","title":"九年级"}],[{"id":"0","title":"全部科目"},{"id":2,"title":"语文"},{"id":3,"title":"数学"},{"id":4,"title":"英语"},{"id":15,"title":"物理"},{"id":16,"title":"化学"},{"id":17,"title":"生物"},{"id":18,"title":"政治"},{"id":19,"title":"历史"},{"id":20,"title":"地理"},{"id":21,"title":"科学"},{"id":22,"title":"历史与社会"},{"id":24,"title":"信息"}]]
     */

    private int status;
    private String hint;
    private List<List<ResultBean>> result;

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

    public List<List<ResultBean>> getResult() {
        return result;
    }

    public void setResult(List<List<ResultBean>> result) {
        this.result = result;
    }

    public static class ResultBean {
        public ResultBean(int id, String title) {
            this.id = id;
            this.title = title;

        }

        public ResultBean(int id, String title, String name) {
            this.id = id;
            this.title = title;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /**
         * id : 0
         * title : 全部年级
         */

        private int id;
        private String title;
        private String name;
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
