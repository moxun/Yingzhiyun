package com.yingzhiyun.yingquxue.OkBean;

public class BaominginfoBean {


    /**
     * status : 200
     * hint : null
     * result : {"signNum":1,"id":1,"time":94278,"isSign":false}
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
         * signNum : 1
         * id : 1
         * time : 94278
         * isSign : false
         */

        private int signNum;
        private int id;
        private int time;
        private boolean isSign;
        private String sig;
        private String password;
        private String username;
        public String getSig() {
            return sig;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setSig(String sig) {
            this.sig = sig;
        }

        public boolean isSign() {
            return isSign;
        }

        public void setSign(boolean sign) {
            isSign = sign;
        }

        public String getInstruction() {
            return instruction;
        }

        public void setInstruction(String instruction) {
            this.instruction = instruction;
        }

        private String instruction;
        public int getSignNum() {
            return signNum;
        }

        public void setSignNum(int signNum) {
            this.signNum = signNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public boolean isIsSign() {
            return isSign;
        }

        public void setIsSign(boolean isSign) {
            this.isSign = isSign;
        }
    }
}
