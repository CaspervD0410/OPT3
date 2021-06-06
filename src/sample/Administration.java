package sample;

import java.util.ArrayList;
import java.util.Scanner;

public class Administration extends Employee{

    public Administration(String name, String password, Integer contractHours) {
        super(name, password, contractHours);
    }

    @Override
    protected void extraOptions() {
        System.out.println("3) Klant aanmaken");
    }

    @Override
    protected void executeChoice(String choice) {
        Scanner in = new Scanner(System.in);
        switch (choice) {
            case "1" : writeHours(); break;
            case "2" : printOptionsHour(); break;
            case "3" : {
                System.out.println("Voer de klantnaam in: ");
                new Client(in.nextLine());
                System.out.println("Klant is aangemaakt.");
                break;
            }
            case "0" : Login.getInstance().logOut(); break;
            case "e" : System.exit(9999);
            default : System.out.println("Foutieve waarde ingevoerd. Probeer opnieuw.");
        }

    }

    private void printOptionsHour() {
        Scanner in = new Scanner(System.in);
        System.out.println("1) Uren per klant\n2) Eigen uren\n0) Terug");
        String choice = in.nextLine();
        switch (choice) {
            case "1" : {
                System.out.println("Voer de klantnaam in: ");
                String client = in.nextLine();
                for (Employee emp : employees) {
                    for (WorkHour wh : workHours) {
                        if (wh.getClient().getName().toLowerCase().contains(client.toLowerCase())) {
                            wh.printHourLine();
                        }
                    }
                }
                break;
            }
            case "2" : printHours(); break;
            case "0" : break;
            default : System.out.println("Foutieve waarde ingevoerd."); break;
        }
    }
}
