package sample;
import java.time.*;
public class Main{

    public static void main(String[] args) {
        while(Login.getInstance().isAuthenticated()) {
            Login.getInstance().getLoggedInUser().printMenu();
        }
    }

}
