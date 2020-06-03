package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class KnowledgeBean {

    /**
     * status : 200
     * hint : 查询成功
     * result : {"knowledgePointsList":[{"name":"三角函数","id":4},{"name":"导数","id":5},{"name":"集合","id":6},{"name":"复数","id":7},{"name":"概率","id":8}],"questionType":[{"array":[{"value":5},{"value":10},{"value":15}],"name":"选择题","id":1},{"array":[{"value":4},{"value":8},{"value":12}],"name":"填空题","id":2},{"array":[{"value":2},{"value":4},{"value":6}],"name":"解答题","id":3},{"array":[{"value":1},{"value":2},{"value":3}],"name":"完形填空","id":4},{"array":[{"value":1},{"value":2},{"value":3}],"name":"材料阅读","id":5},{"array":[{"value":1},{"value":2},{"value":3}],"name":"阅读理解","id":6}]}
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

        private List<KnowledgePointsListBean> knowledgePointsList;
        private List<QuestionTypeBean> questionType;

        public List<KnowledgePointsListBean> getKnowledgePointsList() {
            return knowledgePointsList;
        }

        public void setKnowledgePointsList(List<KnowledgePointsListBean> knowledgePointsList) {
            this.knowledgePointsList = knowledgePointsList;
        }

        public List<QuestionTypeBean> getQuestionType() {
            return questionType;
        }

        public void setQuestionType(List<QuestionTypeBean> questionType) {
            this.questionType = questionType;
        }

        public static class KnowledgePointsListBean {
            /**
             * name : 三角函数
             * id : 4
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

        public static class QuestionTypeBean {
            public QuestionTypeBean(String name, int id, List<ArrayBean> array) {
                this.name = name;
                this.id = id;
                this.array = array;
            }

            /**
             * array : [{"value":5},{"value":10},{"value":15}]
             * name : 选择题
             * id : 1
             */

            private String name;
            private int id;
            private List<ArrayBean> array;
            private int type;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
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

            public List<ArrayBean> getArray() {
                return array;
            }

            public void setArray(List<ArrayBean> array) {
                this.array = array;
            }

            public static class ArrayBean {
                public ArrayBean(int value) {
                    this.value = value;
                }

                /**
                 * value : 5
                 */

                private int value;

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            }
        }
    }
}
