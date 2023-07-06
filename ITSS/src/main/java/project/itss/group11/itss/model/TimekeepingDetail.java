package project.itss.group11.itss.model;

public class TimekeepingDetail {

    private String comeLate;
    private String returnEarly;
    private int machine;
    private String time;
    private String type;

    public TimekeepingDetail(String comeLate, String returnEarly, int machine, String time, String type) {
        this.comeLate = comeLate;
        this.returnEarly = returnEarly;
        this.machine = machine;
        this.time = time;
        this.type = type;
    }
    public TimekeepingDetail() {

    }

    public String getComeLate() {
        return comeLate;
    }

    public void setComeLate(String comeLate) {
        this.comeLate = comeLate;
    }

    public String getReturnEarly() {
        return returnEarly;
    }

    public void setReturnEarly(String returnEarly) {
        this.returnEarly = returnEarly;
    }

    public int getMachine() {
        return machine;
    }

    public void setMachine(int machine) {
        this.machine = machine;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
