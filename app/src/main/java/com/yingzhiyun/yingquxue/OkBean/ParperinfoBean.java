package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class ParperinfoBean {


    /**
     * command : 4
     * result : 1
     * errorMsg :
     * data : [{"answer":null,"doScore":0,"id":3373,"idNumber":"8017215024","paperId":2,"score":1.5,"state":0,"teacherId":0,"testAnswer":null,"testImg":"http://yzy.tonyang.top/upload/student/1/6/1/2/detail/20191008175302412_0047_p1_61.jpeg","testNo":"61","testType":0}]
     * endId : 3373
     */

    private int command;
    private int result;
    private String errorMsg;
    private int endId;
    private List<DataBean> data;
    private int  allNum;
    private int doNum;

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public int getDoNum() {
        return doNum;
    }

    public void setDoNum(int doNum) {
        this.doNum = doNum;
    }

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

    public int getEndId() {
        return endId;
    }

    public void setEndId(int endId) {
        this.endId = endId;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * answer : null
         * doScore : 0.0
         * id : 3373
         * idNumber : 8017215024
         * paperId : 2
         * score : 1.5
         * state : 0
         * teacherId : 0
         * testAnswer : null
         * testImg : http://yzy.tonyang.top/upload/student/1/6/1/2/detail/20191008175302412_0047_p1_61.jpeg
         * testNo : 61
         * testType : 0
         */

        private Object answer;
        private double doScore;
        private int id;
        private String idNumber;
        private int paperId;
        private double score;
        private int state;
        private int teacherId;
        private Object testAnswer;
        private String testImg;
        private String testNo;
        private int testType;

        public Object getAnswer() {
            return answer;
        }

        public void setAnswer(Object answer) {
            this.answer = answer;
        }

        public double getDoScore() {
            return doScore;
        }

        public void setDoScore(double doScore) {
            this.doScore = doScore;
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

        public int getPaperId() {
            return paperId;
        }

        public void setPaperId(int paperId) {
            this.paperId = paperId;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public Object getTestAnswer() {
            return testAnswer;
        }

        public void setTestAnswer(Object testAnswer) {
            this.testAnswer = testAnswer;
        }

        public String getTestImg() {
            return testImg;
        }

        public void setTestImg(String testImg) {
            this.testImg = testImg;
        }

        public String getTestNo() {
            return testNo;
        }

        public void setTestNo(String testNo) {
            this.testNo = testNo;
        }

        public int getTestType() {
            return testType;
        }

        public void setTestType(int testType) {
            this.testType = testType;
        }
    }
}
