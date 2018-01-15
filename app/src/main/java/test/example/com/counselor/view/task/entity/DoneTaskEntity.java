package test.example.com.counselor.view.task.entity;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class DoneTaskEntity implements Serializable {
    private int id;
    private long time;
    private String title;

    public String getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;
    }

    private String fromWhere;

    public DoneTaskEntity() {
    }

    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", title=" + title + ", fromWhere="
                + fromWhere + ", time=" + time + "]";
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
