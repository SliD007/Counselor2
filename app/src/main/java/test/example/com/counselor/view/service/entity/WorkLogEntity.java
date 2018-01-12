package test.example.com.counselor.view.service.entity;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class WorkLogEntity implements Serializable{
    private int id;
    private String serviceObject;
    private String serviceType;
    private long createTime;
    private String serviceVillage;

    public WorkLogEntity() {
    }

    @Override
    public String toString() {
        return "WorkLogEntity{" +
                "createTime=" + createTime +
                ", id=" + id +
                ", serviceObject='" + serviceObject + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", serviceVillage='" + serviceVillage + '\'' +
                '}';
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceObject() {
        return serviceObject;
    }

    public void setServiceObject(String serviceObject) {
        this.serviceObject = serviceObject;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceVillage() {
        return serviceVillage;
    }

    public void setServiceVillage(String serviceVillage) {
        this.serviceVillage = serviceVillage;
    }
}
