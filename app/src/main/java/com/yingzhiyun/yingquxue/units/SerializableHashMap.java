package com.yingzhiyun.yingquxue.units;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SerializableHashMap implements Serializable {

    private LinkedHashMap<Integer,String> map;

    public LinkedHashMap<Integer, String> getMap() {
        return map;
    }

    public void setMap(LinkedHashMap<Integer, String> map) {
        this.map = map;
    }
}