package com.yingzhiyun.yingquxue.OkBean;

import java.io.Serializable;
import java.util.List;

public class StudentinfoBean implements Serializable {
    /**
     * command : 8
     * result : 1
     * errorMsg :
     * batchList : [{"batchId":4,"batchRank":16,"classRank":1,"gradeRank":1,"name":"领军考试","paperList":[{"name":"英语","paperId":1}],"score":36,"time":"2019/12/3"}]
     * student : {"classId":19,"gradeId":1,"id":360,"idNumber":"8017208018","schoolId":1}
     */

    private int command;
    private int result;
    private String errorMsg;
    private StudentBean student;
    private List<BatchListBean> batchList;
    /**
     * timestamp : 2020-03-25T08:16:15.340+0000
     * status : 405
     * error : Method Not Allowed
     * message : Request method 'POST' not supported
     * path : /getdata.ashx
     */

    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;


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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static class StudentBean implements Serializable{
        /**
         * classId : 19
         * gradeId : 1
         * id : 360
         * idNumber : 8017208018
         * schoolId : 1
         */

        private int classId;
        private int gradeId;
        private int id;
        private String idNumber;
        private int schoolId;

        public int getClassId() {
            return classId;
        }

        public void setClassId(int classId) {
            this.classId = classId;
        }

        public int getGradeId() {
            return gradeId;
        }

        public void setGradeId(int gradeId) {
            this.gradeId = gradeId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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
    }

    public static class BatchListBean implements Serializable{
        /**
         * batchId : 4
         * batchRank : 16
         * classRank : 1
         * gradeRank : 1
         * name : 领军考试
         * paperList : [{"name":"英语","paperId":1}]
         * score : 36.0
         * time : 2019/12/3
         */

        private int batchId;
        private int batchRank;
        private int classRank;
        private int gradeRank;
        private String name;
        private double score;
        private String time;
        private List<PaperListBean> paperList;

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

        public int getClassRank() {
            return classRank;
        }

        public void setClassRank(int classRank) {
            this.classRank = classRank;
        }

        public int getGradeRank() {
            return gradeRank;
        }

        public void setGradeRank(int gradeRank) {
            this.gradeRank = gradeRank;
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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<PaperListBean> getPaperList() {
            return paperList;
        }

        public void setPaperList(List<PaperListBean> paperList) {
            this.paperList = paperList;
        }

        public static class PaperListBean implements Serializable{
            /**
             * name : 英语
             * paperId : 1
             */

            private String name;
            private int paperId;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPaperId() {
                return paperId;
            }

            public void setPaperId(int paperId) {
                this.paperId = paperId;
            }
        }
    }


    /**
     * command : 8
     * result : 1
     * errorMsg :
     * batchList : [{"batchId":4,"name":"领军考试","score":36,"time":"2019/12/3"}]
     * student : {"classId":19,"gradeId":1,"id":360,"idNumber":"8017208018","schoolId":1}
     */

}
