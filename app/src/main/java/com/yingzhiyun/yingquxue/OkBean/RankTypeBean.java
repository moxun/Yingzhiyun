package com.yingzhiyun.yingquxue.OkBean;

public class RankTypeBean {

    private String mine_rank;
    private String all_rank;
    private String rank_type;

    public RankTypeBean(String mine_rank, String all_rank, String rank_type) {
        this.mine_rank = mine_rank;
        this.all_rank = all_rank;
        this.rank_type = rank_type;
    }

    public String getMine_rank() {
        return mine_rank;
    }

    public void setMine_rank(String mine_rank) {
        this.mine_rank = mine_rank;
    }

    public String getAll_rank() {
        return all_rank;
    }

    public void setAll_rank(String all_rank) {
        this.all_rank = all_rank;
    }

    public String getRank_type() {
        return rank_type;
    }

    public void setRank_type(String rank_type) {
        this.rank_type = rank_type;
    }
}
