package test.example.com.counselor.view.service.entity;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class WorkLogEntity implements Serializable{
    private int id;
    private String jobLogId;
    private String logType;
    private String serviceObject;
    private String resultType;
    private String serviceVillage;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "WorkLogEntity{" +
                "id=" + id +
                ", jobLogId=" + jobLogId +
                ", logType='" + logType + '\'' +
                ", serviceObject='" + serviceObject + '\'' +
                ", resultType='" + resultType + '\'' +
                ", serviceVillage='" + serviceVillage + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobLogId() {
        return jobLogId;
    }

    public void setJobLogId(String jobLogId) {
        this.jobLogId = jobLogId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getServiceObject() {
        return serviceObject;
    }

    public void setServiceObject(String serviceObject) {
        this.serviceObject = serviceObject;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getServiceVillage() {
        return serviceVillage;
    }

    public void setServiceVillage(String serviceVillage) {
        this.serviceVillage = serviceVillage;
    }

    public WorkLogEntity() {
    }

}
