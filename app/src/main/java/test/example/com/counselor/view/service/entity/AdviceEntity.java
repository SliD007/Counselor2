package test.example.com.counselor.view.service.entity;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class AdviceEntity implements Serializable{
    private int id;
    private String title;
    private String toType;
    private long createTime;

    @Override
    public String toString() {
        return "AdviceEntity{" +
                "createTime=" + createTime +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", toType='" + toType + '\'' +
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

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    private String village;

    public AdviceEntity() {
    }

}
