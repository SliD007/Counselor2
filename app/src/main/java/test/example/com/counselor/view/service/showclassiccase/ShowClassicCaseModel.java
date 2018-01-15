package test.example.com.counselor.view.service.showclassiccase;

/**
 * Created by Sli.D on 2018/1/11.
 */

public class ShowClassicCaseModel implements IShowClassicCaseModel {

    private ClassicCaseEntity classicCaseEntity;

    @Override
    public void setClassicCaseDetialEntity(ClassicCaseEntity classicCaseEntity) {
        this.classicCaseEntity = classicCaseEntity;
    }

    @Override
    public ClassicCaseEntity getClassicCaseDetialEntity() {
        return this.classicCaseEntity;
    }
}
