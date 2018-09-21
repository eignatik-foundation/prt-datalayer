package space.eignatik.prt.datalayer.dataTools.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;
import space.eignatik.prt.datalayer.modelEntities.EmployeeScore;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static space.eignatik.prt.datalayer.dataTools.enums.TableNames.EMP_SCORE;

public class EmployeeScoreDAOTest extends BaseDAOTest {

    @Autowired
    @Qualifier(value = "employeeScoreDAO")
    private IDAO<EmployeeScore> dao;

    @BeforeClass
    public void setUpDB() {
        dao.setFactoryUtil(new SessionFactoryUtilLocal());
    }

    @BeforeMethod
    public void resetDB() {
        DAOTestUtil.deleteAllFromTable(EMP_SCORE.getEntityName());
    }

    @Test
    public void testWhenAddWithItem_thenItCanBeClaimedFromDB() {
        EmployeeScore employeeScore = new EmployeeScore()
                .setUserId(1)
                .setDate("2018-12-31")
                .setScore(14.0f);
        dao.add(employeeScore);
        EmployeeScore employeeScoreFromDB = dao.get(EmployeeScore.class, employeeScore.getId());

        assertEquals(employeeScoreFromDB, employeeScore);
    }

    @Test
    public void testWhenDelete_thenDeletedItemReturned_and_ItIsUnableToClaimFromDB() {
        EmployeeScore employeeScore = new EmployeeScore()
                .setUserId(1)
                .setDate("2018-12-31")
                .setScore(14.0f);
        dao.add(employeeScore);
        assertEquals(dao.delete(EmployeeScore.class, employeeScore.getId()), employeeScore);
        assertNull(dao.get(EmployeeScore.class, employeeScore.getId()));
    }

    @Test
    public void testWhenGetAll_thenFixedSizeListIsReturned() {
        int EXPECTED_SIZE = 2;
        List.of(new EmployeeScore().setUserId(1).setDate("2018-12-31").setScore(14.0f),
                new EmployeeScore().setUserId(2).setDate("2018-12-31").setScore(15.0f))
                .forEach(employeeScore -> dao.add(employeeScore));
        assertEquals(dao.getAll(EmployeeScore.class).size(), EXPECTED_SIZE);
    }

    @Test
    public void testWhenUpdate_thenExistingItemIsUpdatedInDB() {
        EmployeeScore employeeScore = new EmployeeScore()
                .setUserId(1)
                .setDate("2018-12-31")
                .setScore(14.0f);
        dao.add(employeeScore);
        dao.update(employeeScore.setUserId(4).setDate("2018-11-30").setScore(13.0f));
        employeeScore = dao.get(EmployeeScore.class, employeeScore.getId());
        assertEquals(employeeScore.getDate(), "2018-11-30");
        assertEquals(employeeScore.getUserId(), 4);
        assertEquals(employeeScore.getScore(), 13.0f);
    }
}