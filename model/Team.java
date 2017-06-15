package net.proselyte.pmsystem.model;

import java.util.Set;

/**
 * Simple JavaBean domain object that represents Team of {@link Developer}s.
 *
 * @author Eugene Suleimanov
 */

public class Team extends NamedEntity {

    public Team() {
    }

    private Set<Developer> developers;

    public Team(Set<Developer> developers) {
        this.developers = developers;
    }

    public Team(String name, Set<Developer> developers) {
        super(name);
        this.developers = developers;
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "Team{" +
                "developers=" + developers +
                '}';
    }

}
