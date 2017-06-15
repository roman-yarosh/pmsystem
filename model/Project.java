package net.proselyte.pmsystem.model;

import java.util.Set;

/**
 * Simple JavaBean domain object that represents a Project.
 *
 * @author Eugene Suleimanov
 * @author Kyryl Potapenko
 */

public class Project extends NamedEntity {

    private String description;

    private Set<Team> teams;

    public Project(String description, Set<Team> teams) {
        this.description = description;
        this.teams = teams;
    }

    public Project(String name, String description, Set<Team> teams) {
        super(name);
        this.description = description;
        this.teams = teams;
    }

    public Project(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "Project{" +
                "description='" + description + '\'' +
                ", teams=" + teams +
                '}';
    }
}
