package test.example.com.counselor.view.service.showsummary;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class SummaryEntity implements Serializable{

    private int id;
    private String title;
    private String content;
    private long createTime;
    private String toType;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SummaryEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", toType='" + toType + '\'' +
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

    public SummaryEntity() {

    }


}
