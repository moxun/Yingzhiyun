package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class TeachingShaixuanBean {

    /**
     * status : 200
     * hint : null
     * result : {"bookVersion":[{"name":"人教版","id":1}],"subject":{"heightSchool":{"name":"高中","detail":[{"name":"语文","id":9},{"name":"数学","id":6},{"name":"英语","id":7},{"name":"物理","id":10},{"name":"化学","id":11},{"name":"生物","id":12},{"name":"政治","id":13},{"name":"历史","id":14},{"name":"地理","id":8}]},"middleSchool":{"name":"初中","detail":[{"name":"语文","id":2},{"name":"数学","id":3},{"name":"英语","id":4},{"name":"物理","id":15},{"name":"化学","id":16},{"name":"生物","id":17},{"name":"政治","id":18},{"name":"历史","id":19},{"name":"地理","id":20},{"name":"科学","id":21},{"name":"历史与社会","id":22},{"name":"信息","id":24}]}},"bookType":[{"name":"课本","id":1},{"name":"教案","id":2}]}
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
         * bookVersion : [{"name":"人教版","id":1}]
         * subject : {"heightSchool":{"name":"高中","detail":[{"name":"语文","id":9},{"name":"数学","id":6},{"name":"英语","id":7},{"name":"物理","id":10},{"name":"化学","id":11},{"name":"生物","id":12},{"name":"政治","id":13},{"name":"历史","id":14},{"name":"地理","id":8}]},"middleSchool":{"name":"初中","detail":[{"name":"语文","id":2},{"name":"数学","id":3},{"name":"英语","id":4},{"name":"物理","id":15},{"name":"化学","id":16},{"name":"生物","id":17},{"name":"政治","id":18},{"name":"历史","id":19},{"name":"地理","id":20},{"name":"科学","id":21},{"name":"历史与社会","id":22},{"name":"信息","id":24}]}}
         * bookType : [{"name":"课本","id":1},{"name":"教案","id":2}]
         */
        private SubjectBean grade;
        private SubjectBean subject;
        private List<SelectedOptionsBean.ResultBean> bookVersion;
        private List<SelectedOptionsBean.ResultBean> bookType;
        private List<SelectedOptionsBean.ResultBean> province;
        public SubjectBean getGrade() {
            return grade;
        }

        public List<SelectedOptionsBean.ResultBean> getProvince() {
            return province;
        }

        public void setProvince(List<SelectedOptionsBean.ResultBean> province) {
            this.province = province;
        }

        public void setGrade(SubjectBean grade) {
            this.grade = grade;
        }

        public SubjectBean getSubject() {
            return subject;
        }

        public void setSubject(SubjectBean subject) {
            this.subject = subject;
        }

        public List<SelectedOptionsBean.ResultBean> getBookVersion() {
            return bookVersion;
        }

        public void setBookVersion(List<SelectedOptionsBean.ResultBean> bookVersion) {
            this.bookVersion = bookVersion;
        }

        public List<SelectedOptionsBean.ResultBean> getBookType() {
            return bookType;
        }

        public void setBookType(List<SelectedOptionsBean.ResultBean> bookType) {
            this.bookType = bookType;
        }

        public static class SubjectBean {
            /**
             * heightSchool : {"name":"高中","detail":[{"name":"语文","id":9},{"name":"数学","id":6},{"name":"英语","id":7},{"name":"物理","id":10},{"name":"化学","id":11},{"name":"生物","id":12},{"name":"政治","id":13},{"name":"历史","id":14},{"name":"地理","id":8}]}
             * middleSchool : {"name":"初中","detail":[{"name":"语文","id":2},{"name":"数学","id":3},{"name":"英语","id":4},{"name":"物理","id":15},{"name":"化学","id":16},{"name":"生物","id":17},{"name":"政治","id":18},{"name":"历史","id":19},{"name":"地理","id":20},{"name":"科学","id":21},{"name":"历史与社会","id":22},{"name":"信息","id":24}]}
             */

            private HeightSchoolBean heightSchool;
            private MiddleSchoolBean middleSchool;

            public HeightSchoolBean getHeightSchool() {
                return heightSchool;
            }

            public void setHeightSchool(HeightSchoolBean heightSchool) {
                this.heightSchool = heightSchool;
            }

            public MiddleSchoolBean getMiddleSchool() {
                return middleSchool;
            }

            public void setMiddleSchool(MiddleSchoolBean middleSchool) {
                this.middleSchool = middleSchool;
            }

            public static class HeightSchoolBean {
                /**
                 * name : 高中
                 * detail : [{"name":"语文","id":9},{"name":"数学","id":6},{"name":"英语","id":7},{"name":"物理","id":10},{"name":"化学","id":11},{"name":"生物","id":12},{"name":"政治","id":13},{"name":"历史","id":14},{"name":"地理","id":8}]
                 */

                private String name;
                private List<SelectedOptionsBean.ResultBean> detail;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<SelectedOptionsBean.ResultBean> getDetail() {
                    return detail;
                }

                public void setDetail(List<SelectedOptionsBean.ResultBean> detail) {
                    this.detail = detail;
                }

                public static class DetailBean {
                    /**
                     * name : 语文
                     * id : 9
                     */

                    private String name;
                    private int id;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }
                }
            }

            public static class MiddleSchoolBean {
                /**
                 * name : 初中
                 * detail : [{"name":"语文","id":2},{"name":"数学","id":3},{"name":"英语","id":4},{"name":"物理","id":15},{"name":"化学","id":16},{"name":"生物","id":17},{"name":"政治","id":18},{"name":"历史","id":19},{"name":"地理","id":20},{"name":"科学","id":21},{"name":"历史与社会","id":22},{"name":"信息","id":24}]
                 */

                private String name;
                private List<SelectedOptionsBean.ResultBean> detail;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<SelectedOptionsBean.ResultBean> getDetail() {
                    return detail;
                }

                public void setDetail(List<SelectedOptionsBean.ResultBean> detail) {
                    this.detail = detail;
                }

                public static class DetailBeanX {
                    /**
                     * name : 语文
                     * id : 2
                     */

                    private String name;
                    private int id;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }
                }
            }
        }

        public static class BookVersionBean {
            /**
             * name : 人教版
             * id : 1
             */

            private String name;
            private int id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public static class BookTypeBean {
            /**
             * name : 课本
             * id : 1
             */

            private String name;
            private int id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
