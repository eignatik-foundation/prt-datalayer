package space.eignatik.prt.datalayer.dataTools.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;
import space.eignatik.prt.datalayer.modelEntities.Department;

import java.util.List;

import static org.testng.Assert.*;
import static space.eignatik.prt.datalayer.dataTools.enums.TableNames.*;

public class DepartmentDAOTest extends BaseDAOTest {

    @Autowired
    @Qualifier(value = "departmentDAO")
    private IDAO<Department> dao;

    @BeforeClass
    public void setUpDB() {
        dao.setFactoryUtil(new SessionFactoryUtilLocal());
    }

    @BeforeMethod
    public void resetDB() {
        DAOTestUtil.deleteAllFromTable(DEP.getEntityName());
    }

    @Test
    public void testWhenAddWithItem_thenItCanBeClaimedFromDB() {
        Department department = new Department().setTitle("DEPT-1");
        dao.add(department);
        Department depFromDB = dao.get(Department.class, department.getId());

        assertEquals(depFromDB, department);
    }

    @Test
    public void testWhenDelete_thenDeletedItemReturned_and_ItIsUnableToClaimFromDB() {
        Department dep = new Department().setTitle("Delete");
        dao.add(dep);
        assertEquals(dao.delete(Department.class, dep.getId()), dep);
        assertNull(dao.get(Department.class, dep.getId()));
    }

    @Test
    public void testWhenGetAll_thenFixedSizeListIsReturned() {
        int EXPECTED_SIZE = 2;
        List.of(new Department().setTitle("DEPT-1"),
                new Department().setTitle("DEPT-2"))
                .forEach(department -> dao.add(department));
        assertEquals(dao.getAll(Department.class).size(), EXPECTED_SIZE);
    }

    @Test
    public void testWhenUpdate_thenExistingItemIsUpdatedInDB() {
        Department dept = new Department().setTitle("DEPT-1");
        dao.add(dept);
        dao.update(dept.setTitle("DEPT-2"));
        dept = dao.get(Department.class, dept.getId());
        assertEquals(dept.getTitle(), "DEPT-2");
    }
}