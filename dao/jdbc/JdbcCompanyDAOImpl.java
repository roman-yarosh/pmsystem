package net.proselyte.pmsystem.dao.jdbc;

import net.proselyte.pmsystem.dao.CompanyDAO;
import net.proselyte.pmsystem.dao.GenericDAO;
import net.proselyte.pmsystem.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

import static net.proselyte.pmsystem.util.ConnectionUtil.connection;
import static net.proselyte.pmsystem.util.ConnectionUtil.preparedStatement;

/**
 * Implementation of {@link GenericDAO} interface for class {@link Company}.
 *
 * @author Kyryl Potapenko
 */
public class JdbcCompanyDAOImpl implements CompanyDAO {
    public Company getById(Long id) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM company WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            if (resultSet.next()) {
                return createCompany(resultSet);
            } else {
                throw new RuntimeException("Cannot find Company with id = " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while connecting to DB " + e);
        }
    }

    public void save(Company company) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO company VALUES (?,?,?)");
            preparedStatement.setLong(1, company.getId());
            preparedStatement.setString(2, company.getName());
            preparedStatement.setString(3, company.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while connecting to DB " + e);
        }
    }

    public void update(Company company) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE company SET name = ? , description =? where id = ?");
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getDescription());
            preparedStatement.setLong(3, company.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while connecting to DB " + e);
        }
    }

    public void remove(Company company) {
        try {
            preparedStatement = connection.prepareStatement("DELETE from company where id = ?");
            preparedStatement.setLong(1, company.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while connecting to DB " + e);
        }
    }

    private Company createCompany(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getLong("id"));
        company.setName(resultSet.getString("name"));
        company.setDescription(resultSet.getString("description"));
        return company;
    }
}




