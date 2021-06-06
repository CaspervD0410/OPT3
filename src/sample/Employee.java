package sample;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Employee {
    public static ArrayList<Employee> employees = new ArrayList<>();
    protected ArrayList<WorkHour> workHours = new ArrayList<>();
    protected String name;
    protected String password;
    protected Integer contractHours;

    public Employee(String name, String password, Integer contractHours) {
        this.name=name;
        this.password=password;
        this.contractHours=contractHours;
        employees.add(this);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<WorkHour> getWorkHours() { return workHours; }

    public void printMenu() {
        Scanner sc = new Scanner(System.in);
        basicOptions();
        extraOptions();
        System.out.println("0) Uitloggen");
        System.out.println("e) Afsluiten");
        String choice = sc.nextLine();
        executeChoice(choice);

    }

    protected void writeHours() {
        Scanner in = new Scanner(System.in);
        System.out.println("Voer de datum in (voor vandaag: vul niks in; formaat: dd-MM-yyyy):");
        String date = in.nextLine();
        System.out.println("Voer de starttijd in (formaat: 00:00):");
        String startTime = in.nextLine();
        System.out.println("Voer de eindtijd in (formaat: 00:00):");
        String endTime = in.nextLine();
        System.out.println("Voer de klantnaam in:");
        String client = in.nextLine();
        System.out.println("Voer een omschrijving in:");
        String description = in.nextLine();
        WorkHour.checkWorkHour(date,startTime,endTime,client,description);
    }

    protected void basicOptions() {
        System.out.println("" +
                "1) Uren invoeren \n2) Uren bekijken "
        );
    }

    protected abstract void extraOptions();

    protected void executeChoice(String choice) {
        switch (choice) {
            case "1" : writeHours(); break;
            case "2" : printHours(); break;
            case "0" : Login.getInstance().logOut(); break;
            case "e" : System.exit(9999);
            default : System.out.println("Foutieve waarde ingevoerd. Probeer opnieuw.");
        }
    }

    protected void printHours() {
        new WorkHour(LocalDate.parse("2021-06-05"), LocalTime.parse("08:00"),LocalTime.parse("09:00"),Client.clients.get(0),"Onderhoud server");
        new WorkHour(LocalDate.parse("2021-06-06"), LocalTime.parse("09:00"),LocalTime.parse("11:15"),Client.clients.get(0),"Probleem email");

        for (WorkHour wh : workHours) {
            wh.printHourLine();
        }
    }
}
