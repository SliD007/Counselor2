package test.example.com.counselor.view.schedule;

import java.util.List;

/**
 * Created by Sli.D on 2018/1/2.
 */

public interface IScheduleModel {
    void setScheduleEntities(List<ScheduleEntity> scheduleEntities);
    List<ScheduleEntity> getScheduleEntities();
}
