package test.example.com.counselor.view.service.entity;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class AdviceEntity implements Serializable{
    private int id;
    private String title;
    private String reporttype;
    private String toType;
    private long createTime;

    @Override
    public String toString() {
        return "AdviceEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", reporttype='" + reporttype + '\'' +
                ", toType='" + toType + '\'' +
                ", createTime=" + createTime +
                ", village='" + village + '\'' +
                '}';
    }

    private String village;

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

    public String getReporttype() {
        return reporttype;
    }

    public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public AdviceEntity() {
    }

}
