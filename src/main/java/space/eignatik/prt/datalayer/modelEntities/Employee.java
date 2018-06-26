package space.eignatik.prt.datalayer.modelEntities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee implements IEntity {
    @Id
    private int id;
    private String fname;
    private String lname;

    public int getId() {
        return id;
    }

    public Employee setId(int id) {
        this.id = id;
        return this;
    }

    public String getFname() {
        return fname;
    }

    public Employee setFname(String fname) {
        this.fname = fname;
        return this;
    }

    public String getLname() {
        return lname;
    }

    public Employee setLname(String lname) {
        this.lname = lname;
        return this;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(fname, employee.fname) &&
                Objects.equals(lname, employee.lname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, fname, lname);
    }
}
