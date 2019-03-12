package businessLogic;

import businessLogic.validators.ProdusValidator;
import dataAccess.ProdusDAO;
import model.Produs;


import javax.swing.*;

/**
 * realizeaza logica pentru realizarea operatiilor pe tabela produs
 */
public class ProdusBL {
    private ProdusDAO p;

    public ProdusBL(){
        p = new ProdusDAO();
    }

    public Produs findClientById(int id) {
        Produs produs;
        produs = p.findById(id);
        return produs;
    }

    public JTable findAll() {
        return p.findAll();
    }

    public int insert(Produs produs) {
        ProdusValidator v = new ProdusValidator();
        System.out.println(v.validate(produs));
        if(v.validate(produs)!=0)
            return v.validate(produs);
        else
            return p.insert(produs);
    }

    public int delete(int id) {
        return p.delete(id);
    }

    public int update(Produs produs, int id) {
        ProdusValidator v = new ProdusValidator();
        if(v.validate(produs)!=0)
            return v.validate(produs);
        else
            return p.update(produs ,id);
    }
}
