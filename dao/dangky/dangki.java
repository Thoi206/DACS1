package dao.dangky;

public class dangki {
    private String userName;
    private String passWord;
    private String fullName;
    private String email;
    private String SDT;

    public dangki(String username, String password, String fullName, String email, String sdt) {
        this.userName = username;
        this.passWord = password;
        this.fullName = fullName;
        this.email = email;
        this.SDT = sdt;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}