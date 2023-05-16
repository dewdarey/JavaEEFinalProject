package finalproject.models;

public class User {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private int roleID;

    public User(Long id, String email, String password, String fullName, int roleID) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.roleID=roleID;
    }

    public User(String email, String password, String fullName, int roleID) {
        //this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.roleID=roleID;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}
