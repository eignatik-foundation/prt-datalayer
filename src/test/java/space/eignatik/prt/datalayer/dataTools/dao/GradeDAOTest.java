package space.eignatik.prt.datalayer.dataTools.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.dataTools.entities.Grade;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static space.eignatik.prt.datalayer.dataTools.enums.TableNames.GRADE;

public class GradeDAOTest extends BaseDAOTest {

    @Autowired
    @Qualifier(value = "gradeDAO")
    private IDAO<Grade> dao;

    @BeforeClass
    public void setUpDB() {
        dao.setFactoryUtil(new SessionFactoryUtilLocal());
    }

    @BeforeMethod
    public void resetDB() {
        DAOTestUtil.deleteAllFromTable(GRADE.getEntityName());
    }

    @Test
    public void testWhenAddWithItem_thenItCanBeClaimedFromDB() {
        Grade grade = new Grade().setTitle("Senior Software Engineer");
        dao.add(grade);
        Grade gradeFromDB = dao.get(Grade.class, grade.getId());

        assertEquals(gradeFromDB, grade);
    }

    @Test
    public void testWhenDelete_thenDeletedItemReturned_and_ItIsUnableToClaimFromDB() {
        Grade grade = new Grade().setTitle("Senior Software Engineer");
        dao.add(grade);
        assertEquals(dao.delete(Grade.class, grade.getId()), grade);
        assertNull(dao.get(Grade.class, grade.getId()));
    }

    @Test
    public void testWhenGetAll_thenFixedSizeListIsReturned() {
        int EXPECTED_SIZE = 2;
        List.of(new Grade().setTitle("Senior Software Engineer"),
                new Grade().setTitle("Regular Software Engineer"))
                .forEach(grade -> dao.add(grade));
        assertEquals(dao.getAll(Grade.class).size(), EXPECTED_SIZE);
    }

    @Test
    public void testWhenUpdate_thenExistingItemIsUpdatedInDB() {
        Grade grade = new Grade().setTitle("Regular Software Engineer");
        dao.add(grade);
        dao.update(grade.setTitle("Middle Software Engineer"));
        grade = dao.get(Grade.class, grade.getId());
        assertEquals(grade.getTitle(), "Middle Software Engineer");
    }
}