public class User {
    private String UserName;
    private String Password;

    public User(String Username,String Password){
        this.UserName=Username;
        this.Password=Password;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
    public String getUserName(){
        return UserName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPassword() {
        return Password;
    }
}
