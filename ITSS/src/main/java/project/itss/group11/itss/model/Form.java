package project.itss.group11.itss.model;

import java.time.LocalDateTime;

public class Form {
    private int idform;
    private int idnv;
    private LocalDateTime oldT;
    private LocalDateTime newT;
    private int oldDevice;
    private int newDevice;
    private int idlog;

    public int getIdlog() {
        return idlog;
    }

    public int getIdform() {
        return idform;
    }

    public Form() {
    }

    public Form(int idform,int idnv, LocalDateTime oldT, LocalDateTime newT, int oldDevice, int newDevice,int idLog) {
        this.idform = idform;
        this.idnv = idnv;
        this.oldT = oldT;
        this.newT = newT;
        this.oldDevice = oldDevice;
        this.newDevice = newDevice;
        this.idlog = idLog;
    }

    public int getOldDevice() {
        return oldDevice;
    }
    public int getNewDevice() {
        return newDevice;
    }
    public int getIdnv() {
        return idnv;
    }
    public LocalDateTime getOldT() {
        return oldT;
    }
    public LocalDateTime getNewT() {
        return newT;
    }

    public void setNewDevice(int newDevice) {
        this.newDevice = newDevice;
    }

    public void setNewT(LocalDateTime newT) {
        this.newT = newT;
    }
}
