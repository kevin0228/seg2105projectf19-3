package project.seg2015.seg2105_project_f19_3;

public class Patient extends User {
    public Patient(String account, byte[] password) {
        super(account, password);
    }

    @Override
    public String getType() {
        return "Patient";
    }
}
