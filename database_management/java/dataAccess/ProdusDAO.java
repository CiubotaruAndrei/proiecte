package dataAccess;

import model.Produs;

import javax.swing.*;
import java.util.List;

public class ProdusDAO extends AbstractDAO<Produs> {

    @Override
    public JTable findAll() {
        return super.findAll();
    }

    @Override
    public Produs findById(int id) {
        return super.findById(id);
    }

    @Override
    public int insert(Produs produs) {
        return super.insert(produs);
    }

    @Override
    public int delete(int id) {
        return super.delete(id);
    }

    @Override
    public int update(Produs produs, int id) {
        return super.update(produs, id);
    }
}
