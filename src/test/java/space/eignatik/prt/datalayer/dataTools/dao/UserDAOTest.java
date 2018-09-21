package space.eignatik.prt.datalayer.dataTools.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.dataTools.entities.User;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static space.eignatik.prt.datalayer.dataTools.enums.TableNames.EMP_USER;

public class UserDAOTest extends BaseDAOTest {

    @Autowired
    @Qualifier(value = "userDAO")
    private IDAO<User> dao;

    @BeforeClass
    public void setUpDB() {
        dao.setFactoryUtil(new SessionFactoryUtilLocal());
    }

    @BeforeMethod
    public void resetDB() {
        DAOTestUtil.deleteAllFromTable(EMP_USER.getEntityName());
    }

    @Test
    public void testWhenAddWithItem_thenItCanBeClaimedFromDB() {
        User user = new User().setEmployeeId(1).setLogin("gofors").setPassword("password111");
        dao.add(user);
        User userDB = dao.get(User.class, user.getId());

        assertEquals(userDB, user);
    }

    @Test
    public void testWhenDelete_thenDeletedItemReturned_and_ItIsUnableToClaimFromDB() {
        User user = new User().setEmployeeId(1).setLogin("gofors").setPassword("password111");
        dao.add(user);
        assertEquals(dao.delete(User.class, user.getId()), user);
        assertNull(dao.get(User.class, user.getId()));
    }

    @Test
    public void testWhenGetAll_thenFixedSizeListIsReturned() {
        int EXPECTED_SIZE = 2;
        List.of(new User().setEmployeeId(1).setLogin("gofors").setPassword("password111"),
                new User().setEmployeeId(2).setLogin("littleCat").setPassword("password111"))
                .forEach(user -> dao.add(user));
        assertEquals(dao.getAll(User.class).size(), EXPECTED_SIZE);
    }

    @Test
    public void testWhenUpdate_thenExistingItemIsUpdatedInDB() {
        User user = new User().setEmployeeId(1).setLogin("gofors").setPassword("password111");
        dao.add(user);
        dao.update(user.setPassword("newHardPassword"));
        user = dao.get(User.class, user.getId());
        assertEquals(user.getPassword(), "newHardPassword");
    }
}