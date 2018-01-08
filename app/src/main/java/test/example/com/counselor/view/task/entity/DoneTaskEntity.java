package test.example.com.counselor.view.task.entity;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class DoneTaskEntity implements Serializable{
    private int id;
    private long time;
    private String title;
    private String fromWhere;

    public DoneTaskEntity() {

    }

    public DoneTaskEntity(int id, String title, String fromWhere, long time) {
        this.id = id;
        this.time = time;
        this.title = title;
        this.fromWhere = fromWhere;
    }

    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", title=" + title + ", fromWhere="
                + fromWhere + ", time=" + time + "]";
    }

    public String getFrom() {
        return fromWhere;
    }

    public void setFrom(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
