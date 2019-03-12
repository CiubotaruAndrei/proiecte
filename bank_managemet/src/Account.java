import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public abstract class Account extends Observable implements Serializable{
    private int personId;
    private int id;
    private String tip;
    private float valoare;

    public Account() {

    }

    public Account(int id, int personId, String tip, float valoare) {
        this.id = id;
        this.personId = personId;
        this.tip = tip;
        this.valoare = valoare;
    }

    public int getId() {
        return id;
    }

    public int getPersonId() {
        return personId;
    }

    public String getTip() {
        return tip;
    }

    public float getValoare() {
        return valoare;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setValoare(float valoare) {
        this.valoare = valoare;
    }

    public abstract boolean deposit(float sum);

    public abstract boolean withdraw(float sum);

    public String toString() {
        return "Cont " + id + " Persoana " + personId + " " + tip +  " " + valoare;
    }

}
