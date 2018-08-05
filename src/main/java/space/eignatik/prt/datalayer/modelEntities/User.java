package space.eignatik.prt.datalayer.modelEntities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "emp_user")
public class User implements IEntity {

    @Id @GeneratedValue private int id;
    private int employeeId;
    private String login;
    private String password;

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public User setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                employeeId == user.employeeId &&
                Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, login);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", login='" + login + '\'' +
                '}';
    }
}
