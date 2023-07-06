package project.itss.group11.itss.model;
import java.time.LocalDateTime;
public class FormDatabase {
    private int idform;
    private int idlog;
    private int idnhanvien;
    private LocalDateTime oldtime;
    private LocalDateTime newtime;
    private int oldDevice;
    private int newDevice;

    public FormDatabase(int idform,int idlog, int idnhanvien, LocalDateTime oldtime, LocalDateTime newtime, int oldDevice, int newDevice) {
        this.idform = idform;
        this.idlog = idlog;
        this.idnhanvien = idnhanvien;
        this.oldtime = oldtime;
        this.newtime = newtime;
        this.oldDevice = oldDevice;
        this.newDevice = newDevice;
    }

    public int getIdform() {
        return idform;
    }

    public LocalDateTime getOldtime() {
        return oldtime;
    }


    public int getIdlog() {
        return idlog;
    }

    public int getOldDevice() {
        return oldDevice;
    }

    public int getNewDevice() {
        return newDevice;
    }

    public int getIdnhanvien() {
        return idnhanvien;
    }
    public LocalDateTime getNewtime() {
        return newtime;
    }
}
