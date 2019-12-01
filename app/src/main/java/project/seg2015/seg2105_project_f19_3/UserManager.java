package project.seg2015.seg2105_project_f19_3;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> admins;
    private List<User> patients;
    private List<User> employees;
    private MyDBHandler dbHandler;

    private static UserManager instance = buildUserManager();

    private static UserManager buildUserManager() {
        if (null == instance) {
            instance = new UserManager();
        }
        return instance;
    }

    public static UserManager getInstance() {
        return instance;
    }

    private UserManager() {
        dbHandler = new MyDBHandler(LoginActivity.context);
        this.admins = new ArrayList<>();
        this.admins.add(new Admin("admin", Util.stringToHash("5T5ptQ")));
        employees = new ArrayList<>();
        patients = new ArrayList<>();
        ArrayList<User> users = dbHandler.findAllUsers();
        for (User user : users) {
            if (user instanceof ClinicEmployee) {
                employees.add(user);
            } else {
                patients.add(user);
            }
        }
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
        User user = type == UserType.Patient ? new Patient(account, password) : new ClinicEmployee(account, password);
        userList.add(user);
        dbHandler.addUser(user);
        return true;
    }

    public User signIn(UserType type, String account, byte[] password) {
        List<User> userList = patients;
        if (type == UserType.Admin) {
            userList = admins;
        } else if (type == UserType.ClinicEmployee)
            userList = employees;
        for (User user : userList) {
            if (user.getAccount().equals(account)) {
                for (int i = 0; i < user.getPassword().length; i++) {
                    if (user.getPassword()[i] != password[i])
                        return null;
                }
                return user;
            }
        }
        return null;
    }
}
