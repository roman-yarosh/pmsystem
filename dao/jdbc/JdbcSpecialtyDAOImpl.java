package net.proselyte.pmsystem.dao.jdbc;

import net.proselyte.pmsystem.dao.GenericDAO;
import net.proselyte.pmsystem.dao.SpecialtyDAO;
import net.proselyte.pmsystem.model.BaseEntity;
import net.proselyte.pmsystem.model.Customer;
import net.proselyte.pmsystem.model.Specialty;
import net.proselyte.pmsystem.util.ConnectionUtil;

import java.sql.*;

/**
 * Created by Стрела on 10.12.2016.
 * Implement JDBC implementation of interface GenericDAO for class Specialty
 */
public class JdbcSpecialtyDAOImpl implements SpecialtyDAO {

    @Override
    public Specialty getById(Long id) {
        Connection connection = ConnectionUtil.connection;
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT *FROM SPECIALTY WHERE ID = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createSpecialty(resultSet);
            } else {
                throw new RuntimeException("import net.proselyte.pmsystem.model.Specialty with id " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Specialty specialty) {
        Connection connection = ConnectionUtil.connection;
        try (PreparedStatement statement =
                     connection.prepareStatement("INSERT INTO SPECIALTY (name) VALUES (?)")) {
            statement.setString(1, specialty.getName());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Specialty specialty) {
        Connection connection = ConnectionUtil.connection;
        try (PreparedStatement statement = connection.prepareStatement("UPDATE SPECIALTY SET name = ? WHERE NAME = ?")) {
            statement.setString(1, specialty.getName());
            statement.setString(2, specialty.getName()); // может метод должен принимать два значения что изменить и на что изменить
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Specialty specialty) {
        Connection connection = ConnectionUtil.connection;
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM SPECIALTY WHERE name = ?")) {
            statement.setString(1, specialty.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Specialty createSpecialty(ResultSet resultSet) throws SQLException {
        Specialty specialty = new Specialty();
        specialty.setId(resultSet.getLong("ID"));
        specialty.setName(resultSet.getString("NAME"));
        return specialty;
    }
}

