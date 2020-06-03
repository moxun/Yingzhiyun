package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class AcoreQueryBean {


    /**
     * status : 200
     * hint : 查询成功
     * result : {"qteMapList":[{"name":"选择题","count":2}],"kptNum":2,"userTime":"0","worstKpt":"全集及其运算","userScore":"9","userKptMapList":[{"total":1,"name":"集合的相等","right":1},{"total":1,"name":"全集及其运算","right":1}],"score":"150","total":3,"userQteMapList":[{"total":1,"name":"选择题","right":1},{"total":1,"name":"选择题","right":1}],"kptMapList":[{"name":"集合的相等","count":1},{"name":"全集及其运算","count":1}],"avgTime":"0","avgScore":8,"questionNum":2,"rank":2,"time":"120"}
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
         * qteMapList : [{"name":"选择题","count":2}]
         * kptNum : 2
         * userTime : 0
         * worstKpt : 全集及其运算
         * userScore : 9
         * userKptMapList : [{"total":1,"name":"集合的相等","right":1},{"total":1,"name":"全集及其运算","right":1}]
         * score : 150
         * total : 3
         * userQteMapList : [{"total":1,"name":"选择题","right":1},{"total":1,"name":"选择题","right":1}]
         * kptMapList : [{"name":"集合的相等","count":1},{"name":"全集及其运算","count":1}]
         * avgTime : 0
         * avgScore : 8
         * questionNum : 2
         * rank : 2
         * time : 120
         */

        private int kptNum;
        private String userTime;
        private String worstKpt;
        private String userScore;
        private String score;
        private int total;
        private String avgTime;
        private int avgScore;
        private int questionNum;
        private int rank;
        private String time;
        private List<QteMapListBean> qteMapList;
        private List<UserKptMapListBean> userKptMapList;
        private List<UserQteMapListBean> userQteMapList;
        private List<KptMapListBean> kptMapList;

        public int getKptNum() {
            return kptNum;
        }

        public void setKptNum(int kptNum) {
            this.kptNum = kptNum;
        }

        public String getUserTime() {
            return userTime;
        }

        public void setUserTime(String userTime) {
            this.userTime = userTime;
        }

        public String getWorstKpt() {
            return worstKpt;
        }

        public void setWorstKpt(String worstKpt) {
            this.worstKpt = worstKpt;
        }

        public String getUserScore() {
            return userScore;
        }

        public void setUserScore(String userScore) {
            this.userScore = userScore;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getAvgTime() {
            return avgTime;
        }

        public void setAvgTime(String avgTime) {
            this.avgTime = avgTime;
        }

        public int getAvgScore() {
            return avgScore;
        }

        public void setAvgScore(int avgScore) {
            this.avgScore = avgScore;
        }

        public int getQuestionNum() {
            return questionNum;
        }

        public void setQuestionNum(int questionNum) {
            this.questionNum = questionNum;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<QteMapListBean> getQteMapList() {
            return qteMapList;
        }

        public void setQteMapList(List<QteMapListBean> qteMapList) {
            this.qteMapList = qteMapList;
        }

        public List<UserKptMapListBean> getUserKptMapList() {
            return userKptMapList;
        }

        public void setUserKptMapList(List<UserKptMapListBean> userKptMapList) {
            this.userKptMapList = userKptMapList;
        }

        public List<UserQteMapListBean> getUserQteMapList() {
            return userQteMapList;
        }

        public void setUserQteMapList(List<UserQteMapListBean> userQteMapList) {
            this.userQteMapList = userQteMapList;
        }

        public List<KptMapListBean> getKptMapList() {
            return kptMapList;
        }

        public void setKptMapList(List<KptMapListBean> kptMapList) {
            this.kptMapList = kptMapList;
        }

        public static class QteMapListBean {
            /**
             * name : 选择题
             * count : 2
             */

            private String name;
            private int count;
            private int right;

            public int getRight() {
                return right;
            }

            public void setRight(int right) {
                this.right = right;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        public static class UserKptMapListBean {
            /**
             * total : 1
             * name : 集合的相等
             * right : 1
             */

            private int total;
            private String name;
            private int right;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getRight() {
                return right;
            }

            public void setRight(int right) {
                this.right = right;
            }
        }

        public static class UserQteMapListBean {
            /**
             * total : 1
             * name : 选择题
             * right : 1
             */

            private int total;
            private String name;
            private int right;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getRight() {
                return right;
            }

            public void setRight(int right) {
                this.right = right;
            }
        }

        public static class KptMapListBean {
            /**
             * name : 集合的相等
             * count : 1
             */

            private String name;
            private int count;
            private int right;

            public int getRight() {
                return right;
            }

            public void setRight(int right) {
                this.right = right;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }
    }
}
