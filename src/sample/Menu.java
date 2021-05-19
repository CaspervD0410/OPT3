package sample;

public class Menu {
    protected Employee loggedInUser;
    public Menu(Employee loggedInUser) {
        this.loggedInUser= loggedInUser;
    }
    public static void printMenu() {
        printBasicOptions();
        printExtraOptions();
    }
    public static void printBasicOptions(){}
    public static void printExtraOptions(){}
}

class SupervisorMenu extends Menu {
    public SupervisorMenu(Employee loggedInUser) {
        super(loggedInUser);
    }

    public static void printExtraOptions() {}
}

class AdminMenu extends Menu {
    public AdminMenu(Employee loggedInUser) {
        super(loggedInUser);
    }

    public static void printExtraOptions() {}
}