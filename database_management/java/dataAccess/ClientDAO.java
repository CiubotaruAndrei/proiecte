package dataAccess;

import model.Client;

import javax.swing.*;
import java.util.List;

public class ClientDAO extends AbstractDAO<Client> {

    public Client findById(int id) {
        return super.findById(id);
    }

    @Override
    public int insert(Client client) {
        return super.insert(client);
    }

    @Override
    public int delete(int id) {
        return super.delete(id);
    }

    @Override
    public int update(Client client, int id) {
        return super.update(client, id);
    }

    @Override
    public JTable findAll() {
        return super.findAll();
    }
}
