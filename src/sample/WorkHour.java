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

    public boolean saveWorkHour(String date, String startTime, String endTime, String client, Employee emp, String description) {
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
                this.date=LocalDate.parse(date,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                this.startTime=LocalTime.parse(startTime);
                this.endTime=LocalTime.parse(endTime);
                this.client=cl;
                this.employee=emp;
                this.description=description;
                return true;
            }
        }
        System.out.println("Client doesn't exist");
        return false;
    }
}
