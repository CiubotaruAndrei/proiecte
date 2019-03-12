package businessLogic;

import businessLogic.validators.ClientValidator;
import dataAccess.ClientDAO;
import model.Client;

import javax.swing.*;
import java.util.List;

/**
 * realizeaza logica pentru realizarea operatiilor pe tabela client
 */
public class ClientBL {
    private ClientDAO c;
    public ClientBL(){
        c = new ClientDAO();
    }

    public Client findClientById(int id) {
        Client client;
        client = c.findById(id);
        return client;
    }

    public JTable findAll() {
        return c.findAll();
    }

    public int insert(Client client) {
        ClientValidator v = new ClientValidator();
        if(v.validate(client)!=0)
            return v.validate(client);
        else
            return c.insert(client);
    }

    public int delete(int id) {
        return c.delete(id);
    }

    public int update(Client client, int id) {
        ClientValidator v = new ClientValidator();
        if(v.validate(client)!=0)
            return v.validate(client);
        else
            return c.update(client ,id);
    }

}
