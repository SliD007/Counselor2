package test.example.com.counselor.view.contract;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Sli.D on 2018/1/6.
 */

public class ContractEntity {

    private JSONObject village;
    private JSONObject counselor;
    private String representative;
    private String office;
    private String serviceYear;
    private String deadLine;
    private int money;
    private String contractStatus;
    private String accesory;

    @Override
    public String toString() {
        return "ContractEntity{" +
                "village='" + village + '\'' +
                ", counselor=" + counselor +
                ", representative='" + representative + '\'' +
                ", office='" + office + '\'' +
                ", serviceYear='" + serviceYear + '\'' +
                ", deadLine='" + deadLine + '\'' +
                ", money=" + money +
                ", contractStatus='" + contractStatus + '\'' +
                ", accesory='" + accesory + '\'' +
                '}';
    }

    public JSONObject getVillage() {
        return village;
    }

    public void setVillage(JSONObject village) {
        this.village = village;
    }

    public JSONObject getCounselor() {
        return counselor;
    }

    public void setCounselor(JSONObject counselor) {
        this.counselor = counselor;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getServiceYear() {
        return serviceYear;
    }

    public void setServiceYear(String serviceYear) {
        this.serviceYear = serviceYear;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getAccesory() {
        return accesory;
    }

    public void setAccesory(String accesory) {
        this.accesory = accesory;
    }

    public ContractEntity() {
    }
}
