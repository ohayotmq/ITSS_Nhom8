package project.itss.group11.itss.model;

import java.time.LocalDate;

public class Employee {
    private int ID;
    private String name;
    private int role;
    private int Unit;
    private LocalDate birthDate;
    private int gender;

    public Employee(int ID) {
        this.ID = ID;
    }

    public Employee(int ID, String name, LocalDate birthDate, int unit, int role, int gender) {
        this.ID = ID;
        this.name = name;
        this.role = role;
        Unit = unit;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getUnit() {
        return Unit;
    }

    public void setUnit(int unit) {
        Unit = unit;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
