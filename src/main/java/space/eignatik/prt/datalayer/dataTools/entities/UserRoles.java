package space.eignatik.prt.datalayer.dataTools.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "user_roles")
public class UserRoles implements IEntity {
    @Id @GeneratedValue private int id;
    private int roleId;
    private int userId;

    public int getId() {
        return id;
    }

    public UserRoles setId(int id) {
        this.id = id;
        return this;
    }

    public int getRoleId() {
        return roleId;
    }

    public UserRoles setRoleId(int roleId) {
        this.roleId = roleId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public UserRoles setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoles that = (UserRoles) o;
        return id == that.id &&
                roleId == that.roleId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, roleId, userId);
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "id=" + id +
                ", deptId=" + roleId +
                ", userId=" + userId +
                '}';
    }
}
