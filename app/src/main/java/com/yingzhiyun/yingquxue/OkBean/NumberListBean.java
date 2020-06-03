package com.yingzhiyun.yingquxue.OkBean;

import java.util.List;

public class NumberListBean {


    /**
     * command : 3
     * result : 1
     * errorMsg :
     * data : [{"idNumber":"8017208058"},{"idNumber":"8017215049"},{"idNumber":"8017202015"},{"idNumber":"8017208070"},{"idNumber":"8017208018"},{"idNumber":"8017208083"},{"idNumber":"8017208069"},{"idNumber":"8017208024"},{"idNumber":"8017215062"},{"idNumber":"8017208067"},{"idNumber":"8017215035"},{"idNumber":"8017201028"},{"idNumber":"8017208023"},{"idNumber":"8017215058"},{"idNumber":"8017208080"},{"idNumber":"8017202030"},{"idNumber":"8017215042"},{"idNumber":"8017208059"},{"idNumber":"8017204001"},{"idNumber":"8017215003"},{"idNumber":"8017215024"},{"idNumber":"8017208078"},{"idNumber":"8017208038"},{"idNumber":"8017202018"},{"idNumber":"8017208056"},{"idNumber":"8017215053"},{"idNumber":"8017208050"},{"idNumber":"8017208020"},{"idNumber":"8017215007"},{"idNumber":"8017208046"},{"idNumber":"8017208082"},{"idNumber":"8017201007"},{"idNumber":"8017202057"},{"idNumber":"8017215050"},{"idNumber":"8017217011"},{"idNumber":"8017208057"},{"idNumber":"8017208012"},{"idNumber":"8017215070"},{"idNumber":"8017201035"},{"idNumber":"8017202073"},{"idNumber":"8017215028"},{"idNumber":"8017215018"},{"idNumber":"8017208016"},{"idNumber":"8017208066"},{"idNumber":"8017215021"},{"idNumber":"8017208079"},{"idNumber":"8017208005"}]
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
         * idNumber : 8017208058
         */

        private String idNumber;

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }
    }
}
