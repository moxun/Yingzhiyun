package com.yingzhiyun.yingquxue.httpUnits;



/**
 * Created by Administrator on 2018/11/20.
 */

public class MainBean<T> {

    /**
     * code : 0
     * data : {"newsChannelList":[{"channelId":"0","channelName":"资讯"},{"channelId":"b9240eee3b0211e8b64c00163e30445d","channelName":"政策"},{"channelId":"b92536783b0211e8b64c00163e30445d","channelName":"机场"},{"channelId":"b92ecb5a3b0211e8b64c00163e30445d","channelName":"会展"},{"channelId":"b929dcbe3b0211e8b64c00163e30445d","channelName":"无人机"},{"channelId":"b93987453b0211e8b64c00163e30445d","channelName":"科技"},{"channelId":"b927cc203b0211e8b64c00163e30445d","channelName":"资本"},{"channelId":"b92dc0af3b0211e8b64c00163e30445d","channelName":"旅游"},{"channelId":"b93ed4753b0211e8b64c00163e30445d","channelName":"救援"},{"channelId":"b928c8b53b0211e8b64c00163e30445d","channelName":"首飞"},{"channelId":"b93ac6d23b0211e8b64c00163e30445d","channelName":"中国造"},{"channelId":"b949af0a3b0211e8b64c00163e30445d","channelName":"合作"},{"channelId":"b94b92213b0211e8b64c00163e30445d","channelName":"获批"},{"channelId":"b955b61f3b0211e8b64c00163e30445d","channelName":"植保"},{"channelId":"b952c88e3b0211e8b64c00163e30445d","channelName":"世界"},{"channelId":"b92674433b0211e8b64c00163e30445d","channelName":"航企"},{"channelId":"b93636c53b0211e8b64c00163e30445d","channelName":"飞行员"},{"channelId":"b938215a3b0211e8b64c00163e30445d","channelName":"学飞"},{"channelId":"b9432cf83b0211e8b64c00163e30445d","channelName":"制造"},{"channelId":"b94628cb3b0211e8b64c00163e30445d","channelName":"销售"},{"channelId":"b94828703b0211e8b64c00163e30445d","channelName":"二手"},{"channelId":"b94d17b03b0211e8b64c00163e30445d","channelName":"运动"},{"channelId":"b92c794d3b0211e8b64c00163e30445d","channelName":"直升机"},{"channelId":"b92b30b93b0211e8b64c00163e30445d","channelName":"公务机"}]}
     * message : 成功
     */

    private int status;

    private String hint;
    private T result;

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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public MainBean(int status, String hint, T result) {
        this.status = status;
        this.hint = hint;
        this.result = result;
    }
}
