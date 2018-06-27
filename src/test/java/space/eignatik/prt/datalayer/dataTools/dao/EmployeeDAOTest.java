package space.eignatik.prt.datalayer.dataTools.dao;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;
import space.eignatik.prt.datalayer.modelEntities.Employee;
import java.util.List;

import static org.testng.Assert.*;

public class EmployeeDAOTest {
    private IDAO<Employee> dao = new UserDAO();

    @BeforeClass
    public void setUpDB() {
        dao.setFactoryUtil(new SessionFactoryUtilLocal());
    }

    @BeforeMethod
    public void resetDB() {
        DAOTestUtil.deleteAllFromTable("Employee");
    }

    @Test
    public void testWhenAddWithItem_thenItCanBeClaimedFromDB() {
        Employee employee = new Employee()
                .setFname("John")
                .setLname("Dorian");
        dao.add(employee);
        Employee employeeFromDB = dao.get(Employee.class, employee.getId());

        assertEquals(employeeFromDB, employee);
    }

    @Test
    public void testWhenDelete_thenDeletedItemReturned_and_ItIsUnableToClaimFromDB() {
        Employee employee = new Employee()
                .setFname("Test")
                .setLname("Delete");
        dao.add(employee);
        assertEquals(dao.delete(Employee.class, employee.getId()), employee);
        assertNull(dao.get(Employee.class, employee.getId()));
    }

    @Test
    public void testWhenGetAll_thenFixedSizeListIsReturned() {
        int EXPECTED_SIZE = 2;
        List.of(new Employee().setFname("Yan").setLname("Irwin"),
                new Employee().setFname("Greg").setLname("One"))
                .forEach(employee -> dao.add(employee));
        assertEquals(dao.getAll(Employee.class).size(), EXPECTED_SIZE);
    }

    @Test
    public void testWhenUpdate_thenExistingItemIsUpdatedInDB() {
        Employee employee = new Employee().setFname("Cat").setLname("Johnson");
        dao.add(employee);
        dao.update(employee.setFname("Dog").setLname("Catson"));
        employee = dao.get(Employee.class, employee.getId());
        assertEquals(employee.getFname(), "Dog");
        assertEquals(employee.getLname(), "Catson");
    }
}