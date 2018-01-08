package test.example.com.counselor.view.task;

import java.util.List;

import test.example.com.counselor.view.task.entity.DoneTaskEntity;
import test.example.com.counselor.view.task.entity.ToDoTaskEntity;

/**
 * Created by Sli.D on 2018/1/2.
 */

public interface ITaskModel {
    void setToDoTaskEntity(List<ToDoTaskEntity> entity);
    void setDoneTaskEntity(List<DoneTaskEntity> entity);
    List<DoneTaskEntity> getDoneTaskEntity();
    List<ToDoTaskEntity> getToDoTaskEntity();
}
