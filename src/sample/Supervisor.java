package sample;

public class Supervisor extends Employee {

    public Supervisor(String name, String password, Integer contractHours) {
        super(name, password, contractHours);
    }

    @Override
    protected void extraOptions() {

    }
}
