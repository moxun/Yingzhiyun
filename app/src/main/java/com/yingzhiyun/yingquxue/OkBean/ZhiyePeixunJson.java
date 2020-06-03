package com.yingzhiyun.yingquxue.OkBean;

public class ZhiyePeixunJson {



    /**
     * skillTypeId : 17
     * rankingType : 1
     */

    private int skillTypeId;
    private int rankingType;
    private String version;
    private String device;
    private String app_user_id;
    private String token;

    public ZhiyePeixunJson(int skillTypeId, int rankingType, String version, String device, String app_user_id, String token) {
        this.skillTypeId = skillTypeId;
        this.rankingType = rankingType;
        this.version = version;
        this.device = device;
        this.app_user_id = app_user_id;
        this.token = token;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
    public int getSkillTypeId() {
        return skillTypeId;
    }

    public void setSkillTypeId(int skillTypeId) {
        this.skillTypeId = skillTypeId;
    }

    public int getRankingType() {
        return rankingType;
    }

    public void setRankingType(int rankingType) {
        this.rankingType = rankingType;
    }
}
