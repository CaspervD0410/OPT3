package sample;

import java.time.*;

public class WorkHour {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Client client;
    private Employee employee;
    private String description;

    public boolean saveWorkHour(String date, String startTime, String endTime, String client, Employee emp, String description) {
        return true;
    }
}
