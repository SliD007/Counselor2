package test.example.com.counselor.view.task.entity;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class ToDoTaskEntity implements Serializable{
    private int id;
    private String time;
    private String title;
    private String fromWhere;

    public ToDoTaskEntity() {
    }

    public ToDoTaskEntity(int id, String title, String from, String time) {
        this.id = id;
        this.time = time;
        this.title = title;
        this.fromWhere = from;
    }

    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", title=" + title + ", from="
                + fromWhere + ", time=" + time + "]";
    }

    public String getFrom() {
        return fromWhere;
    }

    public void setFrom(String from) {
        this.fromWhere = from;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
