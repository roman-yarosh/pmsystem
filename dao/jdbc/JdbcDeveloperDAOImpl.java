package net.proselyte.pmsystem.dao.jdbc;

import net.proselyte.pmsystem.dao.DeveloperDAO;
import net.proselyte.pmsystem.dao.GenericDAO;
import net.proselyte.pmsystem.model.Developer;

import java.sql.ResultSet;
import java.sql.SQLException;

import static net.proselyte.pmsystem.util.ConnectionUtil.*;

/**
 * Implementation of {@link GenericDAO} interface for class {@link Developer}.
 *
 * Created by Roman Yarosh on 14.12.2016.
 */
public class JdbcDeveloperDAOImpl implements DeveloperDAO {

    private static final String INSERT_NEW = "INSERT INTO developers(firstName, lastName, age, salary, yearsOfExperience, experience) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String GET_BY_ID = "SELECT * FROM developers WHERE ID = ?;";
    private static final String UPDATE_ROW = "UPDATE developers SET firstName = ?, lastName = ?, age = ?, salary = ?, yearsOfExperience = ?, experience = ? WHERE ID = ?;";
    private static final String DELETE_ROW = "DELETE FROM developers WHERE ID = ?;";

     public JdbcDeveloperDAOImpl(){
        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Connection failed " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver not found " + e);
        }
    }

    @Override
    public Developer getById(Long developerId) {
        try{
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, developerId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(!resultSet.next()){
                    return null;
                }
                Developer developer = new Developer();
                developer.setId(resultSet.getLong("id"));
                developer.setFirstName(resultSet.getString("firstName"));
                developer.setLastName(resultSet.getString("lastName"));
                developer.setAge(resultSet.getInt("age"));
                developer.setSalary(resultSet.getBigDecimal("salary"));
                developer.setYearsOfExperience(resultSet.getInt("yearsOfExperience"));
                developer.setExperience(resultSet.getString("experience"));
                return developer;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closePreparedStatement();
        }
    }

    @Override
    public void save(Developer developer) {
         try {
             preparedStatement = connection.prepareStatement(INSERT_NEW);
             preparedStatement.setString(1, developer.getFirstName());
             preparedStatement.setString(2, developer.getLastName());
             preparedStatement.setInt(3, developer.getAge());
             preparedStatement.setBigDecimal(4, developer.getSalary());
             preparedStatement.setInt(5, developer.getYearsOfExperience());
             preparedStatement.setString(6, developer.getExperience());
             preparedStatement.executeUpdate();
        } catch (SQLException e) {
             throw new RuntimeException(e.getMessage(), e);
        } finally {
             closePreparedStatement();
         }
    }

    @Override
    public void update(Developer developer) {
         try{
             preparedStatement = connection.prepareStatement(UPDATE_ROW);
             preparedStatement.setString(1, developer.getFirstName());
             preparedStatement.setString(2, developer.getLastName());
             preparedStatement.setInt(3, developer.getAge());
             preparedStatement.setBigDecimal(4, developer.getSalary());
             preparedStatement.setInt(5, developer.getYearsOfExperience());
             preparedStatement.setString(6, developer.getExperience());
             preparedStatement.setLong(7, developer.getId());
             preparedStatement.executeUpdate();
         } catch (SQLException e) {
             throw new RuntimeException(e.getMessage(), e);
         } finally {
             closePreparedStatement();
         }

    }

    @Override
    public void remove(Developer developer) {
        try{
            preparedStatement = connection.prepareStatement(DELETE_ROW);
            preparedStatement.setLong(1, developer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            closePreparedStatement();
        }
    }
}
