package space.eignatik.prt.datalayer.dataTools.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.dataTools.entities.UserGoals;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;

import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static space.eignatik.prt.datalayer.dataTools.enums.TableNames.USR_GOALS;

public class UserGoalsDAOTest extends BaseDAOTest {

    @Autowired
    @Qualifier(value = "userGoalsDAO")
    private IDAO<UserGoals> dao;

    @BeforeClass
    public void setUpDB() {
        dao.setFactoryUtil(new SessionFactoryUtilLocal());
    }

    @BeforeMethod
    public void resetDB() {
        DAOTestUtil.deleteAllFromTable(USR_GOALS.getEntityName());
    }

    @Test
    public void testWhenAddWithItem_thenItCanBeClaimedFromDB() {
        UserGoals userGoals = new UserGoals()
                .setDescription("DO something")
                .setUserId(1)
                .setGoalName("Goal1")
                .setStartDate(new Date())
                .setEndDate(new Date());
        dao.add(userGoals);
        UserGoals userGoalsDB = dao.get(UserGoals.class, userGoals.getId());

        //TODO: fix when date format is set in stone
        // assertEquals(userGoalsDB, userGoals);
        assertEquals(userGoalsDB.getId(), userGoals.getId());
    }

    @Test
    public void testWhenDelete_thenDeletedItemReturned_and_ItIsUnableToClaimFromDB() {
        UserGoals userGoals = new UserGoals()
                .setDescription("DO something")
                .setUserId(1)
                .setGoalName("Goal1")
                .setStartDate(new Date())
                .setEndDate(new Date());
        dao.add(userGoals);
        assertEquals(dao.delete(UserGoals.class, userGoals.getId()).getId(), userGoals.getId());
        assertNull(dao.get(UserGoals.class, userGoals.getId()));
    }

    @Test
    public void testWhenGetAll_thenFixedSizeListIsReturned() {
        int EXPECTED_SIZE = 2;
        List.of(new UserGoals()
                        .setDescription("DO something")
                        .setUserId(1)
                        .setGoalName("Goal1")
                        .setStartDate(new Date())
                        .setEndDate(new Date()),
                new UserGoals()
                        .setDescription("DO something else")
                        .setUserId(1)
                        .setGoalName("Goal2")
                        .setStartDate(new Date())
                        .setEndDate(new Date()))
                .forEach(goal -> dao.add(goal));
        assertEquals(dao.getAll(UserGoals.class).size(), EXPECTED_SIZE);
    }

    @Test
    public void testWhenUpdate_thenExistingItemIsUpdatedInDB() {
        UserGoals userGoals = new UserGoals()
                .setDescription("DO something")
                .setUserId(1)
                .setGoalName("Goal1")
                .setStartDate(new Date())
                .setEndDate(new Date());
        dao.add(userGoals);
        dao.update(userGoals.setDescription("Changed data"));
        userGoals = dao.get(UserGoals.class, userGoals.getId());
        assertEquals(userGoals.getDescription(), "Changed data");
    }
}