package net.proselyte.pmsystem.model;

import java.util.Set;

/**
 * Simple JavaBean domain object that represents a Customer (AvalBank, McDonalds, etc.)
 *
 * @author Eugene Suleimanov
 */

public class Customer extends NamedEntity {
    private String description;

    private Set<Project> projects;

    public Customer(){

    }

    public Customer(String description, Set<Project> projects) {
        this.description = description;
        this.projects = projects;
    }

    public Customer(String name, String description, Set<Project> projects) {
        super(name);
        this.description = description;
        this.projects = projects;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "description='" + description + '\'' +
                ", projects=" + projects +
                '}';
    }
}
