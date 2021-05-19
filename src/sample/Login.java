package sample;

public class Login {
    private Login singleton;
    private Employee loggedInUser;

    private Login() {
        loggedInUser=null;
    }
}
