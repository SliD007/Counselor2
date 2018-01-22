package test.example.com.counselor.view.task.showtask;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class TaskDetialEntity implements Serializable{
    private int id;
    private String title;
    private String content;
    private String fromWhere;
    private String readType;
    private long createTime;
    private String accesory;

    public TaskDetialEntity() {

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

    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReadType() {
        return readType;
    }

    public void setReadType(String readType) {
        this.readType = readType;
    }

    public String getAccesory() {
        return accesory;
    }

    public void setAccesory(String accesory) {
        this.accesory = accesory;
    }

    @Override
    public String toString() {
        return "ChargeCaseDetialEntity{" +
                "content='" + content + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", fromWhere='" + fromWhere + '\'' +
                ", readType='" + readType + '\'' +
                ", createTime='" + createTime + '\'' +
                ", accesory='" + accesory + '\'' +
                '}';
    }

}
