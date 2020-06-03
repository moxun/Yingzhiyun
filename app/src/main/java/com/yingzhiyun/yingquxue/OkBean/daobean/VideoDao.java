package com.yingzhiyun.yingquxue.OkBean.daobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
public class VideoDao {
    @Id(autoincrement = true)
    Long id;
    @Unique
    String title;
    String path;
    String videoId;
    String type;
    @Generated(hash = 1898023457)
    public VideoDao(Long id, String title, String path, String videoId,
            String type) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.videoId = videoId;
        this.type = type;
    }
    @Generated(hash = 2145009389)
    public VideoDao() {
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
    public String getVideoId() {
        return this.videoId;
    }
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    
}
