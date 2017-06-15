package net.proselyte.pmsystem.test;

import net.proselyte.pmsystem.dao.jdbc.JdbcCompanyDAOImpl;
import net.proselyte.pmsystem.model.Company;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
/**
 * @author Kyryl Potapenko
 */
public class JdbcCompanyDAOImplTest {

    @Test
    public void getById() throws Exception {
        Long id = 123L;
        JdbcCompanyDAOImpl jdbcCompanyDaoImpl = new JdbcCompanyDAOImpl();
        Company company = jdbcCompanyDaoImpl.getById(id);
        assertTrue(company.getId() == id);
    }

    @Test
    public void save() throws Exception {
        Long id = 72L;
        Company company = new Company();
        company.setId(id);
        JdbcCompanyDAOImpl jdbcCompanyDAOImpl = new JdbcCompanyDAOImpl();
        jdbcCompanyDAOImpl.save(company);
        Company company1 = jdbcCompanyDAOImpl.getById(id);
        assertTrue(company1.getId() == company.getId() && company1.getName().equals(company.getName()));
    }

    @Test
    public void update() throws Exception {
        Long id = 123L;
        JdbcCompanyDAOImpl jdbcCompanyDAOImpl = new JdbcCompanyDAOImpl();
        Company company = new Company();
        company.setId(id);
        company.setName("Epam");
        company.setDescription("IT company");
        jdbcCompanyDAOImpl.update(company);
        assertTrue(company.getName().equals(jdbcCompanyDAOImpl.getById(id).getName()));
    }

    @Test(expected = SQLException.class)
    public void remove() throws Exception {
        Long id = 123L;
        JdbcCompanyDAOImpl jdbcCompanyDAOImpl = new JdbcCompanyDAOImpl();
        Company company = new Company();
        company.setId(id);
        company.setName("Uber");
        company.setDescription("Taxi");
        jdbcCompanyDAOImpl.save(company);
        jdbcCompanyDAOImpl.remove(company);
    }
}

