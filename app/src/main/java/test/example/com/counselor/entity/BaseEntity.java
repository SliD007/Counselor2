package test.example.com.counselor.entity;

/**
 * Created by Sli.D on 2017/6/19.
 */

public abstract class BaseEntity {
    int type;//布局类型号

    public BaseEntity(int type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
}
