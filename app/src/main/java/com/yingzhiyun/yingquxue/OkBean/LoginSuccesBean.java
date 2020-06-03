package com.yingzhiyun.yingquxue.OkBean;

public class LoginSuccesBean {


    /**
     * status : 200
     * hint : 登录成功请保存用户信息
     * result : {"user_id":10,"school":"温县第一高级中学","head_path":"http://www.yingzhiyunwenhua.cn/yzyFiles/userHead/2d9dc7d9-bcc1-4bda-b979-06fe0f3f3a50-1567060452048.jpg","grade":8,"nickname":"看看空间","token":"ODQzMWI1MTM2Mzg5NGQzMmJiNTgwNzgxZDQxMzE5NmU="}
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
         * user_id : 10
         * school : 温县第一高级中学
         * head_path : http://www.yingzhiyunwenhua.cn/yzyFiles/userHead/2d9dc7d9-bcc1-4bda-b979-06fe0f3f3a50-1567060452048.jpg
         * grade : 8
         * nickname : 看看空间
         * token : ODQzMWI1MTM2Mzg5NGQzMmJiNTgwNzgxZDQxMzE5NmU=
         */

        private int user_id;
        private String school;
        private String head_path;
        private String grade;
        private String nickname;
        private String token;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getHead_path() {
            return head_path;
        }

        public void setHead_path(String head_path) {
            this.head_path = head_path;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
