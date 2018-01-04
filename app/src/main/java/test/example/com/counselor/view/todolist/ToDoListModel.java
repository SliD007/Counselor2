package test.example.com.counselor.view.todolist;

import java.util.List;

import test.example.com.counselor.view.todolist.entity.DoneListEntity;
import test.example.com.counselor.view.todolist.entity.ToDoListEntity;

/**
 * Created by Sli.D on 2018/1/2.
 */

public class ToDoListModel implements IToDoListModel{


    public List<ToDoListEntity> getToDoListEntities() {
        return toDoListEntities;
    }

    public void setToDoListEntities(List<ToDoListEntity> toDoListEntities) {
        this.toDoListEntities = toDoListEntities;
    }

    public List<DoneListEntity> getDoneListEntities() {
        return doneListEntities;
    }

    public void setDoneListEntities(List<DoneListEntity> doneListEntities) {
        this.doneListEntities = doneListEntities;
    }

    List<ToDoListEntity> toDoListEntities;
    List<DoneListEntity> doneListEntities;
}
