

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Person implements Serializable, Observer{
    private int id;
    private String nume;
    private String adresa;
    private String telefon;

    public Person() {

    }

    public Person(int id, String nume, String adresa, String telefon) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return id == person.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public String toString() {
        return "Perosana " + " " + id + " " + nume +  " " + adresa +  " " + telefon;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Persoana " + id + " a efectuat " + arg);
    }

}

