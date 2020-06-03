package com.yingzhiyun.yingquxue.units.AAChartCoreLib.AAOptionsModel;

public class AAAnimation {
    private Integer duration;
    private String easing;

    public AAAnimation duration(Integer prop) {
        duration = prop;
        return this;
    }

    public AAAnimation easing(String prop) {
        easing = prop;
        return this;
    }
}
