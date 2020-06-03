package com.yingzhiyun.yingquxue.OkBean;

import com.yingzhiyun.yingquxue.units.BaseExpandableRecyclerViewAdapter;

import java.io.Serializable;
import java.util.List;

public class KnowPointerBean implements Serializable {


    /**
     * status : 200
     * hint : 查询成功
     * result : [{"list":[{"title":"第一章、集合与函数的概念","list":[{"title":"1.1集合","list":[{"name":"三角函数","id":4},{"name":"导数","id":5}]},{"title":"1.2函数及其表示","list":[]},{"title":"1.3函数的基本性质","list":[{"name":"集合","id":6}]}]},{"title":"第二章、基本初等函数（1）","list":[{"title":"2.1指数函数","list":[{"name":"复数","id":7}]},{"title":"2.2对数函数","list":[{"name":"更改知识点名称","id":9}]},{"title":"2.3幂函数","list":[]}]},{"title":"第三章、函数的应用","list":[{"title":"3.1函数与方程","list":[{"name":"概率","id":8}]}]}],"title":"必修1"}]
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

    public static class ResultBean implements BaseExpandableRecyclerViewAdapter.BaseGroupBean<ResultBean.ListBeanXX> , Serializable{
        public boolean isIsboole() {
            return isboole;
        }

        public void setIsboole(boolean isboole) {
            this.isboole = isboole;
        }

        /**
         * list : [{"title":"第一章、集合与函数的概念","list":[{"title":"1.1集合","list":[{"name":"三角函数","id":4},{"name":"导数","id":5}]},{"title":"1.2函数及其表示","list":[]},{"title":"1.3函数的基本性质","list":[{"name":"集合","id":6}]}]},{"title":"第二章、基本初等函数（1）","list":[{"title":"2.1指数函数","list":[{"name":"复数","id":7}]},{"title":"2.2对数函数","list":[{"name":"更改知识点名称","id":9}]},{"title":"2.3幂函数","list":[]}]},{"title":"第三章、函数的应用","list":[{"title":"3.1函数与方程","list":[{"name":"概率","id":8}]}]}]
         * title : 必修1
         */

        private String title;
        private List<ListBeanXX> list;
        private boolean isboole;
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ListBeanXX> getList() {
            return list;
        }

        public void setList(List<ListBeanXX> list) {
            this.list = list;
        }

        @Override
        public int getChildCount() {
            return list.size();
        }

        @Override
        public ListBeanXX getChildAt(int childIndex) {
              return list.size() <= childIndex ? null : list.get(childIndex);
        }

        @Override
        public boolean isExpandable() {
            return getList().size()>0;
        }

        public static class ListBeanXX implements BaseExpandableRecyclerViewAdapter.BaseGroupBean<ListBeanXX.ListBeanX>, Serializable {
            /**
             * title : 第一章、集合与函数的概念
             * list : [{"title":"1.1集合","list":[{"name":"三角函数","id":4},{"name":"导数","id":5}]},{"title":"1.2函数及其表示","list":[]},{"title":"1.3函数的基本性质","list":[{"name":"集合","id":6}]}]
             */

            private String title;
            private List<ListBeanX> list;
            private boolean Isboole;

            public boolean isIsboole() {
                return Isboole;
            }

            public void setIsboole(boolean isboole) {
                Isboole = isboole;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ListBeanX> getList() {
                return list;
            }

            public void setList(List<ListBeanX> list) {
                this.list = list;
            }

            @Override
            public int getChildCount() {
                return list.size();
            }

            @Override
            public ListBeanX getChildAt(int childIndex) {
                return list.size() <= childIndex ? null : list.get(childIndex);
            }

            @Override
            public boolean isExpandable() {
                return getChildCount()>0;
            }

            public static class ListBeanX implements BaseExpandableRecyclerViewAdapter.BaseGroupBean<ListBeanX.ListBean> , Serializable{
                /**
                 * title : 1.1集合
                 * list : [{"name":"三角函数","id":4},{"name":"导数","id":5}]
                 */

                private String title;
                private List<ListBean> list;
                private boolean isboole;
                private int id;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public boolean isIsboole() {
                    return isboole;
                }

                public void setIsboole(boolean isboole) {
                    this.isboole = isboole;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public List<ListBean> getList() {
                    return list;
                }

                public void setList(List<ListBean> list) {
                    this.list = list;
                }

                @Override
                public int getChildCount() {
                    return list.size();
                }

                @Override
                public ListBean getChildAt(int childIndex) {
                    return list.size() <= childIndex ? null : list.get(childIndex);
                }

                @Override
                public boolean isExpandable() {
                    return getChildCount()>0;
                }

                public static class ListBean implements Serializable {
                    /**
                     * name : 三角函数
                     * id : 4
                     */

                    private String name;
                    private int id;
                    private boolean isboole;

                    public boolean isIsboole() {
                        return isboole;
                    }

                    public void setIsboole(boolean isboole) {
                        this.isboole = isboole;
                    }

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
    }
}
