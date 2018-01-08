package test.example.com.counselor.view.news;

import java.util.List;

/**
 * Created by Sli.D on 2018/1/2.
 */

public class NewsModel implements INewsModel {


    List<NewsEntity> newsEntities;

    @Override
    public void seNewsEntities(List<NewsEntity> newsEntities) {
        this.newsEntities = newsEntities;
    }

    @Override
    public List<NewsEntity> getNewsEntities() {
        return this.newsEntities;
    }
}
