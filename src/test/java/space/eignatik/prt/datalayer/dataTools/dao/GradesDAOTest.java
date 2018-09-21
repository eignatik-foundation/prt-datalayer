package space.eignatik.prt.datalayer.dataTools.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.dataTools.entities.Grades;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static space.eignatik.prt.datalayer.dataTools.enums.TableNames.GRADES;

public class GradesDAOTest extends BaseDAOTest {

    @Autowired
    @Qualifier(value = "gradesDAO")
    private IDAO<Grades> dao;

    @BeforeClass
    public void setUpDB() {
        dao.setFactoryUtil(new SessionFactoryUtilLocal());
    }

    @BeforeMethod
    public void resetDB() {
        DAOTestUtil.deleteAllFromTable(GRADES.getEntityName());
    }

    @Test
    public void testWhenAddWithItem_thenItCanBeClaimedFromDB() {
        Grades grades = new Grades().setGradeId(1).setUserId(10);
        dao.add(grades);
        Grades gradesFromDB = dao.get(Grades.class, grades.getId());

        assertEquals(gradesFromDB, grades);
    }

    @Test
    public void testWhenDelete_thenDeletedItemReturned_and_ItIsUnableToClaimFromDB() {
        Grades grades = new Grades().setGradeId(1).setUserId(10);
        dao.add(grades);
        assertEquals(dao.delete(Grades.class, grades.getId()), grades);
        assertNull(dao.get(Grades.class, grades.getId()));
    }

    @Test
    public void testWhenGetAll_thenFixedSizeListIsReturned() {
        int EXPECTED_SIZE = 2;
        List.of(new Grades().setGradeId(1).setUserId(10),
                new Grades().setGradeId(2).setUserId(11))
                .forEach(grades -> dao.add(grades));
        assertEquals(dao.getAll(Grades.class).size(), EXPECTED_SIZE);
    }

    /**
     * TODO: investigate the issue regarding #13 issue
     * @link https://github.com/perfo-foundation/prt-datalayer/issues/13
     */

//    @Test(expectedExceptions = GenericJDBCException.class)
//    public void testThatIfBrakeUserIdsUniqueConstraint_thenExceptionsIsThrown() {
//        List.of(new Grades().setGradeId(1).setUserId(10),
//                new Grades().setGradeId(2).setUserId(10))
//                .forEach(grades -> dao.add(grades));
//    }

    @Test
    public void testWhenUpdate_thenExistingItemIsUpdatedInDB() {
        Grades grades = new Grades().setGradeId(1).setUserId(10);
        dao.add(grades);
        dao.update(grades.setGradeId(5));
        grades = dao.get(Grades.class, grades.getId());
        assertEquals(grades.getGradeId(), 5);
    }
}