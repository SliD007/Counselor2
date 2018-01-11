package test.example.com.counselor.view.task.showtask;

/**
 * Created by Sli.D on 2018/1/11.
 */

public class ShowTaskModel implements IShowTaskModel{

    public TaskDetialEntity getTaskDetialEntity() {
        return taskDetialEntity;
    }

    public void setTaskDetialEntity(TaskDetialEntity taskDetialEntity) {
        this.taskDetialEntity = taskDetialEntity;
    }

    private TaskDetialEntity taskDetialEntity;

}
