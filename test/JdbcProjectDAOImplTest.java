package net.proselyte.pmsystem.test;

import net.proselyte.pmsystem.dao.jdbc.JdbcProjectDAOImpl;
import net.proselyte.pmsystem.model.Project;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
/**
 * @author Kyryl Potapenko
 */
public class JdbcProjectDAOImplTest {
    @Test
    public void getById() throws Exception {
        Long id = 123L;
        JdbcProjectDAOImpl jdbcProjectDAOImpl = new JdbcProjectDAOImpl();
        Project project = jdbcProjectDAOImpl.getById(id);
        assertTrue(project.getId() == id);
    }

    @Test
    public void save() throws Exception {
        Long id = 72L;
        Project project = new Project();
        project.setId(id);
        JdbcProjectDAOImpl jdbcProjectDAOImpl = new JdbcProjectDAOImpl();
        jdbcProjectDAOImpl.save(project);
        Project company1 = jdbcProjectDAOImpl.getById(id);
        assertTrue(company1.getId() == project.getId() && company1.getName().equals(project.getName()));
    }

    @Test
    public void update() throws Exception {
        Long id = 123L;
        JdbcProjectDAOImpl jdbcProjectDAOImpl = new JdbcProjectDAOImpl();
        Project project = new Project();
        project.setId(id);
        project.setName("NewBankSoft");
        project.setDescription("Updating HR system software");
        jdbcProjectDAOImpl.update(project);
        assertTrue(project.getName().equals(jdbcProjectDAOImpl.getById(id).getName()));
    }

    @Test(expected = SQLException.class)
    public void remove() throws Exception {
        Long id = 123L;
        JdbcProjectDAOImpl jdbcProjectDAOImpl = new JdbcProjectDAOImpl();
        Project project = new Project();
        project.setId(id);
        project.setName("NewBankSoft");
        project.setDescription("Updating HR system software");
        jdbcProjectDAOImpl.save(project);
        jdbcProjectDAOImpl.remove(project);
    }
}
