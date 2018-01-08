package test.example.com.counselor.view.task;

import java.util.List;

import test.example.com.counselor.view.task.entity.DoneTasskEntity;
import test.example.com.counselor.view.task.entity.ToDoTaskEntity;

/**
 * Created by Sli.D on 2018/1/2.
 */

public interface ITaskModel {
    void setToDoListEntities(List<ToDoTaskEntity> entity);
    void setDoneListEntities(List<DoneTasskEntity> entity);
    List<DoneTasskEntity> getDoneListEntities();
    List<ToDoTaskEntity> getToDoListEntities();
}
