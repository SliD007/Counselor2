package test.example.com.counselor.view.service.entity;

import com.alibaba.fastjson.JSONObject;

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
    private JSONObject serviceVillage;

    public int getId() {
        return id;
    }
    private long createTime;

    @Override
    public String toString() {
        return "WorkLogEntity{" +
                "id=" + id +
                ", jobLogId=" + jobLogId +
                ", logType='" + logType + '\'' +
                ", serviceObject='" + serviceObject + '\'' +
                ", resultType='" + resultType + '\'' +
                ", serviceVillage='" + serviceVillage.toString() + '\'' +
                ", createTime='" + createTime + '\'' +
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

    public JSONObject getServiceVillage() {
        return serviceVillage;
    }

    public void setServiceVillage(JSONObject serviceVillage) {
        this.serviceVillage = serviceVillage;
    }

    public WorkLogEntity() {
    }
    public String getServiceVillageName() {
        return this.serviceVillage.getString("username");
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
