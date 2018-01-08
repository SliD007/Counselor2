package test.example.com.counselor.view.task;

import java.util.List;

import test.example.com.counselor.view.task.entity.DoneTasskEntity;
import test.example.com.counselor.view.task.entity.ToDoTaskEntity;

/**
 * Created by Sli.D on 2018/1/2.
 */

public class TaskModel implements ITaskModel {


    public List<ToDoTaskEntity> getToDoListEntities() {
        return toDoListEntities;
    }

    public void setToDoListEntities(List<ToDoTaskEntity> toDoListEntities) {
        this.toDoListEntities = toDoListEntities;
    }

    public List<DoneTasskEntity> getDoneListEntities() {
        return doneListEntities;
    }

    public void setDoneListEntities(List<DoneTasskEntity> doneListEntities) {
        this.doneListEntities = doneListEntities;
    }

    List<ToDoTaskEntity> toDoListEntities;
    List<DoneTasskEntity> doneListEntities;
}
