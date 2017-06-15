package net.proselyte.pmsystem.dao.jdbc;

import net.proselyte.pmsystem.dao.CustomerDAO;
import net.proselyte.pmsystem.dao.GenericDAO;
import net.proselyte.pmsystem.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

import static net.proselyte.pmsystem.util.ConnectionUtil.*;

/**
 * Created by Alexey on 12/09/2016.
 */
public class JdbcCustomerDAOImpl implements CustomerDAO {
    private static final String INSERT_NEW = "INSERT INTO CUSTOMER(NAME, DESCRIPTION) VALUES (?, ?)";
    private static final String GET_BY_ID = "SELECT * FROM CUSTOMER WHERE ID = ?";
    private static final String UPDATE_ROW = "UPDATE CUSTOMER SET NAME = ?, DESCRIPTION = ? WHERE ID = ?";
    private static final String DELETE_ROW = "DELETE FROM CUSTOMER WHERE ID = ?";

    public JdbcCustomerDAOImpl(){
        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Connection failed " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver not found " + e);
        }
    }

    public Customer getById(Long customerId) {

        try{
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, customerId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(!resultSet.next()){
                    return null;
                }
                Customer customer = new Customer();
                customer.setId(resultSet.getLong(1));
                customer.setName(resultSet.getString(2));
                customer.setDescription(resultSet.getString(3));
                return customer;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    public void save(Customer customer) {
        try {
            preparedStatement = connection.prepareStatement(INSERT_NEW);
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getDescription());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void update(Customer customer) {
        try{
            preparedStatement = connection.prepareStatement(UPDATE_ROW);
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getDescription());
            preparedStatement.setLong(1, customer.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void remove(Customer customer) {
        try{
            preparedStatement = connection.prepareStatement(DELETE_ROW);
            preparedStatement.setLong(1, customer.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
