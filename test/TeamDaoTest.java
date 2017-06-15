package net.proselyte.pmsystem.test;

import net.proselyte.pmsystem.dao.jdbc.JdbcTeamDaoImpl;
import net.proselyte.pmsystem.model.Team;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * @author Anton
 */
public class TeamDaoTest {

    @Test
    public void getById() throws Exception {
        Long id = 1l;
        JdbcTeamDaoImpl jdbcTeamDaoImpl = new JdbcTeamDaoImpl();
        Team team = jdbcTeamDaoImpl.getById(id);
        assertTrue(team.getId() == id);
    }

    @Test
    public void save() throws Exception {
        Long id = 777l;
        Team team = new Team();
        team.setId(id);
        JdbcTeamDaoImpl jdbcTeamDaoImpl = new JdbcTeamDaoImpl();
        jdbcTeamDaoImpl.save(team);
        Team newTeam = jdbcTeamDaoImpl.getById(id);
        assertTrue(newTeam.getId()==team.getId() && newTeam.getName().equals(team.getName()));
    }

    @Test
    public void update() throws Exception {
        Long id = 1l;
        JdbcTeamDaoImpl jdbcTeamDaoImpl = new JdbcTeamDaoImpl();
        Team team = new Team();
        team.setId(id);
        team.setName("Mr Anderson");
        jdbcTeamDaoImpl.update(team);
        assertTrue(team.getName().equals(jdbcTeamDaoImpl.getById(id).getName()));
    }

    @Test(expected = SQLException.class)
    public void remove() throws Exception {
        Long id = 7l;
        JdbcTeamDaoImpl jdbcTeamDaoImpl = new JdbcTeamDaoImpl();
        Team team = new Team();
        team.setId(id);
        team.setName("Stiven");
        jdbcTeamDaoImpl.save(team);
        jdbcTeamDaoImpl.remove(team);
    }
}