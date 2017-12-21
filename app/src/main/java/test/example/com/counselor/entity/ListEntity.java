package test.example.com.counselor.entity;

/**
 * Created by Sli.D on 2017/12/21.
 */

public class ListEntity {

    int layoutId;
    String name;
    String from;
    String time;

    public ListEntity(int layoutId, String name, String from, String time) {
        this.layoutId = layoutId;
        this.name = name;
        this.from = from;
        this.time = time;
    }

    public int getLayoutId() {
        return layoutId;
    }
    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String toString(){
        String str = "ListEntity{name:"+getName()+",from:"+getFrom()+",time:"+getTime()+"}";
        return str;
    }
}
