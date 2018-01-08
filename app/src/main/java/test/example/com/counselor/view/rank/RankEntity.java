package test.example.com.counselor.view.rank;

/**
 * Created by Sli.D on 2018/1/6.
 */

public class RankEntity {
    private String name;
    private String workFor;
    private String rank;
    private String str1;
    private String str11;
    private String str12;
    private String str2;
    private String str3;
    private String str4;
    private String str5;

    public RankEntity(String name, String workFor, String rank, String str1, String str11,
                      String str12, String str2, String str3, String str4, String str5) {
        this.name = name;
        this.workFor = workFor;
        this.rank = rank;
        this.str1 = str1;
        this.str11 = str11;
        this.str12 = str12;
        this.str2 = str2;
        this.str3 = str3;
        this.str4 = str4;
        this.str5 = str5;
    }

    @Override
    public String toString() {
        return "AssessmentEntity{" +
                "name='" + name + '\'' +
                ", workFor='" + workFor + '\'' +
                ", rank='" + rank + '\'' +
                ", str1='" + str1 + '\'' +
                ", str11='" + str11 + '\'' +
                ", str12='" + str12 + '\'' +
                ", str2='" + str2 + '\'' +
                ", str3='" + str3 + '\'' +
                ", str4='" + str4 + '\'' +
                ", str5='" + str5 + '\'' +
                '}';
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getStr11() {
        return str11;
    }

    public void setStr11(String str11) {
        this.str11 = str11;
    }

    public String getStr12() {
        return str12;
    }

    public void setStr12(String str12) {
        this.str12 = str12;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr3() {
        return str3;
    }

    public void setStr3(String str3) {
        this.str3 = str3;
    }

    public String getStr4() {
        return str4;
    }

    public void setStr4(String str4) {
        this.str4 = str4;
    }

    public String getStr5() {
        return str5;
    }

    public void setStr5(String str5) {
        this.str5 = str5;
    }

    public String getWorkFor() {
        return workFor;
    }

    public void setWorkFor(String workFor) {
        this.workFor = workFor;
    }

}
