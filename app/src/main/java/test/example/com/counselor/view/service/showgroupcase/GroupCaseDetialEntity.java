package test.example.com.counselor.view.service.showgroupcase;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class GroupCaseDetialEntity implements Serializable{

    private int id;
    private JSONObject serviceVillage;
    private String matterPlace;
    private int matterNum;
    private String matterTime;
    private String objecttype;
    private String serviceContent;
    private String resultType;

    @Override
    public String toString() {
        return "GroupCaseDetialEntity{" +
                "id=" + id +
                ", serviceVillage=" + serviceVillage +
                ", matterPlace='" + matterPlace + '\'' +
                ", matterNum=" + matterNum +
                ", matterTime='" + matterTime + '\'' +
                ", objecttype='" + objecttype + '\'' +
                ", serviceContent='" + serviceContent + '\'' +
                ", resultType='" + resultType + '\'' +
                '}';
    }

    public int getId() {
        return id;
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

    public String getMatterPlace() {
        return matterPlace;
    }

    public void setMatterPlace(String matterPlace) {
        this.matterPlace = matterPlace;
    }

    public int getMatterNum() {
        return matterNum;
    }

    public void setMatterNum(int matterNum) {
        this.matterNum = matterNum;
    }

    public String getMatterTime() {
        return matterTime;
    }

    public void setMatterTime(String matterTime) {
        this.matterTime = matterTime;
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

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public GroupCaseDetialEntity() {

    }


}
