package space.eignatik.prt.datalayer.dataTools.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.dataTools.entities.Permission;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static space.eignatik.prt.datalayer.dataTools.enums.TableNames.PERM;

public class PermissionDAOTest extends BaseDAOTest {

    @Autowired
    @Qualifier(value = "permissionDAO")
    private IDAO<Permission> dao;

    @BeforeClass
    public void setUpDB() {
        dao.setFactoryUtil(new SessionFactoryUtilLocal());
    }

    @BeforeMethod
    public void resetDB() {
        DAOTestUtil.deleteAllFromTable(PERM.getEntityName());
    }

    @Test
    public void testWhenAddWithItem_thenItCanBeClaimedFromDB() {
        Permission permission = new Permission().setDescription("Allow something").setShortcut("AL_SMTH");
        dao.add(permission);
        Permission permFromDB = dao.get(Permission.class, permission.getId());

        assertEquals(permFromDB, permission);
    }

    @Test
    public void testWhenDelete_thenDeletedItemReturned_and_ItIsUnableToClaimFromDB() {
        Permission permission = new Permission().setDescription("Allow something").setShortcut("AL_SMTH");
        dao.add(permission);
        assertEquals(dao.delete(Permission.class, permission.getId()), permission);
        assertNull(dao.get(Permission.class, permission.getId()));
    }

    @Test
    public void testWhenGetAll_thenFixedSizeListIsReturned() {
        int EXPECTED_SIZE = 2;
        List.of(new Permission().setDescription("Allow something").setShortcut("AL_SMTH"),
                new Permission().setDescription("Allow something else").setShortcut("AL_SMTHE"))
                .forEach(permission -> dao.add(permission));
        assertEquals(dao.getAll(Permission.class).size(), EXPECTED_SIZE);
    }

    @Test
    public void testWhenUpdate_thenExistingItemIsUpdatedInDB() {
        Permission permission = new Permission().setDescription("Allow something else").setShortcut("AL_SMTHE");
        dao.add(permission);
        dao.update(permission.setDescription("Changed data"));
        permission = dao.get(Permission.class, permission.getId());
        assertEquals(permission.getDescription(), "Changed data");
    }
}