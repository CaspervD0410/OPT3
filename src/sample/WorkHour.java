package sample;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class WorkHour {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final Client client;
    private final String description;

    private static boolean saveWorkHour(String date, String startTime, String endTime, String client, String description) {
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
                new WorkHour(LocalDate.parse(date,DateTimeFormatter.ofPattern("dd-MM-yyyy")),LocalTime.parse(startTime),LocalTime.parse(endTime),cl,description);
                System.out.println("Uren opgeslagen.");
                return true;
            }
        }
        System.out.println("Klant bestaat niet.");
        return false;
    }

    public static boolean checkWorkHour(String date, String startTime, String endTime, String client, String description){
        if (description.toLowerCase().contains("vrij")&&!client.equalsIgnoreCase("VDA")){
            System.out.println("Vrij is altijd ten laste van VDA. Uren niet opgeslagen.");
            return false;
        }
        if (Login.getInstance().getLoggedInUser() instanceof Administration &&!client.equalsIgnoreCase("VDA")) {
            System.out.println("Administratief werk is altijd ten last van VDA. Uren niet opgeslagen.");
            return false;
        }
        if (date==null||date.equals("")) { date=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")); }
        return saveWorkHour(date, startTime, endTime, client, description);
    }

    public WorkHour(LocalDate date, LocalTime startTime, LocalTime endTime, Client client, String description) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.client = client;
        this.description = description;
        Login.getInstance().getLoggedInUser().getWorkHours().add(this);
    }

    public void printHourLine() {
        System.out.printf("Datum: %s | Werktijd: ",date.toString());
        calcHours();
        System.out.printf(" | Beschrijving: %s%n%n",description);
    }

    //Methode gaat weer iets returnen als de tests opgelost zijn (moest nog niet voor domein- en eindontwerp)
    public void calcHours() {
        int tempHour=0;
        int tempMinute=0;
        int earlyTimeDifference;
        int lateTimeDifference;
        int timeRest=0;
        String workTime = "";

        if (date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            timeRest=endTime.toSecondOfDay() - startTime.toSecondOfDay();
            workTime+=String.format("%02d",(timeRest/3600))+":"+String.format("%02d",((timeRest % 3600) / 60));
            System.out.print(workTime + " (200%)");
            //return workTime;
        }
        else {
            earlyTimeDifference = startTime.toSecondOfDay() - LocalTime.parse("08:30").toSecondOfDay();
            if (earlyTimeDifference < 0) {
                tempHour += -earlyTimeDifference / 3600;
                tempMinute += (-earlyTimeDifference % 3600) / 60;
                timeRest-=earlyTimeDifference;
            }
            lateTimeDifference = endTime.toSecondOfDay() - LocalTime.parse("17:00").toSecondOfDay();
            if (lateTimeDifference > 0) {
                tempHour += lateTimeDifference / 3600;
                tempMinute += (lateTimeDifference % 3600) / 60;
                timeRest-=lateTimeDifference;
            }
            if(!(earlyTimeDifference<0) && !(lateTimeDifference > 0)) { timeRest += endTime.toSecondOfDay() - startTime.toSecondOfDay();}

            workTime += String.format("%02d",(timeRest / 3600)) + ":" + String.format("%02d",((timeRest % 3600) / 60)) + " (100%), " + String.format("%02d",tempHour) + ":" + String.format("%02d",tempMinute)+" (150%)";
            System.out.print(workTime);
            //return "" + String.format("%02d",(tempHour + (timeRest / 3600))) + ":" + String.format("%02d",(tempMinute+((timeRest % 3600) / 60))%60);
        }
    }

    public Client getClient() { return client; }
}
