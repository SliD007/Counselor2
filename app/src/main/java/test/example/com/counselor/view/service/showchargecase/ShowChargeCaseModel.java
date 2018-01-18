package test.example.com.counselor.view.service.showchargecase;

/**
 * Created by Sli.D on 2018/1/11.
 */

public class ShowChargeCaseModel implements IShowChargeCaseModel {

    public ChargeCaseDetialEntity getChargeCaseDetialEntity() {
        return chargeCaseDetialEntity;
    }

    public void setChargeCaseDetialEntity(ChargeCaseDetialEntity chargeCaseDetialEntity) {
        this.chargeCaseDetialEntity = chargeCaseDetialEntity;
    }

    private ChargeCaseDetialEntity chargeCaseDetialEntity;

}
