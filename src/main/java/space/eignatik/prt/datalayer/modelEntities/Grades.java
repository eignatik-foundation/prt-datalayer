package space.eignatik.prt.datalayer.modelEntities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "grades")
public class Grades implements IEntity {
    @Id @GeneratedValue private int id;
    private int gradeId;
    private int userId;

    public int getId() {
        return id;
    }

    public Grades setId(int id) {
        this.id = id;
        return this;
    }

    public int getGradeId() {
        return gradeId;
    }

    public Grades setGradeId(int gradeId) {
        this.gradeId = gradeId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Grades setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grades grades = (Grades) o;
        return id == grades.id &&
                gradeId == grades.gradeId &&
                userId == grades.userId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, gradeId, userId);
    }

    @Override
    public String toString() {
        return "Grades{" +
                "id=" + id +
                ", gradeId=" + gradeId +
                ", userId=" + userId +
                '}';
    }
}
