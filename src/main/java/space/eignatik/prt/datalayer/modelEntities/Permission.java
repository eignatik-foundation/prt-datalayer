package space.eignatik.prt.datalayer.modelEntities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "permission")
public class Permission implements IEntity {
    @Id @GeneratedValue private int id;
    private String shortcut;
    private String description;

    public int getId() {
        return id;
    }

    public Permission setId(int id) {
        this.id = id;
        return this;
    }

    public String getShortcut() {
        return shortcut;
    }

    public Permission setShortcut(String shortcut) {
        this.shortcut = shortcut;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Permission setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return id == that.id &&
                Objects.equals(shortcut, that.shortcut) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shortcut, description);
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", shortcut='" + shortcut + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
