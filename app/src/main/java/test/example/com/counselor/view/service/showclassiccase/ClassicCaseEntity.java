package test.example.com.counselor.view.service.showclassiccase;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class ClassicCaseEntity implements Serializable{

    private int id;
    private String title;
    private String content;
    private long createTime;
    private String toType;
    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    private String village;
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ClassicCaseEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", toType='" + toType + '\'' +
                ", village='" + village + '\'' +
                '}';
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public ClassicCaseEntity() {

    }


}
