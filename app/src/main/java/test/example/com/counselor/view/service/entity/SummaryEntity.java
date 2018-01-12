package test.example.com.counselor.view.service.entity;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class SummaryEntity implements Serializable{
    private int id;
    private String title;
    private String village;
    private long createTime;

    public SummaryEntity() {
    }

    @Override
    public String toString() {
        return "SummaryEntity{" +
                "createTime=" + createTime +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", village='" + village + '\'' +
                '}';
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

}
