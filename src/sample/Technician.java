package sample;

public class Technician extends Employee {
    public Technician(String name, String password, Integer contractHours) {
        super(name, password, contractHours);
    }

    @Override
    protected void extraOptions() {

    }
}
