package test.example.com.counselor.view.contract.rank;

/**
 * Created by Sli.D on 2018/1/6.
 */

public class ContractEntity {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkFor() {
        return workFor;
    }

    public void setWorkFor(String workFor) {
        this.workFor = workFor;
    }

    private String workFor;


    public ContractEntity(int id, String workFor) {
        this.id = id;
        this.workFor = workFor;
    }

    @Override
    public String toString() {
        return "ContractEntity{" +
                "name='" + id + '\'' +
                ", workFor='" + workFor + '\'' +

                '}';
    }



}
