package test.example.com.counselor.view.login;

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
    private String organization;
    private String education;
    private String academy;
    private String name;
    private String experience;
    private String evaluation;
    private String communityA;
    private String communityB;
    private String star;
    private String com_status;
    private String status;

    public LoginEntity(int id, String role, String username, String password,
                       String gender, String nation, String contact, String email, String organization,
                       String education, String academy, String name, String experience, String evaluation,
                       String communityA, String communityB, String star, String com_status, String status) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.gender = gender;
        this.nation = nation;
        this.contact = contact;
        this.email = email;
        this.organization = organization;
        this.evaluation = evaluation;
        this.academy = academy;
        this.name = name;
        this.experience = experience;
        this.education = education;
        this.communityA = communityA;
        this.communityB = communityB;
        this.star = star;
        this.com_status = com_status;
        this.status = status;
        this.password = password;
    }

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

    public String getCommunityA() {
        return communityA;
    }

    public void setCommunityA(String communityA) {
        this.communityA = communityA;
    }

    public String getCommunityB() {
        return communityB;
    }

    public void setCommunityB(String communityB) {
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
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
                ", organization='" + organization + '\'' +
                ", education='" + education + '\'' +
                ", name='" + name + '\'' +
                ", experience='" + experience + '\'' +
                ", evaluation='" + evaluation + '\'' +
                ", communityA='" + communityA + '\'' +
                ", communityB='" + communityB + '\'' +
                ", star='" + star + '\'' +
                ", com_status='" + com_status + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
