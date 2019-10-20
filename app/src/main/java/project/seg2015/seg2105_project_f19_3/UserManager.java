package project.seg2015.seg2105_project_f19_3;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> admins;
    private List<User> patients;
    private List<User> employees;

    private static UserManager instance = new UserManager();

    public static UserManager getInstance() {
        return instance;
    }

    private UserManager() {
        this.admins = new ArrayList<>();
        this.admins.add(new Admin("admin", Util.stringToHash("5T5ptQ")));
        this.patients = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public boolean signUp(UserType type, String account, byte[] password) {
        List<User> userList = patients;
        if (type == UserType.ClinicEmployee)
            userList = employees;
        for (User user : userList) {
            if (user.getAccount().equals(account)) {
                return false;
            }
        }
        userList.add(type == UserType.Patient ? new Patient(account, password) : new ClinicEmployee(account, password));
        return true;
    }

    public boolean signIn(UserType type, String account, byte[] password) {
        List<User> userList = patients;
        if (type == UserType.Admin) {
            userList = admins;
        } else if (type == UserType.ClinicEmployee)
            userList = employees;
        for (User user : userList) {
            if (user.getAccount().equals(account)) {
                for (int i = 0; i < user.getPassword().length; i++) {
                    if (user.getPassword()[i] != password[i])
                        return false;
                }
                return true;
            }
        }
        return false;
    }
}
