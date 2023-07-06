package project.itss.group8.itss.model;

public class Worker {
    //    Defining fields
    private String workerName;
    private String workerID;
    private int workerUnit;
    private int workMonth;
    private double workerTotalWorkHour;
    private double workerTotalOvertimeHour;

    public Worker(
            String workerName,
            String workerID,
            int workerUnit,
            int workMonth,
            double workerTotalWorkHour,
            double workerTotalOvertimeHour)
    {
        this.workerName = workerName;
        this.workerID = workerID;
        this.workerUnit = workerUnit;
        this.workMonth = workMonth;
        this.workerTotalWorkHour = workerTotalWorkHour;
        this.workerTotalOvertimeHour = workerTotalOvertimeHour;
    }

    public Worker(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerID() {
        return workerID;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;
    }

    public int getWorkerUnit() {
        return workerUnit;
    }

    public void setWorkerUnit(int workerUnit) {
        this.workerUnit = workerUnit;
    }

    public int getWorkMonth() {
        return workMonth;
    }

    public void setWorkMonth(int workMonth) {
        this.workMonth = workMonth;
    }

    public double getWorkerTotalWorkHour() {
        return workerTotalWorkHour;
    }

    public void setWorkerTotalWorkHour(double workerTotalWorkHour) {
        this.workerTotalWorkHour = workerTotalWorkHour;
    }

    public double getWorkerTotalOvertimeHour() {
        return workerTotalOvertimeHour;
    }

    public void setWorkerTotalOvertimeHour(double workerTotalOvertimeHour) {
        this.workerTotalOvertimeHour = workerTotalOvertimeHour;
    }
}
