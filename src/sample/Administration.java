package sample;

import java.util.ArrayList;

public class Administration extends Employee{

    public Administration(String name, String password, Integer contractHours) {
        super(name, password, contractHours);
    }

    @Override
    protected void extraOptions() {

    }

    @Override
    protected void executeChoice(String choice) {
        super.executeChoice(choice);
    }
}
