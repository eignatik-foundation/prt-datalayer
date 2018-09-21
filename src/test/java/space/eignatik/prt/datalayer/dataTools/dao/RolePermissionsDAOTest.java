package space.eignatik.prt.datalayer.dataTools.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.dataTools.entities.RolePermissions;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static space.eignatik.prt.datalayer.dataTools.enums.TableNames.ROLE_PERMS;

public class RolePermissionsDAOTest extends BaseDAOTest {

    @Autowired
    @Qualifier(value = "rolePermissionsDAO")
    private IDAO<RolePermissions> dao;

    @BeforeClass
    public void setUpDB() {
        dao.setFactoryUtil(new SessionFactoryUtilLocal());
    }

    @BeforeMethod
    public void resetDB() {
        DAOTestUtil.deleteAllFromTable(ROLE_PERMS.getEntityName());
    }

    @Test
    public void testWhenAddWithItem_thenItCanBeClaimedFromDB() {
        RolePermissions permission = new RolePermissions().setPermId(1).setRoleId(5);
        dao.add(permission);
        RolePermissions permFromDB = dao.get(RolePermissions.class, permission.getId());

        assertEquals(permFromDB, permission);
    }

    @Test
    public void testWhenDelete_thenDeletedItemReturned_and_ItIsUnableToClaimFromDB() {
        RolePermissions permission = new RolePermissions().setPermId(1).setRoleId(5);
        dao.add(permission);
        assertEquals(dao.delete(RolePermissions.class, permission.getId()), permission);
        assertNull(dao.get(RolePermissions.class, permission.getId()));
    }

    @Test
    public void testWhenGetAll_thenFixedSizeListIsReturned() {
        int EXPECTED_SIZE = 2;
        List.of(new RolePermissions().setPermId(1).setRoleId(5),
                new RolePermissions().setPermId(2).setRoleId(6))
                .forEach(permission -> dao.add(permission));
        assertEquals(dao.getAll(RolePermissions.class).size(), EXPECTED_SIZE);
    }

    @Test
    public void testWhenUpdate_thenExistingItemIsUpdatedInDB() {
        RolePermissions permission = new RolePermissions().setRoleId(5).setPermId(3);
        dao.add(permission);
        dao.update(permission.setPermId(6));
        permission = dao.get(RolePermissions.class, permission.getId());
        assertEquals(permission.getPermId(), 6);
    }
}