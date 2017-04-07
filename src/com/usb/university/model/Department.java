package com.usb.university.model;

/**
 *
 * @author "INNOVAPPS.CO S.A.S."
 */
public class Department {
    private String deptName;
    private String building;
    private double budget;

    public Department() {
    }

    public Department(String deptName) {
        this.deptName = deptName;
    }

    public Department(String deptName, String building, double budget) {
        this.deptName = deptName;
        this.building = building;
        this.budget = budget;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Department{\n" + "\tdeptName=" + deptName + ",\n\tbuilding=" + building + ",\n\tbudget=" + budget + "\n}";
    }     
    
}
