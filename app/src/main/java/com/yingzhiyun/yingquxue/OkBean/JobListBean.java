package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class JobListBean {


    /**
     * command : 2
     * result : 1
     * errorMsg :
     * data : [{"batchName":"领军考试","gradeId":1,"gradeName":"高一","groupId":4,"paperId":2,"paperName":"领军考试","progress":"0.5319148936170212765957446800%","subjectName":"英语","testList":"61;62;63;64;65;66;67;68;69;70;71;72;"},{"batchName":"正泰博文月考","gradeId":2,"gradeName":"高二","groupId":4,"paperId":1,"paperName":"正泰博文月考","progress":"0%","subjectName":"英语","testList":"61;62;63;64;65;66;67;68;69;70;71;72;"}]
     */

    private int command;
    private int result;
    private String errorMsg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * batchName : 领军考试
         * gradeId : 1
         * gradeName : 高一
         * groupId : 4
         * paperId : 2
         * paperName : 领军考试
         * progress : 0.5319148936170212765957446800%
         * subjectName : 英语
         * testList : 61;62;63;64;65;66;67;68;69;70;71;72;
         */

        private String batchName;
        private int gradeId;
        private String gradeName;
        private int groupId;
        private int paperId;
        private String paperName;
        private String progress;
        private String subjectName;
        private String testList;

        public String getBatchName() {
            return batchName;
        }

        public void setBatchName(String batchName) {
            this.batchName = batchName;
        }

        public int getGradeId() {
            return gradeId;
        }

        public void setGradeId(int gradeId) {
            this.gradeId = gradeId;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getPaperId() {
            return paperId;
        }

        public void setPaperId(int paperId) {
            this.paperId = paperId;
        }

        public String getPaperName() {
            return paperName;
        }

        public void setPaperName(String paperName) {
            this.paperName = paperName;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getTestList() {
            return testList;
        }

        public void setTestList(String testList) {
            this.testList = testList;
        }
    }
}
