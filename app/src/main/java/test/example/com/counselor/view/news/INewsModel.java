package test.example.com.counselor.view.news;

import java.util.List;

/**
 * Created by Sli.D on 2018/1/2.
 */

public interface INewsModel {
    void seNewsEntities(List<NewsEntity> rankEntities);
    List<NewsEntity> getNewsEntities();
}
