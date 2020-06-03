package com.yingzhiyun.yingquxue;

public class Jsonnn {


    /**
     * fuid : 216057
     * fvid : 50
     * famount : 100
     * frate : 6.0E-4
     * fterm : 10
     */

    private int fuid;
    private int fvid;
    private int famount;
    private double frate;
    private int fterm;

    public Jsonnn(int fuid, int fvid, int famount, double frate, int fterm) {
        this.fuid = fuid;
        this.fvid = fvid;
        this.famount = famount;
        this.frate = frate;
        this.fterm = fterm;
    }

    public int getFuid() {
        return fuid;
    }

    public void setFuid(int fuid) {
        this.fuid = fuid;
    }

    public int getFvid() {
        return fvid;
    }

    public void setFvid(int fvid) {
        this.fvid = fvid;
    }

    public int getFamount() {
        return famount;
    }

    public void setFamount(int famount) {
        this.famount = famount;
    }

    public double getFrate() {
        return frate;
    }

    public void setFrate(double frate) {
        this.frate = frate;
    }

    public int getFterm() {
        return fterm;
    }

    public void setFterm(int fterm) {
        this.fterm = fterm;
    }
}
