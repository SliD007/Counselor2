package test.example.com.counselor.view.schedule;

import java.util.List;

/**
 * Created by Sli.D on 2018/1/2.
 */

public class ScheduleModel implements IScheduleModel{


    List<ScheduleEntity> scheduleEntities;

    @Override
    public void setScheduleEntities(List<ScheduleEntity> scheduleEntities) {
        this.scheduleEntities = scheduleEntities;
    }

    @Override
    public List<ScheduleEntity> getScheduleEntities() {
        return this.scheduleEntities;
    }
}
