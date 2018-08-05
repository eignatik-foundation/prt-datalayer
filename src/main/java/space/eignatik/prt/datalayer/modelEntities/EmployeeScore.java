package space.eignatik.prt.datalayer.modelEntities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "employee_score")
public class EmployeeScore implements IEntity {
    @Id @GeneratedValue private int id;
    private int userId;
    private String date;
    private float score;

    public int getId() {
        return id;
    }

    public EmployeeScore setId(int id) {
        this.id = id;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public EmployeeScore setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getDate() {
        return date;
    }

    public EmployeeScore setDate(String date) {
        this.date = date;
        return this;
    }

    public float getScore() {
        return score;
    }

    public EmployeeScore setScore(float score) {
        this.score = score;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeScore that = (EmployeeScore) o;
        return id == that.id &&
                userId == that.userId &&
                Float.compare(that.score, score) == 0 &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, date, score);
    }

    @Override
    public String toString() {
        return "EmployeeScore{" +
                "id=" + id +
                ", userId=" + userId +
                ", date='" + date + '\'' +
                ", score=" + score +
                '}';
    }
}
