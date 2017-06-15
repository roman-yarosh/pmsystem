package net.proselyte.pmsystem.dao.jdbc;

import net.proselyte.pmsystem.dao.DocumentDAO;
import net.proselyte.pmsystem.dao.GenericDAO;
import net.proselyte.pmsystem.model.Document;

import java.sql.ResultSet;
import java.sql.SQLException;

import static net.proselyte.pmsystem.util.ConnectionUtil.*;

/**
 * Created by Alexey on 12/10/2016.
 */
public class JdbcDocumentDAOImpl implements DocumentDAO {
    private static final String INSERT_NEW = "INSERT INTO DOCUMENT(NAME) VALUES (?)";
    private static final String GET_BY_ID = "SELECT * FROM DOCUMENT WHERE ID = ?";
    private static final String UPDATE_ROW = "UPDATE DOCUMENT SET NAME = ? CONTENT = ? WHERE ID = ?";
    private static final String DELETE_ROW = "DELETE FROM DOCUMENT WHERE ID = ?";


    public JdbcDocumentDAOImpl(){
        try{
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Connetion failed " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver not found " + e);
        }
    }

    @Override
    public Document getById(Long id) {
        try {
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(!resultSet.next()){
                    return null;
                }
                Document document = new Document();
                document.setId(resultSet.getLong(1));
                document.setName(resultSet.getString(2));
                document.setContent(resultSet.getString(3));
                return document;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void save(Document document) {
        try {
            preparedStatement = connection.prepareStatement(INSERT_NEW);
            preparedStatement.setString(2, document.getName());
            preparedStatement.setString(3, document.getContent());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Document document) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ROW);
            preparedStatement.setLong(1, document.getId());
            preparedStatement.setString(2, document.getName());
            preparedStatement.setString(3, document.getContent());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void remove(Document document) {
        try {
            preparedStatement = connection.prepareStatement(DELETE_ROW);
            preparedStatement.setLong(1, document.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
