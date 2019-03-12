package Coada;

public class Client {
    private int timpSosire;
    private int timpServire;
    private int id;

    public Client (int timpSosire, int timpServire, int id) {
        this.timpSosire=timpSosire;
        this.timpServire=timpServire;
        this.id=id;
    }

    public int getTimpSosire() {
        return timpSosire;
    }

    public int getTimpServire() {
        return timpServire;
    }

    public int getId() {
        return id;
    }
}
