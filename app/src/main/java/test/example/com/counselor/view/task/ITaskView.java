package test.example.com.counselor.view.task;

/**
 * Created by Sli.D on 2018/1/2.
 */

public interface ITaskView {
    void requestTaskSuccess(boolean hasNext,int type);
    void requestTaskFaild();
    void requestStarSuccess();
    void requestStarFaild();
}
