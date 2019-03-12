package model;

/**
 * clasa repezinta maparea tabelului client din baza de date
 */
public class Client {
    private int idClient;
    private String nume;
    private String adresa;
    private String telefon;
    private String email;

    public Client() {

    }

    public Client(int idClient, String nume, String adresa, String telefon, String email) {
        this.idClient = idClient;
        this.nume = nume;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
    }

    public Client(String nume, String adresa, String telefon, String email) {
        this.nume = nume;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Client " + "[idClient= " + idClient + " nume= " + nume + " adresa= " + adresa + " telefon= " + telefon
                + " email= " + email + "]";
    }
}
