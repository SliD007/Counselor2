package test.example.com.counselor.view.schedule;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class ScheduleEntity implements Serializable {

    private int id;
    private String jobType;
    private long placementTime;
    private String village;

    public long getPlacementTime() {
        return placementTime;
    }

    public void setPlacementTime(long placementTime) {
        this.placementTime = placementTime;
    }

    public ScheduleEntity() {
    }


    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "placementTime='" + placementTime + '\'' +
                ", id=" + id +
                ", jobType='" + jobType + '\'' +
                ", village='" + village + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }
}