package space.eignatik.prt.datalayer.modelEntities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "departments")
public class Departments implements IEntity {
    @Id @GeneratedValue private int id;
    private int deptId;
    private int userId;

    public int getId() {
        return id;
    }

    public Departments setId(int id) {
        this.id = id;
        return this;
    }

    public int getDeptId() {
        return deptId;
    }

    public Departments setDeptId(int deptId) {
        this.deptId = deptId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Departments setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departments that = (Departments) o;
        return id == that.id &&
                deptId == that.deptId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, deptId, userId);
    }

    @Override
    public String toString() {
        return "Departments{" +
                "id=" + id +
                ", deptId=" + deptId +
                ", userId=" + userId +
                '}';
    }
}
