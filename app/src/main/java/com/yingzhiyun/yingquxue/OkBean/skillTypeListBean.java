package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class skillTypeListBean {


    /**
     * status : 200
     * hint : null
     * result : {"ranking":[{"id":1,"title":"综合排序"},{"id":2,"title":"人气排序"}],"type":[{"id":6,"detail":[{"id":7,"detail":[{"id":26,"title":"c++"},{"id":27,"title":"c#"},{"id":28,"title":"java"},{"id":29,"title":"php"},{"id":30,"title":"python"},{"id":31,"title":"go"}],"title":"软件开发"}],"title":"职业技能"}]}
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
        private List<RankingBean> ranking;
        private List<TypeBean> type;

        public List<RankingBean> getRanking() {
            return ranking;
        }

        public void setRanking(List<RankingBean> ranking) {
            this.ranking = ranking;
        }

        public List<TypeBean> getType() {
            return type;
        }

        public void setType(List<TypeBean> type) {
            this.type = type;
        }

        public static class RankingBean {
            /**
             * id : 1
             * title : 综合排序
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

        public static class TypeBean {
            /**
             * id : 6
             * detail : [{"id":7,"detail":[{"id":26,"title":"c++"},{"id":27,"title":"c#"},{"id":28,"title":"java"},{"id":29,"title":"php"},{"id":30,"title":"python"},{"id":31,"title":"go"}],"title":"软件开发"}]
             * title : 职业技能
             */

            private int id;
            private String title;
            private List<DetailBeanX> detail;

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

            public List<DetailBeanX> getDetail() {
                return detail;
            }

            public void setDetail(List<DetailBeanX> detail) {
                this.detail = detail;
            }

            public static class DetailBeanX {
                /**
                 * id : 7
                 * detail : [{"id":26,"title":"c++"},{"id":27,"title":"c#"},{"id":28,"title":"java"},{"id":29,"title":"php"},{"id":30,"title":"python"},{"id":31,"title":"go"}]
                 * title : 软件开发
                 */

                private int id;
                private String title;
                private List<DetailBean> detail;

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

                public List<DetailBean> getDetail() {
                    return detail;
                }

                public void setDetail(List<DetailBean> detail) {
                    this.detail = detail;
                }

                public static class DetailBean {
                    /**
                     * id : 26
                     * title : c++
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
        }
    }
}
