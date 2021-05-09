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
            System.out.println("Datum klopt niet.");
            return false;
        }
        if (!LocalTime.parse(endTime).isAfter(LocalTime.parse(startTime))) {
            System.out.println("Start- of eindtijd klopt niet.");
            return false;
        }
        for (Client cl : Client.clients) {
            if (cl.getName().equals(client)) {
                new WorkHour(LocalDate.parse(date,DateTimeFormatter.ofPattern("dd-MM-yyyy")),LocalTime.parse(startTime),LocalTime.parse(endTime),cl,emp,description);
                System.out.println("Uren opgeslagen.");
                return true;
            }
        }
        System.out.println("Klant bestaat niet.");
        return false;
    }

    public static boolean checkWorkHour(String date, String startTime, String endTime, String client, Employee emp, String description){
        if (description.equalsIgnoreCase("vrij")&&!client.equalsIgnoreCase("VDA")){
            System.out.println("Vrij is altijd ten laste van VDA.");
            return false;
        }
        if (emp.getFuntion().equals("Administratie")&&!client.equalsIgnoreCase("VDA")) {
            System.out.println("Administratief werk is altijd ten last van VDA.");
            return false;
        }
        return saveWorkHour(date, startTime, endTime, client, emp, description);
    }

    public static boolean checkWorkHour(String startTime, String endTime, String client, Employee emp, String description) {
        return checkWorkHour(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), startTime, endTime, client, emp, description);
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
