package test.example.com.counselor.view.service.showworklog;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class WorkLogDetialEntity implements Serializable{
    private int id;
    private String serviceObject;
    private String fromType;
    private String objectContact;
    private String serviceIdentity;
    private long createTime;
    private String serviceType;
    private String objectType;
    private String logType;
    private String serviceContent;

    @Override
    public String toString() {
        return "WorkLogDetialEntity{" +
                "createTime=" + createTime +
                ", id=" + id +
                ", serviceObject='" + serviceObject + '\'' +
                ", fromType='" + fromType + '\'' +
                ", objectContact='" + objectContact + '\'' +
                ", serviceIdentity='" + serviceIdentity + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", objectType='" + objectType + '\'' +
                ", logType='" + logType + '\'' +
                ", serviceContent='" + serviceContent + '\'' +
                '}';
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getObjectContact() {
        return objectContact;
    }

    public void setObjectContact(String objectContact) {
        this.objectContact = objectContact;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public String getServiceIdentity() {
        return serviceIdentity;
    }

    public void setServiceIdentity(String serviceIdentity) {
        this.serviceIdentity = serviceIdentity;
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

    public WorkLogDetialEntity() {

    }


}
