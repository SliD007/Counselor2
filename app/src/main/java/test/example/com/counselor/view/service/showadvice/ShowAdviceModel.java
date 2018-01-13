package test.example.com.counselor.view.service.showadvice;

/**
 * Created by Sli.D on 2018/1/11.
 */

public class ShowAdviceModel implements IShowAdviceModel {

    public AdviceEntity getAdviceDetialEntity() {
        return adviceEntity;
    }

    public void setAdviceDetialEntity(AdviceEntity adviceEntity) {
        this.adviceEntity = adviceEntity;
    }

    private AdviceEntity adviceEntity;

}
