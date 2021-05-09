package sample;

import java.util.ArrayList;

public class Employee {
    public static ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<WorkHour> workHours = new ArrayList<>();
    private String name;
    private String funtion;
    private Integer contractHours;

    public ArrayList<WorkHour> getWorkHours() {
        return workHours;
    }
    public Employee(String name, String function, Integer contractHours) {
        this.name=name;
        this.funtion=function;
        this.contractHours=contractHours;
        employees.add(this);
    }

    public String getName() {
        return name;
    }

    public String getFuntion() {
        return funtion;
    }
}
