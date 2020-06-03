package com.yingzhiyun.yingquxue.OkBean;

import com.yingzhiyun.yingquxue.units.BaseExpandableRecyclerViewAdapter;

import java.util.List;

public class ChapterListBean implements BaseExpandableRecyclerViewAdapter.BaseGroupBean<ChapterListBean.ResultBean> {

    /**
     * status : 200
     * hint : 查询成功
     * result : [{"detail":[{"testPaperId":6,"title":"Unit 1 Friendship"},{"testPaperId":null,"title":"Unit 2 English around the world"}],"title":"必修1"},{"detail":[],"title":"必修2"}]
     */

    private int status;
    private String hint;
    private List<ResultBean> result;
    private String booktitle;
    private boolean isboole;

    public boolean isIsboole() {
        return isboole;
    }

    public void setIsboole(boolean isboole) {
        this.isboole = isboole;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

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

    @Override
    public int getChildCount() {
        return result.size();
    }

    @Override
    public ResultBean getChildAt(int childIndex) {
        return result.size() <= childIndex ? null : result.get(childIndex);
    }

    @Override
    public boolean isExpandable() {
        return getChildCount()>0;
    }

    public static class ResultBean implements BaseExpandableRecyclerViewAdapter.BaseGroupBean<ResultBean.DetailBean> {
        /**
         * detail : [{"testPaperId":6,"title":"Unit 1 Friendship"},{"testPaperId":null,"title":"Unit 2 English around the world"}]
         * title : 必修1
         */

        private String title;
        private List<DetailBean> detail;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DetailBean> getDetail() {
            return detail;
        }

        public void setDetail(List<DetailBean> detail) {
            this.detail = detail;
        }

        @Override
        public int getChildCount() {
            return detail.size();
        }

        @Override
        public DetailBean getChildAt(int childIndex) {
            return detail.size() <= childIndex ? null : detail.get(childIndex);
    }

        @Override
        public boolean isExpandable() {
            return getChildCount() > 0;
        }

        public static class DetailBean {
            /**
             * testPaperId : 6
             * title : Unit 1 Friendship
             */

            private int testPaperId;
            private String title;

            public int getTestPaperId() {
                return testPaperId;
            }

            public void setTestPaperId(int testPaperId) {
                this.testPaperId = testPaperId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
