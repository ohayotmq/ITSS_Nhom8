package project.itss.group8.itss.model;

public class Officer {
    private String officerID;
    private String officerName;
    private int officerUnit;
    private int workMonth;
    private int totalWorkDays;
    private double totalFaultHours; // Di muon hoac ve som

    public String getOfficerID() {
        return officerID;
    }

    public void setOfficerID(String officerID) {
        this.officerID = officerID;
    }

    public String getOfficerName() {
        return officerName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public int getOfficerUnit() {
        return officerUnit;
    }

    public void setOfficerUnit(int officerUnit) {
        this.officerUnit = officerUnit;
    }

    public int getWorkMonth() {
        return workMonth;
    }

    public void setWorkMonth(int workMonth) {
        this.workMonth = workMonth;
    }

    public int getTotalWorkDays() {
        return totalWorkDays;
    }

    public void setTotalWorkDays(int totalWorkDays) {
        this.totalWorkDays = totalWorkDays;
    }

    public double getTotalFaultHours() {
        return totalFaultHours;
    }

    public void setTotalFaultHours(double totalFaultHours) {
        this.totalFaultHours = totalFaultHours;
    }
}
