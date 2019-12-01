package project.seg2015.seg2105_project_f19_3;

public class Service {

    private String serviceName;
    private int serviceCost;
    private String role;

    public Service(){
    }

    public Service(String serviceName, int serviceCost, String role) {
        this.serviceName = serviceName;
        this.serviceCost = serviceCost;
        this.role = role;
    }

    public void setServiceName(String name){
        this.serviceName = name;
    }

    public String getServiceName(){
        return serviceName;
    }

    public void setServiceCost(int cost) {
        this.serviceCost = cost;
    }

    public int getServiceCost(){
        return serviceCost;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
