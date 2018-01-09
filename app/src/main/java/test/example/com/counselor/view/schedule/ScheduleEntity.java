package test.example.com.counselor.view.schedule;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class ScheduleEntity implements Serializable {
    private int id;

    public ScheduleEntity() {
    }

    private String time;
    private String workfor;

    public ScheduleEntity(int id, String time, String workfor) {
        this.id = id;
        this.time = time;
        this.workfor = workfor;
    }


    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", workfor=" + workfor + "time=" + time + "]";
    }


    public String getWorkfor() {
        return workfor;
    }

    public void setWorkfor(String workfor) {
        this.workfor = workfor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}