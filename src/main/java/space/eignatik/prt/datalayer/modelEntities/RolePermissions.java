package space.eignatik.prt.datalayer.modelEntities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "role_permissions")
public class RolePermissions implements IEntity {
    @Id @GeneratedValue private int id;
    private int roleId;
    private int permId;

    public int getId() {
        return id;
    }

    public RolePermissions setId(int id) {
        this.id = id;
        return this;
    }

    public int getRoleId() {
        return roleId;
    }

    public RolePermissions setRoleId(int roleId) {
        this.roleId = roleId;
        return this;
    }

    public int getPermId() {
        return permId;
    }

    public RolePermissions setPermId(int permId) {
        this.permId = permId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePermissions that = (RolePermissions) o;
        return id == that.id &&
                roleId == that.roleId &&
                permId == that.permId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, roleId, permId);
    }

    @Override
    public String toString() {
        return "RolePermissions{" +
                "id=" + id +
                ", deptId=" + roleId +
                ", userId=" + permId +
                '}';
    }
}
