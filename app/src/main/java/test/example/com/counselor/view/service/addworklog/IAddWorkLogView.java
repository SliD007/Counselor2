package test.example.com.counselor.view.service.addworklog;

/**
 * Created by Sli.D on 2017/12/25.
 */

public interface IAddWorkLogView {
    void addSuccess();
    void addFailed();
    void addImageSuccess(String imageUrl,int i);
    void addImageFailed();

}
