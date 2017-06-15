package net.proselyte.pmsystem.model;

/**
 * Simple JavaBean domain object that represents a Document (for instance, Project documentation)
 *
 * @author Eugene Suleimanov
 */
public class Document extends NamedEntity {

    private String content;

    public Document() {
    }

    public Document(String content) {
        this.content = content;
    }

    public Document(String name, String content) {
        super(name);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Document{" +
                "content='" + content + '\'' +
                '}';
    }
}
