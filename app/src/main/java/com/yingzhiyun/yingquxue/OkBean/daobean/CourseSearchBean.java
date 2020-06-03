package com.yingzhiyun.yingquxue.OkBean.daobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CourseSearchBean {
    @Id(autoincrement = true)
    Long id;
    @Unique
    String title;

    @Generated(hash = 16371593)
    public CourseSearchBean(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    @Generated(hash = 2123363973)
    public CourseSearchBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
