package businessLogic;

import dataAccess.ClientDAO;
import dataAccess.ComandaDAO;
import dataAccess.ProdusDAO;
import model.Client;
import model.Comanda;
import model.Produs;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * realizeaza logica pentru realizarea operatiilor pe tabela comanda
 */
public class ComandaBL {
    private ComandaDAO c;
    private ProdusDAO p;
    private ClientDAO cl;

    public ComandaBL() {
        c = new ComandaDAO();
        cl = new ClientDAO();
        p = new ProdusDAO();
    }

    private int validate(Comanda comanda) {
        int ok=0;
        if(cl.findById(comanda.getIdClient())==null)
            return 2; //idClient invalid
        if(p.findById(comanda.getIdProdus())==null)
            return 3; //idProdus invalid
        if(comanda.getCantitate()<=0)
            return 4; //cantitate invalida
        Produs produs = p.findById(comanda.getIdProdus());
        if(produs.getStoc()-comanda.getCantitate()<0)
            return 5; //stoc insuficeint
        return ok;
    }

    public int insert(Comanda comanda) {
        if(validate(comanda)!=0)
            return validate(comanda);
        Produs produs = p.findById(comanda.getIdProdus());
        produs.setStoc(produs.getStoc()-comanda.getCantitate());
        p.update(produs,produs.getIdProdus());
        return c.insert(comanda);

    }

    /**
     * realizeaza pasa cu pas continutul facturii pentru o anumita comanda
     * @param comanda obiect de tipul comanda
     * @return  continutul facturii
     */
    public String message(Comanda comanda) {
        String msg = "";
        float total = 0;
        Client client = cl.findById(comanda.getIdClient());
        msg = msg + "ID Comanda " + comanda.getId() + "\n";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        msg = msg + dateFormat.format(date) + "\n";
        msg = msg + client.toString() + "\n";
        msg = msg + "Nr. Crt.   "  + "Produs    "  +  "         Pret" + "\n";
        List<Comanda> list;
        list = c.bon(comanda.getId(), comanda.getIdClient());
        int nr = 1;
        for(Comanda  i : list) {
            Produs produs;
            produs = p.findById(i.getIdProdus());
            float aux;
            aux = produs.getPret() * i.getCantitate();
            msg = msg + nr + "          " + produs.getDenumire() + "            "+ i.getCantitate() + " X " +
                    produs.getPret() + " = " + aux + "\n";
            total+=aux;
            nr++;
        }
        msg = msg + "Total: " + total + "\nVa multumim!!!";
        return msg;
    }
}
