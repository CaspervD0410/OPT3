package sample;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class WorkHour {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Client client;
    private Employee employee;
    private String description;


    public static boolean saveWorkHour(String date, String startTime, String endTime, String client, Employee emp, String description) {
        if (LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(LocalDate.now())) {
            System.out.println("Date isn't correct.");
            return false;
        }
        if (!LocalTime.parse(endTime).isAfter(LocalTime.parse(startTime))) {
            System.out.println("Starting or ending time isn't correct.");
            return false;
        }
        for (Client cl : Client.clients) {
            if (cl.getName().equals(client)) {
                new WorkHour(LocalDate.parse(date,DateTimeFormatter.ofPattern("dd-MM-yyyy")),LocalTime.parse(startTime),LocalTime.parse(endTime),cl,emp,description);
                return true;
            }
        }
        System.out.println("Client doesn't exist");
        return false;
    }

    public WorkHour(LocalDate date, LocalTime startTime, LocalTime endTime, Client client, Employee employee, String description) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.client = client;
        this.employee = employee;
        this.description = description;
        employee.getWorkHours().add(this);
    }

    public Client getClient() {
        return client;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
