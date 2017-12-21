package test.example.com.counselor.entity;

/**
 * Created by Sli.D on 2017/6/19.
 */

public class MyEntity{

    public MyEntity(boolean isDifType,int layoutId, int imgId, String nameStr, String introduceStr, String pirceStr) {
        this.layoutId = layoutId;
        this.isDifType = isDifType;
        this.imgId = imgId;
        this.nameStr = nameStr;
        this.introduceStr = introduceStr;
        this.pirceStr = pirceStr;
    }
    int type;
    boolean isDifType;

    int imgId;
    String nameStr;
    String introduceStr;
    String pirceStr;

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public boolean isDifType() {
        return isDifType;
    }
    public void setDifType(boolean difType) {
        isDifType = difType;
    }
    public String getNameStr() {
        return nameStr;
    }
    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }
    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
    public String getIntroduceStr() {
        return introduceStr;
    }
    public void setIntroduceStr(String introduceStr) {
        this.introduceStr = introduceStr;
    }
    public String getPirceStr() {
        return pirceStr;
    }
    public void setPirceStr(String pirceStr) {
        this.pirceStr = pirceStr;
    }
    public String toString(){
        String str = "MyEntity{isDifType:"+isDifType+",type:"+getType()+",qId:"+getqId()+
                ",answer:"+getAnswer()[2]+"}";
        return str;
    }

    int layoutId;
    int qId;
    String question;
    int[] checkId;
    String[] answer;

    public MyEntity(boolean isDifType,int type,int layoutId,int qId,String question,int[] checkId,String[] answer){
        this.type = type;
        this.isDifType = isDifType;
        this.layoutId = layoutId;
        this.qId = qId;
        this.question = question;
        this.checkId = checkId;
        this.answer = answer;
    }
    public int getLayoutId() {
        return layoutId;
    }
    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }
    public String[] getAnswer() {
        return answer;
    }
    public void setAnswer(String[] answer) {
        this.answer = answer;
    }
    public int[] getCheckId() {
        return checkId;
    }
    public void setCheckId(int[] checkId) {
        this.checkId = checkId;
    }
    public int getqId() {
        return qId;
    }
    public void setqId(int qId) {
        this.qId = qId;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }


}
