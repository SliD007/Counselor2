package test.example.com.counselor.view.task.showtask;

/**
 * Created by Sli.D on 2017/12/25.
 */

public interface IShowTaskView {
    void requestTaskConfigurationSuccess();
    void requestTaskConfigurationFailed();

    void changeTaskStateSuccess();
    void changeTaskStateFailed();
}
