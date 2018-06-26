package space.eignatik.prt.datalayer.dataTools.dao;

import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.modelEntities.Employee;

public class EmployeeDAOTest {
    private IDAO<Employee> dao = new UserDAO();

    @Test
    public void testWhenAddWithItem_thenItWillBeCreatedInDB() {
        dao.add(new Employee()
                    .setId(1000)
                    .setFname("John")
                    .setLname("Dorian")
        );
        dao.get(Employee.class, 1000);
    }
}