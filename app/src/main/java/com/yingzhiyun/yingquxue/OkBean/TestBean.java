package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class TestBean {


    /**
     * command : 8
     * result : 1
     * errorMsg : 没毛病
     * batchList : [{"time":"2019-11-11","name":"联考1","score":566.5,"gradeId":1,"batchId":2,"batchRank":222,"gradeRank":22,"classRank":2,"paperList":[]},{"time":"2019-11-11","name":"联考2","score":576.5,"gradeId":1,"batchId":2,"batchRank":111,"gradeRank":11,"classRank":1,"paperList":[]},{"time":"2019-11-11","name":"联考3","score":596.5,"gradeId":1,"batchId":2,"batchRank":222,"gradeRank":22,"classRank":2,"paperList":[]},{"time":"2019-11-11","name":"联考4","score":516.5,"gradeId":1,"batchId":2,"batchRank":999,"gradeRank":99,"classRank":9,"paperList":[]},{"time":"2019-11-11","name":"联考5","score":536.5,"gradeId":1,"batchId":2,"batchRank":333,"gradeRank":33,"classRank":3,"paperList":[]},{"time":"2019-11-11","name":"联考6","score":556.5,"gradeId":1,"batchId":2,"batchRank":555,"gradeRank":55,"classRank":5,"paperList":[]},{"time":"2019-11-11","name":"联考7","score":596.5,"gradeId":1,"batchId":2,"batchRank":100,"gradeRank":7,"classRank":1,"paperList":[]},{"time":"2019-11-11","name":"联考8","score":616.5,"gradeId":1,"batchId":2,"batchRank":99,"gradeRank":22,"classRank":1,"paperList":[]},{"time":"2019-11-11","name":"联考9","score":506.5,"gradeId":1,"batchId":2,"batchRank":888,"gradeRank":92,"classRank":7,"paperList":[]}]
     * student : {"idNumber":"123456","schoolId":1,"gradeId":2,"classId":3}
     */

    private int command;
    private int result;
    private String errorMsg;
    private StudentBean student;
    private List<BatchListBean> batchList;

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

    public StudentBean getStudent() {
        return student;
    }

    public void setStudent(StudentBean student) {
        this.student = student;
    }

    public List<BatchListBean> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<BatchListBean> batchList) {
        this.batchList = batchList;
    }

    public static class StudentBean {
        /**
         * idNumber : 123456
         * schoolId : 1
         * gradeId : 2
         * classId : 3
         */

        private String idNumber;
        private int schoolId;
        private int gradeId;
        private int classId;

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public int getGradeId() {
            return gradeId;
        }

        public void setGradeId(int gradeId) {
            this.gradeId = gradeId;
        }

        public int getClassId() {
            return classId;
        }

        public void setClassId(int classId) {
            this.classId = classId;
        }
    }

    public static class BatchListBean {
        /**
         * time : 2019-11-11
         * name : 联考1
         * score : 566.5
         * gradeId : 1
         * batchId : 2
         * batchRank : 222
         * gradeRank : 22
         * classRank : 2
         * paperList : []
         */

        private String time;
        private String name;
        private double score;
        private int gradeId;
        private int batchId;
        private int batchRank;
        private int gradeRank;
        private int classRank;
        private List<?> paperList;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getGradeId() {
            return gradeId;
        }

        public void setGradeId(int gradeId) {
            this.gradeId = gradeId;
        }

        public int getBatchId() {
            return batchId;
        }

        public void setBatchId(int batchId) {
            this.batchId = batchId;
        }

        public int getBatchRank() {
            return batchRank;
        }

        public void setBatchRank(int batchRank) {
            this.batchRank = batchRank;
        }

        public int getGradeRank() {
            return gradeRank;
        }

        public void setGradeRank(int gradeRank) {
            this.gradeRank = gradeRank;
        }

        public int getClassRank() {
            return classRank;
        }

        public void setClassRank(int classRank) {
            this.classRank = classRank;
        }

        public List<?> getPaperList() {
            return paperList;
        }

        public void setPaperList(List<?> paperList) {
            this.paperList = paperList;
        }
    }
}
