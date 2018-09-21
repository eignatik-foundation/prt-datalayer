package space.eignatik.prt.datalayer.dataTools.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "grade")
public class Grade implements IEntity {

    @Id @GeneratedValue private int id;
    private String title;

    public int getId() {
        return id;
    }

    public Grade setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Grade setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade team = (Grade) o;
        return id == team.id &&
                Objects.equals(title, team.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
