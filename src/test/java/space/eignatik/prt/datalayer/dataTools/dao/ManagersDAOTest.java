package space.eignatik.prt.datalayer.dataTools.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;
import space.eignatik.prt.datalayer.modelEntities.Managers;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static space.eignatik.prt.datalayer.dataTools.TableNames.MANAGERS;

public class ManagersDAOTest extends BaseDAOTest {

    @Autowired
    @Qualifier(value = "managersDAO")
    private IDAO<Managers> dao;

    @BeforeClass
    public void setUpDB() {
        dao.setFactoryUtil(new SessionFactoryUtilLocal());
    }

    @BeforeMethod
    public void resetDB() {
        DAOTestUtil.deleteAllFromTable(MANAGERS.getEntityName());
    }

    @Test
    public void testWhenAddWithItem_thenItCanBeClaimedFromDB() {
        Managers managers = new Managers().setUserId(1);
        dao.add(managers);
        Managers managerFromDB = dao.get(Managers.class, managers.getId());

        assertEquals(managerFromDB, managers);
    }

    @Test
    public void testWhenDelete_thenDeletedItemReturned_and_ItIsUnableToClaimFromDB() {
        Managers manager = new Managers().setUserId(1);
        dao.add(manager);
        assertEquals(dao.delete(Managers.class, manager.getId()), manager);
        assertNull(dao.get(Managers.class, manager.getId()));
    }

    @Test
    public void testWhenGetAll_thenFixedSizeListIsReturned() {
        int EXPECTED_SIZE = 2;
        List.of(new Managers().setUserId(1),
                new Managers().setUserId(2))
                .forEach(manager -> dao.add(manager));
        assertEquals(dao.getAll(Managers.class).size(), EXPECTED_SIZE);
    }

    @Test
    public void testWhenUpdate_thenExistingItemIsUpdatedInDB() {
        Managers manager = new Managers().setUserId(1);
        dao.add(manager);
        dao.update(manager.setUserId(2));
        manager = dao.get(Managers.class, manager.getId());
        assertEquals(manager.getUserId(), 2);
    }
}