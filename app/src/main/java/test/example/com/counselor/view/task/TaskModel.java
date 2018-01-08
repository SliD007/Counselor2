package test.example.com.counselor.view.task;

import java.util.List;

import test.example.com.counselor.view.task.entity.DoneTaskEntity;
import test.example.com.counselor.view.task.entity.ToDoTaskEntity;

/**
 * Created by Sli.D on 2018/1/2.
 */

public class TaskModel implements ITaskModel {


    public List<ToDoTaskEntity> getToDoTaskEntity() {
        return toDoListEntities;
    }

    public void setToDoTaskEntity(List<ToDoTaskEntity> toDoListEntities) {
        this.toDoListEntities = toDoListEntities;
    }

    public List<DoneTaskEntity> getDoneTaskEntity() {
        return doneListEntities;
    }

    public void setDoneTaskEntity(List<DoneTaskEntity> doneListEntities) {
        this.doneListEntities = doneListEntities;
    }

    List<ToDoTaskEntity> toDoListEntities;
    List<DoneTaskEntity> doneListEntities;
}
