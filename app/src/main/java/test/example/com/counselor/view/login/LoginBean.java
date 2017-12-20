package test.example.com.counselor.view.login;

/**
 * Created by Sli.D on 2017/5/12.
 */

public class LoginBean {
    private int id;
    private String account;
    private String username;
    private String avatar;
    private String email;
    private String tel;
    private String role;
    private int parentId;
    private String parentName;
    private int departmentId;
    private String departmentName;
    private String departmentDescription;
    private int departmentTotalNum;
    public LoginBean(){

    }
    public LoginBean(
             int id,
             String account,
             String username,
             String avatar,
             String email,
             String tel,
             String role,
             int parentId,
             String parentName,
             int departmentId,
             String departmentName,
             String departmentDescription,
             int departmentTotalNum
             ){
        this.account = account;
        this.username = username;
        this.avatar = avatar;
        this.email = email;
        this.tel = tel;
        this.role = role;
        this.parentId = parentId;
        this.parentName = parentName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentDescription = departmentDescription;
        this.departmentTotalNum = departmentTotalNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    public int getDepartmentTotalNum() {
        return departmentTotalNum;
    }

    public void setDepartmentTotalNum(int departmentTotalNum) {
        this.departmentTotalNum = departmentTotalNum;
    }
}
