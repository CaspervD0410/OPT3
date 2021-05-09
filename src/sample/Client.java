package sample;

import java.util.ArrayList;

public class Client {
    private String name;
    public static ArrayList<Client> clients = new ArrayList<>();

    public Client(String name) {
        this.name = name;
        clients.add(this);
    }
    public String getName() {
        return name;
    }
}
