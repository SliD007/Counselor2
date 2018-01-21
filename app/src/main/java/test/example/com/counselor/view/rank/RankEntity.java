package test.example.com.counselor.view.rank;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Sli.D on 2018/1/6.
 */

public class RankEntity {
    private JSONObject counselorId;
    private String village;
    private int rank;
    private int consultCount;
    private int aidCount;
    private int mediateCount;
    private int publicityCount;
    private int chairCount;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }


    public JSONObject getCounselorId() {
        return counselorId;
    }

    public void setCounselorId(JSONObject counselorId) {
        this.counselorId = counselorId;
    }

    public String getCounselorName(){
        return this.counselorId.getString("username");
    }


    public int getConsultCount() {
        return consultCount;
    }

    public void setConsultCount(int consultCount) {
        this.consultCount = consultCount;
    }

    public int getAidCount() {
        return aidCount;
    }

    public void setAidCount(int aidCount) {
        this.aidCount = aidCount;
    }

    public int getMediateCount() {
        return mediateCount;
    }

    public void setMediateCount(int mediateCount) {
        this.mediateCount = mediateCount;
    }

    public int getPublicityCount() {
        return publicityCount;
    }

    public void setPublicityCount(int publicityCount) {
        this.publicityCount = publicityCount;
    }

    public int getChairCount() {
        return chairCount;
    }


    public void setChairCount(int chairCount) {
        this.chairCount = chairCount;
    }

    public RankEntity() {
    }

    @Override
    public String toString() {
        return "RankEntity{" +
                "counselor=" + getCounselorName() +
                ", village='" + village + '\'' +
                ", consultCount=" + consultCount +
                ", aidCount=" + aidCount +
                ", mediateCount=" + mediateCount +
                ", publicityCount=" + publicityCount +
                ", chairCount=" + chairCount +
                ", rank=" + rank +
                '}';
    }
}
