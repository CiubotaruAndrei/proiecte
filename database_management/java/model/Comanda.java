package model;

/**
 * clasa repezinta maparea tabelului comanda din baza de date
 */
public class Comanda {
    private int id;
    private int idClient;
    private int idProdus;
    private int cantitate;

    public Comanda() {

    }

    public Comanda(int id, int idClient, int idProdus, int cantitate) {
        this.id = id;
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.cantitate = cantitate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }
}
