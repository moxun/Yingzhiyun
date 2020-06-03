package com.yingzhiyun.yingquxue.OkBean;

public class YuejuanTeaBean {

    /**
     * command : 1
     * result : 1
     * errorMsg : 登录成功
     * data : {"gradeId":1,"groupId":4,"id":3,"name":"胡彬","phone":"18530920798","roleId":3,"schoolId":1,"state":1,"userName":"hubin","userPwd":"123456"}
     */

    private int command;
    private int result;
    private String errorMsg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * gradeId : 1
         * groupId : 4
         * id : 3
         * name : 胡彬
         * phone : 18530920798
         * roleId : 3
         * schoolId : 1
         * state : 1
         * userName : hubin
         * userPwd : 123456
         */

        private int gradeId;
        private int groupId;
        private int id;
        private String name;
        private String phone;
        private int roleId;
        private int schoolId;
        private int state;
        private String userName;
        private String userPwd;

        public int getGradeId() {
            return gradeId;
        }

        public void setGradeId(int gradeId) {
            this.gradeId = gradeId;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPwd() {
            return userPwd;
        }

        public void setUserPwd(String userPwd) {
            this.userPwd = userPwd;
        }
    }
}
