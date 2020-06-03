package com.yingzhiyun.yingquxue.OkBean;

public class AverageBean {
    private float mine_Average;
    private float all_Average;
    private String Average_type;

    public AverageBean(float mine_Average, float all_Average, String average_type) {
        this.mine_Average = mine_Average;
        this.all_Average = all_Average;
        Average_type = average_type;
    }

    public float getMine_Average() {
        return mine_Average;
    }

    public void setMine_Average(float mine_Average) {
        this.mine_Average = mine_Average;
    }

    public float getAll_Average() {
        return all_Average;
    }

    public void setAll_Average(float all_Average) {
        this.all_Average = all_Average;
    }

    public String getAverage_type() {
        return Average_type;
    }

    public void setAverage_type(String average_type) {
        Average_type = average_type;
    }
}
