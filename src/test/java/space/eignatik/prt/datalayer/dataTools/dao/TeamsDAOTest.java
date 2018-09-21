package space.eignatik.prt.datalayer.dataTools.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.dataTools.entities.Teams;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static space.eignatik.prt.datalayer.dataTools.enums.TableNames.TEAMS;

public class TeamsDAOTest extends BaseDAOTest {

    @Autowired
    @Qualifier(value = "teamsDAO")
    private IDAO<Teams> dao;

    @BeforeClass
    public void setUpDB() {
        dao.setFactoryUtil(new SessionFactoryUtilLocal());
    }

    @BeforeMethod
    public void resetDB() {
        DAOTestUtil.deleteAllFromTable(TEAMS.getEntityName());
    }

    @Test
    public void testWhenAddWithItem_thenItCanBeClaimedFromDB() {
        Teams teams = new Teams().setTeamId(1).setUserId(1);
        dao.add(teams);
        Teams teamDB = dao.get(Teams.class, teams.getId());

        assertEquals(teamDB, teams);
    }

    @Test
    public void testWhenDelete_thenDeletedItemReturned_and_ItIsUnableToClaimFromDB() {
        Teams teams = new Teams().setTeamId(1).setUserId(1);
        dao.add(teams);
        assertEquals(dao.delete(Teams.class, teams.getId()), teams);
        assertNull(dao.get(Teams.class, teams.getId()));
    }

    @Test
    public void testWhenGetAll_thenFixedSizeListIsReturned() {
        int EXPECTED_SIZE = 2;
        List.of(new Teams().setTeamId(1).setUserId(1),
                new Teams().setTeamId(1).setUserId(2))
                .forEach(teams -> dao.add(teams));
        assertEquals(dao.getAll(Teams.class).size(), EXPECTED_SIZE);
    }

    @Test
    public void testWhenUpdate_thenExistingItemIsUpdatedInDB() {
        Teams teams = new Teams().setTeamId(1).setUserId(1);
        dao.add(teams);
        dao.update(teams.setTeamId(2));
        teams = dao.get(Teams.class, teams.getId());
        assertEquals(teams.getTeamId(), 2);
    }
}