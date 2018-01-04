package test.example.com.counselor.view.todolist;

import java.util.List;

/**
 * Created by Sli.D on 2018/1/2.
 */

public interface IToDoListModel {
    void setToDoListEntities(List<ToDoListEntity> toDoListEntities);
    void setDoneListEntities(List<DoneListEntity> doneListEntities);
    List<DoneListEntity> getDoneListEntities();
    List<ToDoListEntity> getToDoListEntities();
}