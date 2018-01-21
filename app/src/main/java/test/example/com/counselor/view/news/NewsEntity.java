package test.example.com.counselor.view.news;

/**
 * Created by Sli.D on 2018/1/6.
 */

public class NewsEntity {


    public NewsEntity() {
    }


    private Long createTime;
    private String title;
    private String content;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NewsEntity{" +
                "createTime=" + createTime +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
