package project.itss.group11.itss.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class LogInfor {
    private int id;
    private int employee_id;
    private LocalDateTime timeStamp;
    private int device;


    @Override
	public int hashCode() {
		return Objects.hash(device, employee_id, timeStamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogInfor other = (LogInfor) obj;
		return device == other.device && employee_id == other.employee_id && Objects.equals(timeStamp, other.timeStamp);
	}

	public int getEmployeeID() {
        return employee_id;
    }

    public void setEmployeeID(int ID) {
        this.employee_id = ID;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
