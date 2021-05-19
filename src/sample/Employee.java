package sample;

import java.util.ArrayList;

public class Employee {
    public static ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<WorkHour> workHours = new ArrayList<>();
    private String name;
    private String password;
    private String funtion;
    private Integer contractHours;
    private boolean isSupervisor;
    private boolean isAdmin;

    public ArrayList<WorkHour> getWorkHours() {
        return workHours;
    }
    public Employee(String name, String password, String function, Integer contractHours, boolean isSupervisor, boolean isAdmin) {
        this.name=name;
        this.password=password;
        this.funtion=function;
        this.contractHours=contractHours;
        this.isSupervisor=isSupervisor;
        this.isAdmin=isAdmin;
        employees.add(this);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getFuntion() {
        return funtion;
    }
}
