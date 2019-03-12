package dataAccess;

import connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * clasa realizeaza interogari asupra bazei de date
 * @param <T> parametru generic
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * metoda returneaza interogrea pentru un anumit camp SELECT * FROM tabela WHERE field=?
     * @param field campul din clauza WHERE
     * @return interogarea SELECT ...
     */
    private String createSelectQuery(String field) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append(" * ");
        query.append(" FROM ");
        query.append(type.getSimpleName());
        query.append(" WHERE " + field + " =?");
        return query.toString();
    }

    /**
     * realizeaza interogarea SELECT * FROM ...
     * @return tabela cu rezultatele interogarii
     */
    public JTable findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createTable(createObjects(resultSet));
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public String getFirstField() {
        String idTabela = "";
        try {
            for (Field field : type.getDeclaredFields()) {
                idTabela = field.getName();
                break;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return idTabela;
    }

    /**
     * realizeaza cautarea dupa id
     * @param id valoarea id-ului
     * @return un obiect de tipul T
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(getFirstField());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(!resultSet.next())
                return null;
            resultSet.beforeFirst();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * utilizeaza tehnica reflection pentru a genera o lista de tipul T
     * @param resultSet rezultatul unei interogari
     * @return lista T
     */
    public List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * insereaza intr-o tabela
     * @param t obiect de tipul T
     * @return 1 daca interogarea se realizeaza cu succes
     */
    public int insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "INSERT INTO " + type.getSimpleName() + " VALUES(";
        int nr=0;
        for(Field field : type.getDeclaredFields())
            nr++;
        int i=0;
        try {
            for (Field field : type.getDeclaredFields()) {
                i++;
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                if(field.getType().getSimpleName().equals("String")){
                    if(i==nr)
                        query = query + "'" + method.invoke(t) + "');";
                    else
                        query = query + "'" +  method.invoke(t) + "',";
                }
                else
                    if(i==nr)
                        query = query + method.invoke(t) + ");";
                    else
                        query = query + method.invoke(t) + ",";

            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            return statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    /**
     * sterge o anumita intrare din tabela
     * @param id id-ul ce trebuie sters
     * @return 1 daca interogarea se realizeaza cu succes
     */
    public int delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM " + type.getSimpleName() + " WHERE " + getFirstField()  + "=?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            System.out.println(statement);
            return statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    /**
     * realizeaza update pe o anumita intrare din tabela
     * @param t obiect de tipul T
     * @param id id-ul ce trebuie editat
     * @return 1 daca interogarea se realizeaza cu succes
     */
    public int update(T t,int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "UPDATE " + type.getSimpleName() + " SET ";
        int nr=0;
        for(Field field : type.getDeclaredFields())
            nr++;
        int i=0;
        try {
            for (Field field : type.getDeclaredFields()) {
                i++;
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                    if (field.getType().getSimpleName().equals("String")){
                        if(i==nr)
                            query = query + field.getName() + "='" + method.invoke(t) + "'";
                        else if(i>1)
                            query = query + field.getName() + "='" + method.invoke(t) + "',";
                    }
                    else
                        if(i==nr)
                            query = query + field.getName() + "=" + method.invoke(t);
                        else if(i>1)
                            query = query + field.getName() + "=" + method.invoke(t) + ",";

            }
            query = query + " WHERE " + getFirstField() + "= " + id;
        }catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            System.out.println(statement);
            return statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    /**
     * primeste o lista de obiecte si construieste un tabel avand coloanele variabilele instanta si il populeaza cu valori
     * @param objects
     * @return tabel
     */
    private JTable createTable(List<T> objects) {
        DefaultTableModel model = new DefaultTableModel();
        for(Field field : objects.get(0).getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                model.addColumn(field.getName());
                System.out.println(field.getName());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        for(int i=0; i<objects.size(); i++) {
            int j=0;
            String[] row = {"","","","","","","",""};
            for(Field field : objects.get(i).getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(objects.get(i));
                    row[j] = "" + value;
                    System.out.print(row[j] + " ");
                    j++;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(" ");
            model.addRow(row);
        }
        JTable table = new JTable(model);
        return table;
    }
}