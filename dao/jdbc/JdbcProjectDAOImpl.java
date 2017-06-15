package net.proselyte.pmsystem.dao.jdbc;

import net.proselyte.pmsystem.dao.GenericDAO;
import net.proselyte.pmsystem.dao.ProjectDAO;
import net.proselyte.pmsystem.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

import static net.proselyte.pmsystem.util.ConnectionUtil.connection;
import static net.proselyte.pmsystem.util.ConnectionUtil.getConnection;
import static net.proselyte.pmsystem.util.ConnectionUtil.preparedStatement;

/**
 * Implementation of {@link GenericDAO} interface for class {@link Project}.
 *
 * @author Kyryl Potapenko
 */
public class JdbcProjectDAOImpl implements ProjectDAO {
    public JdbcProjectDAOImpl() {
        try {
            getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Connection to DB failed " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver not found " + e);
        }
    }

    public Project getById(Long id) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM projects WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            if (resultSet.next()) {
                return createProject(resultSet);
            } else {
                throw new RuntimeException("Cannot find Company with id = " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while connecting to DB " + e);
        }
    }

    public void save(Project project) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO projects VALUES (?,?,?)");
            preparedStatement.setLong(1, project.getId());
            preparedStatement.setString(2, project.getName());
            preparedStatement.setString(3, project.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while connecting to DB " + e);
        }
    }

    public void update(Project project) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE projects SET name = ? , description =? where id = ?");
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setLong(3, project.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while connecting to DB " + e);
        }
    }

    public void remove(Project project) {
        try {
            preparedStatement = connection.prepareStatement("DELETE from projects where id = ?");
            preparedStatement.setLong(1, project.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while connecting to DB " + e);
        }
    }

    private Project createProject(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setId(resultSet.getLong("id"));
        project.setName(resultSet.getString("name"));
        project.setDescription(resultSet.getString("description"));
        return project;
    }
}

