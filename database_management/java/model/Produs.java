package model;

/**
 * clasa repezinta maparea tabelului produs din baza de date
 */
public class Produs {
    private int idProdus;
    private String denumire;
    private float pret;
    private int stoc;

    public Produs() {

    }

    public Produs(String denumire, float pret, int stoc) {
        this.denumire = denumire;
        this.pret = pret;
        this.stoc = stoc;
    }

    public Produs(int idProdus, String denumire, float pret, int stoc) {
        this.idProdus = idProdus;
        this.denumire = denumire;
        this.pret = pret;
        this.stoc = stoc;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    public String toString() {
        return "Produs " + "[idProdus= " + idProdus + " denumire= " + denumire + " pret= " + pret
                + " stoc= " + stoc + "]";
    }
}
