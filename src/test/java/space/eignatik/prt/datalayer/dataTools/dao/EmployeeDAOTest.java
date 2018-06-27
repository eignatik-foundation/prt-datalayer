package space.eignatik.prt.datalayer.dataTools.dao;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;
import space.eignatik.prt.datalayer.modelEntities.Employee;

public class EmployeeDAOTest {
    private IDAO<Employee> dao = new UserDAO();

    @BeforeClass
    public void setUp() {
        dao.setFactoryUtil(new SessionFactoryUtilLocal());
    }

    @Test
    public void testWhenAddWithItem_thenItWillBeCreatedInDB() {
        Employee employee = new Employee()
                .setFname("John")
                .setLname("Dorian");
        dao.add(employee);
        Employee employeeFromDB = dao.get(Employee.class, employee.getId());

        Assert.assertEquals(employee, employeeFromDB);
    }
}