package test.example.com.counselor.view.news;

/**
 * Created by Sli.D on 2018/1/6.
 */

public class NewsEntity {
    @Override
    public String toString() {
        return "NewsEntity{" +
                "context='" + context + '\'' +
                ", from='" + from + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    private String from;
    private String time;
    private String context;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public NewsEntity(String from, String time, String context) {
        this.from = from;
        this.time = time;
        this.context = context;
    }
}
