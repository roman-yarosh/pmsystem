package net.proselyte.pmsystem.model;

/**
 * Simple JavaBean domain object that represents a Specialty of {@link Developer} (Java Developer, C++ Developer, etc.)
 *
 * @author Eugene Suleimanov
 */

public class Specialty extends NamedEntity {
    private Long id;
    private String name;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Companies{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
