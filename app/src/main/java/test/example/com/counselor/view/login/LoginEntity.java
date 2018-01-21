package test.example.com.counselor.view.login;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class LoginEntity implements Serializable{
    private int id;
    private String role;
    private String username;
    private String password;
    private String gender;
    private String nation;
    private String contact;
    private String email;
    private JSONObject office;
    private String education;
    private String academy;
    private String name;
    private String experience;
    private String evaluation;
    private JSONObject communityA;
    private JSONObject communityB;
    private int star;
    private String com_status;
    private String status;

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCom_status() {
        return com_status;
    }

    public void setCom_status(String com_status) {
        this.com_status = com_status;
    }

    public JSONObject getCommunityA() {
        return communityA;
    }

    public void setCommunityA(JSONObject communityA) {
        this.communityA = communityA;
    }

    public JSONObject getCommunityB() {
        return communityB;
    }

    public void setCommunityB(JSONObject communityB) {
        this.communityB = communityB;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public JSONObject getOffice() {
        return office;
    }

    public void setOffice(JSONObject organization) {
        this.office = organization;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LoginEntity(){

    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "academy='" + academy + '\'' +
                ", id=" + id +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", nation='" + nation + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", office='" + office.toString() + '\'' +
                ", education='" + education + '\'' +
                ", name='" + name + '\'' +
                ", experience='" + experience + '\'' +
                ", evaluation='" + evaluation + '\'' +
                ", communityA='" + communityA.toString() + '\'' +
                ", communityB='" + communityB.toString() + '\'' +
                ", star='" + star + '\'' +
                ", com_status='" + com_status + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getVillageA(){
        if(getCommunityA()!=null)
            return getCommunityA().getString("username");
        else
            return "";
    }

    public String getVillageB(){
        if(getCommunityB()!=null)
            return getCommunityB().getString("username");
        else
            return "";
    }

    public int getVillageAId(){
        if(getCommunityA()!=null)
            return getCommunityA().getInteger("id");
        else
            return 0;
    }

    public int getVillageBId(){
        if(getCommunityB()!=null)
            return getCommunityB().getInteger("id");
        else
            return 0;
    }

    public String getOfficeName(){
        if(getOffice()!=null)
            return getOffice().getString("username");
        else
            return "";
    }


}
