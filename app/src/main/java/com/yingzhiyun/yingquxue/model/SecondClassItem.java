package com.yingzhiyun.yingquxue.model;

import java.util.ArrayList;

/**
 * 二级分类，相当于右侧菜单
 * Created by hanj on 14-9-25.
 */
public class SecondClassItem {
    private int id;
    private ArrayList<ThreeClassItem> name;
    private String title;

    public SecondClassItem(){

    }

    public SecondClassItem(int id, ArrayList<ThreeClassItem> name,String title) {
        this.id = id;
        this.name = name;
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<ThreeClassItem> getName() {
        return name;
    }

    public void setName(ArrayList<ThreeClassItem> name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SecondClassItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
