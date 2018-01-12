package test.example.com.counselor.view.service.showworklog;

/**
 * Created by Sli.D on 2018/1/11.
 */

public class ShowWorkLogModel implements IShowWorkLogModel {

    public WorkLogDetialEntity getWorkLogDetialEntity() {
        return workLogDetialEntity;
    }

    public void setWorkLogDetialEntity(WorkLogDetialEntity workLogDetialEntity) {
        this.workLogDetialEntity = workLogDetialEntity;
    }

    private WorkLogDetialEntity workLogDetialEntity;

}
