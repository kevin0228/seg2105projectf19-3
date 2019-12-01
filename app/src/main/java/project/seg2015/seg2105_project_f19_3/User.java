package project.seg2015.seg2105_project_f19_3;

public abstract class User {
    private String account;
    private byte[] password;

    public User (String account, byte[] password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public byte[] getPassword() {
        return password;
    }

    public abstract String getType();
}
