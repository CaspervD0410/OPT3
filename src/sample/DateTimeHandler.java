package sample;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHandler {
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;

    public DateTimeHandler(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date=date;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public static DateTimeHandler checkDateTime(String date, String startTime, String endTime) {
        if (date==null||date.equals("")) { date=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")); }
        if (LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(LocalDate.now())) {
            System.out.println("Datum klopt niet.");
            return null;
        }
        if (!LocalTime.parse(endTime).isAfter(LocalTime.parse(startTime))) {
            System.out.println("Start- of eindtijd klopt niet.");
            return null;
        }
        return new DateTimeHandler(LocalDate.parse(date,DateTimeFormatter.ofPattern("dd-MM-yyyy")),LocalTime.parse(startTime),LocalTime.parse(endTime));
    }

    public void calcHours() {
        String workTime = "";

        if (date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            int timeRest=endTime.toSecondOfDay() - startTime.toSecondOfDay();
            workTime+=String.format("%02d",(timeRest/3600))+":"+String.format("%02d",((timeRest % 3600) / 60));
            System.out.print(workTime + " (200%)");
            //return workTime;
        }
        else {
            workTime+=splitHours();
            System.out.print(workTime);
            //return "" + String.format("%02d",(tempHour + (timeRest / 3600))) + ":" + String.format("%02d",(tempMinute+((timeRest % 3600) / 60))%60);
        }
    }

    public String splitHours() {
        int timeRest=0;
        int tempHour=0;
        int tempMinute=0;
        int earlyTimeDifference;
        int lateTimeDifference;

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

        return String.format("%02d",(timeRest / 3600)) + ":" + String.format("%02d",((timeRest % 3600) / 60)) + " (100%), " + String.format("%02d",tempHour) + ":" + String.format("%02d",tempMinute)+" (150%)";
    }
}
