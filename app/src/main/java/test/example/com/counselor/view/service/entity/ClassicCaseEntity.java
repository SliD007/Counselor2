package test.example.com.counselor.view.service.entity;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class ClassicCaseEntity implements Serializable{
    private int id;
    private String title;
    private String from;
    private String time;

    public ClassicCaseEntity(int id, String title, String from, String time) {
        this.from = from;
        this.id = id;
        this.time = time;
        this.title = title;
    }

    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", title=" + title + ", from="
                + from + ", time=" + time + "]";
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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
