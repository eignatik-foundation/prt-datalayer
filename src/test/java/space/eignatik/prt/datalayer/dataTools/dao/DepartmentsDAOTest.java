package space.eignatik.prt.datalayer.dataTools.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;
import space.eignatik.prt.datalayer.dataTools.entities.Departments;

import java.util.List;

import static org.testng.Assert.*;
import static space.eignatik.prt.datalayer.dataTools.enums.TableNames.DEPS;

public class DepartmentsDAOTest extends BaseDAOTest {
    @Autowired
    @Qualifier(value = "departmentsDAO")
    private IDAO<Departments> dao;

    @BeforeClass
    public void setUpDB() {
        dao.setFactoryUtil(new SessionFactoryUtilLocal());
    }

    @BeforeMethod
    public void resetDB() {
        DAOTestUtil.deleteAllFromTable(DEPS.getEntityName());
    }

    @Test
    public void testWhenAddWithItem_thenItCanBeClaimedFromDB() {
        Departments departments = new Departments()
                .setDeptId(1)
                .setUserId(1);
        dao.add(departments);
        Departments depFromDB = dao.get(Departments.class, departments.getId());

        assertEquals(depFromDB, departments);
    }

    @Test
    public void testWhenDelete_thenDeletedItemReturned_and_ItIsUnableToClaimFromDB() {
        Departments deps = new Departments()
                .setDeptId(1)
                .setUserId(1);
        dao.add(deps);
        assertEquals(dao.delete(Departments.class, deps.getId()), deps);
        assertNull(dao.get(Departments.class, deps.getId()));
    }

    @Test
    public void testWhenGetAll_thenFixedSizeListIsReturned() {
        int EXPECTED_SIZE = 2;
        List.of(new Departments()
                        .setDeptId(1)
                        .setUserId(1),
                new Departments()
                        .setDeptId(2)
                        .setUserId(2))
                .forEach(department -> dao.add(department));
        assertEquals(dao.getAll(Departments.class).size(), EXPECTED_SIZE);
    }

    @Test
    public void testWhenUpdate_thenExistingItemIsUpdatedInDB() {
        Departments dept = new Departments()
                .setDeptId(1)
                .setUserId(1);
        dao.add(dept);
        dao.update(dept
                .setDeptId(2)
                .setUserId(3));
        dept = dao.get(Departments.class, dept.getId());
        assertEquals(dept.getDeptId(), 2);
        assertEquals(dept.getUserId(), 3);
    }
}