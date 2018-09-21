package space.eignatik.prt.datalayer.dataTools.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.dataTools.entities.UserRoles;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static space.eignatik.prt.datalayer.dataTools.enums.TableNames.USR_ROLES;

public class UserRolesDAOTest extends BaseDAOTest {

    @Autowired
    @Qualifier(value = "userRolesDAO")
    private IDAO<UserRoles> dao;

    @BeforeClass
    public void setUpDB() {
        dao.setFactoryUtil(new SessionFactoryUtilLocal());
    }

    @BeforeMethod
    public void resetDB() {
        DAOTestUtil.deleteAllFromTable(USR_ROLES.getEntityName());
    }

    @Test
    public void testWhenAddWithItem_thenItCanBeClaimedFromDB() {
        UserRoles roles = new UserRoles().setRoleId(1).setUserId(2);
        dao.add(roles);
        UserRoles rolesDB = dao.get(UserRoles.class, roles.getId());

        assertEquals(rolesDB, roles);
    }

    @Test
    public void testWhenDelete_thenDeletedItemReturned_and_ItIsUnableToClaimFromDB() {
        UserRoles roles = new UserRoles().setRoleId(1).setUserId(2);
        dao.add(roles);
        assertEquals(dao.delete(UserRoles.class, roles.getId()), roles);
        assertNull(dao.get(UserRoles.class, roles.getId()));
    }

    @Test
    public void testWhenGetAll_thenFixedSizeListIsReturned() {
        int EXPECTED_SIZE = 2;
        List.of(new UserRoles().setRoleId(1).setUserId(2),
                new UserRoles().setRoleId(2).setUserId(3))
                .forEach(userRoles -> dao.add(userRoles));
        assertEquals(dao.getAll(UserRoles.class).size(), EXPECTED_SIZE);
    }

    @Test
    public void testWhenUpdate_thenExistingItemIsUpdatedInDB() {
        UserRoles roles = new UserRoles().setRoleId(1).setUserId(2);
        dao.add(roles);
        dao.update(roles.setRoleId(2));
        roles = dao.get(UserRoles.class, roles.getId());
        assertEquals(roles.getRoleId(), 2);
    }
}