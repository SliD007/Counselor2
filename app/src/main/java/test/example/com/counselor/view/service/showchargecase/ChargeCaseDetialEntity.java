package test.example.com.counselor.view.service.showchargecase;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class ChargeCaseDetialEntity implements Serializable{


    private int id;
    private JSONObject serviceVillage;
    private String serviceObject;
    private String objectContact;
    private String serviceIdentity;
    private String inObject;
    private long createTime;

    private String fromType;
    private String serviceType;
    private String matterType;
    private String subType;
    private String objecttype;

    private String serviceContent;
    private String accessory;
    private String resultType;

    private String objectAddress;
    private String matterMoney ;
    private String resultContent;

    public String getResultContent() {
        return resultContent;
    }

    public void setResultContent(String resultContent) {
        this.resultContent = resultContent;
    }


    public String getObjectAddress() {
        return objectAddress;
    }

    public void setObjectAddress(String objectAddress) {
        this.objectAddress = objectAddress;
    }

    public String getMatterMoney() {
        return matterMoney;
    }

    public void setMatterMoney(String matterMoney) {
        this.matterMoney = matterMoney;
    }

    public boolean isConflict() {
        return isConflict;
    }

    public void setConflict(boolean conflict) {
        isConflict = conflict;
    }

    private boolean isConflict;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ChargeCaseDetialEntity{" +
                "id=" + id +
                ", serviceVillage=" + serviceVillage +
                ", serviceObject='" + serviceObject + '\'' +
                ", objectContact='" + objectContact + '\'' +
                ", serviceIdentity='" + serviceIdentity + '\'' +
                ", inObject='" + inObject + '\'' +
                ", createTime=" + createTime +
                ", fromType='" + fromType + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", matterType='" + matterType + '\'' +
                ", subType='" + subType + '\'' +
                ", objecttype='" + objecttype + '\'' +
                ", serviceContent='" + serviceContent + '\'' +
                ", accessory='" + accessory + '\'' +
                ", resultType='" + resultType + '\'' +
                ", objectAddress='" + objectAddress + '\'' +
                ", matterMoney='" + matterMoney + '\'' +
                ", isConflict=" + isConflict +
                ", resultContent=" + resultContent +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public JSONObject getServiceVillage() {
        return serviceVillage;
    }

    public void setServiceVillage(JSONObject serviceVillage) {
        this.serviceVillage = serviceVillage;
    }

    public String getServiceVillageName() {
        return serviceVillage.getString("username");
    }
    public int getServiceVillageId() {
        return serviceVillage.getInteger("id");
    }


    public String getServiceObject() {
        return serviceObject;
    }

    public void setServiceObject(String serviceObject) {
        this.serviceObject = serviceObject;
    }

    public String getObjectContact() {
        return objectContact;
    }

    public void setObjectContact(String objectContact) {
        this.objectContact = objectContact;
    }

    public String getServiceIdentity() {
        return serviceIdentity;
    }

    public void setServiceIdentity(String serviceIdentity) {
        this.serviceIdentity = serviceIdentity;
    }

    public String getInObject() {
        return inObject;
    }

    public void setInObject(String inObject) {
        this.inObject = inObject;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getMatterType() {
        return matterType;
    }

    public void setMatterType(String matterType) {
        this.matterType = matterType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getObjecttype() {
        return objecttype;
    }

    public void setObjecttype(String objecttype) {
        this.objecttype = objecttype;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }


    public ChargeCaseDetialEntity() {

    }


}
