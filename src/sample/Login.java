package sample;

import java.util.Scanner;

public class Login {
    private static Login singleton;
    private Employee loggedInUser;

    private Login() {
        loggedInUser=null;
    }
    public static Login getInstance() {
        if(singleton==null) {
            singleton = new Login();
        }
        return singleton;
    }
    private boolean userExists (String name) {
        for (Employee emp : Employee.employees) {
            if (emp.getName().equals(name)) {
                loggedInUser=emp;
                return true;
            }
        }
        return false;
    }
    private boolean userIsAuthenticated() { return loggedInUser != null; }
    public boolean isAuthenticated() {
        if (userIsAuthenticated()) {
            return true;
        }
        else {
            Scanner in = new Scanner(System.in);
            System.out.println("Voer uw gebruikersnaam in: ");
            String userName = in.nextLine();
            System.out.println("Voer uw wachtwoord in: ");
            String password = in.nextLine();
            if (userExists(userName)&&password.equals(loggedInUser.getPassword())) {
                return true;
            }
            System.out.println("Gebruikersnaam of wachtwoord klopt niet.");
            return false;
        }
    }
}
