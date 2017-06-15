package net.proselyte.pmsystem.dao.jdbc;

import net.proselyte.pmsystem.dao.GenericDAO;
import net.proselyte.pmsystem.dao.TeamDAO;
import net.proselyte.pmsystem.model.Team;

import java.sql.ResultSet;
import java.sql.SQLException;

import static net.proselyte.pmsystem.util.ConnectionUtil.*;

/**
 * Implementation of {@link GenericDAO} interface for class {@link Team}.
 *
 * @author Anton
 */
public class JdbcTeamDaoImpl implements TeamDAO {

    public JdbcTeamDaoImpl() {
        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Connection to DB failed " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver not found " + e);
        }
    }


    public Team getById(Long id) {
        try {
            preparedStatement = connection.prepareStatement("select from team where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();

            if (resultSet.next()) {
                return createTeam(resultSet);
            } else {
                throw new RuntimeException("Cannot find Team with id = " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while connecting to DB " + e);
        }
    }

    public void save(Team entity) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO team VALUES (?,?)");
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while connecting to DB " + e);
        }
    }

    public void update(Team entity) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE team SET name = ? where id = ?");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while connecting to DB " + e);
        }
    }

    public void remove(Team entity) {
        try {
            preparedStatement = connection.prepareStatement("DELETE from team where id = ?");
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while connecting to DB " + e);
        }
    }

    private Team createTeam(ResultSet resultSet) throws SQLException {
        Team team = new Team();
        team.setId(resultSet.getLong("id"));
        team.setName(resultSet.getString("name"));
        return team;
    }
}