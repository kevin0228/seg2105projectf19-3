package project.seg2015.seg2105_project_f19_3;

public class ClinicEmployee extends User {
    private String address;
    private String phone;
    private String clinicName;
    private String[] insuranceTypes;
    private String[] paymentMethods;
    private String[] services;
    private String startTime;
    private String endTime;

    public ClinicEmployee(String account) {
        super(account);
    }

    public ClinicEmployee(String account, byte[] password) {
        super(account, password);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setInsuranceTypes(String[] insuranceTypes) {
        this.insuranceTypes = insuranceTypes;
    }

    public String[] getInsuranceTypes() {
        return insuranceTypes;
    }

    public void setPaymentMethods(String[] paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public String[] getPaymentMethods() {
        return paymentMethods;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

    public String[] getServices() {
        return services;
    }

    public String getInsuranceTypeString() {
        if (insuranceTypes != null && insuranceTypes.length > 0) {
            String s = insuranceTypes[0];
            for (int i = 1; i < insuranceTypes.length; i++) {
                s += "_" + insuranceTypes[i];
            }
            return s;
        }
        return "";
    }

    public String getPaymentMethodsString() {
        if (paymentMethods != null && paymentMethods.length > 0) {
            String s = paymentMethods[0];
            for (int i = 1; i < paymentMethods.length; i++) {
                s += "_" + paymentMethods[i];
            }
            return s;
        }
        return "";
    }

    public String getServicesString() {
        if (services != null && services.length > 0) {
            String s = services[0];
            for (int i = 1; i < services.length; i++) {
                s += "_" + services[i];
            }
            return s;
        }
        return "";
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String getType() {
        return "ClinicEmployee";
    }
}
