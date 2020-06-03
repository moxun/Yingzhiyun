package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class TestPaperBean {

    /**
     * status : 200
     * hint : 组卷完毕
     * result : {"totalRight":null,"id":61,"title":"手动组卷练习--2019-07-29 10:58:29","totalSize":0,"daTiBeanList":[{"totalSize":0,"title":"选择题","detail":null,"totalScore":null,"stemBeanList":[],"stemIdList":[]}],"testPaperOutlineBean":{"title":"手动组卷练习--2019-07-29 10:58:29","totalScore":0,"totalSize":0,"content":[{"size":0,"title":"选择题","totalScore":null}]},"score":0}
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
         * totalRight : null
         * id : 61
         * title : 手动组卷练习--2019-07-29 10:58:29
         * totalSize : 0
         * daTiBeanList : [{"totalSize":0,"title":"选择题","detail":null,"totalScore":null,"stemBeanList":[],"stemIdList":[]}]
         * testPaperOutlineBean : {"title":"手动组卷练习--2019-07-29 10:58:29","totalScore":0,"totalSize":0,"content":[{"size":0,"title":"选择题","totalScore":null}]}
         * score : 0
         */

        private Object totalRight;
        private int id;
        private String title;
        private int totalSize;
        private TestPaperOutlineBeanBean testPaperOutlineBean;
        private int score;
        private List<DaTiBeanListBean> daTiBeanList;

        public Object getTotalRight() {
            return totalRight;
        }

        public void setTotalRight(Object totalRight) {
            this.totalRight = totalRight;
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

        public int getTotalSize() {
            return totalSize;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public TestPaperOutlineBeanBean getTestPaperOutlineBean() {
            return testPaperOutlineBean;
        }

        public void setTestPaperOutlineBean(TestPaperOutlineBeanBean testPaperOutlineBean) {
            this.testPaperOutlineBean = testPaperOutlineBean;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<DaTiBeanListBean> getDaTiBeanList() {
            return daTiBeanList;
        }

        public void setDaTiBeanList(List<DaTiBeanListBean> daTiBeanList) {
            this.daTiBeanList = daTiBeanList;
        }

        public static class TestPaperOutlineBeanBean {
            /**
             * title : 手动组卷练习--2019-07-29 10:58:29
             * totalScore : 0
             * totalSize : 0
             * content : [{"size":0,"title":"选择题","totalScore":null}]
             */

            private String title;
            private int totalScore;
            private int totalSize;
            private List<ContentBean> content;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getTotalScore() {
                return totalScore;
            }

            public void setTotalScore(int totalScore) {
                this.totalScore = totalScore;
            }

            public int getTotalSize() {
                return totalSize;
            }

            public void setTotalSize(int totalSize) {
                this.totalSize = totalSize;
            }

            public List<ContentBean> getContent() {
                return content;
            }

            public void setContent(List<ContentBean> content) {
                this.content = content;
            }

            public static class ContentBean {
                /**
                 * size : 0
                 * title : 选择题
                 * totalScore : null
                 */

                private int size;
                private String title;
                private Object totalScore;

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public Object getTotalScore() {
                    return totalScore;
                }

                public void setTotalScore(Object totalScore) {
                    this.totalScore = totalScore;
                }
            }
        }

        public static class DaTiBeanListBean {
            /**
             * totalSize : 0
             * title : 选择题
             * detail : null
             * totalScore : null
             * stemBeanList : []
             * stemIdList : []
             */

            private int totalSize;
            private String title;
            private Object detail;
            private Object totalScore;
            private List<?> stemBeanList;
            private List<?> stemIdList;

            public int getTotalSize() {
                return totalSize;
            }

            public void setTotalSize(int totalSize) {
                this.totalSize = totalSize;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getDetail() {
                return detail;
            }

            public void setDetail(Object detail) {
                this.detail = detail;
            }

            public Object getTotalScore() {
                return totalScore;
            }

            public void setTotalScore(Object totalScore) {
                this.totalScore = totalScore;
            }

            public List<?> getStemBeanList() {
                return stemBeanList;
            }

            public void setStemBeanList(List<?> stemBeanList) {
                this.stemBeanList = stemBeanList;
            }

            public List<?> getStemIdList() {
                return stemIdList;
            }

            public void setStemIdList(List<?> stemIdList) {
                this.stemIdList = stemIdList;
            }
        }
    }
}
