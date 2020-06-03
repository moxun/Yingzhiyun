package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class FullscoreBean {


    /**
     * command : 9
     * result : 1
     * errorMsg :
     * batchAvg : 38.122448
     * batchRank : 16
     * batchStudent : 213
     *
     * classAvg : 36
     * classRank : 1
     * classStudent : 284
     * doScore : 36
     * fullScore : 72
     * gradeAvg : 36
     * gradeList : [{"advantage":false,"doScore":36,"fullScore":72,"paperId":1,"subjectName":"英语"}]
     * gradeRank : 1
     * gradeStudent : 0
     */

    private int command;
    private int result;
    private String errorMsg;
    private double batchAvg;
    private int batchRank;
    private int batchStudent;
    private int classAvg;
    private int classRank;
    private int classStudent;
    private int doScore;
    private int fullScore;
    private int gradeAvg;
    private int gradeRank;
    private int gradeStudent;
    private List<GradeListBean> gradeList;

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public double getBatchAvg() {
        return batchAvg;
    }

    public void setBatchAvg(double batchAvg) {
        this.batchAvg = batchAvg;
    }

    public int getBatchRank() {
        return batchRank;
    }

    public void setBatchRank(int batchRank) {
        this.batchRank = batchRank;
    }

    public int getBatchStudent() {
        return batchStudent;
    }

    public void setBatchStudent(int batchStudent) {
        this.batchStudent = batchStudent;
    }

    public int getClassAvg() {
        return classAvg;
    }

    public void setClassAvg(int classAvg) {
        this.classAvg = classAvg;
    }

    public int getClassRank() {
        return classRank;
    }

    public void setClassRank(int classRank) {
        this.classRank = classRank;
    }

    public int getClassStudent() {
        return classStudent;
    }

    public void setClassStudent(int classStudent) {
        this.classStudent = classStudent;
    }

    public int getDoScore() {
        return doScore;
    }

    public void setDoScore(int doScore) {
        this.doScore = doScore;
    }

    public int getFullScore() {
        return fullScore;
    }

    public void setFullScore(int fullScore) {
        this.fullScore = fullScore;
    }

    public int getGradeAvg() {
        return gradeAvg;
    }

    public void setGradeAvg(int gradeAvg) {
        this.gradeAvg = gradeAvg;
    }

    public int getGradeRank() {
        return gradeRank;
    }

    public void setGradeRank(int gradeRank) {
        this.gradeRank = gradeRank;
    }

    public int getGradeStudent() {
        return gradeStudent;
    }

    public void setGradeStudent(int gradeStudent) {
        this.gradeStudent = gradeStudent;
    }

    public List<GradeListBean> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<GradeListBean> gradeList) {
        this.gradeList = gradeList;
    }

    public static class GradeListBean {
        /**
         * advantage : false
         * doScore : 36
         * fullScore : 72
         * paperId : 1
         * subjectName : 英语
         */

        private boolean advantage;
        private int doScore;
        private int fullScore;
        private int paperId;
        private String subjectName;

        public boolean isAdvantage() {
            return advantage;
        }

        public void setAdvantage(boolean advantage) {
            this.advantage = advantage;
        }

        public int getDoScore() {
            return doScore;
        }

        public void setDoScore(int doScore) {
            this.doScore = doScore;
        }

        public int getFullScore() {
            return fullScore;
        }

        public void setFullScore(int fullScore) {
            this.fullScore = fullScore;
        }

        public int getPaperId() {
            return paperId;
        }

        public void setPaperId(int paperId) {
            this.paperId = paperId;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }
    }
}
