package dataAccess;

import connection.ConnectionFactory;
import model.Comanda;
import model.Produs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ComandaDAO extends AbstractDAO<Comanda> {

    @Override
    public Comanda findById(int id) {
        return super.findById(id);
    }

    @Override
    public int insert(Comanda comanda) {
        return super.insert(comanda);
    }

    @Override
    public int delete(int id) {
        return super.delete(id);
    }

    @Override
    public int update(Comanda comanda, int id) {
        return super.update(comanda, id);
    }

    /**
     * cauta comanda efectuata de un anumit client
     * @param id id-ul comenzii
     * @param idClient id-ul clientului
     * @return lista produselor comandate de un client
     */
    public List<Comanda> bon (int id, int idClient) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM comanda WHERE id= " + id + " and idClient= " + idClient;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
