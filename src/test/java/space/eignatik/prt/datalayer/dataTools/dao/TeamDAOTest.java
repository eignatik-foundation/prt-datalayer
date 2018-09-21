package space.eignatik.prt.datalayer.dataTools.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.dataTools.entities.Team;
import space.eignatik.prt.datalayer.dataTools.session.SessionFactoryUtilLocal;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static space.eignatik.prt.datalayer.dataTools.enums.TableNames.TEAM;

public class TeamDAOTest extends BaseDAOTest {

    @Autowired
    @Qualifier(value = "teamDAO")
    private IDAO<Team> dao;

    @BeforeClass
    public void setUpDB() {
        dao.setFactoryUtil(new SessionFactoryUtilLocal());
    }

    @BeforeMethod
    public void resetDB() {
        DAOTestUtil.deleteAllFromTable(TEAM.getEntityName());
    }

    @Test
    public void testWhenAddWithItem_thenItCanBeClaimedFromDB() {
        Team team = new Team().setTeamName("team1");
        dao.add(team);
        Team teamDB = dao.get(Team.class, team.getId());

        assertEquals(teamDB, team);
    }

    @Test
    public void testWhenDelete_thenDeletedItemReturned_and_ItIsUnableToClaimFromDB() {
        Team team = new Team().setTeamName("team1");
        dao.add(team);
        assertEquals(dao.delete(Team.class, team.getId()), team);
        assertNull(dao.get(Team.class, team.getId()));
    }

    @Test
    public void testWhenGetAll_thenFixedSizeListIsReturned() {
        int EXPECTED_SIZE = 2;
        List.of(new Team().setTeamName("team1"),
                new Team().setTeamName("team2"))
                .forEach(team -> dao.add(team));
        assertEquals(dao.getAll(Team.class).size(), EXPECTED_SIZE);
    }

    @Test
    public void testWhenUpdate_thenExistingItemIsUpdatedInDB() {
        Team team = new Team().setTeamName("team1");
        dao.add(team);
        dao.update(team.setTeamName("Changed data"));
        team = dao.get(Team.class, team.getId());
        assertEquals(team.getTeamName(), "Changed data");
    }
}