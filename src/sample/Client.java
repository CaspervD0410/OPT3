package sample;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Client {
    private String name;
    public static ArrayList<Client> clients = new ArrayList<>();

    public Client(String name) {
        this.name = name;
        clients.add(this);
    }
    public String getName() {
        return name;
    }
    public Double showWorkHours() {
        double tempHour = 0.0;
        double tempMinute = 0.0;
        LocalTime tempTime;
        System.out.println("\n\nClient: "+name);
        for (Employee emp : Employee.employees) {
            for (WorkHour hour : emp.getWorkHours()) {
                if (hour.getClient().equals(this)) {
                    System.out.print("Employee: " + emp.getName() + "\tDate: " + hour.getDate().toString());
                    System.out.print("\tWorked time: ");
                    tempTime=LocalTime.parse(calcHours(hour));
                    tempHour+=tempTime.getHour();
                    tempMinute+=tempTime.getMinute();
                }
            }
        }
        return tempHour+(tempMinute*(5.0/300.0));
    }
    public String calcHours(WorkHour hour) {
        int tempHour=0;
        int tempMinute=0;
        int earlyTimeDifference=0;
        int lateTimeDifference=0;
        int timeRest=0;
        String workTime = "";

        if (hour.getDate().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            timeRest=hour.getEndTime().toSecondOfDay() - hour.getStartTime().toSecondOfDay();
            workTime+=String.format("%02d",(timeRest/3600))+":"+String.format("%02d",((timeRest % 3600) / 60));
            System.out.println(workTime + " (200%)");
            return workTime;
        }
        else {
            earlyTimeDifference = hour.getStartTime().toSecondOfDay() - LocalTime.parse("08:30").toSecondOfDay();
            if (earlyTimeDifference < 0) {
                tempHour += -earlyTimeDifference / 3600;
                tempMinute += (-earlyTimeDifference % 3600) / 60;
                timeRest-=earlyTimeDifference;
            }
            lateTimeDifference = hour.getEndTime().toSecondOfDay() - LocalTime.parse("17:00").toSecondOfDay();
            if (lateTimeDifference > 0) {
                tempHour += lateTimeDifference / 3600;
                tempMinute += (lateTimeDifference % 3600) / 60;
                timeRest-=lateTimeDifference;
            }
            timeRest += hour.getEndTime().toSecondOfDay() - hour.getStartTime().toSecondOfDay();

            workTime += String.format("%02d",(timeRest / 3600)) + ":" + String.format("%02d",((timeRest % 3600) / 60)) + " (100%), " + String.format("%02d",tempHour) + ":" + String.format("%02d",tempMinute)+" (150%)";
            System.out.println(workTime);
            return "" + String.format("%02d",(tempHour + (timeRest / 3600))) + ":" + String.format("%02d",(tempMinute+((timeRest % 3600) / 60))%60);
        }
    }
}
